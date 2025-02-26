package controller.component.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class MemberCardFormController {

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblID;

    @FXML
    private Label lblMemberDate;

    @FXML
    private Label lblName;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void bynDeleteOnAction(ActionEvent event) {

    }
    public void setMemberData(String id, String name, String address, LocalDate membershipDate ,String contact ,String email){
        lblID.setText(id);
        lblName.setText(name);
        lblAddress.setText(address);
        lblMemberDate.setText(membershipDate.toString());
        lblContact.setText(contact);
        lblEmail.setText(email);
    }

}