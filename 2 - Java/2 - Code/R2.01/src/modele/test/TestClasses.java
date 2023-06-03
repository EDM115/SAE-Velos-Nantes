package modele.test;

import java.time.LocalDate;

import modele.AttributPortee;
import modele.Compteur;
import modele.Jour;
import modele.Quartier;

/**
 * Classe TestCompteur pour tester les méthodes de la classe Compteur
 */
public class TestClasses {
    
    /**
     * Méthode main pour tester les méthodes de la classe Compteur
     */
    public static void main(String[] args) {

        ////////////////////////
        // Test Constructeur //
        ///////////////////////

        // Création d'un quatuor Compteur Quartire Jour AttributPortee
        Quartier quartier1 = new Quartier(1, "Quartier 1", 1500);
        LocalDate date1 = LocalDate.of(2020, 2, 5);
        Jour jour1 = new Jour(date1, 5.0, "Mercredi", "Hiver");
        int[] tabH1 = new int[] {14, 15, 78, 41, 56, 894, 147, 47, 5, 78, 21, 25, 45, 65, 45, 78, 98, 47, 52, 51, 51, 21, 21, 47};
        Compteur compteur1 = new Compteur(1, "Compteur 1 Nord", "Nord", 45.0, 47.0, quartier1);
        AttributPortee attributPortee1 = new AttributPortee(tabH1, "", compteur1, jour1);

        /////////////////////////
        // Test lasse Compteur //
        /////////////////////////

        System.out.println("Test de la classe Compteur :");
        System.out.println(compteur1.toString());

        System.out.println(jour1.toString());
        System.out.println(quartier1.toString());
        System.out.println(attributPortee1.toString());


        //////////////////////////
        // Test classe Quartier //
        //////////////////////////

        //////////////////////
        // Test classe Jour //
        //////////////////////

        ////////////////////////////////
        // Test classe AttributPortee //
        ////////////////////////////////

        /////////////////////////////
        // Test classe BonneValeur //
        /////////////////////////////

    }

}
