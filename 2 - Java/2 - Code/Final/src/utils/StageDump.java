package utils;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageDump {
	public StageDump() {}

	public Stage dump(Stage otherStage) {
		Stage newStage = new Stage();

		if (otherStage == null) {
			newStage.setTitle("Accueil");
        	newStage.initStyle(StageStyle.UNDECORATED);
			newStage.setResizable(false);
			newStage.setWidth(1280);
			newStage.setHeight(720);
		} else {
			newStage.setX(otherStage.getX());
			newStage.setY(otherStage.getY());
			newStage.setWidth(otherStage.getWidth());
			newStage.setHeight(otherStage.getHeight());
			newStage.setMinWidth(otherStage.getMinWidth());
			newStage.setMinHeight(otherStage.getMinHeight());
			newStage.setMaxWidth(otherStage.getMaxWidth());
			newStage.setMaxHeight(otherStage.getMaxHeight());
			newStage.setResizable(otherStage.isResizable());
			newStage.setFullScreen(otherStage.isFullScreen());
			newStage.setIconified(otherStage.isIconified());
			newStage.setOpacity(otherStage.getOpacity());
			newStage.setAlwaysOnTop(otherStage.isAlwaysOnTop());
			newStage.initStyle(otherStage.getStyle());
			newStage.setScene(otherStage.getScene());
		}
		
		return newStage;
	}
}
