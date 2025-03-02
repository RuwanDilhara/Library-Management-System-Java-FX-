package controller.component.book;

import com.google.inject.Inject;
import dto.Book;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    BookService service = new BookServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBook();
    }

    int column = 4;

    public void loadBook() {
        int col = 0;
        int raw = 0;
        for (Book book : service.getAll()) {
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
    void reLoadData(){
        Platform.runLater(() -> {
            gridPane.getChildren().clear();
            loadBook();
        });
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
