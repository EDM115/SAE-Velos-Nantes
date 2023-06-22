package frontend;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import com.jfoenix.controls.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.*;

import backend.AccueilB;
import utils.StageDump;
import utils.TitleBar;
import utils.WindowDrag;

public class Accueil extends Application {
    private AccueilB accueilB;
    private StageDump stageDump = new StageDump();
    private boolean firstTime = true;
    private WindowDrag windowDrag;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Stage newStage;
        // Import stuff
        accueilB = new AccueilB();
        TitleBar titleBarElement = new TitleBar();
        if (firstTime) {
            newStage = primaryStage;
        } else {
            newStage = stageDump.dump(primaryStage);
        }

        // Create UI components
        JFXHamburger menuButton = new JFXHamburger();
        ImageView logoImage = new ImageView(new Image("file:res/images/nantes_white_smaller.png"));        
        // Load the Roboto font files
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Bold.ttf.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Light.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Thin.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto_Mono/static/RobotoMono-Bold.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto_Mono/static/RobotoMono-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto_Mono/static/RobotoMono-Light.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto_Mono/static/RobotoMono-Medium.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            Font.loadFont(new File("res/fonts/Roboto_Mono/static/RobotoMono-Thin.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        Label titleLabel = new Label("CycloNantais");
        Label subtitleLabel = new Label("L'app qui vous montre la voie !");
        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        JFXButton startButton = new JFXButton("COMMENCEZ VOTRE NAVIGATION EN OUVRANT LE MENU");

        // Apply styles
        titleLabel.setFont(Font.font("Roboto", FontWeight.NORMAL, 130));
        titleLabel.setTextFill(Color.web("#f8f8f2"));
        subtitleLabel.setFont(Font.font("Roboto", FontWeight.LIGHT, 70));
        subtitleLabel.setTextFill(Color.web("#bd93f9"));
        startButton.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        startButton.setStyle("-fx-background-color: #bd93f9; -fx-text-fill: #282a36;");
        menuButton.setStyle("-jfx-hamburger-color: #f8f8f2;");

        // Set up the layout
        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(Color.web("#282a36"), CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(10));

        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Accueil");
        VBox.setMargin(titleBar, new Insets(0, 0, 10, 0)); // Set margin for spacing below title bar
        root.getChildren().add(titleBar);

        // Position the logo image in the top layer
        StackPane logoContainer = new StackPane();
        logoContainer.setAlignment(Pos.TOP_RIGHT);
        logoContainer.getChildren().add(logoImage);
        root.getChildren().add(logoContainer);

        // Add the other components
        VBox centerBox = new VBox(10, titleLabel, subtitleLabel, startButton);
        centerBox.setAlignment(Pos.CENTER);
        root.getChildren().add(centerBox);

        // Create the scene
        Scene scene = new Scene(root, 1280, 720);
        try {
            scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        newStage.setScene(scene);
        newStage.setTitle("Accueil");
        newStage.initStyle(StageStyle.UNDECORATED);

        // Implement window dragging functionality
        windowDrag = new WindowDrag(root, newStage);

        // Add event listeners
        menuButton.setOnMouseClicked(event -> {
            accueilB.start(newStage);
        });

        startButton.setOnAction(event -> {
            accueilB.start(newStage);
        });

        // Add event listener to the logoImage
        logoImage.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://metropole.nantes.fr/"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        newStage.show();
    }

    public void setFirstTime(boolean b) {
        this.firstTime = b;
    }
}
