package utils;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * WindowDrag, allows to drag a window
 */
public class WindowDrag {

	/**
	 * xOffset
	 */
	private double xOffset = 0;

	/**
	 * yOffset
	 */
	private double yOffset = 0;

	/**
	 * VBox root
	 */
	VBox root;

	/**
	 * BorderPane root2
	 */
	BorderPane root2;

	/**
	 * GridPane root3
	 */
	GridPane root3;

	/**
	 * Stage newStage
	 */
	Stage newStage;

	/**
	 * WindowDrag constructor
	 * @param root the root
	 * @param newStage the new stage
	 */
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

	/**
	 * WindowDrag constructor
	 * @param root the root
	 * @param newStage the new stage
	 */
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

	/**
	 * WindowDrag constructor
	 * @param root the root
	 * @param newStage the new stage
	 */
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
