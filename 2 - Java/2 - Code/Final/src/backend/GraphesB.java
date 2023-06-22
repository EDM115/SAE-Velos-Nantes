package backend;

import javafx.application.Application;
import javafx.stage.Stage;

import frontend.Graphes;
import frontend.Menu;

/**
 * The GraphesB class, backend of the graphes window
 */
public class GraphesB extends Application {

    /**
     * The graphes
     */
    private Graphes graphes;

    /**
     * The main method
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The constructor
     * @param graphes the graphes
     */
	public GraphesB(Graphes graphes) {
		this.graphes = graphes;
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

}
