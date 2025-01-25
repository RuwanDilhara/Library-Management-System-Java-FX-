package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ForgotPasswordFormController {

    public AnchorPane loadPage;
    @FXML
    private TextField txtEmail;

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) {
        URL resource = this.loadPage.getClass().getResource("/view/verify_email_form.fxml");
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadPage.getChildren().clear();
            loadPage.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        URL resource = this.loadPage.getClass().getResource("/view/login_form.fxml");
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadPage.getChildren().clear();
            loadPage.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        URL resource = this.loadPage.getClass().getResource("/view/login_form.fxml");
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadPage.getChildren().clear();
            loadPage.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}