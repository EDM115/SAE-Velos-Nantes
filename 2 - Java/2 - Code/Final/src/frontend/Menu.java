package frontend;

import java.io.File;
import java.net.MalformedURLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToggleButton;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;

import backend.MenuB;
import utils.TitleBar;
import utils.GlobalVar;

public class Menu extends Stage {
    private double xOffset = 0;
    private double yOffset = 0;

    // Create buttons
    JFXButton homeButton = new JFXButton("Accueil");
    JFXButton option1 = new JFXButton("Recherche de trajet");
    JFXButton option2 = new JFXButton("Recherche d'affluence");
    JFXButton option3 = new JFXButton("Station la plus proche");
    JFXButton graphesButton = new JFXButton("Graphes");
    JFXButton addDataButton = new JFXButton("Saisie de données");
    JFXButton editDataButton = new JFXButton("Modification de données");
    Stage previousStage;
    GlobalVar globalVar = new GlobalVar();

    public Menu(Stage previousStage) {
        this.previousStage = previousStage;
        // Import stuff
        TitleBar titleBarElement = new TitleBar();
        MenuB menuB = new MenuB(this);

        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        JFXButton closeMenuButton = new JFXButton("✕");
        //closeMenuButton.setGraphic(createIcon("res/images/cross.png"));
        closeMenuButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2; -fx-font-size: 30px; -fx-font-weight: bold;");

        // Set icons for buttons
        homeButton.setGraphic(createIcon("res/images/home.png"));
        homeButton.setStyle("-fx-text-fill: #f8f8f2");
        option1.setGraphic(createIcon("res/images/search.png"));
        option1.setStyle("-fx-text-fill: #f8f8f2");
        option2.setGraphic(createIcon("res/images/search.png"));
        option2.setStyle("-fx-text-fill: #f8f8f2");
        option3.setGraphic(createIcon("res/images/search.png"));
        option3.setStyle("-fx-text-fill: #f8f8f2");
        graphesButton.setGraphic(createIcon("res/images/graph.png"));
        graphesButton.setStyle("-fx-text-fill: #f8f8f2");
        addDataButton.setGraphic(createIcon("res/images/plus.png"));
        addDataButton.setStyle("-fx-text-fill: #f8f8f2");
        editDataButton.setGraphic(createIcon("res/images/pencil.png"));
        editDataButton.setStyle("-fx-text-fill: #f8f8f2");

        Text adminLabel = new Text("Administrateur");
        adminLabel.setFill(Color.web("#f8f8f2"));

        // Create the toggle button
        JFXToggleButton adminToggle = new JFXToggleButton();
        // Set the initial state of the toggle button based on the value of 'admin' variable
        adminToggle.setSelected(globalVar.isAdmin());
        adminToggle.getStyleClass().add("jfx-toggle-button");

        // Create an HBox to hold the label and toggle button
        HBox adminToggleContainer = new HBox(10);
        adminToggleContainer.setAlignment(Pos.CENTER);
        adminToggleContainer.getChildren().addAll(adminLabel, adminToggle);

        VBox menuLayout = new VBox();
        menuLayout.setSpacing(10);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(150));
        menuLayout.getChildren().addAll(closeMenuButton, homeButton, option1, option2, option3, graphesButton, addDataButton, editDataButton, adminToggleContainer);


        // Create the popup
        JFXPopup popup = new JFXPopup(menuLayout);

        // Set the position of the popup relative to the closeMenuButton
        closeMenuButton.setOnMouseClicked(e -> popup.show(closeMenuButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, 12, 15));

        VBox root = new VBox();
        root.getChildren().addAll(titleBarElement.createTitleBar(this, minimizeButton, maximizeRestoreButton, closeButton, "Menu"), menuLayout);


        /* titleBarElement.createTitleBar(this, closeMenuButton, minimizeButton, maximizeRestoreButton, closeButton, "Menu"); */

        //VBox root = new VBox();
        //root.getChildren().addAll(titleBarElement.createTitleBar(this, closeMenuButton, minimizeButton, maximizeRestoreButton, closeButton, "Menu"), menuLayout);
        VBox.setMargin(menuLayout, new Insets(10, 0, 0, 0)); // Set margin for spacing below title bar

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
        double previousStageX = previousStage.getX();
        double previousStageY = previousStage.getY();
        double previousStageWidth = previousStage.getWidth();
        double previousStageHeight = previousStage.getHeight();
        double menuX = previousStageX + (previousStageWidth - getWidth()) / 2;
        double menuY = previousStageY + (previousStageHeight - getHeight()) / 2;
        setX(menuX);
        setY(menuY);

        // Implement window dragging functionality
        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            setX(event.getScreenX() - xOffset);
            setY(event.getScreenY() - yOffset);
        });

        setTitle("Menu");
        initStyle(StageStyle.UNDECORATED);

        // Handle close button event to show the previous stage
        setOnCloseRequest(event -> {
            event.consume(); // Prevents closing the menu immediately
            hide();
            previousStage.show();
        });

        // Handle close button event to exit the application
        closeButton.setOnAction(event -> {
            event.consume();
            hide();
            previousStage.close();
            // Make sure to exit the JavaFX application thread
            Platform.exit();
        });

        // Handle close menu button event to show the previous stage
        closeMenuButton.setOnAction(event -> {
            event.consume();
            hide();
            previousStage.show();
        });

        // Handle toggle button event
        adminToggle.setOnAction(event -> {
            globalVar.setAdmin(adminToggle.isSelected());
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

    public JFXButton getHomeButton() {
        return this.homeButton;
    }

    public JFXButton getOption1() {
        return this.option1;
    }

    public JFXButton getOption2() {
        return this.option2;
    }

    public JFXButton getOption3() {
        return this.option3;
    }

    public JFXButton getGraph() {
        return this.graphesButton;
    }

    public JFXButton getAddDataButton() {
        return this.addDataButton;
    }

    public JFXButton getEditDataButton() {
        return this.editDataButton;
    }

    public Stage getStage() {
        return this;
    }

    public Stage getPreviousStage() {
        return this.previousStage;
    }
}
