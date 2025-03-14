package service.custome.impl;

import config.HibernateConfig;
import dto.BorrowBookDetails;
import entity.BookEntity;
import entity.BorrowBookDetailsEntity;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import repository.custome.BorrowBookDetailsDao;
import repository.custome.impl.BorrowBookDetailsDaoImpl;
import service.custome.BorrowBookDetailsService;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowBookDetailsServiceImpl implements BorrowBookDetailsService {

    BorrowBookDetailsDao borrowBookDetailsDao = new BorrowBookDetailsDaoImpl();

    @Override
    public boolean save(BorrowBookDetails borrowBookDetails) {
        return borrowBookDetailsDao.save(new ModelMapper()
                .map(borrowBookDetails, BorrowBookDetailsEntity.class));
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
        return borrowBookDetailsDao.getAll().stream().map(borrowBookDetailsEntity ->
            new ModelMapper().map(borrowBookDetailsEntity,BorrowBookDetails.class))
                .collect(Collectors.toList());

    }
}
