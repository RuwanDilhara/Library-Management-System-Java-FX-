package controller.component;

import com.jfoenix.controls.JFXButton;
import dto.BorrowBookDetails;
import dto.Fine;
import dto.FineManagementTableDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custome.BorrowBookDetailsService;
import service.custome.FineService;
import service.custome.impl.BorrowBookDetailsServiceImpl;
import javafx.event.ActionEvent;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.custome.impl.FineServiceImpl;
import util.enums.RecordStatus;

public class FineManagementController implements Initializable {

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookTitle;

    @FXML
    private TableColumn<?, ?> colBorrowedDate;

    @FXML
    private TableColumn<?, ?> colFineAmount;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colMemberName;

    @FXML
    private TableColumn<?, ?> colRecordID;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colOverDouDate;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtFineAmount;

    @FXML
    private TextField txtMemberId;

    @FXML
    private TextField txtMemberName;

    @FXML
    private TextField txtBorrowDate;

    @FXML
    private TextField txtRecordId;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtUserEnterAmount;

    @FXML
    private TextField txtOverDueDateCount;

    @FXML
    private TableView<FineManagementTableDto> tblBorrowBookTable;

    @FXML
    private JFXButton btnPay;

    BorrowBookDetailsService borrowBookDetailsService = new BorrowBookDetailsServiceImpl();
    FineService fineService = new FineServiceImpl();

    List<BorrowBookDetails> borrowedBookList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fineCalculation();

        colRecordID.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colOverDouDate.setCellValueFactory(new PropertyValueFactory<>("overDueDate"));
        colFineAmount.setCellValueFactory(new PropertyValueFactory<>("fineAmount"));

        tableLoader();

        tblBorrowBookTable.getSelectionModel().selectedItemProperty().addListener((observableValue, fineManagementTableDtoTableViewSelectionModel, t1) -> {
            if (t1 != null) {
                setDataTableRawToTextField(t1);
            }
        });

    }

    public void tableLoader() {
        ObservableList<FineManagementTableDto> borrowBookObservableList = FXCollections.observableArrayList();
        borrowBookDetailsService.getAll().forEach(borrowBookDetails -> {
            if (borrowBookDetails.getStatus().equals(RecordStatus.OVERDUE)) {
                borrowBookObservableList.add(new FineManagementTableDto(
                        borrowBookDetails.getRecordId(),
                        borrowBookDetails.getBookId(),
                        borrowBookDetails.getBookTitle(),
                        borrowBookDetails.getMemberId(),
                        borrowBookDetails.getMemberName(),
                        borrowBookDetails.getBorrowedDate(),
                        borrowBookDetails.getReturnDate(),
                        overDueDateCalculation(borrowBookDetails.getReturnDate()),
                        borrowBookDetails.getStatus(),
                        borrowBookDetails.getFineAmount()
                ));
            }
        });
        tblBorrowBookTable.setItems(borrowBookObservableList);
    }

    public Integer overDueDateCalculation(LocalDate returnDate) {
        if (LocalDate.now().isBefore(returnDate)) {
            return 0;
        } else {
            return LocalDate.now().compareTo(returnDate);
        }
    }

    public void fineCalculation() {
        List<Fine> allFines = fineService.getAll();

        borrowBookDetailsService.getAll().forEach(borrowBookDetails -> {
            if (borrowBookDetails.getStatus().equals(RecordStatus.ACTIVE)) {
                Integer overDueDateCount = overDueDateCalculation(borrowBookDetails.getReturnDate());
                double totalFineAmount = overDueDateCount * 10.00;

                double fineAmount = allFines.stream()
                        .filter(fine -> fine.getBorrowRecordId().equals(borrowBookDetails.getRecordId()))
                        .mapToDouble(Fine::getAmount)
                        .sum();

                borrowBookDetailsService.update(new BorrowBookDetails(
                        borrowBookDetails.getRecordId(),
                        borrowBookDetails.getBookId(),
                        borrowBookDetails.getBookTitle(),
                        borrowBookDetails.getMemberId(),
                        borrowBookDetails.getMemberName(),
                        borrowBookDetails.getBorrowedDate(),
                        borrowBookDetails.getReturnDate(),
                        overDueDateCount > 0 ? RecordStatus.OVERDUE : RecordStatus.ACTIVE,
                        totalFineAmount - fineAmount
                ));
            }
        });
    }


    public void setDataTableRawToTextField(FineManagementTableDto borrowBookDetailsDto) {
        txtRecordId.setText(borrowBookDetailsDto.getRecordId());
        txtBookId.setText(borrowBookDetailsDto.getBookId());
        txtBookName.setText(borrowBookDetailsDto.getBookTitle());
        txtMemberId.setText(borrowBookDetailsDto.getMemberId());
        txtMemberName.setText(borrowBookDetailsDto.getMemberName());
        txtReturnDate.setText(borrowBookDetailsDto.getReturnDate().toString());
        txtOverDueDateCount.setText(borrowBookDetailsDto.getOverDueDate().toString());
        txtFineAmount.setText(borrowBookDetailsDto.getFineAmount().toString());
        txtBorrowDate.setText(borrowBookDetailsDto.getBorrowedDate().toString());
    }

    public Double finalizeFineAmount(Double fineAmount, Double userInputAmount) {
        if (fineAmount < userInputAmount) {
            new Alert(Alert.AlertType.INFORMATION, "Please enter a valid amount !");
        } else if (userInputAmount.equals(fineAmount)) {
            return 0.00;
        }
        return fineAmount - userInputAmount;

    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
        if (Double.parseDouble(txtFineAmount.getText()) > 0) {
            boolean isUpdate = borrowBookDetailsService.update(new BorrowBookDetails(
                    txtRecordId.getText(),
                    txtMemberId.getText(),
                    txtMemberName.getText(),
                    txtBookId.getText(),
                    txtBookName.getText(),
                    LocalDate.parse(txtBorrowDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDate.parse(txtReturnDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    finalizeFineAmount(Double.parseDouble(txtFineAmount.getText()),
                            Double.parseDouble(txtUserEnterAmount.getText())) == 0 ? RecordStatus.PAID : RecordStatus.OVERDUE,
                    finalizeFineAmount(Double.parseDouble(txtFineAmount.getText()),
                            Double.parseDouble(txtUserEnterAmount.getText()))

            ));
            if (isUpdate) {
                savePaidFineAmount(
                        txtRecordId.getText(),
                        txtMemberId.getText(),
                        txtMemberName.getText(),
                        Double.parseDouble(txtUserEnterAmount.getText()));

                tableLoader();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "Please enter a valid amount !");
        }
    }

    public boolean savePaidFineAmount(String borrowRecordId,
                                      String memberId,
                                      String memberName,Double paymentAmount) {
        return fineService.save(new Fine(
                generateId(),
                borrowRecordId,
                memberId,
                memberName,
                LocalDate.now(),
                paymentAmount
        ));
    }
    public String generateId() {
        String newId;
        if (!fineService.getAll().isEmpty()) {
            String lastNumber = fineService.getAll()
                    .getLast()
                    .getFineId()
                    .substring(1);

            newId = "F" + String.format("%03d", Integer.parseInt(lastNumber) + 1);

            return newId;
        } else {
            return "F001";
        }
    }
}
