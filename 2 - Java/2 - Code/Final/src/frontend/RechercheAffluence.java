package frontend;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import backend.RechercheAffluenceB;
import backend.RechercheTrajetB;
import utils.TitleBar;
import utils.StageDump;
import utils.WindowDrag;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;

public class RechercheAffluence extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
	private StageDump stageDump = new StageDump();
    private WindowDrag windowDrag;

    @Override
    public void start(Stage primaryStage) {
		// copy primaryStage to newStage (new object, not a reference)
		Stage newStage = stageDump.dump(primaryStage);
        // Import stuff
		RechercheAffluenceB rechercheAffluenceB = new RechercheAffluenceB(this);
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        rechercheAffluenceB.lesCompteursBdd();
        ArrayList<String> lesCompteurs = rechercheAffluenceB.getLesCompteurs();

        // Create UI components
        ComboBox<String> departureStation = new ComboBox<>();
        for (String compteur : lesCompteurs) {
            departureStation.getItems().add(compteur);
        }
        departureStation.setValue(departureStation.getItems().get(0));
        departureStation.getItems().addAll("Station 1", "Station 2", "Station 3");
        DatePicker datePicker = new DatePicker(LocalDate.now());
        Spinner<Integer> hourSpinner = new Spinner<>();
        Button searchButton = new Button("RECHERCHE");
        searchButton.setOnAction(event -> {
            String departure = departureStation.getValue();
            int hour = hourSpinner.getValue();
            LocalDate date = datePicker.getValue();
            rechercheAffluenceB.rechercherAffluence(departure, hour, date);
        });
        searchButton.setGraphic(createIcon("res/images/search_cl.png"));
        searchButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        searchButton.setStyle("-fx-background-color: #8be9fd; -fx-text-fill: #44475a;");

        // Set up the hour spinner
        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalTime.now().getHour());
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

		// Show the stage
		newStage.show();
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

	public static void main(String[] args) {
		launch(args);
	}
}