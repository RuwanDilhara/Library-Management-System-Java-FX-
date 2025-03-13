package controller.component.book;

import controller.component.IssueBookFormController;
import dto.Book;
import javafx.scene.input.MouseEvent;
import service.custome.BookService;
import service.custome.impl.BookServiceImpl;
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


import java.io.IOException;


public class BookCardFormController {
    @FXML
    private Label lblId;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblISBM;

    @FXML
    private Label lblQty;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblGen;

    @FXML
    private ImageView image;

    private BookStatus status;

    BookService service = new BookServiceImpl();

    public void setBookData(String id,String title,String iSBM, String author, Integer year, String imagePath ,BookStatus available ,int qty) {
        lblId.setText(id);
        lblTitle.setText(title);
        lblISBM.setText(iSBM);
        lblAuthor.setText(author);
        lblGen.setText(year.toString());
        image.setImage(new Image(imagePath));

        this.status=available;
        this.lblQty.setText(String.valueOf(qty));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/component/book_update_form.fxml"));

        try {
            Parent root = loader.load();
            UpdateBookFormController controller = loader.getController();

            controller.setData(
                    lblId.getText(),
                    lblTitle.getText(),
                    lblAuthor.getText(),
                    Integer.parseInt(lblGen.getText()),
                    lblISBM.getText(),
                    image.getImage().getUrl(),
                    status
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update Book");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        service.delete(new Book(
                lblId.getText(),
                lblTitle.getText(),
                lblISBM.getText(),
                lblAuthor.getText(),
                Integer.parseInt(lblGen.getText()),
                image.getImage().getUrl(),
                BookStatus.AVAILABLE,
                Integer.parseInt(lblQty.getText())
        ));

    }
    @FXML
    void btnSelectBookOnAction(MouseEvent event) {
        IssueBookFormController.getInstance().setBookCardDetails(
                lblId.getText(),
                lblTitle.getText(),
                lblAuthor.getText(),
                lblISBM.getText(),
                lblGen.getText(),
                lblQty.getText(),
                image.getImage().getUrl()

        );
    }
}