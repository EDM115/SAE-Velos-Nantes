package utils;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowDrag {
	private double xOffset = 0;
	private double yOffset = 0;
	VBox root;
	BorderPane root2;
	GridPane root3;
	Stage newStage;

	public WindowDrag(VBox root, Stage newStage) {
		this.root = root;
		this.newStage = newStage;

		// Implement window dragging functionality
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		
		root.setOnMouseDragged(event -> {
			newStage.setX(event.getScreenX() - xOffset);
			newStage.setY(event.getScreenY() - yOffset);
		});
	}

	public WindowDrag(BorderPane root, Stage newStage) {
		this.root2 = root;
		this.newStage = newStage;

		// Implement window dragging functionality
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		
		root.setOnMouseDragged(event -> {
			newStage.setX(event.getScreenX() - xOffset);
			newStage.setY(event.getScreenY() - yOffset);
		});
	}

	public WindowDrag(GridPane root, Stage newStage) {
		this.newStage = newStage;
		this.root3 = root;

		// Implement window dragging functionality
		root.setOnMousePressed(event -> {
			xOffset = event.getSceneX();
			yOffset = event.getSceneY();
		});
		
		root.setOnMouseDragged(event -> {
			newStage.setX(event.getScreenX() - xOffset);
			newStage.setY(event.getScreenY() - yOffset);
		});
	}
}
