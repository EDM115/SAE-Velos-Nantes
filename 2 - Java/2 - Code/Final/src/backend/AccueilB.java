package backend;

import frontend.Menu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AccueilB extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create menu
        Menu menu = new Menu(primaryStage);

        // Create buttons
        Button menuButton = new Button("Show Menu");
        Button closeButton = new Button("Close");

        // Handle button event to show the menu
        menuButton.setOnAction(event -> {
            menu.show();
            primaryStage.hide();
        });

        // Handle close button event to exit the application
        closeButton.setOnAction(event -> {
            primaryStage.close();
            // Make sure to exit the JavaFX application thread
            Platform.exit();
        });

        // Create the root layout
        StackPane root = new StackPane();
        root.getChildren().addAll(menuButton, closeButton);

        // Create the scene
        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
