package frontend;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
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

import backend.ResultatsRechercheB;
import utils.StageDump;
import utils.TitleBar;
import utils.WindowDrag;

/**
 * The ResultatsRecherche class, allows to create the search results window
 */
public class ResultatsRecherche extends Application {
    
    /**
     * The stage dump
     */
    private StageDump stageDump = new StageDump();

    /**
     * The boolean trajet, true if the user wants to search for a route, false if he wants to search for a crowd
     */ 
    private boolean trajet;

    /**
     * The data file
     */
    private File data;

    /**
     * The window drag
     */
    private WindowDrag windowDrag;

    /**
     * The resultatsRechercheB object, used to call the backend methods
     */
    private ResultatsRechercheB resultatsRechercheB = new ResultatsRechercheB();
    
    /**
     * The dist string
     */
    private String dist = "";

    /**
     * The dist label
     */
    private Label distLabel = new Label(dist);

    /**
     * The time string
     */
    private String time = "";

    /**
     * The time label
     */
    private Label timeLabel = new Label(time);

    /**
     * The constructor of ResultatsRecherche
     * @param trajet true if the user wants to search for a route, false if he wants to search for a crowd
     * @param data the data file
     */
    public ResultatsRecherche(boolean trajet, File data) {
        this.trajet = trajet;
        this.data = data;
    }

    /**
     * The start method, called when the window is created
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        Stage newStage = stageDump.dump(primaryStage);
        // Create the title bar
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Résultats de recherche");

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #282a36;");
        Label stringLabel = new Label("");
        VBox leftPane;
        VBox rightPane;

        if (trajet) {
            String departureLat = "";
            String departureLon = "";
            String arrivalLat = "";
            String arrivalLon = "";
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
            stringLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2; -fx-font-size: 20px; -fx-padding: 0 0 0 20px;");
            stringLabel.setText(search.get(0) + " \u2192 " + search.get(4) + " • " + search.get(10) + " • " + search.get(11) + ":00");

            // Create the left side (Google Maps integration)
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();

            webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                // Wait for the page to load
                if (newValue == State.SUCCEEDED) {
                    String currentUrl = webEngine.getLocation();

                    // If it's the cookie consent page
                    if (currentUrl.startsWith("https://consent.google.com/m?continue=")) {
                        // Simulate a click on the element with the "Tout refuser" attribute
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

            // Build the string, and uses the compteur name if the coordinates are not available
            String url = "";
            if (departureLat.equals("0.0") || departureLon.equals("0.0") || arrivalLat.equals("0.0") || arrivalLon.equals("0.0")) {
                String origin = departureLat + "," + departureLon;
                String destination = arrivalLat + "," + arrivalLon;
                if (departureLat.equals("0.0") || departureLon.equals("0.0")) {
                    origin = search.get(0) + " Nantes";
                } else if (arrivalLat.equals("0.0") || arrivalLon.equals("0.0")) {
                    destination = search.get(4) + " Nantes";
                }
                url = "https://www.google.com/maps/dir/?api=1&origin=" + origin + "&destination=" + destination + "&travelmode=bicycling&mode=dark&hl=fr";
            } else {
                url = "https://www.google.com/maps/dir/?api=1&origin=" + departureLat + "," + departureLon + "&destination=" + arrivalLat + "," + arrivalLon + "&travelmode=bicycling&mode=dark&hl=fr";
            }
            webEngine.load(url);

            leftPane = new VBox(webView);
            leftPane.setAlignment(Pos.CENTER);

            // Create the right side (Titles)
            rightPane = new VBox(10);
            rightPane.setAlignment(Pos.TOP_LEFT);
            rightPane.setPadding(new Insets(10));

            Label anomaliesLabel = new Label("Présence d'anomalies ?");
            anomaliesLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(anomaliesLabel);
            try {
                Label temp = new Label(search.get(13));
                temp.setStyle("-fx-text-fill: #f8f8f2;");
                rightPane.getChildren().add(temp);
            } catch (IndexOutOfBoundsException e) {
                Label temp = new Label("Aucune");
                temp.setStyle("-fx-text-fill: #f8f8f2;");
                rightPane.getChildren().add(temp);
            }

            Label affluenceLabel = new Label("Affluence estimée :");
            affluenceLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(affluenceLabel);
            Label temp2 = new Label(search.get(8) + " cyclistes le long du parcours");
            temp2.setStyle("-fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(temp2);

            Label temperatureLabel = new Label("Température :");
            temperatureLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(temperatureLabel);
            Label temp3 = new Label(search.get(9) + "°C");
            temp3.setStyle("-fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(temp3);

            Label distanceLabel = new Label("Distance :");
            distanceLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(distanceLabel);
            distLabel.setStyle("-fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(distLabel);

            Label dureeLabel = new Label("Durée estimée :");
            dureeLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(dureeLabel);
            timeLabel.setStyle("-fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(timeLabel);

            // Create the root layout
            root.setAlignment(Pos.CENTER);
            root.setStyle("-fx-background-color: #282a36");
        } else {
            String compteurLat = "";
            String compteurLon = "";
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
            stringLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2; -fx-font-size: 20px; -fx-padding: 0 0 0 20px;");
            stringLabel.setText(search.get(0) + " • " + search.get(4) + " • " + search.get(5) + ":00");

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

            // in case the coordinates are not available (bad request, idk)
            String url = "";
            if (compteurLat.equals("0.0") || compteurLon.equals("0.0")) {
                url = "https://www.google.com/maps/search/?api=1&query=" + search.get(0) + " Nantes" + "&travelmode=bicycling&mode=dark&hl=fr";
            } else {
                url = "https://www.google.com/maps/search/?api=1&query=" + compteurLat + "," + compteurLon + "&travelmode=bicycling&mode=dark&hl=fr";
            }

            webEngine.load(url);

            leftPane = new VBox(webView);
            leftPane.setAlignment(Pos.CENTER);

            // Create the right side (Titles)
            rightPane = new VBox(10);
            rightPane.setAlignment(Pos.TOP_LEFT);
            rightPane.setPadding(new Insets(10));

            Label affluenceLabel = new Label("Affluence estimée :");
            affluenceLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(affluenceLabel);
            Label temp1 = new Label(search.get(6) + " cyclistes proches de la station");
            temp1.setStyle("-fx-text-fill: #f8f8f2;");
            rightPane.getChildren().add(temp1);

            // Create the root layout
            root.setAlignment(Pos.CENTER);
            root.setStyle("-fx-background-color: #282a36");
        }

        // Scroll pane for the right side (Titles)
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: radial-gradient(focus-angle 45deg, focus-distance 0%, center 100% 100%, radius 60%, #bd93f9 0%, rgba(40, 42, 54, 0.9) 70%, #282a36 100%)");

        Scene scene = new Scene(scrollPane, 800, 600, Color.web("#282a36"));
        scene.setFill(Color.TRANSPARENT);
        newStage.setScene(scene);
        newStage.setTitle("Résultats de recherche");

        root.getChildren().addAll(titleBar, stringLabel);

        HBox centerPane = new HBox();
        centerPane.setAlignment(Pos.CENTER);
        centerPane.getChildren().addAll(leftPane, rightPane);

        root.getChildren().add(centerPane);

        windowDrag = new WindowDrag(root, newStage);

        menuButton.setOnMouseClicked(event -> {
            resultatsRechercheB.start(newStage);
        });

        // Show the stage
        newStage.show();
    }

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
