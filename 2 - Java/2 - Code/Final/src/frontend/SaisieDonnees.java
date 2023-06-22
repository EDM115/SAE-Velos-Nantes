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
import javafx.scene.control.TextField;
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
import backend.SaisieDonneesB;
import backend.StationProcheB;
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

public class SaisieDonnees extends Application {
    
    private double xOffset = 0;
    String actualTable = "";
    private double yOffset = 0;
	private StageDump stageDump = new StageDump();
    private WindowDrag windowDrag;

    @Override
    public void start(Stage primaryStage) {
		// copy primaryStage to newStage (new object, not a reference)
		Stage newStage = stageDump.dump(primaryStage);
        // Import stuff
		SaisieDonneesB saisieDonneesB = new SaisieDonneesB(this);
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        saisieDonneesB.lesTablesBdd();
        ArrayList<String> lesTables = saisieDonneesB.getLesTables();
        
        // Create UI components
        TextField idQuartier = new TextField();
        TextField nomQuartier = new TextField();
        TextField longueurPisteVelo = new TextField();

        TextField idCompteur = new TextField();
        TextField nomCompteur = new TextField();
        TextField sens = new TextField();
        TextField coord_X = new TextField();
        TextField coord_Y = new TextField();
        TextField leQuartier = new TextField();

        TextField laDate = new TextField();
        TextField tempMoy = new TextField();
        TextField jour = new TextField();
        TextField vacances = new TextField();

        TextField leCompteur = new TextField();
        TextField dateComptage= new TextField();
        TextField h00 = new TextField();
        TextField h01 = new TextField();
        TextField h02 = new TextField();
        TextField h03 = new TextField();
        TextField h04 = new TextField();
        TextField h05 = new TextField();
        TextField h06 = new TextField();
        TextField h07 = new TextField();
        TextField h08 = new TextField();
        TextField h09 = new TextField();
        TextField h10 = new TextField();
        TextField h11 = new TextField();
        TextField h12 = new TextField();
        TextField h13 = new TextField();
        TextField h14 = new TextField();
        TextField h15 = new TextField();
        TextField h16 = new TextField();
        TextField h17 = new TextField();
        TextField h18 = new TextField();
        TextField h19 = new TextField();
        TextField h20 = new TextField();
        TextField h21 = new TextField();
        TextField h22 = new TextField();
        TextField h23 = new TextField();
        TextField presenceAnomalie = new TextField();


        ComboBox<String> tables = new ComboBox<>();
        for (String laTable : lesTables) {
            tables.getItems().add(laTable);
        }
        
        Button searchButton = new Button("AJOUTER");
        searchButton.setOnAction(event -> {
            String departure = tables.getValue();
            //saisieDonnees.ajout(les attributs);
        });
        searchButton.setGraphic(createIcon("res/images/search_cl.png"));
        searchButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        searchButton.setStyle("-fx-background-color: #8be9fd; -fx-text-fill: #44475a;");

        // Set up the layout
        GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#282a36"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setVgap(20);
        root.setHgap(10);

        // Add labels for UI components
        Text tableLabel = new Text("Table à modifier");
        tableLabel.setFill(Color.WHITE);

		// Add components to the grid
        root.add(tableLabel, 0, 0);
        root.add(tables, 1, 0);
        //String actualTable = tables.getValue();
        tables.valueProperty().addListener((observable, oldValue, newValue) -> {
            actualTable = newValue;
        });

        if (actualTable == "Quartier") {
            root.add(idQuartier, 0, 1);
            root.add(nomQuartier, 0, 2);
            root.add(longueurPisteVelo, 0, 3);

        } else if (actualTable == "Compteur") {
            root.add(idCompteur, 0, 1);
            root.add(nomCompteur, 0, 2);
            root.add(sens, 0, 3);
            root.add(coord_X, 0, 4);
            root.add(coord_Y, 0, 5);
            root.add(leQuartier, 0, 6);

        } else if (actualTable == "DateInfo") {
            root.add(laDate, 0, 1);
            root.add(tempMoy, 0, 2);
            root.add(jour, 0, 3);
            root.add(vacances, 0, 4);

        } else if (actualTable == "Comptage") {
            root.add(leCompteur, 0, 1);
            root.add(dateComptage, 0, 2);
            root.add(h00, 0, 3);
            root.add(h01, 0, 4);
            root.add(h02, 0, 5);
            root.add(h03, 0, 6);
            root.add(h04, 0, 7);
            root.add(h05, 0, 8);
            root.add(h06, 0, 9);
            root.add(h07, 0, 10);
            root.add(h08, 0, 11);
            root.add(h09, 0, 12);
            root.add(h10, 0, 13);
            root.add(h11, 0, 14);
            root.add(h12, 0, 15);
            root.add(h13, 0, 16);
            root.add(h14, 0, 17);
            root.add(h15, 0, 18);
            root.add(h16, 0, 19);
            root.add(h17, 0, 20);
            root.add(h18, 0, 21);
            root.add(h19, 0, 22);
            root.add(h20, 0, 23);
            root.add(h21, 0, 24);
            root.add(h22, 0, 25);
            root.add(h23, 0, 26);
            root.add(presenceAnomalie, 0, 27);
        }
        root.add(searchButton, 0, 28);
		
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
            saisieDonneesB.start(newStage);
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