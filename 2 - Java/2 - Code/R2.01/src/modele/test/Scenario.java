package modele.test;

import java.time.LocalDate;

import modele.AttributPorte;
import modele.Compteur;
import modele.Jour;
import modele.Quartier;

/**
 * Classe Scenario qui modélise un scénario qui utilise toutes les classes et leurs méhodes
 */
public class Scenario {
    /**
     * Point d'entrée du programme
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("\t\u001B[33mTest du scénario :\u001B[0m");
        
        System.out.println("");
        System.out.println("\u001B[33m*** Création des objets\u001B[0m");
        
        System.out.println("LocalDate date = LocalDate.now()");
        LocalDate date = LocalDate.now();
        System.out.println("Quartier quartier = new Quartier(1, \"Centre Ville\", 21548.7)");
        Quartier quartier = new Quartier(1, "Centre Ville", 21548.7);
        System.out.println("Jour jour = new Jour(date, 12.5, date.getDayOfWeek().toString(), \"Hors Vacances\")");
        Jour jour = new Jour(date, 12.5, date.getDayOfWeek().toString(), "Hors Vacances");
        System.out.println("Compteur compteur = new Compteur(787, \"50 Otages\", \"Sud\", 47.219974801673004, -1.5550472960683386, quartier)");
        Compteur compteur = new Compteur(787, "50 Otages", "Sud", 47.219974801673004, -1.5550472960683386, quartier);
        System.out.println("AttributPorte attributPorte = new AttributPorte(new int[] {12, 15, 78, 41, 56, 894, 147, 47, 5, 78, 21, 25, 45, 65, 45, 78, 98, 47, 52, 51, 51, 21, 21, 47}, \"\", compteur, jour)");
        AttributPorte attributPorte = new AttributPorte(new int[] {12, 15, 78, 41, 56, 894, 147, 47, 5, 78, 21, 25, 45, 65, 45, 78, 98, 47, 52, 51, 51, 21, 21, 47}, "", compteur, jour);

        System.out.println("");
        System.out.println("\u001B[33m*** Affichage des objets\u001B[0m");

        System.out.println("");
        System.out.println("\u001B[36mQuartier :\n\u001B[0m" + quartier.toString());
        System.out.println("\u001B[36mJour :\n\u001B[0m" + jour.toString());
        System.out.println("\u001B[36mCompteur :\n\u001B[0m" + compteur.toString());
        System.out.println("\u001B[36mAttributPorte :\n\u001B[0m" + attributPorte.toString());

        System.out.println("");
        System.out.println("\u001B[33m*** Modification des objets\u001B[0m");

        System.out.println("");
        quartier.setIdQuartier(2);
        System.out.println("\u001B[36mquartier.setIdQuartier(2) : \u001B[0m" + quartier.getIdQuartier());
        jour.setVacances("Ete");
        System.out.println("\u001B[36mjour.setVacances(\"Ete\") : \u001B[0m" + jour.getVacances());

        System.out.println("");
        System.out.println("\u001B[33m*** Cas pratique\u001B[0m");

        System.out.println("");
        System.out.println("\u001B[36mCombien de jours il y a-t-il entre la date d'aujourd'hui et le 1er Avril 2023 : \u001B[0m" + jour.getNombreJours(LocalDate.of(2023, 4, 1)));
        System.out.println("\u001B[36mCombien de jours il y a-t-il entre la date d'aujourd'hui et le 25 Décembre 2023 : \u001B[0m" + jour.getNombreJours(LocalDate.of(2023, 12, 25)));
        System.out.print("\u001B[36mCombien de passages il y a eu aujourd'hui : \u001B[0m" + attributPorte.getNbPassagesTotal());
        System.out.println("\u001B[36mQuelle est la longueur en kilomètres des pistes de vélo de la rue du 50 Otages : \u001B[0m" + compteur.getLeQuartier().conversionEnKilometre());
        System.out.println("");
        Compteur compteur2 = new Compteur(644, "Bonduelle", "Sud", 47.21156230858751, -1.5434019650772974, quartier);
        System.out.println(compteur2.toString());
        System.out.println("\u001B[36mEst-ce que le compteur de 50 Otages est à moins de 1 kilomètre du compteur de Bonduelle : \u001B[0m" + compteur.closeTo(compteur2));
    }

    /**
     * Constructeur par défaut
     */
    public Scenario() {
        super();
    }
}
