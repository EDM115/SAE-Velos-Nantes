package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import utils.ConnexionBdd;

import frontend.Menu;
import frontend.ModificationDonnees;

/**
 * The ModificationDonneesB class, backend of the modification donnees window
 */
public class ModificationDonneesB extends Application {

    /**
     * ArrayList of the tables
     */
    private ArrayList<String> lesTables;

    /**
     * The modification donnees object
     */
    private ModificationDonnees modificationDonnees;

    /**
     * ArrayList of the columns for the table Quartier
     */
    private ArrayList<String> colonnesQuartier;

    /**
     * ArrayList of the columns for the table Compteur
     */
    private ArrayList<String> colonnesCompteur;

    /**
     * ArrayList of the columns for the table DateInfo
     */
    private ArrayList<String> colonnesDateInfo;

    /**
     * ArrayList of the columns for the table Comptage
     */
    private ArrayList<String> colonnesComptage;

    /**
     * ArrayList of the primary keys for the table Quartier
     */
    private ArrayList<String> clePrimaireQuartier;

    /**
     * ArrayList of the primary keys for the table Compteur
     */
    private ArrayList<String> clePrimaireCompteur;

    /**
     * ArrayList of the primary keys for the table DateInfo
     */
    private DatePicker clePrimaireDateInfo;

    /**
     * ArrayList of the primary keys for the table Comptage
     */
    private ArrayList<String> clePrimaireComptage1;

    /**
     * DatePicker for the primary keys for the table Comptage
     */
    private DatePicker clePrimaireComptage2;

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
     * @param modificationDonnees the modification donnees object
     */
    public ModificationDonneesB(ModificationDonnees modificationDonnees) {
        this.modificationDonnees = modificationDonnees;
        this.lesTablesBdd();
    }

    /**
     * Creates the ArrayList of the tables
     */
    public void lesTablesBdd() {
        this.lesTables = new ArrayList<String>();
        this.lesTables.add("Quartier");
        this.lesTables.add("Compteur");
        this.lesTables.add("DateInfo");
        this.lesTables.add("Comptage");
    }

    /**
     * Creates the ArrayList of the columns for the table Quartier
     */
    public void lesColonnesQuartier() {
        this.colonnesQuartier = new ArrayList<String>();
        this.colonnesQuartier.add("idQuartier");
        this.colonnesQuartier.add("nomQuartier");
        this.colonnesQuartier.add("longueurPisteVelo");
    }

    /**
     * Creates the ArrayList of the columns for the table Compteur
     */
    public void lesColonnesCompteur() {
        this.colonnesCompteur = new ArrayList<String>();
        this.colonnesCompteur.add("idCompteur");
        this.colonnesCompteur.add("nomCompteur");
        this.colonnesCompteur.add("sens");
        this.colonnesCompteur.add("coord_X");
        this.colonnesCompteur.add("coord_Y");
        this.colonnesCompteur.add("leQuartier");
    }

    /**
     * Creates the ArrayList of the columns for the table DateInfo
     */
    public void lesColonnesDateInfo() {
        this.colonnesDateInfo = new ArrayList<String>();
        this.colonnesDateInfo.add("laDate");
        this.colonnesDateInfo.add("tempMoy");
        this.colonnesDateInfo.add("jour");
        this.colonnesDateInfo.add("vacances");
    }

    /**
     * Creates the ArrayList of the columns for the table Comptage
     */
    public void lesColonnesComptage() {
        this.colonnesComptage = new ArrayList<String>();
        this.colonnesComptage.add("leCompteur");
        this.colonnesComptage.add("dateComptage");
        this.colonnesComptage.add("h00");
        this.colonnesComptage.add("h01");
        this.colonnesComptage.add("h02");
        this.colonnesComptage.add("h03");
        this.colonnesComptage.add("h04");
        this.colonnesComptage.add("h05");
        this.colonnesComptage.add("h06");
        this.colonnesComptage.add("h07");
        this.colonnesComptage.add("h08");
        this.colonnesComptage.add("h09");
        this.colonnesComptage.add("h10");
        this.colonnesComptage.add("h11");
        this.colonnesComptage.add("h12");
        this.colonnesComptage.add("h13");
        this.colonnesComptage.add("h14");
        this.colonnesComptage.add("h15");
        this.colonnesComptage.add("h16");
        this.colonnesComptage.add("h17");
        this.colonnesComptage.add("h18");
        this.colonnesComptage.add("h19");
        this.colonnesComptage.add("h20");
        this.colonnesComptage.add("h21");
        this.colonnesComptage.add("h22");
        this.colonnesComptage.add("h23");
        this.colonnesComptage.add("presenceAnomalie");
    }

