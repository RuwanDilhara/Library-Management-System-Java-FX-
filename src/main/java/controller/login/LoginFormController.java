package controller.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class LoginFormController {

    @FXML
    private ComboBox<?> cmbRole;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private AnchorPane loadPage;

    @FXML
    private StackPane stkpane;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnForgotPasswordOnAction(MouseEvent event) {

    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

}
