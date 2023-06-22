package backend;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import frontend.Menu;
import frontend.SaisieDonnees;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConnexionBdd;

public class SaisieDonneesB extends Application {

    private ArrayList<String> lesTables;
    private SaisieDonnees saisieDonnees;

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

    public SaisieDonneesB(SaisieDonnees saisieDonnees) {
        this.saisieDonnees = saisieDonnees;
        this.lesTablesBdd();
    }

    public void lesTablesBdd() {
        this.lesTables = new ArrayList<String>();
        this.lesTables.add("Quartier");
        this.lesTables.add("Compteur");
        this.lesTables.add("DateInfo");
        this.lesTables.add("Comptage");
    }

    public void ajoutQuartier(int idQuartier, String nomQuartier, double longueurPisteVelo) {
        try {
            String query = "INSERT INTO Quartier VALUES (" + idQuartier + ", '" + nomQuartier + "', " + longueurPisteVelo + ");";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);
        
        // Vérifiez le nombre de lignes affectées si nécessaire
        System.out.println(rowsAffected + " ligne(s) ajoutée(s) dans la table Quartier.");

        } catch (SQLException e) {
            System.out.println("\u001B[31mERREUR INSERTION IMPOSSIBLE\u001B[0m");
        } 
    }

    public void ajoutCompteur(int idCompteur, String nomCompteur, String sens, double coord_X, double coord_Y, int leQuartier) {
        try {
            String query = "INSERT INTO Compteur VALUES (" + idCompteur + ", '" + nomCompteur + "', '" + sens + "', " + coord_X + ", " + coord_Y + ", " + leQuartier + ");";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            // Vérifiez le nombre de lignes affectées si nécessaire
            System.out.println(rowsAffected + " ligne(s) ajoutée(s) dans la table Compteur.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajoutDateInfo(LocalDate laDate, double tempMoy, String jour, String vacances) {
        try {
            String query = "INSERT INTO DateInfo VALUES ('" + laDate + "','" + tempMoy + "', '" + jour + "', '" + vacances + "');";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            // Vérifiez le nombre de lignes affectées si nécessaire
            System.out.println(rowsAffected + " ligne(s) ajoutée(s) dans la table DateInfo.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ajoutComptage(int leCompteur, LocalDate dateComptage, int h00, int h01, int h02, int h03, int h04, int h05, int h06, int h07, int h08, int h09, int h10, int h11, int h12, int h13, int h14, int h15, int h16, int h17, int h18, int h19, int h20, int h21, int h22, int h23, String presenceAnomalie) {
        try {
            String query = "INSERT INTO Comptage VALUES ('" + leCompteur + "', '" + dateComptage + "', '" + h00 + "', '" + h01 + "', '" + h02 + "', '" + h03 + "', '" + h04 + "', '" + h05 + "', '" + h06 + "', '" + h07 + "', '" + h08 + "', '" + h09 + "', '" + h10 + "', '" + h11 + "', '" + h12 + "', '" + h13 + "', '" + h14 + "', '" + h15 + "', '" + h16 + "', '" + h17 + "', '" + h18 + "', '" + h19 + "', '" + h20 + "', '" + h21 + "', '" + h22 + "', '" + h23 + "', '" + presenceAnomalie + "');";

            ConnexionBdd connexionBdd = new ConnexionBdd();
            Statement statement = connexionBdd.getConnection().createStatement();
            int rowsAffected = statement.executeUpdate(query);

            // Vérifiez le nombre de lignes affectées si nécessaire
            System.out.println(rowsAffected + " ligne(s) ajoutée(s) dans la table Comptage.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getLesTables() {
        return this.lesTables;
    }
}
