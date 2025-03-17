package dto;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
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
public class ReturnBookTableDto {
    private CheckBox checkBox;
    private String recordId;
    private String memberId;
    private String memberName;
    private String bookId;
    private String bookTitle;
    private LocalDate borrowedDate;
    private LocalDate returnDate;
    private RecordStatus status;
    private Double fineAmount;

}
