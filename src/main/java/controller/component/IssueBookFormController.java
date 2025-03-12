package controller.component;

import controller.component.member.MemberCardFormController;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import service.custome.MemberService;
import service.custome.impl.MemberServiceImpl;
import util.enums.MemberStatus;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class IssueBookFormController implements Initializable {

    MemberService service = new MemberServiceImpl();

    List<Member> memberList = new ArrayList<>();

    private static final int column_count = 3;

    @FXML
    private ComboBox<String> cmbSearchType;
    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView image;

    @FXML
    private ImageView imageUrl;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblContact;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblEmail1;

    @FXML
    private Label lblEmail2;

    @FXML
    private Label lblGen;

    @FXML
    private Label lblID;

    @FXML
    private Label lblISBM;

    @FXML
    private Label lblId;

    @FXML
    private Label lblMemberDate;

    @FXML
    private Label lblName;

    @FXML
    private Label lblTitle;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxLoader();
        loadMember();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    public void loadMember(){
        int col = 0;
        int raw = 0;
        for (Member member : service.getAll()){
            if (member.getStatus().equals(MemberStatus.ACTIVE)){
                memberAddToGrid(member,col,raw);
                col++;
                if (col==column_count){
                    raw++;
                    col=0;
                }
            }
        }
    }
    public void memberAddToGrid(Member member,int col ,int raw){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/member_card.fxml"));
        try {
            Parent root = loader.load();

            MemberCardFormController controller = loader.getController();
            controller.setMemberData(
                    member.getId(),
                    member.getName(),
                    member.getAddress(),
                    member.getMembershipDate(),
                    member.getContact(),
                    member.getEmail(),
                    member.getStatus(),
                    member.getBorrowedBookCount(),
                    member.getGender(),
                    member.getImageUrl()
            );

            gridPane.add(root,col,raw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboBoxLoader(){
        ObservableList<String> searchTypes = FXCollections.observableArrayList();
        searchTypes.add("Member");
        searchTypes.add("Book");

        cmbSearchType.setItems(searchTypes);
    }


}
