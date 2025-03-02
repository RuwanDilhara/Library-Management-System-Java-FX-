package service.custome.impl;

import com.google.inject.Inject;
import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.custome.BookDao;
import repository.custome.impl.BookDaoImpl;
import service.custome.BookService;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        bookDao.getAll().forEach(entity->{
            bookList.add(new ModelMapper().map(entity,Book.class));
        });
        return bookList;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.update(new ModelMapper()
                .map(book, BookEntity.class));
    }
}
