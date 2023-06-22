package backend;

import frontend.Graphes;
import frontend.Menu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphesB extends Application {
	Graphes graphes;

    public static void main(String[] args) {
        launch(args);
    }

	public GraphesB(Graphes graphes) {
		this.graphes = graphes;
	}

    @Override
    public void start(Stage primaryStage) {
        // Create menu
        Menu menu = new Menu(primaryStage);
		menu.show();
		primaryStage.hide();
    }
}