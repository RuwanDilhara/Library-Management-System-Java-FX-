package service.custome;

import dto.Fine;

import java.util.List;

public interface FineService {
    List<Fine> getAll();
    Fine fineById(String id);
    boolean save(Fine fine);
    List<Fine> fineByMemberId(String id);
    List<Fine> fineByBorrowRecordId(String id);
}
