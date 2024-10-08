package backend;

import com.google.gson.JsonObject;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

import frontend.Menu;
import frontend.RechercheAffluence;
import frontend.ResultatsRecherche;
import utils.ConnexionBdd;

/**
 * The RechercheAffluenceB class, backend of the search window
 */
public class RechercheAffluenceB extends Application {

    /**
     * ArrayList of the counters
     */
    private ArrayList<String> lesCompteurs;

    /**
     * ArrayList of the counters id
     */
    private ArrayList<Integer> lesIdCompteurs;

    /**
     * The crowd search window
     */
    private RechercheAffluence rechercheAffluence;

    /**
     * The travel search window, to use its methods
     */
    private RechercheTrajetB rechercheTrajetB = new RechercheTrajetB();

    /**
     * The main method
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method, called when the window is created
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        // Create menu
        Menu menu = new Menu(primaryStage);
        menu.show();
        primaryStage.hide();
    }

    /**
     * The constructor
     * @param rechercheAffluence the crowd search window
     */
    public RechercheAffluenceB(RechercheAffluence rechercheAffluence) {
        this.rechercheAffluence = rechercheAffluence;
        this.lesCompteursBdd();
        this.setupButtonActions();
    }

    /**
     * Get the counters from the database
     */
    public void lesCompteursBdd() {
        this.lesCompteurs = new ArrayList<String>();
        this.lesIdCompteurs = new ArrayList<Integer>();
        ConnexionBdd connexionBdd = new ConnexionBdd();

        try {
            String query =  "SELECT CONCAT(nomCompteur, sens) AS resultat, MAX(idCompteur) AS idCompteur FROM Compteur GROUP BY nomCompteur, sens ORDER BY resultat;";
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                this.lesCompteurs.add(resultSet.getString("resultat"));
                this.lesIdCompteurs.add(resultSet.getInt("idCompteur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the counters
     * @return the counters
     */
    public ArrayList<String> getLesCompteurs() {
        return this.lesCompteurs;
    }

    /**
     * Set up the button actions
     */
    public void setupButtonActions() {
        this.rechercheAffluence.getSearchButton().setOnAction(event -> {
            String compteur = this.rechercheAffluence.getCompteur();
            int compteurIndex = this.rechercheAffluence.getCompteurIndex();
            int hour = this.rechercheAffluence.getHour();
            LocalDate date = this.rechercheAffluence.getDate();

            this.rechercherAffluence(compteur, compteurIndex, hour, date);
        });
    }

    /**
     * Search the crowd, and put the results in a JSON file
     * @param compteur the counter
     * @param compteurIndex the counter index
     * @param hour the hour
     * @param date the date
     */
    public void rechercherAffluence(String compteur, int compteurIndex, int hour, LocalDate date) {
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

            String[] data = new String[7];
            data[0] = compteur;
            data[1] = compteurIndex + "";
            data[2] = rechercheTrajetB.getGeo(rechercheTrajetB.getCompteurFromId(compteurIndex, this.lesCompteurs))[0] + "";
            data[3] = rechercheTrajetB.getGeo(rechercheTrajetB.getCompteurFromId(compteurIndex, this.lesCompteurs))[1] + "";
            data[4] = date.toString();
            data[5] = hour + "";
            data[6] = nbCyclistes + "";

            File file = rechercheTrajetB.writeData(dataToJson(data));

            ResultatsRecherche resultatsRecherche = new ResultatsRecherche(false, file);
            resultatsRecherche.start(this.rechercheAffluence.getStage());
            this.rechercheAffluence.getStage().hide();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("\u001B[31mERREUR\u001B[0m");
        } catch (SQLException e) {
            System.err.println("\u001B[31mREQUETE IMPOSSIBLE\u001B[0m");
            e.printStackTrace();
        }
    }

    /**
     * Put the data in a JSON object
     * @param data the data
     * @return the JSON object
     */
    public JsonObject dataToJson(String[] data) {
        // create a new JSONObject
        JsonObject jsonObject = new JsonObject();

        // add the data to the JSONObject
        jsonObject.addProperty("compteur", data[0]);
        jsonObject.addProperty("compteurIndex", data[1]);
        jsonObject.addProperty("compteurLat", data[2]);
        jsonObject.addProperty("compteurLong", data[3]);
        jsonObject.addProperty("date", data[4]);
        jsonObject.addProperty("hour", data[5]);
        jsonObject.addProperty("nbCyclistes", data[6]);
        return jsonObject;
    }

}
