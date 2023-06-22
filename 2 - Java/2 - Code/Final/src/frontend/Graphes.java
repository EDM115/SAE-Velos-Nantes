package frontend;

import java.io.File;
import java.net.MalformedURLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.ArrayList;
import utils.StageDump;
import utils.TitleBar;
import utils.WindowDrag;
import backend.GraphesB;

public class Graphes extends Application {

    private StageDump stageDump = new StageDump();
    private WindowDrag windowDrag;
    ArrayList<String> imagePaths = new ArrayList<>();
    ArrayList<String> labels = new ArrayList<>();
    Stage newStage;

    @Override
    public void start(Stage primaryStage) {
        newStage = stageDump.dump(primaryStage);
        GraphesB graphesB = new GraphesB(this);

        // initialize the image paths
        imagePaths.add("res/images/graphes/grapheDeg.png");
        imagePaths.add("res/images/graphes/grapheDeg1.png");
        imagePaths.add("res/images/graphes/grapheDist.png");
        imagePaths.add("res/images/graphes/grapheDist1.png");
        imagePaths.add("res/images/graphes/grapheExentricité.png");
        imagePaths.add("res/images/graphes/grapheExentricité1.png");
        imagePaths.add("res/images/graphes/grapheTemps.png");
        imagePaths.add("res/images/graphes/grapheTemps1.png");

        // initialize the labels
        labels.add("Degré 1");
        labels.add("Degré 2");
        labels.add("Distance 1");
        labels.add("Distance 2");
        labels.add("Exentricité 1");
        labels.add("Exentricité 2");
        labels.add("Temps 1");
        labels.add("Temps 2");

        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Graphes");

        // Create the GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));

        // Add images and labels to the GridPane
        for (int i = 0; i < imagePaths.size(); i++) {
            String imagePath = imagePaths.get(i);
            try {
                Image image = new Image(new File(imagePath).toURI().toURL().toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250);
                imageView.setPreserveRatio(true);

                String labelValue = labels.get(i);
                Label label = new Label(labelValue);
				label.setAlignment(Pos.CENTER);
                label.setStyle("-fx-text-fill: #f8f8f2; -fx-font-size: 14px;");

                VBox vbox = new VBox(10);
				vbox.setAlignment(Pos.CENTER);
				vbox.getChildren().addAll(imageView, label);

				GridPane.setRowIndex(vbox, i / 4);
				GridPane.setColumnIndex(vbox, i % 4);
				gridPane.getChildren().add(vbox);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        // Create the root layout
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(gridPane);

        windowDrag = new WindowDrag(root, newStage);

        // Add event listeners
        menuButton.setOnMouseClicked(event -> {
            graphesB.start(newStage);
        });

        // Customize stage
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setTitle("Recherche d'affluence");

        // Create the scene
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        scene.setFill(Color.TRANSPARENT);

        // Apply the CSS styling
        try {
            scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Set the stage properties
        newStage.setScene(scene);
        newStage.setTitle("Graphes");
        newStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
