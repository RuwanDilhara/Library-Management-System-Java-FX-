package controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

public class StaffDashboardController {

    @FXML
    private StackPane componentLoader;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblWelcome;

    @FXML
    void btnAboutOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/about_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBookManagementOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/book_management_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnFineManagementOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/fine_management_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/home_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnMemberManagementOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/member_management_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnNotificationOnAction(MouseEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/notification_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnProfileOnClicked(MouseEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/profile_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReportOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/report_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReturnBookOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/return_book_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) {
        URL url = this.componentLoader.getClass().getResource("/view/component/setting_component.fxml");
        assert url!=null;

        try {
            Parent load = FXMLLoader.load(url);
            this.componentLoader.getChildren().clear();
            this.componentLoader.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
