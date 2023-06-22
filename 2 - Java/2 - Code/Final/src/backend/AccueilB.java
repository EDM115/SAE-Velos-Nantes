package backend;

import frontend.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class AccueilB extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create menu
        Menu menu = new Menu(primaryStage);
        menu.show();
        primaryStage.hide();
    }
}
