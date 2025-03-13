package controller.component.member;

import controller.component.IssueBookFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.enums.MemberGenderType;
import util.enums.MemberStatus;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemberCardFormController {

    public ImageView imageUrl;
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
    private Label lblGender;

    public int borrowBookCount;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void bynDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnOnActionSelectMember(MouseEvent event) {

        IssueBookFormController controller = IssueBookFormController.getInstance();

        if (controller != null) {
            controller.setMemberCardDetails(
                    lblID.getText(),
                    lblName.getText(),
                    lblAddress.getText(),
                    LocalDate.parse(lblMemberDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    lblContact.getText(),
                    lblEmail.getText(),
                    lblGender.getText(),
                    borrowBookCount,
                    imageUrl.getImage().getUrl()
            );
        } else {
            System.out.println("Controller Not Found!");
        }

    }


    public void setMemberData(String id, String name, String address, LocalDate membershipDate, String contact, String email, MemberStatus status, Integer borrowBookCount, MemberGenderType type, String imageUrl) {
        lblID.setText(id);
        lblName.setText(name);
        lblAddress.setText(address);
        lblMemberDate.setText(membershipDate.toString());
        lblContact.setText(contact);
        lblEmail.setText(email);
        lblGender.setText(type.toString());
        this.imageUrl.setImage(new Image(imageUrl));

        this.borrowBookCount=borrowBookCount;
    }


}