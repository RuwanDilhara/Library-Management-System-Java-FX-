package controller.component.book;

import config.HibernateConfig;
import entity.BookEntity;
import org.hibernate.Session;
import service.ServiceFactory;
import service.custome.BookService;
import util.enums.BookStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.enums.ServiceType;

import java.io.IOException;


public class BookCardFormController {
    public Label lblId;
    @FXML
    private Label lblTitle;

    @FXML
    private Label lblISBM;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblGen;

    @FXML
    private ImageView image;


    public void setBookData(String id,String title,String iSBM, String author, Integer year, String imagePath ,Enum<BookStatus> available) {
        lblId.setText(id);
        lblTitle.setText(title);
        lblISBM.setText(iSBM);
        lblAuthor.setText(author);
        lblGen.setText(year.toString());
        image.setImage(new Image(imagePath));

    }


    public void bynDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/component/book_update_form.fxml"));

        try {
            Parent root = loader.load();
            UpdateBookFormController controller = loader.getController();

            controller.setData(lblId.getText(),lblTitle.getText(),lblAuthor.getText(),4545,lblISBM.getText());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update Book");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
