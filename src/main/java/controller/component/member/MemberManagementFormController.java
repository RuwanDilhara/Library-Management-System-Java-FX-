package controller.component.member;

import dto.Member;
import util.enums.MemberStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MemberManagementFormController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private ScrollPane scrollPane;

    List<Member> memberList = new ArrayList<>();

    private int column = 4;
    private int col=0;
    private int raw=0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMember();
    }
    public void loadMember(){
        memberList.add(new Member("M1001", "Ruwan Dilhara", "Yakkalamulla", "ruwandilhara81@gmail.com", "07851357619", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1002", "Kasun Perera", "Galle", "kasunperera@gmail.com", "07851234562", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1003", "Sanduni Fernando", "Colombo", "sandunifernando@gmail.com", "07851234563", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1004", "Nimal Senanayake", "Kandy", "nimalsenanayake@gmail.com", "07851234564", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1005", "Chamari Jayasinghe", "Matara", "chamarijayasinghe@gmail.com", "07851234565", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1006", "Tharindu Silva", "Kurunegala", "tharindusilva@gmail.com", "07851234566", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1007", "Isuru Madushan", "Anuradhapura", "isurumadushan@gmail.com", "07851234567", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1008", "Dilini Ratnayake", "Negombo", "diliniratnayake@gmail.com", "07851234568", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1009", "Shehan Wijesinghe", "Rathnapura", "shehanwijesinghe@gmail.com", "07851234569", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1010", "Kavindu Amarasinghe", "Gampaha", "kavinduamarasinghe@gmail.com", "07851234570", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1011", "Lakshani Dissanayake", "Jaffna", "lakshanidissanayake@gmail.com", "07851234571", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1012", "Hiruni Wickramasinghe", "Badulla", "hiruniwickramasinghe@gmail.com", "07851234572", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1013", "Sahan Kumara", "Hambantota", "sahankumara@gmail.com", "07851234573", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1014", "Rashmi Fernando", "Puttalam", "rashmifernando@gmail.com", "07851234574", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1015", "Pasindu Jayawardena", "Polonnaruwa", "pasindujayawardena@gmail.com", "07851234575", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1016", "Buddhika Gamage", "Mannar", "buddhikagamage@gmail.com", "07851234576", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1017", "Kusal Rajapaksha", "Trincomalee", "kusalrajapaksha@gmail.com", "07851234577", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1018", "Vimukthi Liyanage", "Kilinochchi", "vimukthiliyanage@gmail.com", "07851234578", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1019", "Dineth Samarasinghe", "Vavuniya", "dinethsamarasinghe@gmail.com", "07851234579", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));
        memberList.add(new Member("M1020", "Sachini Ekanayake", "Batticaloa", "sachiniekanayake@gmail.com", "07851234580", LocalDate.of(2025, 2, 17), MemberStatus.ACTIVE));

        for (Member member : memberList){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/component/member_card.fxml"));
                AnchorPane memberCard = loader.load();

                MemberCardFormController memberCardFormController = loader.getController();
                memberCardFormController.setMemberData(member.getId(),member.getName(),member.getAddress(),member.getMembershipDate(),member.getContact(),member.getEmail());

                gridPane.add(memberCard,col,raw);

                col++;
                if (col == column){
                    col=0;
                    raw++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
