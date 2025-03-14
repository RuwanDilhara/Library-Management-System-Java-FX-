package controller.component;

import dto.ReturnBookTableDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import util.enums.RecordStatus;


public class ReturnBookComponentController implements Initializable {
    @FXML
    private TableColumn<?, ?> colBookName;

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
    private TableView tblReturnBookTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        colRecordID.setCellValueFactory(new PropertyValueFactory<>("recordId"));
    }
    public void loadTable(){
        ObservableList<ReturnBookTableDto> objectObservableList = FXCollections.observableArrayList();
        objectObservableList.add(new ReturnBookTableDto(
                "R001",
                "M123",
                "John Doe",
                "B456",
                "Java Programming",
                LocalDate.of(2024, 3, 10),
                LocalDate.of(2024, 3, 20),
                false,
                RecordStatus.ACTIVE,
                0.0
        ));
        tblReturnBookTable.setItems(objectObservableList);

    }
}
