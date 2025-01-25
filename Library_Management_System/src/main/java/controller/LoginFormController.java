package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Admin;
import model.Member;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public PasswordField txtPassword;
    public Label lblForgotPassword;
    public TextField txtEmail;
    public ComboBox cmbRole;
    public AnchorPane loadPage;

    public void btnForgotPasswordOnAction(MouseEvent mouseEvent) {
        URL resource = this.loadPage.getClass().getResource("/view/forgot_password_form.fxml");
        assert resource != null;

        try {
            Parent load = FXMLLoader.load(resource);
            loadPage.getChildren().clear();
            loadPage.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSignInOnAction(ActionEvent actionEvent) {
        List<Member> allMember = LoginController.getInstance().getAllMember();
        List<Admin> allAdmin = LoginController.getInstance().getAllAdmin();
        if (!txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
            if (cmbRole.getValue() != null) {
                if (cmbRole.getValue().equals("Member")) {
                    String email = "";
                    String password = "";
                    for (Member member : allMember) {
                        if (member.getEmail().equals(txtEmail.getText()) && member.getPassword().equals(txtPassword.getText())) {
                            email = member.getEmail();
                            password = member.getPassword();
                            URL resource = this.loadPage.getClass().getResource("/view/member_form.fxml");
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

                }
                if (cmbRole.getValue().equals("Librarian")) {
                    String email = "";
                    String password = "";
                    for (Admin admin : allAdmin) {
                        if (admin.getEmail().equals(txtEmail.getText()) && admin.getPassword().equals(txtPassword.getText())) {
                            email = admin.getEmail();
                            password = admin.getPassword();
                            URL resource = this.loadPage.getClass().getResource("/view/admin_form.fxml");
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
                    if (email.isEmpty() && password.isEmpty()) {
                        new Alert(Alert.AlertType.INFORMATION, "Invalid Inputs Try again !").show();
                    }
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Select Your Role :)").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Enter Your inputs  :)").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> roleObservableList = FXCollections.observableArrayList();
        cmbRole.setValue("Member");
        roleObservableList.add("Member");
        roleObservableList.add("Librarian");
        roleObservableList.add("Owner");
        cmbRole.setItems(roleObservableList);

    }
}