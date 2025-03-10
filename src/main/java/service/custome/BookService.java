package service.custome;

import dto.Book;
import service.SuperService;

import java.util.List;

public interface BookService extends SuperService {
    List<Book> getAll();
    boolean updateBook(Book book);
    boolean addBook(Book book);
    boolean delete(Book book);
    List<String> getAllAuthor();
    List<Book> searchByTitle(String title);
    Book searchByID(String id);
    List<Book> searchByAuthor(String author);
}
