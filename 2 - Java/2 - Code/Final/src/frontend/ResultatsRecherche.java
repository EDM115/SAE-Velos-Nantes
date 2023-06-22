package frontend;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
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
    
    private String dist = "";
    Label distLabel = new Label(dist);
    private String time = "";
    Label timeLabel = new Label(time);

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

        BorderPane root = new BorderPane();

        if (trajet) {

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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

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


            webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == State.SUCCEEDED) {
                    String currentUrl = webEngine.getLocation();

                    if (currentUrl.startsWith("https://consent.google.com/m?continue=")) {
                        // Simulate a click on the element with class "VfPpkd-RLmnJb"
                        webEngine.executeScript("document.querySelector('button[aria-label=\\\"Tout refuser\\\"]').click()");
                    } else if (currentUrl.startsWith("https://www.google.com/maps/dir/")) {
                        // Retrieve duration and distance values
                        String pageContent = (String) webEngine.executeScript("document.documentElement.outerHTML");

                        int durationStartIndex = pageContent.indexOf("<div class=\"Fk3sm fontHeadlineSmall\">") + "<div class=\"Fk3sm fontHeadlineSmall\">".length();
                        int durationEndIndex = pageContent.indexOf("</div>", durationStartIndex);
                        String durationValue = pageContent.substring(durationStartIndex, durationEndIndex).trim();

                        int distanceStartIndex = pageContent.indexOf("<div class=\"ivN21e tUEI8e fontBodyMedium\">") + "<div class=\"ivN21e tUEI8e fontBodyMedium\">".length();
                        int distanceEndIndex = pageContent.indexOf("</div>", distanceStartIndex);
                        String distanceValue = pageContent.substring(distanceStartIndex, distanceEndIndex).trim();

                        // Update the variables
                        dist = distanceValue.replace("&nbsp;", " ");
                        time = durationValue.replace("&nbsp;", " ");

                        // Update the label texts on the JavaFX application thread
                        Platform.runLater(() -> {
                            distLabel.setText(dist);
                            timeLabel.setText(time);
                        });
                    }
                }
            });

            String url = "https://www.google.com/maps/dir/?api=1&origin=" + departureLat + "," + departureLon + "&destination=" + arrivalLat + "," + arrivalLon + "&travelmode=bicycling&mode=dark&hl=fr";
            webEngine.load(url);

            VBox leftPane = new VBox(webView);
            leftPane.setAlignment(Pos.CENTER);

            // Create the right side (Titles)
            VBox rightPane = new VBox(10);
            rightPane.setAlignment(Pos.TOP_LEFT);
            rightPane.setPadding(new Insets(10));

            // Présence d'anomalies ?
            Label anomaliesLabel = new Label("Présence d'anomalies ?");
            anomaliesLabel.setStyle("-fx-font-weight: bold;");
            rightPane.getChildren().add(anomaliesLabel);
            try {
                rightPane.getChildren().add(new Label(search.get(13)));
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
            rightPane.getChildren().add(distLabel);

            // Durée estimée :
            Label dureeLabel = new Label("Durée estimée :");
            dureeLabel.setStyle("-fx-font-weight: bold;");
            rightPane.getChildren().add(dureeLabel);
            rightPane.getChildren().add(timeLabel);

            // Create the root layout
            root = new BorderPane();
            root.setTop(titleBar);
            root.setBottom(stringLabel);
            root.setLeft(leftPane);
            root.setRight(rightPane);

        } else {

            // used later
            String compteurLat = "";
            String compteurLon = "";

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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            compteurLat = search.get(2);
            compteurLon = search.get(3);

            // Create the string label
            Label stringLabel = new Label(String.join(" • ", search));
            stringLabel.setStyle("-fx-font-weight: bold;");

            // Create the left side (Google Maps integration)
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();


            webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == State.SUCCEEDED) {
                    String currentUrl = webEngine.getLocation();

                    if (currentUrl.startsWith("https://consent.google.com/m?continue=")) {
                        // Simulate a click on the element with the "Tout refuser" aria-label
                        webEngine.executeScript("document.querySelector('button[aria-label=\\\"Tout refuser\\\"]').click()");
                    }
                }
            });

            String url = "https://www.google.com/maps/search/?api=1&query=" + compteurLat + "," + compteurLon + "&travelmode=bicycling&mode=dark&hl=fr";
            webEngine.load(url);

            VBox leftPane = new VBox(webView);
            leftPane.setAlignment(Pos.CENTER);

            // Create the right side (Titles)
            VBox rightPane = new VBox(10);
            rightPane.setAlignment(Pos.TOP_LEFT);
            rightPane.setPadding(new Insets(10));

            // Affluence estimée :
            Label affluenceLabel = new Label("Affluence estimée :");
            affluenceLabel.setStyle("-fx-font-weight: bold;");
            rightPane.getChildren().add(affluenceLabel);
            rightPane.getChildren().add(new Label(search.get(6) + " cyclistes le long du parcours"));

            // Create the root layout
            root.setTop(titleBar);
            root.setBottom(stringLabel);
            root.setLeft(leftPane);
            root.setRight(rightPane);
            
        }

        

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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
