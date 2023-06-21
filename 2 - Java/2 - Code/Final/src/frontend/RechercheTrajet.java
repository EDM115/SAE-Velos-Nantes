package frontend;

import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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

import backend.RechercheTrajetB;
import utils.TitleBar;
import utils.StageDump;
import utils.ConnexionBdd;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;

public class RechercheTrajet extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
	private StageDump stageDump = new StageDump();
    Button searchButton = new Button("RECHERCHE");
    String departure;
    String arrival;
    int hour;
    LocalDate date;
    Stage newStage;

    @Override
    public void start(Stage primaryStage) {
		// copy primaryStage to newStage (new object, not a reference)
        newStage = stageDump.dump(primaryStage);
        // Import stuff
        RechercheTrajetB rechercheTrajetB = new RechercheTrajetB(this);

        // Create the spinner and text for the popup
        JFXSpinner spinner = new JFXSpinner();
        Text text = new Text("Connexion à la base de données");

        // Create the popup layout
        JFXDialogLayout popupLayout = new JFXDialogLayout();
        popupLayout.setBody(new VBox(spinner, text));
        popupLayout.setStyle("-fx-background-color: white; -fx-padding: 20;");

        // Create a new StackPane object
        StackPane stackPane = new StackPane();

        // Get the root node of the scene
        Parent root1 = newStage.getScene().getRoot();

        // Add the StackPane object to the root node
        ((Pane) root1).getChildren().add(stackPane);

        // Create the popup dialog
        JFXDialog popup = new JFXDialog(stackPane, popupLayout, JFXDialog.DialogTransition.CENTER);

        // Show the popup
        popup.show();

       
        rechercheTrajetB.lesCompteursBdd();
        ArrayList<String> lesCompteurs = rechercheTrajetB.getLesCompteurs();

        // Close the popup
        popup.close();

        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Create UI components
        // add the strings from lesCompteurs to the departureStation and arrivalStation ComboBoxes
        ComboBox<String> departureStation = new ComboBox<>();
        //departureStation.getItems().addAll("Station 1", "Station 2", "Station 3");
        for (String compteur : lesCompteurs) {
            departureStation.getItems().add(compteur);
        }
        

        ComboBox<String> arrivalStation = new ComboBox<>();
        //arrivalStation.getItems().addAll("Station A", "Station B", "Station C");
        for (String compteur : lesCompteurs) {
            arrivalStation.getItems().add(compteur);
        }
        DatePicker datePicker = new DatePicker(LocalDate.now());
        Spinner<Integer> hourSpinner = new Spinner<>();
        /* searchButton.setOnAction(event -> {
            String departure = departureStation.getValue();
            String arrival = arrivalStation.getValue();
            int hour = hourSpinner.getValue();
            LocalDate date = datePicker.getValue();
            rechercheTrajetB.rechercherTrajet(departure, arrival, hour, date);
        }); */

        
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
		
        // Make the window draggable
        titleBar.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
		
        titleBar.setOnMouseDragged(event -> {
			newStage.setX(event.getScreenX() - xOffset);
            newStage.setY(event.getScreenY() - yOffset);
        });

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

        // dynamically updates the values of the instance variables departure, arrival, date, hour everytime the user changes one of the ComboBoxes/Spinners/DatePicker
        departureStation.valueProperty().addListener((observable, oldValue, newValue) -> {
            departure = newValue;
        });
        arrivalStation.valueProperty().addListener((observable, oldValue, newValue) -> {
            arrival = newValue;
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

    public Button getSearchButton() {
        return this.searchButton;
    }

    public String getDeparture() {
        return this.departure;
    }

    public String getArrival() {
        return this.arrival;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getHour() {
        return this.hour;
    }

    public Stage getStage() {
        return this.newStage;
    }
}