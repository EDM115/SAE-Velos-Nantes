package modele.scenario;

import java.util.Calendar;

/**
 * Classe Scenario qui modélise un scénario qui utilise toutes les classes et leurs méhodes
 */
public class Scenario {
    /**
     * Point d'entrée du programme
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 0, 1);
        java.util.Date date = calendar.getTime();
    }
}
