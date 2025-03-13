package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import util.enums.RecordStatus;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowBookDetails {
    private String recordId;
    private String memberId;
    private String memberName;
    private String bookId;
    private String bookTitle;
    private LocalDate borrowedDate;
    private LocalDate returnDate;
//    private Integer isReturn;
    private RecordStatus status;
    private Double fineAmount;
}
