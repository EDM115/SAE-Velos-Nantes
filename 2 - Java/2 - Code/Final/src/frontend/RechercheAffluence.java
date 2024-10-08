package frontend;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner; 
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import backend.RechercheAffluenceB;
import utils.TitleBar;
import utils.StageDump;
import utils.WindowDrag;

/**
 * The RechercheAffluence class, allows to create the crowd search window
 */
public class RechercheAffluence extends Application {

    /**
     * The stage dump
     */
	private StageDump stageDump = new StageDump();

    /**
     * The window drag
     */
    private WindowDrag windowDrag;

    /**
     * The search button
     */
    private Button searchButton = new Button("RECHERCHE");

    /**
     * The compteur
     */
    private String compteur;

    /**
     * The compteur index
     */
    private int compteurIndex;

    /**
     * The hour
     */
    private int hour;

    /**
     * The date
     */
    private LocalDate date;

    /**
     * The new stage
     */
    private Stage newStage;

    /**
     * The popup stage
     */
    private Stage popupStage;

    /**
     * The constructor
     * @param popup the popup stage
     */
    public RechercheAffluence(Stage popup) {
        this.popupStage = popup;
    }

    /**
     * Start the stage
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
		newStage = stageDump.dump(primaryStage);
		RechercheAffluenceB rechercheAffluenceB = new RechercheAffluenceB(this);
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        rechercheAffluenceB.lesCompteursBdd();
        ArrayList<String> lesCompteurs = rechercheAffluenceB.getLesCompteurs();

        // Close the popup
        Platform.runLater(() -> {
            this.popupStage.close();
        });

        // Create UI components
        ComboBox<String> departureStation = new ComboBox<>();
        for (String compteur : lesCompteurs) {
            departureStation.getItems().add(compteur);
        }
        DatePicker datePicker = new DatePicker();
        Spinner<Integer> hourSpinner = new Spinner<>();
        searchButton.setGraphic(createIcon("res/images/search_cl.png"));
        searchButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        searchButton.setStyle("-fx-background-color: #8be9fd; -fx-text-fill: #44475a;");

        // Set up the hour spinner
        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hourSpinner.setValueFactory(hourValueFactory);

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
        Text dateLabel = new Text("Date");
        dateLabel.setFill(Color.WHITE);
        Text timeLabel = new Text("Heure");
        timeLabel.setFill(Color.WHITE);

		// Add components to the grid
        root.add(departureLabel, 0, 0);
        root.add(departureStation, 1, 0);
        root.add(dateLabel, 0, 1);
        root.add(datePicker, 1, 1);
        root.add(timeLabel, 0, 2);
        root.add(hourSpinner, 1, 2);
        root.add(searchButton, 1, 3);
		
        // Create the title bar
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Recherche d'affluence");
		
        // Create the root pane
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(titleBar);
        rootPane.setCenter(root);
		
        windowDrag = new WindowDrag(rootPane, newStage);

		// Add event listeners
        menuButton.setOnMouseClicked(event -> {
            rechercheAffluenceB.start(newStage);
        });

		// Customize stage
		newStage.initStyle(StageStyle.UNDECORATED);
		newStage.setTitle("Recherche d'affluence");
		
		// Set the root pane as the scene content
		Scene scene = new Scene(rootPane, 400, 400);
		try {
			scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		scene.setFill(Color.TRANSPARENT);
		newStage.setScene(scene);

        // dynamically updates the values of the instance variables departure, arrival, date, hour everytime the user changes one of the ComboBoxes/Spinners/DatePicker
        departureStation.valueProperty().addListener((observable, oldValue, newValue) -> {
            compteur = newValue;
            compteurIndex = departureStation.getSelectionModel().getSelectedIndex();
        });
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            date = newValue;
        });
        hourSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            hour = newValue;
        });

		// Show the stage
		newStage.show();
	}

    /**
     * Create an icon
     * @param imagePath the path of the image
     * @return the icon
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
     * Get the search button
     * @return the search button
     */
    public Button getSearchButton() {
        return searchButton;
    }

    /**
     * Get the compteur
     * @return the compteur
     */
    public String getCompteur() {
        return compteur;
    }

    /**
     * Get the compteur index
     * @return the compteur index
     */
    public int getCompteurIndex() {
        return compteurIndex;
    }

    /**
     * Get the hour
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get the date
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the stage
     * @return the stage
     */
    public Stage getStage() {
        return newStage;
    }

}
