package controller.component;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class AboutComponentController {

    @FXML
    private ImageView profileImage;

    @FXML
    public void initialize() {
        Circle clip = new Circle(75, 75, 75);
        profileImage.setClip(clip);
    }
}
