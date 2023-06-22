package frontend;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import backend.StationProcheB;
import utils.TitleBar;
import utils.StageDump;
import utils.WindowDrag;

/**
 * The StationProche class, allows to create the nearest station window
 */
public class StationProche extends Application {

    /**
     * The stage dump
     */
	private StageDump stageDump = new StageDump();

    /**
     * The window drag
     */
    private WindowDrag windowDrag;
    
    /**
     * The nearest station label
     */
    private Label nearestStationLabel = new Label();

    /**
     * The distance label
     */
    private Label distanceLabel = new Label();

    /**
     * The popup stage
     */
    private Stage popupStage;

    /**
     * The constructor
     * @param popup the popup stage
     */
    public StationProche(Stage popup) {
        this.popupStage = popup;
    }

    /**
     * The start method, called when the window is created
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
		Stage newStage = stageDump.dump(primaryStage);
		StationProcheB stationProcheB = new StationProcheB(this);
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        stationProcheB.lesCompteursBdd();
        ArrayList<String> lesCompteurs = stationProcheB.getLesCompteurs();

        Platform.runLater(() -> {
            this.popupStage.close();
        });
        
        // Create UI components
        ComboBox<String> departureStation = new ComboBox<>();
        for (String compteur : lesCompteurs) {
            departureStation.getItems().add(compteur);
        }
        
        Button searchButton = new Button("RECHERCHE");
        searchButton.setOnAction(event -> {
            String departure = departureStation.getValue();
            stationProcheB.rechercherStation(departure);
        });
        searchButton.setGraphic(createIcon("res/images/search_cl.png"));
        searchButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        searchButton.setStyle("-fx-background-color: #8be9fd; -fx-text-fill: #44475a;");

        // Create labels for displaying results
        nearestStationLabel.setTextFill(Color.web("#f8f8f2"));
        nearestStationLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 14));

        distanceLabel.setTextFill(Color.web("#f8f8f2"));
        distanceLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 14));

        // Set up the layout
        GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#282a36"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        root.setHgap(10);

        // Add labels for UI components
        Text departureLabel = new Text("Station de départ");
        departureLabel.setFill(Color.WHITE);

		// Add components to the grid
        root.add(departureLabel, 0, 0);
        root.add(departureStation, 1, 0);
        root.add(searchButton, 1, 1);
        root.add(nearestStationLabel, 0, 2, 2, 1);
        root.add(distanceLabel, 0, 3, 2, 1);
		
        // Create the title bar
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Station la plus proche");
		
        // Create the root pane
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(titleBar);
        rootPane.setCenter(root);
		
        windowDrag = new WindowDrag(rootPane, newStage);

		// Add event listeners
        menuButton.setOnMouseClicked(event -> {
            stationProcheB.start(newStage);
        });

		// Customize stage
		newStage.initStyle(StageStyle.UNDECORATED);
		newStage.setTitle("Station la plus proche");
		
		// Set the root pane as the scene content
		Scene scene = new Scene(rootPane, 400, 400);
		try {
			scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		scene.setFill(Color.TRANSPARENT);
		newStage.setScene(scene);

		// Show the stage
		newStage.show();
	}

    /**
     * Create an icon
     * @param imagePath the image path
     * @return the image view
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
     * The main method
     * @param args the arguments
     */
	public static void main(String[] args) {
		launch(args);
	}

    /**
     * Set the nearest station label
     * @param text the text
     */
    public void setNearestStationLabel(String text) {
        this.nearestStationLabel.setText(text);
    }

    /**
     * Set the distance label
     * @param text the text
     */
    public void setDistanceLabel(String text) {
        this.distanceLabel.setText(text);
    }

}
