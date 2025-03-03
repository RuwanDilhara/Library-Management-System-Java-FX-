package service.custome.impl;

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
        bookDao.getAll().forEach(entity -> bookList.add(new ModelMapper()
                .map(entity, Book.class)));
        return bookList;
    }

    @Override
    public boolean updateBook(Book book) {
        return bookDao.update(new ModelMapper()
                .map(book, BookEntity.class));
    }

    @Override
    public boolean addBook(Book book) {
        return bookDao.save(new ModelMapper()
                .map(book, BookEntity.class));
    }

    @Override
    public boolean delete(Book book) {
        return bookDao.delete(new ModelMapper()
                .map(book, BookEntity.class));
    }

    @Override
    public List<String> getAllAuthor() {
        List<String> authorList = new ArrayList<>();
        for (Book book : getAll()) {
            if (authorList.isEmpty()) {
                authorList.add(book.getAuthor());
            }else {
                String exitAuthor="";
                for (String author:authorList){
                    if (book.getAuthor().equals(author)){
                        exitAuthor=author;
                    }
                }
                if (exitAuthor.isBlank()){
                    authorList.add(book.getAuthor());
                }
            }
        }
        return authorList;
    }
}
