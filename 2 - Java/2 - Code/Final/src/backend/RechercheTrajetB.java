package backend;

import frontend.Menu;
import frontend.RechercheTrajet;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConnexionBdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class RechercheTrajetB extends Application {

    private ArrayList<String> lesCompteurs;
    private RechercheTrajet rechercheTrajet;

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

    public RechercheTrajetB(RechercheTrajet rechercheTrajet) {
        this.rechercheTrajet = rechercheTrajet;
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

    public ArrayList<String> getLesCompteurs() {
        return this.lesCompteurs;
    }

    public void rechercherTrajet(String departure, String arrival, int hour, LocalDate date) {
        try {
            String[] split = new String[2];
            int lastIndex = departure.lastIndexOf(" ");
            if (lastIndex != -1) {
                split[0] = departure.substring(0, lastIndex);
                split[1] = departure.substring(lastIndex + 1);
            } else {
                // Handle the case when there is no space in the string
                split[0] = departure;
                split[1] = ""; // or handle it as needed
            }
            // bad database
            split[0] = split[0] + " ";

            String ajout0 = "";
            if (hour < 10) {
                ajout0 = "0";
            }

            String[] split2 = new String[2];
            lastIndex = arrival.lastIndexOf(" ");
            if (lastIndex != -1) {
                split2[0] = arrival.substring(0, lastIndex);
                split2[1] = arrival.substring(lastIndex + 1);
            } else {
                // Handle the case when there is no space in the string
                split2[0] = arrival;
                split2[1] = ""; // or handle it as needed
            }
            // bad database
            split2[0] = split2[0] + " ";

            String query = "SELECT h" + ajout0 + hour + " FROM Comptage, Compteur, DateInfo WHERE leCompteur = idCompteur AND laDate = dateComptage AND nomCompteur = '" + split[0] + "' AND sens = '" + split[1] + "'" + " AND dateComptage = '" + date + "';";

            String query2 = "SELECT h" + ajout0 + hour + " FROM Comptage, Compteur, DateInfo WHERE leCompteur = idCompteur AND laDate = dateComptage AND nomCompteur = '" + split2[0] + "' AND sens = '" + split2[1] + "'" + " AND dateComptage = '" + date + "';";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Statement statement2 = connexionBdd.getConnection().createStatement();
            ResultSet resultSet2 = statement2.executeQuery(query2);

            while(resultSet.next()) {
                int nbCyclistes = resultSet.getInt("h" + ajout0 + hour);
                System.out.println(nbCyclistes);
            }

            while(resultSet2.next()) {
                int nbCyclistes = resultSet2.getInt("h" + ajout0 + hour);
                System.out.println(nbCyclistes);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

