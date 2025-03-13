package controller.component.borrow_book_details;

import controller.component.IssueBookFormController;
import dto.BorrowBookDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import service.custome.BorrowBookDetailsService;
import service.custome.impl.BorrowBookDetailsServiceImpl;
import util.enums.RecordStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BorrowBookDetailsFormController {

    BorrowBookDetailsService service = new BorrowBookDetailsServiceImpl();

    @FXML
    private DatePicker iptReturnDate;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookTitle;

    @FXML
    private TextField txtBorrowedDate;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtMemberName;

    @FXML
    private TextField txtRecordId;

    private int borrowBookCount;

    @FXML
    void btnSelectBookOnAction(ActionEvent event) {
        IssueBookFormController.getInstance().clearBookCardDetails();
        IssueBookFormController.getInstance().loadBook();
    }

    @FXML
    void btnSelectMemberOnAction(ActionEvent event) {
        IssueBookFormController.getInstance().clearMemberCardDetails();
        IssueBookFormController.getInstance().loadMember();
    }

    public void setBorrowBookDetails(String recordId, String memberId, String memberName, String bookId, String bookTitle, LocalDate bookBorrowedDate, int borrowBookCount) {
        txtRecordId.setText(recordId);
        txtMemberId.setText(memberId);
        txtMemberName.setText(memberName);
        txtBookId.setText(bookId);
        txtBookTitle.setText(bookTitle);
        txtBorrowedDate.setText(bookBorrowedDate.toString());
        this.borrowBookCount = borrowBookCount;
    }

    @FXML
    private void btnIssueBookOnAction(ActionEvent actionEvent) {
        if (iptReturnDate.getValue() != null) {
            if (borrowBookCount <= 3) {
                service.save(new BorrowBookDetails(
                        txtRecordId.getText(),
                        txtMemberId.getText(),
                        txtMemberName.getText(),
                        txtBookId.getText(),
                        txtBookTitle.getText(),
                        LocalDate.now(),
                        iptReturnDate.getValue(),
                        RecordStatus.PENDING,
                        0.00
                ));
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please select a return date for the book !").show();
        }
    }
}
