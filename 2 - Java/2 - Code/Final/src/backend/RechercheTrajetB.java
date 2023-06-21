package backend;

import frontend.Menu;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConnexionBdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RechercheTrajetB extends Application {

    private ArrayList<String> lesCompteurs;

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

    public void lesCompteursBdd() {
        this.lesCompteurs = new ArrayList<String>();
        ConnexionBdd connexionBdd = new ConnexionBdd();

        try {
            String query =  "SELECT CONCAT(nomCompteur, '', sens) AS resultat_concatene\r\n" +
                            "FROM Compteur\r\n" + 
                            "ORDER BY nomCompteur;";
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                this.lesCompteurs.add(resultSet.getString("nomCompteur"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLesCompteurs() {
        return this.lesCompteurs;
    }
}

