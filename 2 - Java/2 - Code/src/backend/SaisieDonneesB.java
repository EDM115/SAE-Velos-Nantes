package backend;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

import frontend.Menu;
import frontend.SaisieDonnees;
import utils.ConnexionBdd;

/**
 * The SaisieDonneesB class, backend of the data entry window
 */
public class SaisieDonneesB extends Application {

    /**
     * ArrayList of the tables
     */
    private ArrayList<String> lesTables;

    /**
     * The data entry window
     */
    private SaisieDonnees saisieDonnees;

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
     * Constructor of the SaisieDonneesB class
     * @param saisieDonnees the data entry window
     */
    public SaisieDonneesB(SaisieDonnees saisieDonnees) {
        this.saisieDonnees = saisieDonnees;
        this.lesTablesBdd();
    }

    /**
     * Method to initialize the arraylist of the tables
     */
    public void lesTablesBdd() {
        this.lesTables = new ArrayList<String>();
        this.lesTables.add("Quartier");
        this.lesTables.add("Compteur");
        this.lesTables.add("DateInfo");
        this.lesTables.add("Comptage");
    }

    /**
     * Add a district to the database
     * @param idQuartier the district id
     * @param nomQuartier the district name
     * @param longueurPisteVelo the bike path length
     */
    public void ajoutQuartier(int idQuartier, String nomQuartier, double longueurPisteVelo) {
        try {
            String query = "INSERT INTO Quartier VALUES (" + idQuartier + ", '" + nomQuartier + "', " + longueurPisteVelo + ");";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            saisieDonnees.setEditedData(rowsAffected + " ligne(s) ajoutée(s) dans la table Quartier.");
        } catch (SQLException e) {
            saisieDonnees.setEditedData("Erreur lors de l'ajout dans la table Quartier.");
            e.printStackTrace();
        } 
    }

    /**
     * Add a counter to the database
     * @param idCompteur the counter id
     * @param nomCompteur the counter name
     * @param sens the counter direction
     * @param coord_X the counter X coordinate
     * @param coord_Y the counter Y coordinate
     * @param leQuartier the district id
     */
    public void ajoutCompteur(int idCompteur, String nomCompteur, String sens, double coord_X, double coord_Y, int leQuartier) {
        try {
            String query = "INSERT INTO Compteur VALUES (" + idCompteur + ", '" + nomCompteur + "', '" + sens + "', " + coord_X + ", " + coord_Y + ", " + leQuartier + ");";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            saisieDonnees.setEditedData(rowsAffected + " ligne(s) ajoutée(s) dans la table Compteur.");
        } catch (SQLException e) {
            saisieDonnees.setEditedData("Erreur lors de l'ajout dans la table Compteur.");
            e.printStackTrace();
        }
    }

    /**
     * Add a date to the database
     * @param laDate the date
     * @param tempMoy the average temperature
     * @param jour the day
     * @param vacances the holidays
     */
    public void ajoutDateInfo(LocalDate laDate, double tempMoy, String jour, String vacances) {
        try {
            String query = "INSERT INTO DateInfo VALUES ('" + laDate + "','" + tempMoy + "', '" + jour + "', '" + vacances + "');";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            saisieDonnees.setEditedData(rowsAffected + " ligne(s) ajoutée(s) dans la table DateInfo.");
        } catch (SQLException e) {
            saisieDonnees.setEditedData("Erreur lors de l'ajout dans la table DateInfo.");
            e.printStackTrace();
        }
    }

    /**
     * Add a counter to the database
     * @param leCompteur the counter id
     * @param dateComptage the count date
     * @param h00 the count at 00:00
     * @param h01 the count at 01:00
     * @param h02 the count at 02:00
     * @param h03 the count at 03:00
     * @param h04 the count at 04:00
     * @param h05 the count at 05:00
     * @param h06 the count at 06:00
     * @param h07 the count at 07:00
     * @param h08 the count at 08:00
     * @param h09 the count at 09:00
     * @param h10 the count at 10:00
     * @param h11 the count at 11:00
     * @param h12 the count at 12:00
     * @param h13 the count at 13:00
     * @param h14 the count at 14:00
     * @param h15 the count at 15:00
     * @param h16 the count at 16:00
     * @param h17 the count at 17:00
     * @param h18 the count at 18:00
     * @param h19 the count at 19:00
     * @param h20 the count at 20:00
     * @param h21 the count at 21:00
     * @param h22 the count at 22:00
     * @param h23 the count at 23:00
     * @param presenceAnomalie the anomaly presence
     */
    public void ajoutComptage(int leCompteur, LocalDate dateComptage, int h00, int h01, int h02, int h03, int h04, int h05, int h06, int h07, int h08, int h09, int h10, int h11, int h12, int h13, int h14, int h15, int h16, int h17, int h18, int h19, int h20, int h21, int h22, int h23, String presenceAnomalie) {
        try {
            String query = "INSERT INTO Comptage VALUES ('" + leCompteur + "', '" + dateComptage + "', '" + h00 + "', '" + h01 + "', '" + h02 + "', '" + h03 + "', '" + h04 + "', '" + h05 + "', '" + h06 + "', '" + h07 + "', '" + h08 + "', '" + h09 + "', '" + h10 + "', '" + h11 + "', '" + h12 + "', '" + h13 + "', '" + h14 + "', '" + h15 + "', '" + h16 + "', '" + h17 + "', '" + h18 + "', '" + h19 + "', '" + h20 + "', '" + h21 + "', '" + h22 + "', '" + h23 + "', '" + presenceAnomalie + "');";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            saisieDonnees.setEditedData(rowsAffected + " ligne(s) ajoutée(s) dans la table Comptage.");
        } catch (SQLException e) {
            saisieDonnees.setEditedData("Erreur lors de l'ajout dans la table Comptage.");
            e.printStackTrace();
        }
    }

    /**
     * Getter for the tables
     * @return the tables
     */
    public ArrayList<String> getLesTables() {
        return this.lesTables;
    }

}
