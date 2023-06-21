package backend;

import frontend.Menu;
import frontend.RechercheTrajet;
import frontend.ResultatsRecherche;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConnexionBdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
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
        setupButtonActions();
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

    public void setupButtonActions() {
        rechercheTrajet.getSearchButton().setOnAction(event -> {
            String departure = rechercheTrajet.getDeparture();
            String arrival = rechercheTrajet.getArrival();
            int hour = rechercheTrajet.getHour();
            LocalDate date = rechercheTrajet.getDate();

            this.rechercherTrajet(departure, arrival, hour, date);
        });
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

            System.out.println("Affluence estimée : " + (nbCyclistes + nbCyclistes2));
            System.out.println("Température : " + temperature);
            System.out.println("Anomalie à " + departure + " : " + anomalie);
            System.out.println("Anomalie à " + arrival + " : " + anomalie2);

            // add all data to a String[], including the departure and arrival
            String[] data = new String[6];
            data[0] = departure;
            data[1] = arrival;
            data[2] = Integer.toString(nbCyclistes + nbCyclistes2);
            data[3] = Double.toString(temperature);
            data[4] = anomalie;
            data[5] = anomalie2;

            // start a frontend.ResultatsRecherche with these results
            ResultatsRecherche resultatsRecherche = new ResultatsRecherche(true, data);
            resultatsRecherche.start(this.rechercheTrajet.getStage());

            
        } catch (SQLSyntaxErrorException e) {
            System.out.println("\u001B[31mERREUR\u001B[0m");
        } catch (SQLException e) {
            System.out.println("\u001B[31mREQUETE IMPOSSIBLE\u001B[0m");
        } 
    }
}

