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
import javafx.scene.control.Label;
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
        TextField idQuartier = new TextField("oui");
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

        // un label pour chaque textField
        Text idQuartierLabel = new Text("idQuartier");
        idQuartierLabel.setFill(Color.WHITE);
        Text nomQuartierLabel = new Text("nomQuartier");
        nomQuartierLabel.setFill(Color.WHITE);
        Text longueurPisteVeloLabel = new Text("longueurPisteVelo");
        longueurPisteVeloLabel.setFill(Color.WHITE);

        Text idCompteurLabel = new Text("idCompteur");
        idCompteurLabel.setFill(Color.WHITE);
        Text nomCompteurLabel = new Text("nomCompteur");
        nomCompteurLabel.setFill(Color.WHITE);
        Text sensLabel = new Text("sens");
        sensLabel.setFill(Color.WHITE);
        Text coord_XLabel = new Text("coord_X");
        coord_XLabel.setFill(Color.WHITE);
        Text coord_YLabel = new Text("coord_Y");
        coord_YLabel.setFill(Color.WHITE);
        Text leQuartierLabel = new Text("leQuartier");
        leQuartierLabel.setFill(Color.WHITE);

        Text laDateLabel = new Text("laDate");
        laDateLabel.setFill(Color.WHITE);
        Text tempMoyLabel = new Text("tempMoy");
        tempMoyLabel.setFill(Color.WHITE);
        Text jourLabel = new Text("jour");
        jourLabel.setFill(Color.WHITE);
        Text vacancesLabel = new Text("vacances");
        vacancesLabel.setFill(Color.WHITE);

        Text leCompteurLabel = new Text("leCompteur");
        leCompteurLabel.setFill(Color.WHITE);
        Text dateComptageLabel = new Text("dateComptage");
        dateComptageLabel.setFill(Color.WHITE);
        Text h00Label = new Text("h00");
        h00Label.setFill(Color.WHITE);
        Text h01Label = new Text("h01");
        h01Label.setFill(Color.WHITE);
        Text h02Label = new Text("h02");
        h02Label.setFill(Color.WHITE);
        Text h03Label = new Text("h03");
        h03Label.setFill(Color.WHITE);
        Text h04Label = new Text("h04");
        h04Label.setFill(Color.WHITE);
        Text h05Label = new Text("h05");
        h05Label.setFill(Color.WHITE);
        Text h06Label = new Text("h06");
        h06Label.setFill(Color.WHITE);
        Text h07Label = new Text("h07");
        h07Label.setFill(Color.WHITE);
        Text h08Label = new Text("h08");
        h08Label.setFill(Color.WHITE);
        Text h09Label = new Text("h09");
        h09Label.setFill(Color.WHITE);
        Text h10Label = new Text("h10");
        h10Label.setFill(Color.WHITE);
        Text h11Label = new Text("h11");
        h11Label.setFill(Color.WHITE);
        Text h12Label = new Text("h12");
        h12Label.setFill(Color.WHITE);
        Text h13Label = new Text("h13");
        h13Label.setFill(Color.WHITE);
        Text h14Label = new Text("h14");
        h14Label.setFill(Color.WHITE);
        Text h15Label = new Text("h15");
        h15Label.setFill(Color.WHITE);
        Text h16Label = new Text("h16");
        h16Label.setFill(Color.WHITE);
        Text h17Label = new Text("h17");
        h17Label.setFill(Color.WHITE);
        Text h18Label = new Text("h18");
        h18Label.setFill(Color.WHITE);
        Text h19Label = new Text("h19");
        h19Label.setFill(Color.WHITE);
        Text h20Label = new Text("h20");
        h20Label.setFill(Color.WHITE);
        Text h21Label = new Text("h21");
        h21Label.setFill(Color.WHITE);
        Text h22Label = new Text("h22");
        h22Label.setFill(Color.WHITE);
        Text h23Label = new Text("h23");
        h23Label.setFill(Color.WHITE);
        Text presenceAnomalieLabel = new Text("presenceAnomalie");
        presenceAnomalieLabel.setFill(Color.WHITE);


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
        

        tables.setOnAction(event -> {
            root.getChildren().removeAll(
                idQuartier, nomQuartier, longueurPisteVelo,
                idCompteur, nomCompteur, sens, coord_X, coord_Y, leQuartier,
                laDate, tempMoy, jour, vacances,
                leCompteur, dateComptage, h00, h01, h02, h03, h04, h05, h06, h07, h08, h09, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21, h22, h23, presenceAnomalie,
                idQuartierLabel, nomQuartierLabel, longueurPisteVeloLabel,
                idCompteurLabel, nomCompteurLabel, sensLabel, coord_XLabel, coord_YLabel, leQuartierLabel,
                laDateLabel, tempMoyLabel, jourLabel, vacancesLabel,
                leCompteurLabel, dateComptageLabel, h00Label, h01Label, h02Label, h03Label, h04Label, h05Label, h06Label, h07Label, h08Label, h09Label, h10Label, h11Label, h12Label, h13Label, h14Label, h15Label, h16Label, h17Label, h18Label, h19Label, h20Label, h21Label, h22Label, h23Label, presenceAnomalieLabel
            );

            if (actualTable.equals("Quartier")) {
                root.add(idQuartierLabel, 0, 1);
                root.add(nomQuartierLabel, 0, 2);
                root.add(longueurPisteVeloLabel, 0, 3);
                root.add(idQuartier, 1, 1);
                root.add(nomQuartier, 1, 2);
                root.add(longueurPisteVelo, 1, 3);
                root.add(searchButton, 1, 4);

            } else if (actualTable.equals("Compteur")) {
                root.add(idCompteurLabel, 0, 1);
                root.add(nomCompteurLabel, 0, 2);
                root.add(sensLabel, 0, 3);
                root.add(coord_XLabel, 0, 4);
                root.add(coord_YLabel, 0, 5);
                root.add(leQuartierLabel, 0, 6);
                root.add(idCompteur, 1, 1);
                root.add(nomCompteur, 1, 2);
                root.add(sens, 1, 3);
                root.add(coord_X, 1, 4);
                root.add(coord_Y, 1, 5);
                root.add(leQuartier, 1, 6);
                root.add(searchButton, 1, 7);

            } else if (actualTable.equals("DateInfo")) {
                root.add(laDateLabel, 0, 1);
                root.add(tempMoyLabel, 0, 2);
                root.add(jourLabel, 0, 3);
                root.add(vacancesLabel, 0, 4);
                root.add(laDate, 1, 1);
                root.add(tempMoy, 1, 2);
                root.add(jour, 1, 3);
                root.add(vacances, 1, 4);
                root.add(searchButton, 1, 5);

            } else if (actualTable.equals("Comptage")) {
                root.add(leCompteurLabel, 0, 1);
                root.add(dateComptageLabel, 0, 2);
                root.add(h00Label, 0, 3);
                root.add(h01Label, 0, 4);
                root.add(h02Label, 0, 5);
                root.add(h03Label, 0, 6);
                root.add(h04Label, 0, 7);
               
                root.add(leCompteur, 1, 1);
                root.add(dateComptage, 1, 2);
                root.add(h00, 1, 3);
                root.add(h01, 1, 4);
                root.add(h02, 1, 5);
                root.add(h03, 1, 6);
                root.add(h04, 1, 7);

                root.add(h05Label, 2, 1);
                root.add(h06Label, 2, 2);
                root.add(h07Label, 2, 3);
                root.add(h08Label, 2, 4);
                root.add(h09Label, 2, 5);
                root.add(h10Label, 2, 6);
                root.add(h11Label, 2, 7);
                
                root.add(h05, 3, 1);
                root.add(h06, 3, 2);
                root.add(h07, 3, 3);
                root.add(h08, 3, 4);
                root.add(h09, 3, 5);
                root.add(h10, 3, 6);
                root.add(h11, 3, 7);

                root.add(h12Label, 4, 1);
                root.add(h13Label, 4, 2);
                root.add(h14Label, 4, 3);
                root.add(h15Label, 4, 4);
                root.add(h16Label, 4, 5);
                root.add(h17Label, 4, 6);
                root.add(h18Label, 4, 7);
                
                root.add(h12, 5, 1);
                root.add(h13, 5, 2);
                root.add(h14, 5, 3);
                root.add(h15, 5, 4);
                root.add(h16, 5, 5);
                root.add(h17, 5, 6);
                root.add(h18, 5, 7);

                root.add(h19Label, 6, 1);
                root.add(h20Label, 6, 2);
                root.add(h21Label, 6, 3);
                root.add(h22Label, 6, 4);
                root.add(h23Label, 6, 5);
                root.add(presenceAnomalieLabel, 6, 6);

                root.add(h19, 7, 1);
                root.add(h20, 7, 2);
                root.add(h21, 7, 3);
                root.add(h22, 7, 4);
                root.add(h23, 7, 5);
                root.add(presenceAnomalie, 7, 6);
                root.add(searchButton, 7, 7);
            }
            
        });

        
		
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