package entity;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private RecordStatus status;

    private Double fineAmount;
}