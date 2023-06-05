package modele.test;

import modele.Compteur;
import modele.Quartier;

/**
 * Classe TestCompteur pour tester les méthodes de la classe Compteur
 */
public class TestCompteur {
    
    /**
     * Méthode main pour tester les méthodes de la classe Compteur
     * @param args les arguments de la méthode main
     */
    public static void main(String[] args) { 

        ////////////////////////
        // Test Constructeur //
        ///////////////////////

        // Création d'un quartier pour le bon déroulement des tests
        Quartier quartier1 = new Quartier(1, "Quartier 1", 1500);

        /////////////////////////
        // Test classe Compteur //
        /////////////////////////

        System.out.println("");
		System.out.println("\t\u001B[33mTest de la classe Compteur :\u001B[0m");

        System.out.println("");
        System.out.println("\u001B[36m*** Compteur()\u001B[0m");
        try {
            System.out.println("Compteur compteur2 = new Compteur(-1, \"Compteur 2 Sud\", \"Sud\", 45.0, 47.0, null);");
            Compteur compteur2 = new Compteur(-1, "Compteur 2 Sud", "Sud", 45.0, 47.0, null);
            System.err.println("\u001B[31mERREUR : l'exception n'a pas été levée\u001B[0m");
        } catch (IllegalArgumentException e) {
            System.out.println("\u001B[32mOK\u001B[0m");
        }

        try {
            System.out.println("Compteur compteur1 = new Compteur(1, \"Compteur 1 Nord\", \"Nord\", 45.0, 47.0, quartier1);");
            Compteur compteur1 = new Compteur(1, "Compteur 1 Nord", "Nord", 45.0, 47.0, quartier1);
            System.out.println("\u001B[32mOK\u001B[0m");
        } catch (IllegalArgumentException e) {
            System.err.println("\u001B[31mERREUR : l'exception a été levée\u001B[0m");
        }

        // Création des objets pour les tests
        Compteur compteur1 = new Compteur(1, "Compteur 1 Nord", "Nord", 45.0, 47.0, quartier1);
        int idCompteur = 1;
        String libelle = "Compteur 1 Nord";
        String sens = "Nord";
        double latitude = 45.0;
        double longitude = 47.0;
        Quartier quartier = quartier1;

        System.out.println("");
        System.out.println("\u001B[36m*** getIdCompteur()\u001B[0m");

        System.out.println("compteur1.getIdCompteur() = " + compteur1.getIdCompteur());
        if (compteur1.getIdCompteur() == idCompteur) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : l'id du compteur est incorrect\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** getLibelle()\u001B[0m");

        System.out.println("compteur1.getLibelle() = " + compteur1.getLibelle());
        if (compteur1.getLibelle().equals(libelle)) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : le libellé du compteur est incorrect\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** getSens()\u001B[0m");

        System.out.println("compteur1.getSens() = " + compteur1.getSens());
        if (compteur1.getSens().equals(sens)) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : le sens du compteur est incorrect\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** getLatitude()\u001B[0m");

        System.out.println("compteur1.getLatitude() = " + compteur1.getLatitude());
        if (compteur1.getLatitude() == latitude) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : la latitude du compteur est incorrecte\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** getLongitude()\u001B[0m");

        System.out.println("compteur1.getLongitude() = " + compteur1.getLongitude());
        if (compteur1.getLongitude() == longitude) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : la longitude du compteur est incorrecte\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** getLeQuartier()\u001B[0m");

        System.out.println("compteur1.getQuartier() = " + compteur1.getLeQuartier());
        if (compteur1.getLeQuartier().equals(quartier)) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : le quartier du compteur est incorrect\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** setIdCompteur()\u001B[0m");

        System.out.println("compteur1.setIdCompteur(2);");
        compteur1.setIdCompteur(2);
        if (compteur1.getIdCompteur() == 2) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : l'id du compteur n'a pas été modifié\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** setLibelle()\u001B[0m");

        System.out.println("compteur1.setLibelle(\"Compteur 2 Nord\");");
        compteur1.setLibelle("Compteur 2 Nord");
        if (compteur1.getLibelle().equals("Compteur 2 Nord")) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : le libellé du compteur n'a pas été modifié\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** setSens()\u001B[0m");

        System.out.println("compteur1.setSens(\"Sud\");");
        compteur1.setSens("Sud");
        compteur1.setLibelle("Compteur 2 Sud");
        if (compteur1.getSens().equals("Sud")) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : le sens du compteur n'a pas été modifié\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** setLatitude()\u001B[0m");

        System.out.println("compteur1.setLatitude(46.0);");
        compteur1.setLatitude(46.0);
        if (compteur1.getLatitude() == 46.0) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : la latitude du compteur n'a pas été modifiée\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** setLongitude()\u001B[0m");

        System.out.println("compteur1.setLongitude(46.0);");
        compteur1.setLongitude(46.0);
        if (compteur1.getLongitude() == 46.0) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : la longitude du compteur n'a pas été modifiée\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** setLeQuartier()\u001B[0m");

        System.out.println("compteur1.setLeQuartier(quartier2);");
        Quartier quartier2 = new Quartier(2, "Quartier 2", 2500);
        compteur1.setLeQuartier(quartier2);
        if (compteur1.getLeQuartier().equals(quartier2)) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : le quartier du compteur n'a pas été modifié\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** toString()\u001B[0m");

        String expectedOutput = "\u001B[33mInformations du compteur :\u001B[0m\n" +
            "\u001B[32mID compteur\u001B[0m          : 2\n" +
            "\u001B[32mLibellé\u001B[0m              : Compteur 2 Sud\n" +
            "\u001B[32mSens\u001B[0m                 : Sud\n" +
            "\u001B[32mLatitude\u001B[0m             : 46.0\n" +
            "\u001B[32mLongitude\u001B[0m            : 46.0\n";
        String actualOutput = compteur1.toString();

        System.out.println("compteur1.toString() = \n" + actualOutput);
        if (actualOutput.equals(expectedOutput)) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : la méthode toString() ne fonctionne pas correctement\u001B[0m");
        }

        System.out.println("");
        System.out.println("\u001B[36m*** closeTo()\u001B[0m");

        Compteur compteur2 = new Compteur(2, "Compteur 2 Sud", "Sud", 46.0, 6.0, quartier2);
        Compteur compteur3 = new Compteur(3, "Compteur 3 Sud", "Sud", 46.0, 46.1, quartier2);

        System.out.println("compteur1.closeTo(compteur2) = " + compteur1.closeTo(compteur2));
        if (compteur1.closeTo(compteur2)) {
            System.err.println("\u001B[31mERREUR : la méthode closeTo() ne fonctionne pas correctement\u001B[0m");
        } else {
            System.out.println("\u001B[32mOK\u001B[0m");
        }

        System.out.println("compteur1.closeTo(compteur3) = " + compteur1.closeTo(compteur3));
        if (compteur1.closeTo(compteur3)) {
            System.out.println("\u001B[32mOK\u001B[0m");
        } else {
            System.err.println("\u001B[31mERREUR : la méthode closeTo() ne fonctionne pas correctement\u001B[0m");
        }
    }

    /**
     * Constructeur par défaut
     */
    public TestCompteur() {
        super();
    }
}
