package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.enums.RecordStatus;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BorrowBookDetails")
public class BorrowBookDetailsEntity {
    @Id
    private String recordId;
    private String memberId;
    private String memberName;
    private String bookId;
    private String bookTitle;
    private LocalDate borrowedDate;
    private LocalDate returnDate;
    private Integer isReturn;
    private RecordStatus status;
    private Double fineAmount;
}
