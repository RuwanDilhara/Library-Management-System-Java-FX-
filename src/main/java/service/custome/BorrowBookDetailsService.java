package service.custome;

import dto.BorrowBookDetails;
import entity.BorrowBookDetailsEntity;

import java.util.List;

public interface BorrowBookDetailsService {
    boolean save(BorrowBookDetails borrowBookDetails);
    boolean update(BorrowBookDetails borrowBookDetails);
    boolean delete(BorrowBookDetails borrowBookDetails);
    BorrowBookDetails search(String id);
    List<BorrowBookDetails> getAll();
}
