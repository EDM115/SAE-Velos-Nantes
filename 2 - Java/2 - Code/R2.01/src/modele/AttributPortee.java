package modele;

import modele.utilitaires.BonneValeur;

/**
 * Classe AttributsPortees qui modélise le nombre de passagers par heure et la présence d'anomalie
 */
public class AttributPortee {
    
    // Les attributs de AttributsPortees
    private int[] tabNbPassagesParHeure = new int[24];
    private String presenceAnomalie;

    // Les relations de AttributsPortees
    private Compteur leCompteur;
    private Jour leJour;

    // BonneValeur pour vérifier les valeurs des attributs
    private BonneValeur bonneValeur = new BonneValeur();

    /**
     * Constructeur de la classe AttributsPortees
     * @param tabNbPassagesParHeure le tableau du nombre de passagers par heure
     * @param presenceAnomalie la présence d'anomalie
     * @param leCompteur le compteur
     * @param leJour le jour
     * @throws IllegalArgumentException si l'attribut presenceAnomalie n'est pas dans une bonne plage de valeur
     */
    public AttributPortee(int[] tabNbPassagesParHeure, String presenceAnomalie, Compteur leCompteur, Jour leJour) throws IllegalArgumentException{
        if (!(bonneValeur.anomalieIsValid(presenceAnomalie))) {
            throw new IllegalArgumentException("AttributPortee :L'attribut presenceAnomalie n'est pas dans une bonne plage de valeur");
        } else {
            this.tabNbPassagesParHeure = tabNbPassagesParHeure;
            this.presenceAnomalie = presenceAnomalie;
            this.leCompteur = leCompteur;
            this.leJour = leJour;
        }
    }

    //////////////////////
    // Getter et Setter //
    //////////////////////

    /**
     * Getter du tableau du nombre de passagers par heure
     * @return le tableau du nombre de passagers par heure
     */
    public int[] getTabNbPassagesParHeure() {
        return tabNbPassagesParHeure;
    }

    /**
     * Setter du tableau du nombre de passagers par heure
     * @param tabNbPassagesParHeure le tableau du nombre de passagers par heure
     */
    public void setTabNbPassagesParHeure(int[] tabNbPassagesParHeure) {
        this.tabNbPassagesParHeure = tabNbPassagesParHeure;
    }

    /**
     * Getter de la présence d'anomalie
     * @return la présence d'anomalie
     */
    public String getPresenceAnomalie() {
        return presenceAnomalie;
    }

    /**
     * Setter de la présence d'anomalie
     * @param presenceAnomalie la présence d'anomalie
     */
    public void setPresenceAnomalie(String presenceAnomalie) {
        this.presenceAnomalie = presenceAnomalie;
    }

    /**
     * Getter du compteur
     * @return le compteur
     */
    public Compteur getLeCompteur() {
        return leCompteur;
    }

    /**
     * Setter du compteur
     * @param leCompteur le compteur
     */
    public void setLeCompteur(Compteur leCompteur) {
        this.leCompteur = leCompteur;
    }

    /**
     * Getter du jour
     * @return le jour
     */
    public Jour getLeJour() {
        return leJour;
    }

    /**
     * Setter du jour
     * @param leJour le jour
     */
    public void setLeJour(Jour leJour) {
        this.leJour = leJour;
    }

    ///////////////////////
    // Méthodes ajoutées //
    ///////////////////////

    /**
     * Affiche le nombre de passsages total du compteur sur la journée
     * @return le nombre de passages total du compteur sur la journée
     */
    public String getNbPassagesTotal() {
        String ret = "";
        int somme = 0;
        for (int i = 0; i < tabNbPassagesParHeure.length; i++) {
            somme += this.tabNbPassagesParHeure[i];
        }
        ret += "Nombre de passages total : " + somme + "\n";
        return ret;
    }

    /**
     * Affiche toutes les informations de l'attribut portee
     * @return toutes les informations de l'attribut portee
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[33mInformations sur l'attribut portee :\u001B[0m\n");
        String[] tabHeures = {"00h-01h", "01h-02h", "02h-03h", "03h-04h", "04h-05h", "05h-06h", "06h-07h", "07h-08h", "08h-09h", "09h-10h", "10h-11h", "11h-12h", "12h-13h", "13h-14h", "14h-15h", "15h-16h", "16h-17h", "17h-18h", "18h-19h", "19h-20h", "20h-21h", "21h-22h", "22h-23h", "23h-00h"};
        for (int i = 0; i < tabHeures.length; i++) {
            sb.append("\u001B[32m" + tabHeures[i] + "\u001B[0m : " + this.tabNbPassagesParHeure[i] + "\n");
        }
        sb.append("\u001B[32mPrésence d'anomalie\u001B[0m : " + this.presenceAnomalie + "\n");
        return sb.toString();
    }
}
