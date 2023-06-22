package frontend;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import backend.ModificationDonneesB;
import utils.TitleBar;
import utils.StageDump;
import utils.WindowDrag;

import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;

public class ModificationDonnees extends Application {
    
    String actualTable = "";
    String actualColumn = "";
    String actualCle = "";
    String actualCle2 = "";
    int num = 0;
	private StageDump stageDump = new StageDump();
    private WindowDrag windowDrag;
    Stage popup;

    Label editedData = new Label();

    public ModificationDonnees(Stage popup) {
        this.popup = popup;
    }

    @Override
    public void start(Stage primaryStage) {
		// copy primaryStage to newStage (new object, not a reference)
		Stage newStage = stageDump.dump(primaryStage);
        // Import stuff
		ModificationDonneesB modificationDonneesB = new ModificationDonneesB(this);
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        modificationDonneesB.lesTablesBdd();
        ArrayList<String> lesTables = modificationDonneesB.getLesTables();

        modificationDonneesB.lesColonnesQuartier();
        ArrayList<String> lesColonnesQuartier = modificationDonneesB.getColonnesQuartier();

        modificationDonneesB.lesColonnesCompteur();
        ArrayList<String> lesColonnesCompteur = modificationDonneesB.getColonnesCompteur();

        modificationDonneesB.lesColonnesDateInfo();
        ArrayList<String> lesColonnesDateInfo = modificationDonneesB.getColonnesDateInfo();

        modificationDonneesB.lesColonnesComptage();
        ArrayList<String> lesColonnesComptage = modificationDonneesB.getColonnesComptage();

        modificationDonneesB.clePrimaireQuartier();
        ArrayList<String> lesClesPrimaireQuartier = modificationDonneesB.getClePrimaireQuartier();

        modificationDonneesB.clePrimaireCompteur();
        ArrayList<String> lesClesPrimaireCompteur = modificationDonneesB.getClePrimaireCompteur();

        modificationDonneesB.clePrimaireDateInfo();

        modificationDonneesB.clePrimaireComptage1();
        ArrayList<String> lesClesPrimaireComptage1 = modificationDonneesB.getClePrimaireComptage1();

        modificationDonneesB.clePrimaireComptage2();

        Platform.runLater(() -> {
            this.popup.close();
        });

        
        // Create UI components
        

        // un label pour chaque textField
        ComboBox<String> clePrimaireQuartier = new ComboBox<>();
        for (String cle : lesClesPrimaireQuartier) {
            clePrimaireQuartier.getItems().add(cle);
        }

        ComboBox<String> clePrimaireCompteur = new ComboBox<>();
        for (String cle : lesClesPrimaireCompteur) {
            clePrimaireCompteur.getItems().add(cle);
        }

        DatePicker clePrimaireDateInfo = new DatePicker();

        ComboBox<String> clePrimaireComptage1 = new ComboBox<>();
        for (String cle : lesClesPrimaireComptage1) {
            clePrimaireComptage1.getItems().add(cle);
        }

        DatePicker clePrimaireComptage2 = new DatePicker();
       
        ComboBox<String> colonnesQuartier = new ComboBox<>();
        for (String colonne : lesColonnesQuartier) {
            colonnesQuartier.getItems().add(colonne);
        }

        ComboBox<String> colonnesCompteur = new ComboBox<>();
        for (String colonne : lesColonnesCompteur) {
            colonnesCompteur.getItems().add(colonne);
        }

        ComboBox<String> colonnesDateInfo = new ComboBox<>();
        for (String colonne : lesColonnesDateInfo) {
            colonnesDateInfo.getItems().add(colonne);
        }

        ComboBox<String> colonnesComptage = new ComboBox<>();
        for (String colonne : lesColonnesComptage) {
            colonnesComptage.getItems().add(colonne);
        }

        ComboBox<String> tables = new ComboBox<>();
        for (String laTable : lesTables) {
            tables.getItems().add(laTable);
        }


        TextField newValeur = new TextField();
        
        Button searchButton = new Button("AJOUTER");
        searchButton.setOnAction(event -> {
            if (num == 1) {
                String colonne = colonnesQuartier.getValue();
                int clePrimaire = Integer.parseInt(clePrimaireQuartier.getValue());
                String valeur = newValeur.getText();
                modificationDonneesB.modifQuartier(colonne, clePrimaire, valeur);  
            } else if (num == 2) {
                String colonne = colonnesCompteur.getValue();
                int clePrimaire = Integer.parseInt(clePrimaireCompteur.getValue());
                String valeur = newValeur.getText();
                modificationDonneesB.modifCompteur(colonne, clePrimaire, valeur);

            } else if (num == 3) {
                String colonne = colonnesDateInfo.getValue();
                LocalDate clePrimaire = clePrimaireDateInfo.getValue();
                String valeur = newValeur.getText();
                System.out.println(colonne + " " + clePrimaire + " " + valeur);
                modificationDonneesB.modifDateInfo(colonne, clePrimaire, valeur);

            } else if (num == 4) {
                String colonne = colonnesComptage.getValue();
                int clePrimaire1 = Integer.parseInt(clePrimaireComptage1.getValue());
                LocalDate clePrimaire2 = clePrimaireComptage2.getValue();
                String valeur = newValeur.getText();
                modificationDonneesB.modifComptage(colonne, clePrimaire1, clePrimaire2, valeur);

            }
        });
        searchButton.setGraphic(createIcon("res/images/search_cl.png"));
        searchButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        searchButton.setStyle("-fx-background-color: #8be9fd; -fx-text-fill: #44475a;");

        editedData.setTextFill(Color.web("#f8f8f2"));
        editedData.setFont(Font.font("Roboto", FontWeight.BOLD, 14));

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

        Text colonneLabel = new Text("Colonne à modifier");
        colonneLabel.setFill(Color.WHITE);

        Text clePrimaireLabel = new Text("Clé primaire");
        clePrimaireLabel.setFill(Color.WHITE);

        Text newValeurLabel = new Text("Nouvelle valeur");
        newValeurLabel.setFill(Color.WHITE);


        

		// Add components to the grid
        root.add(tableLabel, 0, 0);
        root.add(tables, 1, 0);
        root.add(editedData, 1, 8);
        //String actualTable = tables.getValue();
        tables.valueProperty().addListener((observable, oldValue, newValue) -> {
            actualTable = newValue;
        });

        tables.setOnAction(event -> {
            root.getChildren().removeAll(
                colonnesQuartier, colonnesCompteur, colonnesDateInfo, colonnesComptage,
                clePrimaireQuartier, clePrimaireCompteur, clePrimaireDateInfo, clePrimaireComptage1, clePrimaireComptage2,
                colonneLabel, clePrimaireLabel, searchButton,
                newValeurLabel, newValeur
            );

            if (actualTable.equals("Quartier")) {
                num = 1;
                root.add(colonneLabel, 0, 1);
                root.add(colonnesQuartier, 1, 1);
                root.add(clePrimaireLabel, 0, 2);
                root.add(clePrimaireQuartier, 1, 2);
                root.add(newValeurLabel, 0, 3);
                root.add(newValeur, 1, 3);
                root.add(searchButton, 1, 4);


            } else if (actualTable.equals("Compteur")) {
                num = 2;
                root.add(colonneLabel, 0, 1);
                root.add(colonnesCompteur, 1, 1);
                root.add(clePrimaireLabel, 0, 2);
                root.add(clePrimaireCompteur, 1, 2);
                root.add(newValeurLabel, 0, 3);
                root.add(newValeur, 1, 3);
                root.add(searchButton, 1, 4);



            } else if (actualTable.equals("DateInfo")) {
                num = 3;
                root.add(colonneLabel, 0, 1);
                root.add(colonnesDateInfo, 1, 1);
                root.add(clePrimaireLabel, 0, 2);
                root.add(clePrimaireDateInfo, 1, 2);
                root.add(newValeurLabel, 0, 3);
                root.add(newValeur, 1, 3);
                root.add(searchButton, 1, 4);


            } else if (actualTable.equals("Comptage")) {
                num = 4;
                root.add(colonneLabel, 0, 1);
                root.add(colonnesComptage, 1, 1);
                root.add(clePrimaireLabel, 0, 2);
                root.add(clePrimaireComptage1, 1, 2);
                root.add(clePrimaireComptage2, 1, 3);
                root.add(newValeurLabel, 0, 4);
                root.add(newValeur, 1, 4);
                root.add(searchButton, 1, 5);

            }
            
        });

        
		
        // Create the title bar
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Modification de données");
		
        // Create the root pane
        BorderPane rootPane = new BorderPane();
        rootPane.setTop(titleBar);
        rootPane.setCenter(root);
		
        windowDrag = new WindowDrag(rootPane, newStage);

		// Add event listeners
        menuButton.setOnMouseClicked(event -> {
            modificationDonneesB.start(newStage);
        });

		// Customize stage
		newStage.initStyle(StageStyle.UNDECORATED);
		newStage.setTitle("Modification de données");
		
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

    public void setEditedData(String editedData) {
        this.editedData.setText(editedData);
    }
}