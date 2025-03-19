package controller.component;

import dto.Book;
import dto.BorrowBookDetails;
import dto.Member;
import dto.ReturnBookTableDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import service.custome.BookService;
import service.custome.BorrowBookDetailsService;
import service.custome.MemberService;
import service.custome.impl.BookServiceImpl;
import service.custome.impl.BorrowBookDetailsServiceImpl;
import service.custome.impl.MemberServiceImpl;
import util.enums.BookStatus;
import util.enums.RecordStatus;


public class ReturnBookComponentController implements Initializable {
    @FXML
    private TableColumn<?, ?> colBookTitle;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowedDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colMemberName;

    @FXML
    private TableColumn<?, ?> colRecordID;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colFineAmount;

    @FXML
    private TableColumn<?, ?> colSelect;

    @FXML
    private TableView<ReturnBookTableDto> tblReturnBookTable;

    BorrowBookDetailsService borrowBookDetailsService = new BorrowBookDetailsServiceImpl();
    BookService bookService = new BookServiceImpl();
    MemberService memberService = new MemberServiceImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        colRecordID.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colFineAmount.setCellValueFactory(new PropertyValueFactory<>("fineAmount"));
        colSelect.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
    }

    public void loadTable() {
        ObservableList<ReturnBookTableDto> objectObservableList = FXCollections.observableArrayList();

        borrowBookDetailsService.getAll().forEach(borrowBookDetails -> {
            if (borrowBookDetails.getStatus().equals(RecordStatus.PAID)) {
                objectObservableList.add(
                        new ReturnBookTableDto(
                                new CheckBox(),
                                borrowBookDetails.getRecordId(),
                                borrowBookDetails.getMemberId(),
                                borrowBookDetails.getMemberName(),
                                borrowBookDetails.getBookId(),
                                borrowBookDetails.getBookTitle(),
                                borrowBookDetails.getBorrowedDate(),
                                borrowBookDetails.getReturnDate(),
                                borrowBookDetails.getStatus(),
                                borrowBookDetails.getFineAmount()
                        )
                );
            }
        });
        tblReturnBookTable.setItems(objectObservableList);

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmReturnOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Return");
        alert.setHeaderText("Are you sure you want to return the book?");
        alert.setContentText("Please confirm your action.");

        alert.showAndWait().ifPresent(present -> {
            if (present.equals(ButtonType.OK)) {
                tblReturnBookTable.getItems().forEach(items -> {
                    if (items.getCheckBox().isSelected()) {
                        boolean isUpdate = borrowBookDetailsService.update(
                                new BorrowBookDetails(
                                        items.getRecordId(),
                                        items.getMemberId(),
                                        items.getMemberName(),
                                        items.getBookId(),
                                        items.getBookTitle(),
                                        items.getBorrowedDate(),
                                        items.getReturnDate(),
                                        RecordStatus.RETURN,
                                        items.getFineAmount()
                                )
                        );
                        if (isUpdate){
                            boolean isBookUpdate = updateBookQty(items.getBookId());
                            if (isBookUpdate){
                                updateMemberBorrowedBooksCount(items.getMemberId());
                            }
                        }
                    }
                });
            }
        });
        loadTable();
    }
    public boolean updateBookQty(String bookId){
        Book result = bookService.getAll().stream().filter(book ->
                book.getId().equals(bookId)).findFirst().orElse(null);
        if (result != null){
            result.setQty(result.getQty()+1);
            if (result.getQty()<=0){
                result.setStatus(BookStatus.UNAVAILABLE);
            }else {
                result.setStatus(BookStatus.AVAILABLE);
            }
            return bookService.updateBook(result);
        }
        return false;
    }
    public boolean updateMemberBorrowedBooksCount(String memberId){
        Member result = memberService.getAll().stream().filter(member ->
                member.getId().equals(memberId)).findFirst().orElse(null);
        if (result != null){
            result.setBorrowedBookCount(result.getBorrowedBookCount()-1);
            return memberService.updateMember(result);
        }
        return false;
    }
}
