package controller.component.member;

import com.jfoenix.controls.JFXCheckBox;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.custome.MemberService;
import service.custome.impl.MemberServiceImpl;
import util.enums.MemberStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MemberManagementFormController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private JFXCheckBox checkPassive;

    @FXML
    private ComboBox<String> cmbMember;

    @FXML
    private ComboBox<String> cmbSortBy;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtSearch;

    MemberService service = new MemberServiceImpl();

    List<Member> memberList = service.getAll();

    private static final int COLUMN_COUNT = 4;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMember(null);
        comboBoxLoader();
        searchSuggester();
    }

    public void loadMember(String filterByMember) {
        gridPane.getChildren().clear();
        int col = 0;
        int raw = 0;
        for (Member member : memberList) {
                if (getSelectedMemberStatus() == member.getStatus() && (filterByMember == null || filterByMember.equals(member.getName()))) {
                    addMemberToGrid(member,col,raw);
                    col++;
                    if (col == COLUMN_COUNT) {
                        col = 0;
                        raw++;
                    }
                }

        }
    }

    public void addMemberToGrid(Member member, int col, int raw) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/member_card.fxml"));
            AnchorPane memberCard = loader.load();

            MemberCardFormController memberCardFormController = loader.getController();
            memberCardFormController.setMemberData(
                    member.getId(),
                    member.getName(),
                    member.getAddress(),
                    member.getMembershipDate(),
                    member.getContact(),
                    member.getEmail(),
                    member.getStatus(),
                    member.getBorrowedBookCount(),
                    member.getGender(),
                    member.getImageUrl());

            gridPane.add(memberCard, col, raw);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/add_member_form.fxml"));
        try {
            Parent root = loader.load();
            gridPane.getChildren().clear();
            gridPane.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnFilterOnAction(ActionEvent event) {
        if (cmbSortBy.getValue() != null || cmbMember.getValue() != null || checkPassive.isSelected()) {
            if (cmbSortBy.getValue() == null) {
                loadMember(cmbMember.getValue());
            } else {
                sortMember(cmbSortBy.getValue());
                loadMember(cmbMember.getValue());
            }
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        memberList=service.getAll();
        loadMember(null);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        memberList.clear();
        memberList.add(service.searchMemberById(txtSearch.getText()));
        loadMember(null);
    }

    public void comboBoxLoader() {
        ObservableList<String> memberNameObservableList = FXCollections.observableArrayList();
        memberNameObservableList.addAll(service.getAllMember());
        cmbMember.setItems(memberNameObservableList);

        ObservableList<String> sortTypeObservableList = FXCollections.observableArrayList();
        sortTypeObservableList.add("Id");
        sortTypeObservableList.add("Name");
        cmbSortBy.setItems(sortTypeObservableList);
    }

    public void sortMember(String type) {
        System.out.println(type);
        switch (type) {
            case "Id":
                sortById();
                break;
            case "Name":
                sortByName();
                break;
            default:
        }
    }

    public void sortById() {
        memberList.sort(Comparator.comparing(Member::getId));
    }

    public void sortByName() {
        memberList.sort(Comparator.comparing(Member::getName));
    }

    private MemberStatus getSelectedMemberStatus() {
        return checkPassive.isSelected() ? MemberStatus.INACTIVE : MemberStatus.ACTIVE;
    }

    private void searchSuggester(){
        txtSearch.textProperty().addListener((observableValue, string, newValue) -> {
            memberList = service.getMembersByName(newValue);
            loadMember(null );

        });
    }
}
