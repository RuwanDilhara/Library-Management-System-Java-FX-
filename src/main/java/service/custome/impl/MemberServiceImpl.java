package service.custome.impl;

import dto.Member;
import entity.MemberEntity;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.modelmapper.ModelMapper;
import repository.custome.MemberDao;
import repository.custome.impl.MemberDaoImpl;
import service.custome.MemberService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemberServiceImpl implements MemberService {
    MemberDao memberDao = new MemberDaoImpl();

    @Override
    public List<Member> getAll() {
        List<Member> memberList = new ArrayList<>();
        memberDao.getAll().forEach(entity -> {
            memberList.add(new ModelMapper().map(entity, Member.class));
        });
        return memberList;
    }

    @Override
    public boolean updateMember(Member member) {
        return memberDao.update(new ModelMapper()
                .map(member, MemberEntity.class));
    }

    @Override
    public boolean addMember(Member member) {
        return memberDao.save(new ModelMapper()
                .map(member, MemberEntity.class));
    }

    @Override
    public boolean deleteMemberById(Member member) {
        return memberDao.delete(new ModelMapper()
                .map(member, MemberEntity.class));
    }

    @Override
    public List<Member> getMembersByName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String userInput = name.toLowerCase();

        LevenshteinDistance levenshtein = new LevenshteinDistance();

        List<Member> result= getAll().stream().filter(member -> {
            if (member.getName() == null) return false;
            String memberId = member.getName().toLowerCase();

            int distance = levenshtein.apply(userInput, memberId);
            return memberId.contains(userInput) || distance <= 2;
        }).toList();

        if (result.isEmpty()) {
            result = getAll().stream().filter(member -> {
                if (member.getId() == null) return false;
                String memberId= member.getId().toLowerCase();

                int distance = levenshtein.apply(userInput, memberId);
                return memberId.contains(userInput) || distance <= 1;
            }).toList();

            return result;
        }
        return result;

    }

    @Override
    public Member searchMemberById(String id) {
        return getAll().stream().filter(member -> member.getId()
                .equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<String> getAllMember() {
        List<String> newMemberNames = new ArrayList<>();
        for (Member memberA : getAll()) {
            if (newMemberNames.isEmpty()) {
                newMemberNames.add(memberA.getName());
            } else {
                String exitMember = "";
                for (String memberB : newMemberNames) {
                    if (memberB.equals(memberA.getName())) {
                        exitMember = memberB;
                    }
                }
                if (exitMember.isBlank()) {
                    newMemberNames.add(memberA.getName());
                }
            }
        }
        return newMemberNames;
    }

}
