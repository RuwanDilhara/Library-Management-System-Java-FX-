package controller.component.book;

import com.google.inject.Inject;
import dto.Book;
import service.custome.BookService;
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

    @Inject
    BookService service;

    int col=0;
    int raw=0;
    List<Book> bookList= new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addBook();
        loadBook();
    }

    public void addBook(){
        bookList.add(new Book("B001","Book Title 1", "ISBN-1001", "Author 1", 2001, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B002","Book Title 2", "ISBN-1002", "Author 2", 2002, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B003","Book Title 3", "ISBN-1003", "Author 3", 2003, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B004","Book Title 4", "ISBN-1004", "Author 4", 2004, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B005","Book Title 5", "ISBN-1005", "Author 5", 2005, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B006","Book Title 6", "ISBN-1006", "Author 6", 2006, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B007","Book Title 7", "ISBN-1007", "Author 7", 2007, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B008","Book Title 8", "ISBN-1008", "Author 8", 2008, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B009","Book Title 9", "ISBN-1009", "Author 9", 2009, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0010","Book Title 10", "ISBN-1010", "Author 10", 2010, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0011","Book Title 11", "ISBN-1011", "Author 11", 2011, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0012","Book Title 12", "ISBN-1012", "Author 12", 2012, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0013","Book Title 13", "ISBN-1013", "Author 13", 2013, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0014","Book Title 14", "ISBN-1014", "Author 14", 2014, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0015","Book Title 15", "ISBN-1015", "Author 15", 2015, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0016","Book Title 16", "ISBN-1016", "Author 16", 2016, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0017","Book Title 17", "ISBN-1017", "Author 17", 2017, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0018","Book Title 18", "ISBN-1018", "Author 18", 2018, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0019","Book Title 19", "ISBN-1019", "Author 19", 2019, "img/Book/images.jpeg", BookStatus.AVAILABLE));
        bookList.add(new Book("B0020","Book Title 20", "ISBN-1020", "Author 20", 2020, "img/Book/images.jpeg", BookStatus.AVAILABLE));
    }
    int column = 4;
    public void loadBook(){
        for(Book book: bookList){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/book_card.fxml"));
                AnchorPane bookCard = loader.load();

                BookCardFormController bookCardFormController = loader.getController();
                bookCardFormController.setBookData(book.getId(),book.getTitle(),book.getISBM(),book.getAuthor(),book.getYear(),book.getImage(),book.getStatus());


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
