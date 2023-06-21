package frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.StageDump;
import utils.TitleBar;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class ResultatsRecherche extends Application {

    private StageDump stageDump = new StageDump();
	private boolean trajet;
    private String[] search;

	public ResultatsRecherche(boolean trajet, String[] search) {
		this.trajet = trajet;
		this.search = search;
	}

    @Override
    public void start(Stage primaryStage) {
        // copy primaryStage to newStage (new object, not a reference)
        Stage newStage = stageDump.dump(primaryStage);
        // Create the title bar
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Recherche d'affluence");

        // Create the string label
        Label stringLabel = new Label(String.join(" • ", search));
        stringLabel.setStyle("-fx-font-weight: bold;");

        // Create the left side (Google Maps integration)
        Pane leftPane = new Pane(); // Placeholder for Google Maps integration

        // Create the right side (Titles)
        VBox rightPane = new VBox(10);
        rightPane.setAlignment(Pos.TOP_LEFT);
        rightPane.setPadding(new Insets(10));

        if (trajet) {
            // Placeholder for 10 titles
            for (int i = 1; i <= 10; i++) {
                Label titleLabel = new Label("Title " + i);
                rightPane.getChildren().add(titleLabel);
            }
        } else {
            // Placeholder for 2 titles
            for (int i = 1; i <= 2; i++) {
                Label titleLabel = new Label("Title " + i);
                rightPane.getChildren().add(titleLabel);
            }
        }

        // Create the root layout
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setLeft(leftPane);
        root.setRight(rightPane);

        // Scroll pane for the right side (Titles)
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 800, 600, Color.WHITE);
        newStage.setScene(scene);
        newStage.setTitle("Résultats de recherche");

        // Apply the CSS styling
        try {
			scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

        // Show the stage
        newStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
