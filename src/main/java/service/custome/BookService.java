package service.custome;

import dto.Book;
import service.SuperService;

import java.util.List;

public interface BookService extends SuperService {
    List<Book> getAll();
}
