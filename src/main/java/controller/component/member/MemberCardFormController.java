package controller.component.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.enums.MemberGenderType;
import util.enums.MemberStatus;

import javax.imageio.ImageIO;
import java.time.LocalDate;

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
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void bynDeleteOnAction(ActionEvent event) {

    }

    public void setMemberData(String id, String name, String address, LocalDate membershipDate, String contact, String email, MemberStatus status ,Integer borrowBookCount, MemberGenderType type, String imageUrl) {
        lblID.setText(id);
        lblName.setText(name);
        lblAddress.setText(address);
        lblMemberDate.setText(membershipDate.toString());
        lblContact.setText(contact);
        lblEmail.setText(email);
        this.imageUrl.setImage(new Image(imageUrl));
    }

}