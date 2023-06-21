package frontend;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import utils.StageDump;
import utils.TitleBar;
import utils.WindowDrag;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import backend.ResultatsRechercheB;

public class ResultatsRecherche extends Application {

    private StageDump stageDump = new StageDump();
    private boolean trajet;
    private File data;
    private WindowDrag windowDrag;
    private ResultatsRechercheB resultatsRechercheB = new ResultatsRechercheB();

    public ResultatsRecherche(boolean trajet, File data) {
        this.trajet = trajet;
        this.data = data;
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

        // used later
        String departureLat = "";
        String departureLon = "";
        String arrivalLat = "";
        String arrivalLon = "";

        // read the JSON file and get its content this way :
        // for property in the file, add its value to the List<String> search

        // Read the JSON file
        List<String> search = new ArrayList<>();

        // Read the JSON file
        try (Reader reader = new FileReader("res/data.json")) {
            // Parse the JSON file into a JsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);

            // Convert the JsonElement to a JsonObject
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Get the property values and add them to the List<String>
            for (String key : jsonObject.keySet()) {
                String value = jsonObject.get(key).getAsString();
                search.add(value);
                System.out.println(key + " : " + value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // add the departure and arrival coordinates to their respective variables from the json
        /* try (Reader reader = new FileReader("res/data.json")) {
            // Parse the JSON file into a JsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);

            // Convert the JsonElement to a JsonObject
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Get the property values and add them to the List<String>
            for (String key : jsonObject.keySet()) {
                String value = jsonObject.get(key).getAsString();
                if (key.equals("departureLat")) {
                                        departureLat = Double.parseDouble(value);
                } else if (key.equals("departureLon")) {
                    departureLon = Double.parseDouble(value);
                } else if (key.equals("arrivalLat")) {
                    arrivalLat = Double.parseDouble(value);
                } else if (key.equals("arrivalLon")) {
                    arrivalLon = Double.parseDouble(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } */

        departureLat = search.get(2);
        departureLon = search.get(3);
        arrivalLat = search.get(6);
        arrivalLon = search.get(7);

        // Create the string label
        Label stringLabel = new Label(String.join(" • ", search));
        stringLabel.setStyle("-fx-font-weight: bold;");

        // Create the left side (Google Maps integration)
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        String url = "https://www.google.com/maps/dir/?api=1&origin=" + departureLat + "," + departureLon + "&destination=" + arrivalLat + "," + arrivalLon + "&travelmode=bicycling";
        webEngine.load(url);

        VBox leftPane = new VBox(webView);
        leftPane.setAlignment(Pos.CENTER);

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

        // Update the right side with dynamic content
        rightPane.getChildren().clear();

        // Présence d'anomalies ?
        Label anomaliesLabel = new Label("Présence d'anomalies ?");
        anomaliesLabel.setStyle("-fx-font-weight: bold;");
        rightPane.getChildren().add(anomaliesLabel);
        try {
            rightPane.getChildren().add(new Label(search.get(10)));
        } catch (IndexOutOfBoundsException e) {
            rightPane.getChildren().add(new Label("Aucune"));
        }

        // Affluence estimée :
        Label affluenceLabel = new Label("Affluence estimée :");
        affluenceLabel.setStyle("-fx-font-weight: bold;");
        rightPane.getChildren().add(affluenceLabel);
        rightPane.getChildren().add(new Label(search.get(8) + " cyclistes le long du parcours"));

        // Température :
        Label temperatureLabel = new Label("Température :");
        temperatureLabel.setStyle("-fx-font-weight: bold;");
        rightPane.getChildren().add(temperatureLabel);
        rightPane.getChildren().add(new Label(search.get(9) + "°C"));

        // Distance :
        Label distanceLabel = new Label("Distance :");
        distanceLabel.setStyle("-fx-font-weight: bold;");
        rightPane.getChildren().add(distanceLabel);
        // Replace "dist" with your actual variable for distance
        double dist = 10.5;
        rightPane.getChildren().add(new Label(String.valueOf(dist) + " m"));

        // Durée estimée :
        Label dureeLabel = new Label("Durée estimée :");
        dureeLabel.setStyle("-fx-font-weight: bold;");
        rightPane.getChildren().add(dureeLabel);
        // Replace "time" with your actual variable for duration
        int time = 45;
        rightPane.getChildren().add(new Label(String.valueOf(time)));

        // Create the root layout
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setBottom(stringLabel);
        root.setLeft(leftPane);
        root.setRight(rightPane);

        // Scroll pane for the right side (Titles)
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 800, 600, Color.WHITE);
        newStage.setScene(scene);
        newStage.setTitle("Résultats de recherche");

        windowDrag = new WindowDrag(root, newStage);

        menuButton.setOnMouseClicked(event -> {
            resultatsRechercheB.start(newStage);
        });

        // Apply the CSS styling
        try {
            scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Show the stage
        newStage.show();

        // show the url every time it changes
        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
