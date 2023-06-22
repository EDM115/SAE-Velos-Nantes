package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import frontend.Menu;
import frontend.ModificationDonnees;
import javafx.application.Application;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import utils.ConnexionBdd;

public class ModificationDonneesB extends Application {

    private ArrayList<String> lesTables;
    private ModificationDonnees modificationDonnees;

    private ArrayList<String> colonnesQuartier;
    private ArrayList<String> colonnesCompteur;
    private ArrayList<String> colonnesDateInfo;
    private ArrayList<String> colonnesComptage;

    private ArrayList<String> clePrimaireQuartier;
    private ArrayList<String> clePrimaireCompteur;
    private DatePicker clePrimaireDateInfo;
    private ArrayList<String> clePrimaireComptage1;
    private DatePicker clePrimaireComptage2;

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

    public ModificationDonneesB(ModificationDonnees modificationDonnees) {
        this.modificationDonnees = modificationDonnees;
        this.lesTablesBdd();
    }

    public void lesTablesBdd() {
        this.lesTables = new ArrayList<String>();
        this.lesTables.add("Quartier");
        this.lesTables.add("Compteur");
        this.lesTables.add("DateInfo");
        this.lesTables.add("Comptage");
    }

    public void lesColonnesQuartier() {
        this.colonnesQuartier = new ArrayList<String>();
        this.colonnesQuartier.add("idQuartier");
        this.colonnesQuartier.add("nomQuartier");
        this.colonnesQuartier.add("longueurPisteVelo");
    }

    public void lesColonnesCompteur() {
        this.colonnesCompteur = new ArrayList<String>();
        this.colonnesCompteur.add("idCompteur");
        this.colonnesCompteur.add("nomCompteur");
        this.colonnesCompteur.add("sens");
        this.colonnesCompteur.add("coord_X");
        this.colonnesCompteur.add("coord_Y");
        this.colonnesCompteur.add("leQuartier");
    }

    public void lesColonnesDateInfo() {
        this.colonnesDateInfo = new ArrayList<String>();
        this.colonnesDateInfo.add("laDate");
        this.colonnesDateInfo.add("tempMoy");
        this.colonnesDateInfo.add("jour");
        this.colonnesDateInfo.add("vacances");
    }

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
            System.out.println("\u001B[31mERREUR UPDATE IMPOSSIBLE\u001B[0m");
        }
    }

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
            System.out.println("\u001B[31mERREUR UPDATE IMPOSSIBLE\u001B[0m");
        }
    }

    public void clePrimaireDateInfo() {
        this.clePrimaireDateInfo = new DatePicker();
    }

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
            System.out.println("\u001B[31mERREUR UPDATE IMPOSSIBLE ouo\u001B[0m");
        }
    }

    public void clePrimaireComptage2() {
        this.clePrimaireComptage2 = new DatePicker();
    }


    public void modifQuartier(String colonne, int clePrimaire, String newValeur) {
        try {
            String query = "UPDATE Quartier SET " + colonne + " = '" + newValeur + "' WHERE idQuartier = " + clePrimaire + ";";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            System.out.println(rowsAffected + " ligne(s) modifiée(s) dans la table Quartier.");

        } catch (SQLException e) {
            System.out.println("\u001B[31mERREUR INSERTION IMPOSSIBLE\u001B[0m");
        } 
    }

    public void modifCompteur(String colonne, int clePrimaire, String newValeur) {
        try {
            String query = "UPDATE Compteur SET " + colonne + " = '" + newValeur + "' WHERE idCompteur = " + clePrimaire + ";";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            System.out.println(rowsAffected + " ligne(s) modifiée(s) dans la table Compteur.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifDateInfo(String colonne, LocalDate clePrimaire, String newValeur) {
        try {
            String query = "UPDATE DateInfo SET " + colonne + " = '" + newValeur + "' WHERE laDate = '" + clePrimaire + "';";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            System.out.println(rowsAffected + " ligne(s) modifiée(s) dans la table DateInfo.");
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifComptage(String colonne, int clePrimaire1, LocalDate clePrimaire2, String newValeur) {
        try {
            String query = "UPDATE Comptage SET " + colonne + " = '" + newValeur + "' WHERE leCompteur = '" + clePrimaire1 + "' AND dateComptage = '" + clePrimaire2 + "';";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            System.out.println(rowsAffected + " ligne(s) modifiée(s) dans la table Comptage.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public ArrayList<String> getLesTables() {
        return this.lesTables;
    }

    public ArrayList<String> getColonnesQuartier() {
        return this.colonnesQuartier;
    }

    public ArrayList<String> getColonnesCompteur() {
        return this.colonnesCompteur;
    }

    public ArrayList<String> getColonnesDateInfo() {
        return this.colonnesDateInfo;
    }

    public ArrayList<String> getColonnesComptage() {
        return this.colonnesComptage;
    }

    public ArrayList<String> getClePrimaireQuartier() {
        return this.clePrimaireQuartier;
    }

    public ArrayList<String> getClePrimaireCompteur() {
        return this.clePrimaireCompteur;
    }

    public DatePicker getClePrimaireDateInfo() {
        return this.clePrimaireDateInfo;
    }

    public ArrayList<String> getClePrimaireComptage1() {
        return this.clePrimaireComptage1;
    }

    public DatePicker getClePrimaireComptage2() {
        return this.clePrimaireComptage2;
    }
}
