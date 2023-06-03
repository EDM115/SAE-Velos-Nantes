package modele.scenario;

//import java.sql.Date;
import java.util.Calendar;

import modele.Compteur;
import modele.Jour;
import modele.Quartier;
import modele.utilitaires.AttributsPortes;


public class ScenarioCompteur {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 0, 1);
        java.util.Date date = calendar.getTime();
    }
}
