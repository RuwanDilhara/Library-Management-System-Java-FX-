package controller.component.member;

import com.jfoenix.controls.JFXToggleButton;
import dto.Member;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.custome.MemberService;
import service.custome.impl.MemberServiceImpl;
import util.enums.MemberGenderType;
import util.enums.MemberStatus;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UpdateMemberFormController implements Initializable {

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
    private ComboBox<String> cmbGenderType;

    private Integer borrowedBookCount;

    private MemberService service = new MemberServiceImpl();

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
        boolean isUpdate = service.updateMember(
                new Member(
                        txtID.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtEmail.getText(),
                        txtContact.getText(),
                        LocalDate.parse(lblMemberDate.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        tglActive.isSelected() ? MemberStatus.INACTIVE : MemberStatus.ACTIVE,
                        this.borrowedBookCount,
                        isMemberGenderType(),
                        isImageUrl()
                )
        );

        if (isUpdate){
            new Alert(Alert.AlertType.INFORMATION,"Member is Updated !").show();
        }
    }
    private String isImageUrl(){
        return cmbGenderType.getValue()
                .equals(MemberGenderType.MALE.toString())?"img/member/male_avatar.png"
                :"img/member/female_avatar.png";
    }
    private MemberGenderType isMemberGenderType(){
        if (cmbGenderType.getValue().equals(MemberGenderType.FEMALE.toString())) {
            return MemberGenderType.FEMALE;
        } else if (cmbGenderType.getValue().equals(MemberGenderType.OTHER.toString())) {
            return MemberGenderType.OTHER;
        }
        return MemberGenderType.MALE;
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    public void setDataToForm(String id,String name,
                                     String address,
                                     String membershipDate,
                                     String contact,
                                     String email,
                                     String gender,Integer borrowedBookCount){
        lblID.setText(id);
        lblName.setText(name);
        lblAddress.setText(address);
        lblMemberDate.setText(membershipDate);
        lblContact.setText(contact);
        lblEmail.setText(email);
        lblGender.setText(gender);

        txtID.setText(id);
        txtName.setText(name);
        txtAddress.setText(address);
        txtContact.setText(contact);
        txtEmail.setText(email);
        dateSinceDate.setText(id);
        cmbGenderType.setValue(gender);

        this.borrowedBookCount= borrowedBookCount;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> genderTypeList = FXCollections.observableArrayList();
        genderTypeList.add("MALE");
        genderTypeList.add("FEMALE");
        genderTypeList.add("OTHER");
        cmbGenderType.setValue("MALE");

        cmbGenderType.setItems(genderTypeList);


    }
}
