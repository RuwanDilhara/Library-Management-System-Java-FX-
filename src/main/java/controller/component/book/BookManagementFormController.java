package controller.component.book;

import com.google.inject.Inject;
import dto.Book;
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

public class BookManagementFormController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    BookService service = new BookServiceImpl();

    int col=0;
    int raw=0;
    List<Book> bookList= new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBook();
        loadBook();
    }

    public void addBook(){
        bookList=service.getAll();
    }
    int column = 4;
    public void loadBook(){
        for(Book book: bookList){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/book_card.fxml"));
                AnchorPane bookCard = loader.load();

                BookCardFormController bookCardFormController = loader.getController();
                bookCardFormController.setBookData(book.getId(),book.getTitle(),book.getIsbn(),book.getAuthor(),book.getYear(),book.getImage(),book.getStatus());


                gridPane.add(bookCard,col,raw);

                col++;

                if (col== column){
                    col=0;
                    raw++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
