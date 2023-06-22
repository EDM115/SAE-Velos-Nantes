package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import frontend.Menu;
import frontend.RechercheAffluence;
import frontend.StationProche;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConnexionBdd;

public class StationProcheB extends Application {

    private ArrayList<String> lesCompteurs;
    private StationProche stationProche;

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

    public StationProcheB(StationProche stationProche) {
        this.stationProche = stationProche;
        this.lesCompteursBdd();
    }

    public void lesCompteursBdd() {
        this.lesCompteurs = new ArrayList<String>();
        ConnexionBdd connexionBdd = new ConnexionBdd();

        try {
            String query =  "SELECT DISTINCT CONCAT(nomCompteur, sens) AS resultat FROM Compteur ORDER BY resultat;";
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                this.lesCompteurs.add(resultSet.getString("resultat"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rechercherStation(String compteur) {
        try {
            String[] split = new String[2];
            int lastIndex = compteur.lastIndexOf(" ");
            if (lastIndex != -1) {
                split[0] = compteur.substring(0, lastIndex);
                split[1] = compteur.substring(lastIndex + 1);
            } else {
                // Handle the case when there is no space in the string
                split[0] = compteur;
                split[1] = "";
            }
            // bad database
            split[0] = split[0] + " ";


            String query = "SELECT c2.nomCompteur, c2.sens, MIN(SQRT(POW((RADIANS(c2.coord_Y) - RADIANS(c1.coord_Y)), 2) + POW((RADIANS(c2.coord_X) - RADIANS(c1.coord_X)), 2) * COS(RADIANS(c1.coord_Y)) * COS(RADIANS(c2.coord_Y))) * 6371000) AS distance_metres FROM Compteur c1 JOIN Compteur c2 ON c1.nomCompteur = '" + split[0] + "' AND c1.sens = '" + split[1] + "' WHERE c2.nomCompteur <> '" + split[0] + "' GROUP BY c2.nomCompteur, c2.sens ORDER BY distance_metres LIMIT 1;";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            double nbMetres = 0;
            String nomCompteur = "";
            String sens = "";

            while (resultSet.next()) {
                nbMetres = resultSet.getDouble("distance_metres");
                nomCompteur = resultSet.getString("nomCompteur");
                sens = resultSet.getString("sens");
            }
            System.out.println("Station la plus proche  : " + nomCompteur + "" + sens);
            System.out.println("Distance : " + nbMetres + " mètres");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getLesCompteurs() {
        return this.lesCompteurs;
    }
}