    /**
     * Creates the ArrayList of the primary keys for the table Quartier
     */
    public void clePrimaireQuartier() {
        this.clePrimaireQuartier = new ArrayList<String>();
        try {
            String query = "SELECT DISTINCT idQuartier FROM Quartier;";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                this.clePrimaireQuartier.add(resultSet.getString("idQuartier"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the ArrayList of the primary keys for the table Compteur
     */ 
    public void clePrimaireCompteur() {
        this.clePrimaireCompteur = new ArrayList<String>();
        try {
            String query = "SELECT DISTINCT idCompteur FROM Compteur;";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                this.clePrimaireCompteur.add(resultSet.getString("idCompteur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the ArrayList of the primary keys for the table DateInfo
     */
    public void clePrimaireDateInfo() {
        this.clePrimaireDateInfo = new DatePicker();
    }

    /**
     * Creates the ArrayList of the primary keys for the table Comptage
     */
    public void clePrimaireComptage1() {
        this.clePrimaireComptage1 = new ArrayList<String>();
        try {
            String query = "SELECT DISTINCT leCompteur FROM Comptage;";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                this.clePrimaireComptage1.add(resultSet.getString("leCompteur"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the ArrayList of the primary keys for the table Comptage
     */
    public void clePrimaireComptage2() {
        this.clePrimaireComptage2 = new DatePicker();
    }

    /**
     * Allows to edit the data of the table Quartier
     * @param colonne the column to edit
     * @param clePrimaire the primary key of the row to edit
     * @param newValeur the new value to set
     */
    public void modifQuartier(String colonne, int clePrimaire, String newValeur) {
        try {
            String query = "UPDATE Quartier SET " + colonne + " = '" + newValeur + "' WHERE idQuartier = " + clePrimaire + ";";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            modificationDonnees.setEditedData(rowsAffected + " ligne(s) modifiée(s) dans la table Quartier.");
        } catch (SQLException e) {
            modificationDonnees.setEditedData("Erreur lors de la modification de la table Quartier.");
            e.printStackTrace();
        } 
    }

    /**
     * Allows to edit the data of the table Compteur
     * @param colonne the column to edit
     * @param clePrimaire the primary key of the row to edit
     * @param newValeur the new value to set
     */
    public void modifCompteur(String colonne, int clePrimaire, String newValeur) {
        try {
            String query = "UPDATE Compteur SET " + colonne + " = '" + newValeur + "' WHERE idCompteur = " + clePrimaire + ";";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            modificationDonnees.setEditedData(rowsAffected + " ligne(s) modifiée(s) dans la table Compteur.");
        } catch (SQLException e) {
            modificationDonnees.setEditedData("Erreur lors de la modification de la table Compteur.");
            e.printStackTrace();
        }
    }

    /**
     * Allows to edit the data of the table DateInfo
     * @param colonne the column to edit
     * @param clePrimaire the primary key of the row to edit
     * @param newValeur the new value to set
     */
    public void modifDateInfo(String colonne, LocalDate clePrimaire, String newValeur) {
        try {
            String query = "UPDATE DateInfo SET " + colonne + " = '" + newValeur + "' WHERE laDate = '" + clePrimaire + "';";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            modificationDonnees.setEditedData(rowsAffected + " ligne(s) modifiée(s) dans la table DateInfo.");
        } catch (SQLException e) {
            modificationDonnees.setEditedData("Erreur lors de la modification de la table DateInfo.");
            e.printStackTrace();
        }
    }

    /**
     * Allows to edit the data of the table Comptage
     * @param colonne the column to edit
     * @param clePrimaire1 the first primary key of the row to edit
     * @param clePrimaire2 the second primary key of the row to edit
     * @param newValeur the new value to set
     */
    public void modifComptage(String colonne, int clePrimaire1, LocalDate clePrimaire2, String newValeur) {
        try {
            String query = "UPDATE Comptage SET " + colonne + " = '" + newValeur + "' WHERE leCompteur = '" + clePrimaire1 + "' AND dateComptage = '" + clePrimaire2 + "';";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            modificationDonnees.setEditedData(rowsAffected + " ligne(s) modifiée(s) dans la table Comptage.");
        } catch (SQLException e) {
            modificationDonnees.setEditedData("Erreur lors de la modification de la table Comptage.");
            e.printStackTrace();
        }
    }

    /**
     * Getter of the ArrayList of the tables
     * @return the ArrayList of the tables
     */
    public ArrayList<String> getLesTables() {
        return this.lesTables;
    }

    /**
     * Getter of the ArrayList of the columns of the table Quartier
     * @return the ArrayList of the columns of the table Quartier
     */ 
    public ArrayList<String> getColonnesQuartier() {
        return this.colonnesQuartier;
    }

    /**
     * Getter of the ArrayList of the columns of the table Compteur
     * @return the ArrayList of the columns of the table Compteur
     */
    public ArrayList<String> getColonnesCompteur() {
        return this.colonnesCompteur;
    }

    /**
     * Getter of the ArrayList of the columns of the table DateInfo
     * @return the ArrayList of the columns of the table DateInfo
     */
    public ArrayList<String> getColonnesDateInfo() {
        return this.colonnesDateInfo;
    }

    /**
     * Getter of the ArrayList of the columns of the table Comptage
     * @return the ArrayList of the columns of the table Comptage
     */
    public ArrayList<String> getColonnesComptage() {
        return this.colonnesComptage;
    }

    /**
     * Getter of the ArrayList of the primary keys of the table Quartier
     * @return the ArrayList of the primary keys of the table Quartier
     */
    public ArrayList<String> getClePrimaireQuartier() {
        return this.clePrimaireQuartier;
    }

    /**
     * Getter of the ArrayList of the primary keys of the table Compteur
     * @return the ArrayList of the primary keys of the table Compteur
     */
    public ArrayList<String> getClePrimaireCompteur() {
        return this.clePrimaireCompteur;
    }

    /**
     * Getter of the ArrayList of the primary keys of the table DateInfo
     * @return the ArrayList of the primary keys of the table DateInfo
     */
    public DatePicker getClePrimaireDateInfo() {
        return this.clePrimaireDateInfo;
    }

    /**
     * Getter of the ArrayList of the primary keys of the table Comptage
     * @return the ArrayList of the primary keys of the table Comptage
     */
    public ArrayList<String> getClePrimaireComptage1() {
        return this.clePrimaireComptage1;
    }

    /**
     * Getter of the ArrayList of the primary keys of the table Comptage
     * @return the ArrayList of the primary keys of the table Comptage
     */
    public DatePicker getClePrimaireComptage2() {
        return this.clePrimaireComptage2;
    }

}
