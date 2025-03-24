package controller.component.member;

import controller.component.IssueBookFormController;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.custome.MemberService;
import service.custome.impl.MemberServiceImpl;
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

    MemberService service = new MemberServiceImpl();

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/member_update_form.fxml"));
        try {
            Parent root = loader.load();
            UpdateMemberFormController controller = loader.getController();

            controller.setDataToForm(
                    lblID.getText(),
                    lblName.getText(),
                    lblAddress.getText(),
                    lblMemberDate.getText(),
                    lblContact.getText(),
                    lblEmail.getText(),
                    lblGender.getText(),
                    borrowBookCount

            );

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update Member");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this member?");
        alert.setContentText("Member ID: " + lblID.getText());

        alert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                boolean isDeleted = service.deleteMemberById(
                        new Member(
                                lblID.getText(),
                                lblName.getText(),
                                lblAddress.getText(),
                                lblEmail.getText(),
                                lblContact.getText(),
                                LocalDate.parse(lblMemberDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                                MemberStatus.ACTIVE,
                                borrowBookCount,
                                isMemberType(),
                                imageUrl.getImage().getUrl()
                        )
                );

                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Member Deleted Successfully!").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to Delete Member!").show();
                }
            }
        });
    }
    public MemberGenderType  isMemberType(){
        if (lblGender.getText().equals(MemberGenderType.MALE.toString())){
            return MemberGenderType.MALE;
        } else if (lblGender.getText().equals(MemberGenderType.FEMALE.toString())){
            return MemberGenderType.FEMALE;
        } else {
            return MemberGenderType.OTHER;
        }
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