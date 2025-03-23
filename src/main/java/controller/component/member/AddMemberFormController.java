package controller.component.member;

import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.checkerframework.checker.units.qual.A;
import service.custome.MemberService;
import service.custome.impl.MemberServiceImpl;
import util.enums.MemberGenderType;
import util.enums.MemberStatus;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddMemberFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbGenderType;

    @FXML
    private ImageView image;

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
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    String imagePath;

    private MemberService service = new MemberServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateId();
        comboBoxLoader();
    }
    private void comboBoxLoader(){
        ObservableList<String> genderTypeObservableList = FXCollections.observableArrayList();
        genderTypeObservableList.add("MALE");
        genderTypeObservableList.add("FEMALE");
        genderTypeObservableList.add("OTHER");
        cmbGenderType.setValue("MALE");
        cmbGenderType.setItems(genderTypeObservableList);
    }

    private boolean isTextFieldValidate() {
        if (
                !txtID.getText().isEmpty() &&
                        !txtName.getText().isEmpty() &&
                        !txtAddress.getText().isEmpty() &&
                        !txtContact.getText().isEmpty() &&
                        !txtID.getText().isEmpty() &&
                        !txtEmail.getText().isEmpty() &&
                        !cmbGenderType.getValue().isEmpty()
        ) {
            return true;
        }
        new Alert(Alert.AlertType.INFORMATION,"Please Fill all Member Details !").show();
        return false;
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (isTextFieldValidate()){
            boolean isSave = service.addMember(new Member(
                    txtID.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    txtContact.getText(),
                    LocalDate.now(),
                    MemberStatus.ACTIVE,
                    0,
                    isMemberType(),
                    imagePath

            ));
            if(isSave){
                new Alert(Alert.AlertType.INFORMATION,"Member Added Successfully").show();
            }
        }
    }
    public MemberGenderType  isMemberType(){
        if (cmbGenderType.getValue().equals(MemberGenderType.MALE.toString())){
            return MemberGenderType.MALE;
        } else if (cmbGenderType.getValue().equals(MemberGenderType.FEMALE.toString())){
            return MemberGenderType.FEMALE;
        } else {
            return MemberGenderType.OTHER;
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtContact.clear();
    }

    @FXML
    void btnPreviewOnAction(ActionEvent event) {
        if (isTextFieldValidate()){
            lblID.setText(txtID.getText());
            lblName.setText(txtName.getText());
            lblEmail.setText(txtEmail.getText());
            lblMemberDate.setText(LocalDate.now().toString());
            lblContact.setText(txtContact.getText());
            lblAddress.setText(txtAddress.getText());
        }
    }

    private void generateId() {
        String newID = "M1001";
        if (!service.getAll().isEmpty()) {
            String lastNumber = service.getAll().getLast().getId().substring(1);
            newID = "M" + String.format("%03d", Integer.parseInt(lastNumber) + 1);

        }
        lblID.setText(newID);
        txtID.setText(newID);
    }
}