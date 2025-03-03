package controller.component.book;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXCheckBox;
import dto.Book;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import service.custome.BookService;
import service.custome.impl.BookServiceImpl;
import util.enums.BookStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private ScrollPane scrollPane;

    BookService service = new BookServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBook(null);
        comboBoXLoader();
    }

    int column = 4;


    public BookStatus isBookAvailableType() {
        return checkUnavailable.isSelected() ? BookStatus.
                UNAVAILABLE : BookStatus.AVAILABLE;
    }

    public void loadBook(String str) {
        int col = 0;
        int raw = 0;

        if (str == null) {
            gridPane.getChildren().clear();
            for (Book book : service.getAll()) {
                if (isBookAvailableType() == book.getStatus()) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/book_card.fxml"));
                        AnchorPane bookCard = loader.load();

                        BookCardFormController bookCardFormController = loader.getController();
                        bookCardFormController.setBookData(book.getId(),
                                book.getTitle(),
                                book.getIsbn(),
                                book.getAuthor(),
                                book.getYear(),
                                book.getImage(),
                                book.getStatus());
                        gridPane.add(bookCard, col, raw);

                        col++;

                        if (col == column) {
                            col = 0;
                            raw++;
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            gridPane.getChildren().clear();
            for (Book book : service.getAll()) {
                if (isBookAvailableType() == book.getStatus()) {
                    if (str.equals(book.getAuthor())){
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/book_card.fxml"));
                            AnchorPane bookCard = loader.load();

                            BookCardFormController bookCardFormController = loader.getController();
                            bookCardFormController.setBookData(book.getId(),
                                    book.getTitle(),
                                    book.getIsbn(),
                                    book.getAuthor(),
                                    book.getYear(),
                                    book.getImage(),
                                    book.getStatus());
                            gridPane.add(bookCard, col, raw);

                            col++;

                            if (col == column) {
                                col = 0;
                                raw++;
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    @FXML
    void btnAddBookOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/add_book_form.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Book Form");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent actionEvent) {
        reLoadData();
    }

    @FXML
    void btnFilterOnAction(ActionEvent event) {
        if (cmbAuthor.getValue() == null) {
            new Alert(Alert.AlertType.INFORMATION, "Select Author").show();
        } else {
            loadBook(cmbAuthor.getValue());
        }
    }

    @FXML
    void reLoadData() {
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            loadBook(null);
            comboBoXLoader();
        });
    }

    public void comboBoXLoader() {
        ObservableList<String> authurObservableList = FXCollections.observableArrayList();
        authurObservableList.addAll(service.getAllAuthor());
        cmbAuthor.setItems(authurObservableList);

        ObservableList<String> sortObservableList = FXCollections.observableArrayList();
        sortObservableList.add("Id");
        sortObservableList.add("Title");
        sortObservableList.add("Year");
        cmbSortBy.setItems(sortObservableList);
    }
//    @FXML
//    void reLoader(){
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        Runnable task = () -> {
//            reLoadData();
//        };
//        scheduler.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
//    }

}
