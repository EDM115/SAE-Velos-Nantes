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

import backend.RechercheTrajetB;
import utils.TitleBar;
import utils.StageDump;
import utils.WindowDrag;

/**
 * The RechercheTrajet class, allows to create the travel search window
 */
public class RechercheTrajet extends Application {

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
    Button searchButton = new Button("RECHERCHE");

    /**
     * The departure station
     */
    private String departure;

    /**
     * The departure index
     */
    private int departureIndex;

    /**
     * The arrival station
     */
    private String arrival;

    /**
     * The arrival index
     */
    private int arrivalIndex;

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
    public RechercheTrajet(Stage popup) {
        this.popupStage = popup;
    }

    /**
     * The start method
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        newStage = stageDump.dump(primaryStage);
        RechercheTrajetB rechercheTrajetB = new RechercheTrajetB(this);
        rechercheTrajetB.lesCompteursBdd();
        ArrayList<String> lesCompteurs = rechercheTrajetB.getLesCompteurs();

        // Close the popup
        Platform.runLater(() -> {
            this.popupStage.close();
        });

        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Create UI components
        ComboBox<String> departureStation = new ComboBox<>();
        for (String compteur : lesCompteurs) {
            departureStation.getItems().add(compteur);
        }
        ComboBox<String> arrivalStation = new ComboBox<>();
        for (String compteur : lesCompteurs) {
            arrivalStation.getItems().add(compteur);
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
        Text arrivalLabel = new Text("Station d'arrivée");
        arrivalLabel.setFill(Color.WHITE);
        Text dateLabel = new Text("Date");
        dateLabel.setFill(Color.WHITE);
        Text timeLabel = new Text("Heure");
        timeLabel.setFill(Color.WHITE);

		// Add components to the grid
        root.add(departureLabel, 0, 0);
        root.add(departureStation, 1, 0);
        root.add(arrivalLabel, 0, 1);
        root.add(arrivalStation, 1, 1);
        root.add(dateLabel, 0, 2);
        root.add(datePicker, 1, 2);
        root.add(timeLabel, 0, 3);
        root.add(hourSpinner, 1, 3);
        root.add(searchButton, 1, 4);
		
        // Create the title bar
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Recherche de trajet");
		
        // Create the root pane
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(titleBar);
        rootPane.setCenter(root);

        windowDrag = new WindowDrag(rootPane, newStage);

		// Add event listeners
        menuButton.setOnMouseClicked(event -> {
            rechercheTrajetB.start(newStage);
        });

		// Customize stage
		newStage.initStyle(StageStyle.UNDECORATED);
		newStage.setTitle("Recherche de trajet");
		
		// Set the root pane as the scene content
		Scene scene = new Scene(rootPane, 400, 400);
		try {
			scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		scene.setFill(Color.TRANSPARENT);
		newStage.setScene(scene);

        departureStation.valueProperty().addListener((observable, oldValue, newValue) -> {
            departure = newValue;
            departureIndex = departureStation.getSelectionModel().getSelectedIndex();
        });
        arrivalStation.valueProperty().addListener((observable, oldValue, newValue) -> {
            arrival = newValue;
            arrivalIndex = arrivalStation.getSelectionModel().getSelectedIndex();
        });
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            date = newValue;
        });
        hourSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            hour = newValue;
        });

        windowDrag = new WindowDrag(rootPane, newStage);

		// Show the stage
		newStage.show();
	}

    /**
     * Create an icon
     * @param imagePath the path to the image
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
     * Main method
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
        return this.searchButton;
    }

    /**
     * Get the departure station
     * @return the departure station
     */
    public String getDeparture() {
        return this.departure;
    }

    /**
     * Get the departure station index
     * @return the departure station index
     */
    public int getDepartureIndex() {
        return this.departureIndex;
    }

    /**
     * Get the arrival station
     * @return the arrival station
     */
    public String getArrival() {
        return this.arrival;
    }

    /**
     * Get the arrival station index
     * @return the arrival station index
     */
    public int getArrivalIndex() {
        return this.arrivalIndex;
    }

    /**
     * Get the date
     * @return the date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Get the hour
     * @return the hour
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Get the stage
     * @return the stage
     */
    public Stage getStage() {
        return this.newStage;
    }

}
