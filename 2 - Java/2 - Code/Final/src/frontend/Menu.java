package frontend;

import java.io.File;
import java.net.MalformedURLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import utils.TitleBar;

public class Menu extends Stage {
    private double xOffset = 0;
    private double yOffset = 0;

    public Menu(Stage previousStage) {
        // Import stuff
        TitleBar titleBarElement = new TitleBar();

        JFXHamburger hamburger = new JFXHamburger();
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        JFXButton closeMenuButton = new JFXButton("");
        closeMenuButton.setGraphic(createIcon("/res/images/cross.png"));

        // Create dropdown options
        JFXButton option1 = new JFXButton("Recherche de trajet");
        JFXButton option2 = new JFXButton("Recherche d'affluence");
        JFXButton option3 = new JFXButton("Station la plus proche");
        JFXButton option4 = new JFXButton("Précédentes recherches");

        // Set icons for dropdown options
        option1.setGraphic(createIcon("res/images/search.png"));
        option2.setGraphic(createIcon("res/images/search.png"));
        option3.setGraphic(createIcon("res/images/search.png"));
        option4.setGraphic(createIcon("res/images/search.png"));

        // Create buttons with icons
        JFXButton addButton = new JFXButton();
        addButton.setGraphic(createIcon("res/images/plus.png"));

        JFXButton editButton = new JFXButton();
        editButton.setGraphic(createIcon("res/images/pencil.png"));

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

        titleBarElement.createTitleBar(this, closeMenuButton, minimizeButton, maximizeRestoreButton, closeButton, "Menu");

        VBox root = new VBox();
        root.getChildren().addAll(titleBarElement.createTitleBar(this, closeMenuButton, minimizeButton, maximizeRestoreButton, closeButton, "Menu"), hamburger);
        VBox.setMargin(hamburger, new Insets(10, 0, 0, 0)); // Set margin for spacing below title bar

        Scene scene = new Scene(root, 400, 400, Color.WHITE);
        try {
            scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        setScene(scene);

        // Set the size of the menu to match the previous stage
        setWidth(previousStage.getWidth());
        setHeight(previousStage.getHeight());

        // Set the position of the menu to match the previous stage
        setX(previousStage.getX());
        setY(previousStage.getY());

        // Implement window dragging functionality
        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            setX(event.getScreenX() - xOffset);
            setY(event.getScreenY() - yOffset);
        });

        setScene(scene);
        setTitle("Menu");
        initStyle(StageStyle.UNDECORATED);

        // Handle close button event to show the previous stage
        setOnCloseRequest(event -> {
            event.consume(); // Prevents closing the menu immediately
            hide();
            previousStage.show();
        });
    }

    private ImageView createIcon(String imagePath) {
        try {
            Image image = new Image(new File(imagePath).toURI().toURL().toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(24);
            imageView.setFitHeight(24);
            return imageView;
        } catch (Exception e) {
            System.err.println("Failed to load icon: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }
}
