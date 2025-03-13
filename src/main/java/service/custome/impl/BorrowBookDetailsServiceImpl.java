package service.custome.impl;

import dto.BorrowBookDetails;
import repository.custome.BorrowBookDetailsDao;
import repository.custome.impl.BorrowBookDetailsDaoImpl;
import service.custome.BorrowBookDetailsService;

import java.util.List;

public class BorrowBookDetailsServiceImpl implements BorrowBookDetailsService {

    BorrowBookDetailsDao borrowBookDetailsDao = new BorrowBookDetailsDaoImpl();

    @Override
    public boolean save(BorrowBookDetails borrowBookDetails) {
        return false;
    }

    @Override
    public boolean update(BorrowBookDetails borrowBookDetails) {
        return false;
    }

    @Override
    public boolean delete(BorrowBookDetails borrowBookDetails) {
        return false;
    }

    @Override
    public BorrowBookDetails search(String id) {
        return null;
    }

    @Override
    public List<BorrowBookDetails> getAll() {
        return List.of();
    }
}
