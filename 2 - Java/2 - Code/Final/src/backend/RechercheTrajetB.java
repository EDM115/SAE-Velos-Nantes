package backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

import frontend.Menu;
import frontend.RechercheTrajet;
import frontend.ResultatsRecherche;
import utils.ConnexionBdd;

/**
 * The RechercheTrajetB class, backend of the search window
 */
public class RechercheTrajetB extends Application {

    /**
     * ArrayList of the counters
     */
    private ArrayList<String> lesCompteurs;

    /**
     * ArrayList of the counters id
     */
    private ArrayList<Integer> lesIdCompteurs;

    /**
     * The travel search window
     */
    private RechercheTrajet rechercheTrajet;

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
     * @param rechercheTrajet the travel search window
     */
    public RechercheTrajetB(RechercheTrajet rechercheTrajet) {
        this.rechercheTrajet = rechercheTrajet;
        this.lesCompteursBdd();
        setupButtonActions();
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
     * Get the geo coordinates of a counter
     * @param idCompteur the counter id
     * @return the geo coordinates
     */
    public double[] getGeo(int idCompteur) {
        double[] geo = new double[2];
        ConnexionBdd connexionBdd = new ConnexionBdd();
        try {
            String query = "SELECT FORMAT(coord_X, 13) AS formatted_coord_X, FORMAT(coord_Y, 13) AS formatted_coord_Y FROM Compteur WHERE idCompteur = " + idCompteur + ";";
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                geo[0] = resultSet.getDouble("formatted_coord_X");
                geo[1] = resultSet.getDouble("formatted_coord_Y");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return geo;
    }

    /**
     * Get the counter id from the index
     * @param index the index
     * @param lesCompteurs the counters
     * @return the counter id
     */
    public int getCompteurFromId(int index, ArrayList<String> lesCompteurs) {
        String compteur = lesCompteurs.get(index);
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
        compteur = "'" + split[0] + " '";

        int id = 0;
        ConnexionBdd connexionBdd = new ConnexionBdd();
        try {
            String query =  "SELECT idCompteur FROM Compteur WHERE nomCompteur = " + compteur + ";";
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                id = resultSet.getInt("idCompteur");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    /**
     * Set up the actions of the buttons
     */
    public void setupButtonActions() {
        rechercheTrajet.getSearchButton().setOnAction(event -> {
            String departure = rechercheTrajet.getDeparture();
            int departureIndex = rechercheTrajet.getDepartureIndex();
            String arrival = rechercheTrajet.getArrival();
            int arrivalIndex = rechercheTrajet.getArrivalIndex();
            int hour = rechercheTrajet.getHour();
            LocalDate date = rechercheTrajet.getDate();

            this.rechercherTrajet(departure, departureIndex, arrival, arrivalIndex, hour, date);
        });
    }

    /**
     * Search for a travel, and writ eit to a JSON file
     * @param departure the departure
     * @param departureIndex the departure index
     * @param arrival the arrival
     * @param arrivalIndex the arrival index
     * @param hour the hour
     * @param date the date
     */
    public void rechercherTrajet(String departure, int departureIndex, String arrival, int arrivalIndex, int hour, LocalDate date) {
        try {
            String[] split = new String[2];
            int lastIndex = departure.lastIndexOf(" ");
            if (lastIndex != -1) {
                split[0] = departure.substring(0, lastIndex);
                split[1] = departure.substring(lastIndex + 1);
            } else {
                // Handle the case when there is no space in the string
                split[0] = departure;
                split[1] = "";
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
                split2[1] = "";
            }
            // bad database
            split2[0] = split2[0] + " ";

            String query = "SELECT h" + ajout0 + hour + ", tempMoy FROM Comptage, Compteur, DateInfo WHERE leCompteur = idCompteur AND laDate = dateComptage AND nomCompteur = '" + split[0] + "' AND sens = '" + split[1] + "'" + " AND dateComptage = '" + date + "';";

            String query2 = "SELECT h" + ajout0 + hour + " FROM Comptage, Compteur, DateInfo WHERE leCompteur = idCompteur AND laDate = dateComptage AND nomCompteur = '" + split2[0] + "' AND sens = '" + split2[1] + "'" + " AND dateComptage = '" + date + "';";

            String query3 = "SELECT presenceAnomalie FROM Comptage, Compteur, DateInfo WHERE leCompteur = idCompteur AND laDate = dateComptage AND nomCompteur = '" + split[0] + "' AND sens = '" + split[1] + "'" + " AND dateComptage = '" + date + "';";

            String query4 = "SELECT presenceAnomalie FROM Comptage, Compteur, DateInfo WHERE leCompteur = idCompteur AND laDate = dateComptage AND nomCompteur = '" + split2[0] + "' AND sens = '" + split2[1] + "'" + " AND dateComptage = '" + date + "';";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Statement statement2 = connexionBdd.getConnection().createStatement();
            ResultSet resultSet2 = statement2.executeQuery(query2);
            Statement statement3 = connexionBdd.getConnection().createStatement();
            ResultSet resultSet3 = statement3.executeQuery(query3);
            Statement statement4 = connexionBdd.getConnection().createStatement();
            ResultSet resultSet4 = statement4.executeQuery(query4);
            int nbCyclistes = 0;
            int nbCyclistes2 = 0;
            double temperature = 0;
            String anomalie = "";
            String anomalie2 = "";

            while(resultSet.next()) {
                nbCyclistes = resultSet.getInt("h" + ajout0 + hour);
                temperature = resultSet.getDouble("tempMoy");
            }

            while(resultSet2.next()) {
                nbCyclistes2 = resultSet2.getInt("h" + ajout0 + hour);
            }

            while(resultSet3.next()) {
                anomalie = resultSet3.getString("presenceAnomalie");
            }

            while(resultSet4.next()) {
                anomalie2 = resultSet4.getString("presenceAnomalie");
            }

            // add all data to a String[], including the departure and arrival
            String[] data = new String[14];
            data[0] = departure;
            data[1] = departureIndex + "";
            data[2] = getGeo(getCompteurFromId(departureIndex, this.lesCompteurs))[0] + "";
            data[3] = getGeo(getCompteurFromId(departureIndex, this.lesCompteurs))[1] + "";
            data[4] = arrival;
            data[5] = arrivalIndex + "";
            data[6] = getGeo(getCompteurFromId(arrivalIndex, this.lesCompteurs))[0] + "";
            data[7] = getGeo(getCompteurFromId(arrivalIndex, this.lesCompteurs))[1] + "";
            data[8] = Integer.toString(nbCyclistes + nbCyclistes2);
            data[9] = Double.toString(temperature);
            data[10] = date.toString();
            data[11] = hour + "";
            data[12] = anomalie;
            data[13] = anomalie2;

            File file = writeData(dataToJson(data));

            // start a frontend.ResultatsRecherche with these results
            ResultatsRecherche resultatsRecherche = new ResultatsRecherche(true, file);
            resultatsRecherche.start(this.rechercheTrajet.getStage());
            this.rechercheTrajet.getStage().hide();
        } catch (SQLSyntaxErrorException e) {
            System.err.println("\u001B[31mERREUR\u001B[0m");
        } catch (SQLException e) {
            System.err.println("\u001B[31mREQUETE IMPOSSIBLE\u001B[0m");
            e.printStackTrace();
        }
    }

    /**
     * Write the data in a JSON file
     * @param data the data to write
     * @return the JSON file
     */
    public JsonObject dataToJson(String[] data) {
        // create a new JSONObject
        JsonObject jsonObject = new JsonObject();

        // add the data to the JSONObject
        jsonObject.addProperty("departure", data[0]);
        jsonObject.addProperty("departureIndex", data[1]);
        jsonObject.addProperty("departureLat", data[2]);
        jsonObject.addProperty("departureLong", data[3]);
        jsonObject.addProperty("arrival", data[4]);
        jsonObject.addProperty("arrivalIndex", data[5]);
        jsonObject.addProperty("arrivalLat", data[6]);
        jsonObject.addProperty("arrivalLong", data[7]);
        jsonObject.addProperty("nbCyclistes", data[8]);
        jsonObject.addProperty("temperature", data[9]);
        jsonObject.addProperty("date", data[10]);
        jsonObject.addProperty("hour", data[11]);
        jsonObject.addProperty("anomalie", data[12]);
        jsonObject.addProperty("anomalie2", data[13]);

        return jsonObject;
    }

    /**
     * Write the data in a JSON file
     * @param jsonObject the data to write
     * @return the JSON file
     */
    public File writeData(JsonObject jsonObject) {
        // Create a new JSON file
        File file = new File("res/data.json");

        try {
            // Create a Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convert the JsonObject to a JSON string
            String jsonString = gson.toJson(jsonObject);

            // Create a FileWriter
            FileWriter fileWriter = new FileWriter(file);

            // Write the JSON string to the file
            fileWriter.write(jsonString);

            // Close the FileWriter
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * Default constructor
     */
    public RechercheTrajetB() {}

}
