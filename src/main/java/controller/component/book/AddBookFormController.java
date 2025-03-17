package controller.component.book;

import dto.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookFormController implements Initializable {

    final BookService service = new BookServiceImpl();

    @FXML
    private TextField txtQty;
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
    private TextField txtISBM;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
    }

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
    void btnAddOnAction(ActionEvent event) {
        String updatedImagePath = (imagePath != null) ? imagePath : "img/Book/images.jpeg"; // Use selected or default image

        boolean bookSaved = service.addBook(new Book(
                txtID.getText(),
                txtTitle.getText(),
                txtISBM.getText(),
                txtAuthor.getText(),
                Integer.parseInt(txtYear.getText()),
                updatedImagePath, // Use the correct image path
                BookStatus.AVAILABLE,
                Integer.parseInt(txtQty.getText())
        ));
        new Alert(Alert.AlertType.INFORMATION,bookSaved?"Book saved !":"Book not saved !").show();

        System.out.println("Book Updated with Image Path: " + updatedImagePath);
    }

    public void generateId(){
        String newId = "B001";
        if (!service.getAll().isEmpty()){
            String lastNumber = service.getAll()
                    .getLast()
                    .getId()
                    .substring(1);

            System.out.println(service.getAll().getLast());

            newId = "B" + String.format("%03d", Integer.parseInt(lastNumber) + 1);

        }
        lblId.setText(newId);
        txtID.setText(newId);
    }
}
