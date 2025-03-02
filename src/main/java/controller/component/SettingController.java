package controller.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class SettingController {
    @FXML
    private ComboBox<String> themeSelector;

    @FXML
    private CheckBox notificationToggle;

    @FXML
    private Button changePasswordBtn, logoutBtn;

    public void initialize() {
        // Load available themes
        themeSelector.getItems().addAll("Light Mode", "Dark Mode", "Blue Theme");

        // Set default theme
        themeSelector.setValue("Dark Mode");

        // Handle theme change
        themeSelector.setOnAction(event -> {
            String selectedTheme = themeSelector.getValue();
            System.out.println("Selected Theme: " + selectedTheme);
            // Logic to change theme
        });

        // Handle logout
        logoutBtn.setOnAction(event -> {
            System.out.println("User Logged Out!");
            // Logic to handle logout
        });

        // Handle password change
        changePasswordBtn.setOnAction(event -> {
            System.out.println("Redirecting to Change Password...");
            // Logic to change password
        });
    }
}
