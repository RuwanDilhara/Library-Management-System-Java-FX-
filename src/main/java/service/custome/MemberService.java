package service.custome;

import dto.Book;
import dto.Member;
import service.SuperService;

import java.util.List;

public interface MemberService extends SuperService {
    List<Member> getAll();
    boolean updateMember(Member member);
    boolean addMember(Member member);
    boolean deleteMemberById(Member member);
    List<Member> getMemberByName(String name);
    Member searchMemberById(String id);
    List<String> getAllMember();
}
