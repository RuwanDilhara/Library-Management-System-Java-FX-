package repository.custome.impl;

import entity.MemberEntity;
import repository.custome.MemberDao;

import java.util.List;

public class MemberDaoImpl implements MemberDao {
    @Override
    public boolean save(MemberEntity entity) {
        return false;
    }

    @Override
    public boolean update(String string, MemberEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public MemberEntity search(String string) {
        return null;
    }

    @Override
    public List<MemberEntity> getAll() {
        return List.of();
    }
}
