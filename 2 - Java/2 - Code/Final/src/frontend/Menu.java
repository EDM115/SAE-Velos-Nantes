package frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu extends Stage {

    public Menu(Stage previousStage) {
        JFXHamburger hamburger = new JFXHamburger();

        // Create dropdown options
        JFXButton option1 = new JFXButton("Recherche de trajet");
        JFXButton option2 = new JFXButton("Recherche d'affluence");
        JFXButton option3 = new JFXButton("Station la plus proche");
        JFXButton option4 = new JFXButton("Précédentes recherches");

        // Set icons for dropdown options
        option1.setGraphic(createIcon("/res/images/search.png"));
        option2.setGraphic(createIcon("/res/images/search.png"));
        option3.setGraphic(createIcon("/res/images/search.png"));
        option4.setGraphic(createIcon("/res/images/search.png"));

        // Create buttons with icons
        JFXButton addButton = new JFXButton();
        addButton.setGraphic(createIcon("/res/images/plus.png"));

        JFXButton editButton = new JFXButton();
        editButton.setGraphic(createIcon("/res/images/pencil.png"));

        // Create the menu layout
        VBox menuLayout = new VBox();
        menuLayout.setSpacing(10);
        menuLayout.setAlignment(Pos.TOP_LEFT);
        menuLayout.setPadding(new Insets(10));
        menuLayout.getChildren().addAll(option1, option2, option3, option4, addButton, editButton);

        // Create the popup
        JFXPopup popup = new JFXPopup(menuLayout);

        // Set the position of the popup relative to the hamburger icon
        hamburger.setOnMouseClicked(e -> popup.show(hamburger, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, -12, 15));

        Scene scene = new Scene(new VBox(hamburger), 400, 400, Color.WHITE);
        setScene(scene);

        // Set the size of the menu to match the previous stage
        setWidth(previousStage.getWidth());
        setHeight(previousStage.getHeight());

        // Set the position of the menu to match the previous stage
        setX(previousStage.getX());
        setY(previousStage.getY());

        // Handle close button event to show the previous stage
        setOnCloseRequest(event -> {
            event.consume(); // Prevents closing the menu immediately
            hide();
            previousStage.show();
        });
    }

    private ImageView createIcon(String imagePath) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(24);
            imageView.setFitHeight(24);
            return imageView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
