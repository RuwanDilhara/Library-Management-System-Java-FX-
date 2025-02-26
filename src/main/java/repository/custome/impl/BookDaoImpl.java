package repository.custome.impl;

import entity.BookEntity;
import repository.custome.BookDao;

import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public boolean save(BookEntity entity) {
        return false;
    }

    @Override
    public boolean update(String string, BookEntity entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public BookEntity search(String string) {
        return null;
    }

    @Override
    public List<BookEntity> getAll() {
        return List.of();
    }
}
