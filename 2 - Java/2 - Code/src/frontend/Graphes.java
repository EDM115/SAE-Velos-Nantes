package frontend;

import java.io.File;
import java.net.MalformedURLException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import backend.GraphesB;
import utils.StageDump;
import utils.TitleBar;
import utils.WindowDrag;

/**
 * The Graphes class, allows to create the graphs page
 */
public class Graphes extends Application {

    /**
     * The StageDump object, allows to save the stage
     */
    private StageDump stageDump = new StageDump();

    /**
     * The WindowDrag object, allows to drag the window
     */
    private WindowDrag windowDrag;

    /**
     * Path to the first image
     */
    private String imagePath1 = "res/images/graphes/grapheDist.png";

    /**
     * Path to the second image
     */
	private String imagePath2 = "res/images/graphes/grapheDist1.png";

    /**
     * Text of the first label
     */
	private String labelText1 = "Distance à l'échelle";

    /**
     * Text of the second label
     */
	private String labelText2 = "Distance";

    /**
     * The Stage object, allows to create the stage
     */
    private Stage newStage;
    
    /**
     * The start method, allows to create the stage
     * @param primaryStage The stage
     */
    @Override
    public void start(Stage primaryStage) {
        newStage = stageDump.dump(primaryStage);
        GraphesB graphesB = new GraphesB(this);

        try {
            Font.loadFont(new File("res/fonts/Roboto/Roboto-Regular.ttf").toURI().toURL().toExternalForm(), 12);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        JFXButton closeButton = new JFXButton("✕");
        JFXButton minimizeButton = new JFXButton("—");
        JFXButton maximizeRestoreButton = new JFXButton("⬜");
        TitleBar titleBarElement = new TitleBar();
        JFXHamburger menuButton = new JFXHamburger();
        HBox titleBar = titleBarElement.createTitleBar(newStage, menuButton, minimizeButton, maximizeRestoreButton, closeButton, "Graphes");

        // Create the GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));

        // Add images and labels to the GridPane
		try {
			Image image1 = new Image(new File(imagePath1).toURI().toURL().toExternalForm());
			ImageView imageView1 = new ImageView(image1);
			imageView1.setFitWidth(600);
			imageView1.setPreserveRatio(true);

			Image image2 = new Image(new File(imagePath2).toURI().toURL().toExternalForm());
			ImageView imageView2 = new ImageView(image2);
			imageView2.setFitWidth(600);
			imageView2.setPreserveRatio(true);

			Label label1 = new Label(labelText1);
			label1.setAlignment(Pos.CENTER);
			label1.setStyle("-fx-text-fill: #f8f8f2; -fx-font-size: 14px;");

			Label label2 = new Label(labelText2);
			label2.setAlignment(Pos.CENTER);
			label2.setStyle("-fx-text-fill: #f8f8f2; -fx-font-size: 14px;");

			VBox vbox1 = new VBox(10);
			vbox1.setAlignment(Pos.CENTER);
			vbox1.getChildren().addAll(imageView1, label1);

			VBox vbox2 = new VBox(10);
			vbox2.setAlignment(Pos.CENTER);
			vbox2.getChildren().addAll(imageView2, label2);

			HBox hbox = new HBox(10);
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(vbox1, vbox2);

			gridPane.getChildren().add(hbox);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

        // Create the root layout
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(gridPane);

        windowDrag = new WindowDrag(root, newStage);

        // Add event listeners
        menuButton.setOnMouseClicked(event -> {
            graphesB.start(newStage);
        });

        // Customize stage
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setTitle("Recherche d'affluence");

        // Create the scene
        Scene scene = new Scene(root, 800, 600, Color.WHITE);
        scene.setFill(Color.TRANSPARENT);

        // Apply the CSS styling
        try {
            scene.getStylesheets().add(new File("res/style/style.css").toURI().toURL().toExternalForm());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Set the stage properties
        newStage.setScene(scene);
        newStage.setTitle("Graphes");
        newStage.show();
    }

    /**
     * The main method, launches the application
     * @param args The arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Default constructor of the class
     */
    public Graphes() {}

}
