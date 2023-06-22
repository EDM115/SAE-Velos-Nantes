package backend;

import javafx.application.Application;
import javafx.stage.Stage;

import frontend.Menu;

/**
 * The ResultatsRechercheB class, backend of the search results window
 */
public class ResultatsRechercheB extends Application {

    /**
     * The main method
     * @param args the arguments
     */
	public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method, called when the window is created
     * @param primaryStage the primary stage
     */
	@Override
    public void start(Stage primaryStage) {
        // Create menu
        Menu menu = new Menu(primaryStage);
        menu.show();
        primaryStage.hide();
    }

    /**
     * Default constructor of the class
     */
    public ResultatsRechercheB() {}

}
