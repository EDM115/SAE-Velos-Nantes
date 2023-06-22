package backend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import utils.GlobalVar;

import frontend.*;

/**
 * The MenuB class, backend of the menu window
 */
public class MenuB {

    /**
     * The menu object
     */
    private Menu menu;

    /**
     * The constructor
     * @param menu the menu
     */
    public MenuB(Menu menu) {
        this.menu = menu;
        setupButtonActions();
    }

    /**
     * Set up the button actions
     */
    private void setupButtonActions() {
        menu.getHomeButton().setOnAction(event -> {
            Accueil accueil = new Accueil();
            accueil.setFirstTime(false);
            accueil.start(this.menu.getPreviousStage());
            menu.hide();
        });

        menu.getOption1().setOnAction(event -> {
            Stage popup = showSpinnerPopup();
            popup.show();
            RechercheTrajet rechercheTrajet = new RechercheTrajet(popup);
            Platform.runLater(() -> {
                rechercheTrajet.start(this.menu.getPreviousStage());
                menu.hide();
            });
        });

        menu.getOption2().setOnAction(event -> {
            Stage popup = showSpinnerPopup();
            popup.show();
            RechercheAffluence rechercheAffluence = new RechercheAffluence(popup);
            Platform.runLater(() -> {
                rechercheAffluence.start(this.menu.getPreviousStage());
                menu.hide();
            });
        });

        menu.getOption3().setOnAction(event -> {
            Stage popup = showSpinnerPopup();
            popup.show();
            StationProche stationProche = new StationProche(popup);
            Platform.runLater(() -> {
                stationProche.start(this.menu.getPreviousStage());
                menu.hide();
            });
        });

        menu.getAddDataButton().setOnAction(event -> {
            GlobalVar globalVar = new GlobalVar();
            if (globalVar.isAdmin()) {
                Stage popup = showSpinnerPopup();
                popup.show();
                SaisieDonnees saisieDonnees = new SaisieDonnees(popup);
                Platform.runLater(() -> {
                    saisieDonnees.start(this.menu.getPreviousStage());
                    menu.hide();
                });
            } else {
                showError(" Erreur : vous n'êtes pas administrateur");
            }
        });
        
        menu.getGraph().setOnAction(event -> {
            Graphes graphes = new Graphes();
            graphes.start(this.menu.getPreviousStage());
            menu.hide();
        });

        menu.getEditDataButton().setOnAction(event -> {
            GlobalVar globalVar = new GlobalVar();
            if (globalVar.isAdmin()) {
                Stage popup = showSpinnerPopup();
                popup.show();
                ModificationDonnees modificationDonnees = new ModificationDonnees(popup);
                Platform.runLater(() -> {
                    modificationDonnees.start(this.menu.getPreviousStage());
                    menu.hide();
                });
            } else {
                showError(" Erreur : vous n'êtes pas administrateur");
            }
        }); 
    }

    /**
     * Show the error popup
     * @param errorMessage the error message to display
     */
    private void showError(String errorMessage) {
        // Create the error message text
        Text errorText = new Text(errorMessage);
        errorText.setFill(Color.web("#ff5555"));
        errorText.setFont(Font.font("Roboto-Regular", FontWeight.BOLD, 16));

        // Create the close button
        JFXButton closeButton = new JFXButton("✕");
        closeButton.setTextFill(Color.web("#ff5555"));
        closeButton.setStyle("-fx-background-color: transparent;");
        closeButton.setButtonType(JFXButton.ButtonType.FLAT);
        closeButton.setFont(Font.font("Roboto-Regular", FontWeight.BOLD, 16));

        // Create the error pane and add the text and close button
        BorderPane errorPane = new BorderPane();
        errorPane.setBackground(new Background(new BackgroundFill(Color.web("#282a36"), CornerRadii.EMPTY, Insets.EMPTY)));
        errorPane.setPadding(new Insets(20));
        errorPane.setLeft(closeButton);
        errorPane.setCenter(errorText);

        // Create the root pane to hold the error pane
        StackPane rootPane = new StackPane(errorPane);
        rootPane.setBorder(new Border(new BorderStroke(Color.web("#44475a"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        // Create the error stage and scene
        Stage errorStage = new Stage();
        errorStage.initOwner(menu.getStage());
        errorStage.initStyle(StageStyle.UNDECORATED);
        errorStage.initModality(Modality.APPLICATION_MODAL);
        errorStage.setScene(new Scene(rootPane));

        // Center the error stage on the screen
        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        double stageWidth = errorPane.prefWidth(-1) + errorStage.getScene().getX();
        double stageHeight = errorPane.prefHeight(stageWidth) + errorStage.getScene().getY();
        errorStage.setX((screenWidth - stageWidth) / 2);
        errorStage.setY((screenHeight - stageHeight) / 2);

        // Close the error stage when the close button is clicked
        closeButton.setOnAction(event -> errorStage.close());

        errorStage.showAndWait();
    }

    /**
     * Show the spinner popup
     * @return the stage of the popup
     */
    public Stage showSpinnerPopup() {
        // Create the spinner
        JFXSpinner spinner = new JFXSpinner();
        spinner.setStyle("-fx-progress-color: #f8f8f2;");
        spinner.setRadius(20);

        // Create the text
        Text text = new Text("Connexion à la base de données");
        text.setFill(Color.web("#f8f8f2"));
        text.setFont(Font.font("Roboto-Regular", FontWeight.BOLD, 12));

        // Create the popup pane and add the spinner and text
        BorderPane popupPane = new BorderPane();
        popupPane.setBackground(new Background(new BackgroundFill(Color.web("#282a36"), CornerRadii.EMPTY, Insets.EMPTY)));
        popupPane.setCenter(spinner);
        popupPane.setLeft(text);
        BorderPane.setAlignment(text, Pos.CENTER_LEFT);
        BorderPane.setMargin(text, new javafx.geometry.Insets(0, 10, 0, 10));

        // Create the popup stage and scene
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initStyle(StageStyle.UNDECORATED);
        popupStage.setScene(new Scene(popupPane));

        // Center the popup stage on the screen
        popupStage.centerOnScreen();

        return popupStage;
    }

}
