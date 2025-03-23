package controller.component.member;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UpdateMemberFormController {

    @FXML
    private TextField dateSinceDate;

    @FXML
    private ImageView imageUrl;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblID;

    @FXML
    private Label lblMemberDate;

    @FXML
    private Label lblName;

    @FXML
    private JFXToggleButton tglActive;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnOnActionSelectMember(MouseEvent event) {

    }

    @FXML
    void btnPreviewOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void bynDeleteOnAction(ActionEvent event) {

    }

    public void setDataToMemberCard(String Id,String name,
                                     String address,
                                     String membershipDate,
                                     String contact,
                                     String email,
                                     String gender){
        lblID.setText(Id);
        lblName.setText(name);
        lblAddress.setText(address);
        lblMemberDate.setText(membershipDate);
        lblContact.setText(contact);
        lblEmail.setText(email);
        lblGender.setText(gender);
    }

}
