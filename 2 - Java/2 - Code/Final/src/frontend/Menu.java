package frontend;

import java.io.File;
import java.net.MalformedURLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToggleButton;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import backend.MenuB;
import utils.GlobalVar;
import utils.TitleBar;

/**
 * The Menu class, allows to create the menu
 */
public class Menu extends Stage {

    /**
     * xOffset
     */
    private double xOffset = 0;

    /**
     * yOffset
     */
    private double yOffset = 0;

    /**
     * The home button
     */
    private JFXButton homeButton = new JFXButton("Accueil");

    /**
     * The travel search button
     */
    private JFXButton option1 = new JFXButton("Recherche de trajet");

    /**
     * The crowd search button
     */
    private JFXButton option2 = new JFXButton("Recherche d'affluence");

    /**
     * The nearest station search button
     */
    private JFXButton option3 = new JFXButton("Station la plus proche");

    /**
     * The graphs button
     */
    private JFXButton graphesButton = new JFXButton("Graphes");

    /**
     * The add data button
     */
    private JFXButton addDataButton = new JFXButton("Saisie de données");

    /**
     * The edit data button
     */
    private JFXButton editDataButton = new JFXButton("Modification de données");

    /**
     * The previous stage
     */
    private Stage previousStage;

    /**
     * The GlobalVar object, allows to save global variables
     */
    private GlobalVar globalVar = new GlobalVar();
    
    /**
     * The Menu constructor
     * @param previousStage The previous stage
     */
    public Menu(Stage previousStage) {
        this.previousStage = previousStage;
        TitleBar titleBarElement = new TitleBar();
        MenuB menuB = new MenuB(this);

        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        JFXButton closeMenuButton = new JFXButton("✕");
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

        // Set the position of the menu to match the previous stage (utils.WindowDrag can't be used here because the menu is not a child of the previous stage)
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

    /**
     * Creates an icon from an image file
     * @param imagePath The path of the image file
     * @return The icon
     */
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

    /**
     * Returns the home button
     * @return The home button
     */
    public JFXButton getHomeButton() {
        return this.homeButton;
    }

    /**
     * Returns the travel search button
     * @return The travel search button
     */
    public JFXButton getOption1() {
        return this.option1;
    }

    /**
     * Returns the crowd search button
     * @return The crowd search button
     */
    public JFXButton getOption2() {
        return this.option2;
    }

    /**
     * Return the nearest search button
     * @return The nearest search button
     */
    public JFXButton getOption3() {
        return this.option3;
    }

    /**
     * Returns the graph button
     * @return The graph button
     */
    public JFXButton getGraph() {
        return this.graphesButton;
    }

    /**
     * Returns the add data button
     * @return The add data button
     */
    public JFXButton getAddDataButton() {
        return this.addDataButton;
    }

    /**
     * Returns the edit data button
     * @return The edit data button
     */
    public JFXButton getEditDataButton() {
        return this.editDataButton;
    }

    /**
     * Returns the stage
     * @return The stage
     */
    public Stage getStage() {
        return this;
    }

    /**
     * Returns the previous stage
     * @return The previous stage
     */
    public Stage getPreviousStage() {
        return this.previousStage;
    }

}
