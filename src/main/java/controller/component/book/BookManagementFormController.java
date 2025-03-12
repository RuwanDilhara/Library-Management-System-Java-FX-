package controller.component.book;

import com.jfoenix.controls.JFXCheckBox;
import dto.Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import service.custome.BookService;
import service.custome.impl.BookServiceImpl;
import util.enums.BookStatus;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BookManagementFormController implements Initializable {

    @FXML
    private JFXCheckBox checkUnavailable;

    @FXML
    private ComboBox<String> cmbAuthor;

    @FXML
    private ComboBox<String> cmbSortBy;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField txtSearch;

    @FXML
    private ScrollPane scrollPane;

    private final BookService service = new BookServiceImpl();
    private static final int COLUMN_COUNT = 4;

    List<Book> books = service.getAll();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBooks(null);
        loadComboBoxes();
        searchSuggester();
    }

    private BookStatus getSelectedBookStatus() {
        return checkUnavailable.isSelected() ? BookStatus.UNAVAILABLE : BookStatus.AVAILABLE;
    }

    private void loadBooks(String authorFilter) {
        gridPane.getChildren().clear();
        int col = 0;
        int row = 0;

        for (Book book : books) {
            if (book.getStatus() == getSelectedBookStatus() && (authorFilter == null || authorFilter.equals(book.getAuthor()))) {
                addBookToGrid(book, col, row);
                if (++col == COLUMN_COUNT) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private void addBookToGrid(Book book, int col, int row) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/book_card.fxml"));
            AnchorPane bookCard = loader.load();
            BookCardFormController controller = loader.getController();
            controller.setBookData(book.getId(), book.getTitle(), book.getIsbn(), book.getAuthor(), book.getYear(), book.getImage(), book.getStatus());
            gridPane.add(bookCard, col, row);
        } catch (IOException e) {
            showError("Error loading book card.");
        }
    }

    @FXML
    void btnAddBookOnAction() {
//        loadNewWindow("/view/component/add_book_form.fxml", "Add Book Form");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/add_member_form.fxml"));
        try {
            Parent root = loader.load();
            gridPane.getChildren().clear();
            gridPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReloadOnAction() {
        reLoadData();
    }
    @FXML
    void btnSearchOnAction() {
        List<Book> result = service.searchByTitle(txtSearch.getText());
        if (result.isEmpty()){
            result=service.searchByAuthor(txtSearch.getText());
            if (result.isEmpty()){
                result.add(service.searchByID(txtSearch.getText()));
            }
        }
        books= result;
        loadBooks(null);
    }

    @FXML
    void btnFilterOnAction() {
        if (checkUnavailable.isSelected() || cmbAuthor.getValue() != null || cmbSortBy.getValue() != null) {
            if (cmbSortBy.getValue() == null){
                loadBooks(cmbAuthor.getValue());
            }else{
                sortBooks(cmbSortBy.getValue());
                loadBooks(cmbAuthor.getValue());
            }
        } else {
            showInfo("Select Filter Option");
        }
    }

    @FXML
    void reLoadData() {
        books=service.getAll();
            gridPane.getChildren().clear();
            loadBooks(null);
            loadComboBoxes();
    }

    public void loadComboBoxes() {
        cmbAuthor.setItems(FXCollections.observableArrayList(service.getAllAuthor()));
        cmbSortBy.setItems(FXCollections.observableArrayList("Id", "Title", "Year"));
    }

    public void sortBooks(String sortBy) {
        switch (sortBy) {
            case "Id" : sortingById();break;
            case "Title" : sortingByTitle();break;
            case "Year" : sortingByYear();break;
            default : showError("Invalid sorting option selected.");
        }
    }

    public void loadNewWindow(String resourcePath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(resourcePath));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            showError("Unable to open ->" + e.getMessage());
        }
    }

    public void sortingById(){
        books.sort(Comparator.comparing(Book::getId));
    }
    public void sortingByTitle(){
        books.sort(Comparator.comparing(Book::getTitle));
    }
    public void sortingByYear(){
        books.sort(Comparator.comparing(Book::getYear));
    }
    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    private void showInfo(String message) {
        new Alert(Alert.AlertType.INFORMATION, message).show();
    }


    public void searchSuggester() {
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty()) {
                books = service.searchByTitle(newValue);
                if (books.isEmpty()) {
                    books = service.searchByAuthor(newValue);
                    if (books.isEmpty()) {
                        Book book = service.searchByID(newValue);
                        if (book != null) {
                            books.add(book);
                        }
                    }
                }
            } else {
                books = service.getAll();
            }
            loadBooks(null);
        });
    }

}
