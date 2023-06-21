package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import frontend.Menu;
import frontend.RechercheAffluence;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConnexionBdd;

public class RechercheAffluenceB extends Application {

    private ArrayList<String> lesCompteurs;
    private RechercheAffluence rechercheAffluence;

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

    public RechercheAffluenceB(RechercheAffluence rechercheAffluence) {
        this.rechercheAffluence = rechercheAffluence;
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

    public void rechercherAffluence(String compteur, int hour, LocalDate date) {
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

            String ajout0 = "";
            if (hour < 10) {
                ajout0 = "0";
            }

            String query = "SELECT h" + ajout0 + hour + " FROM Comptage, Compteur, DateInfo WHERE leCompteur = idCompteur AND laDate = dateComptage AND nomCompteur = '" + split[0] + "' AND sens = '" + split[1] + "'" + " AND dateComptage = '" + date + "';";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            int nbCyclistes = 0;

            while (resultSet.next()) {
                nbCyclistes = resultSet.getInt("h" + ajout0 + hour);
            }
            System.out.println("Affluence estimée : " + nbCyclistes + " cyclistes");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getLesCompteurs() {
        return this.lesCompteurs;
    }
}
