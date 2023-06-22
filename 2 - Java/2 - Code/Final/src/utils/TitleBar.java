package utils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;

/**
 * TitleBar, allows to create a title bar
 */
public class TitleBar {

	/**
	 * TitleBar constructor
	 * @param stage the stage
	 * @param menuButton the menu button
	 * @param minimizeButton the minimize button
	 * @param maximizeRestoreButton the maximize/restore button
	 * @param closeButton the close button
	 * @param title the title
	 * @return the title bar
	 */
	public HBox createTitleBar(Stage stage, JFXHamburger menuButton, JFXButton minimizeButton, JFXButton maximizeRestoreButton, JFXButton closeButton, String title) {
		HBox titleBar = new HBox(10);
		titleBar.setPadding(new Insets(5));
		titleBar.setBackground(new Background(new BackgroundFill(Color.web("#44475a"), new CornerRadii(10), Insets.EMPTY)));
		titleBar.setAlignment(Pos.CENTER);

		menuButton.setStyle("-fx-hamburger-color: #f8f8f2;");

		minimizeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");
		maximizeRestoreButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");
		closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");

		minimizeButton.setOnAction(event -> stage.setIconified(true));
		maximizeRestoreButton.setOnAction(event -> {
			if (stage.isMaximized()) {
				stage.setMaximized(false);
				maximizeRestoreButton.setText("⬜");
			} else {
				stage.setMaximized(true);
				maximizeRestoreButton.setText("🗖");
			}
		});
		closeButton.setOnAction(event -> {
			EmptyFile.emptyFile("res/data.json");
			stage.close();
		});

		HBox.setHgrow(menuButton, Priority.NEVER);
		HBox.setHgrow(maximizeRestoreButton, Priority.NEVER);

		HBox titleBarButtons = new HBox(5, minimizeButton, maximizeRestoreButton, closeButton);
		titleBarButtons.setAlignment(Pos.CENTER_RIGHT);

		titleBar.getChildren().addAll(menuButton, createTitleLabel(title), titleBarButtons);
		return titleBar;
	}

	/**
	 * Create a title label, for the menu
	 * @param stage the stage
	 * @param closeMenu the close menu button
	 * @param minimizeButton the minimize button
	 * @param maximizeRestoreButton the maximize/restore button
	 * @param closeButton the close button
	 * @param title the title
	 * @return the title label
	 */
	public HBox createTitleBar(Stage stage, JFXButton closeMenu, JFXButton minimizeButton, JFXButton maximizeRestoreButton, JFXButton closeButton, String title) {
		HBox titleBar = new HBox(10);
		titleBar.setPadding(new Insets(5));
		titleBar.setBackground(new Background(new BackgroundFill(Color.web("#44475a"), new CornerRadii(10), Insets.EMPTY)));
		titleBar.setAlignment(Pos.CENTER);

		closeMenu.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");
		minimizeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");
		maximizeRestoreButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");
		closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");

		closeMenu.setOnAction(event -> stage.close());
		minimizeButton.setOnAction(event -> stage.setIconified(true));
		maximizeRestoreButton.setOnAction(event -> {
			if (stage.isMaximized()) {
				stage.setMaximized(false);
				maximizeRestoreButton.setText("⬜");
			} else {
				stage.setMaximized(true);
				maximizeRestoreButton.setText("🗖");
			}
		});
		closeButton.setOnAction(event -> stage.close());

		HBox.setHgrow(closeMenu, Priority.NEVER);
		HBox.setHgrow(maximizeRestoreButton, Priority.NEVER);

		HBox titleBarButtons = new HBox(5, closeMenu, minimizeButton, maximizeRestoreButton, closeButton);
		titleBarButtons.setAlignment(Pos.CENTER_RIGHT);

		titleBar.getChildren().addAll(createTitleLabel(title), titleBarButtons);
		return titleBar;
	}

	/**
	 * Create a title label, for an empty menu
	 * @param stage the stage
	 * @param minimizeButton the minimize button
	 * @param maximizeRestoreButton the maximize/restore button
	 * @param closeButton the close button
	 * @param title the title
	 * @return the title label
	 */
	public HBox createTitleBar(Stage stage, JFXButton minimizeButton, JFXButton maximizeRestoreButton, JFXButton closeButton, String title) {
		HBox titleBar = new HBox(10);
		titleBar.setPadding(new Insets(5));
		titleBar.setBackground(new Background(new BackgroundFill(Color.web("#44475a"), new CornerRadii(10), Insets.EMPTY)));
		titleBar.setAlignment(Pos.CENTER);

		minimizeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");
		maximizeRestoreButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");
		closeButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8f2;");

		minimizeButton.setOnAction(event -> stage.setIconified(true));
		maximizeRestoreButton.setOnAction(event -> {
			if (stage.isMaximized()) {
				stage.setMaximized(false);
				maximizeRestoreButton.setText("⬜");
			} else {
				stage.setMaximized(true);
				maximizeRestoreButton.setText("🗖");
			}
		});
		closeButton.setOnAction(event -> stage.close());

		HBox.setHgrow(maximizeRestoreButton, Priority.NEVER);

		HBox titleBarButtons = new HBox(5, minimizeButton, maximizeRestoreButton, closeButton);
		titleBarButtons.setAlignment(Pos.CENTER_RIGHT);

		titleBar.getChildren().addAll(createTitleLabel(title), titleBarButtons);
		return titleBar;
	}

	/**
	 * Create a title label
	 * @param title the title
	 * @return the title label
	 */
	public Label createTitleLabel(String title) {
		Label titleLabel = new Label(title);
		titleLabel.setFont(Font.font("Roboto", FontWeight.LIGHT, 20));
		titleLabel.setTextFill(Color.web("#f8f8f2"));
		return titleLabel;
	}

	/**
	 * Create a title label, for the menu
	 */
	public TitleBar() {}

}
