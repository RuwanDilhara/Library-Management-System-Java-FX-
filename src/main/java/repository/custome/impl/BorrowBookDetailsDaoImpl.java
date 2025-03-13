package repository.custome.impl;

import entity.BorrowBookDetailsEntity;
import repository.custome.BorrowBookDetailsDao;

import java.util.List;

public class BorrowBookDetailsDaoImpl implements BorrowBookDetailsDao {
    @Override
    public boolean save(BorrowBookDetailsEntity entity) {
        return false;
    }

    @Override
    public boolean update(BorrowBookDetailsEntity entity) {
        return false;
    }

    @Override
    public boolean delete(BorrowBookDetailsEntity book) {
        return false;
    }

    @Override
    public BorrowBookDetailsEntity search(String string) {
        return null;
    }

    @Override
    public List<BorrowBookDetailsEntity> getAll() {
        return List.of();
    }
}
