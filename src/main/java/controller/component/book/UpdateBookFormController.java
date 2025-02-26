package controller.component.book;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class UpdateBookFormController {

    public TextField txtYear;
    public TextField txtAuthor;
    public TextField txtTitle;
    public TextField txtID;
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

    

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnPreviewOnAction(ActionEvent event) {

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

                image.setImage(new Image(destFile.toURI().toString()));

                System.out.println("Image Saved at: " + destFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Image Save Error: " + e.getMessage());
            }

        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
    public void setData(String id ,String title ,String author ,Integer year ,String iSBM ){
        this.txtID.setText(id);
        this.txtTitle.setText(title);
        this.txtISBM.setText(iSBM);
        this.txtAuthor.setText(author);
        this.txtYear.setText(year.toString());
    }

}
