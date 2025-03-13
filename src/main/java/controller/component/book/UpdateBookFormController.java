package controller.component.book;

import com.jfoenix.controls.JFXToggleButton;
import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.custome.BookService;
import service.custome.impl.BookServiceImpl;
import util.enums.BookStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateBookFormController {

    final BookService service = new BookServiceImpl();
    public JFXToggleButton tglAvailability;
    private String imagePath;

    public TextField txtYear;
    public TextField txtAuthor;
    public TextField txtTitle;
    public TextField txtID;
    public Label lblId;

    @FXML
    private ImageView image;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblGen;

    @FXML
    private Label lblISBM;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtISBM;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        ((Stage) txtID.getScene().getWindow()).close();
    }

    @FXML
    void btnPreviewOnAction(ActionEvent event) {
        this.lblTitle.setText(txtTitle.getText());
        this.lblAuthor.setText(txtAuthor.getText());
        this.lblGen.setText(txtYear.getText());
        this.lblISBM.setText(txtISBM.getText());

    }

    @FXML
    void btnSelectOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) image.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                File destFolder = new File("C:/MyImages");
                if (!destFolder.exists()) {
                    destFolder.mkdirs();
                }

                File destFile = new File(destFolder, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                imagePath = destFile.toURI().toString(); // Save image path
                image.setImage(new Image(imagePath));

                System.out.println("Image Saved at: " + destFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Image Save Error: " + e.getMessage());
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String updatedImagePath = (imagePath != null) ? imagePath : "img/Book/images.jpeg"; // Use selected or default image

        service.updateBook(new Book(
                txtID.getText(),
                txtTitle.getText(),
                txtISBM.getText(),
                txtAuthor.getText(),
                Integer.parseInt(txtYear.getText()),
                updatedImagePath,
                isAvailable(),
                Integer.parseInt(txtQty.getText())
        ));

        System.out.println("Book Updated with Image Path: " + updatedImagePath);
    }

    public BookStatus isAvailable(){
        return tglAvailability.isSelected()?
                BookStatus.UNAVAILABLE:BookStatus.AVAILABLE;
    }
    public void setData(String id, String title, String author, Integer year, String iSBM, String imgPath,BookStatus status) {
        this.txtID.setText(id);
        this.txtTitle.setText(title);
        this.txtISBM.setText(iSBM);
        this.txtAuthor.setText(author);
        this.txtYear.setText(year.toString());

        tglAvailability.setSelected(status != BookStatus.AVAILABLE);

        this.lblId.setText(id);
        this.lblTitle.setText(title);
        this.lblAuthor.setText(author);
        this.lblGen.setText(year.toString());
        this.lblISBM.setText(iSBM);

        this.imagePath = imgPath; // Store the existing image path
        image.setImage(new Image(imgPath)); // Load the existing image
    }

    public void bynDeleteOnAction(ActionEvent actionEvent) {
    }
}
