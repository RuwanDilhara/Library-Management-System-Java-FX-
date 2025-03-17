package controller.component;

import com.jfoenix.controls.JFXButton;
import dto.ReturnBookTableDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custome.BorrowBookDetailsService;
import service.custome.impl.BorrowBookDetailsServiceImpl;
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
    private TableView tblReturnBookTable;

    BorrowBookDetailsService borrowBookDetailsService = new BorrowBookDetailsServiceImpl();


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
           if (borrowBookDetails.getStatus().equals(RecordStatus.PAID)){
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
}
