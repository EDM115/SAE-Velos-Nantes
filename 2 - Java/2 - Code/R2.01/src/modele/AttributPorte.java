package modele;

import modele.utilitaires.BonneValeur;

/**
 * Classe AttributPorte qui modélise le nombre de passagers par heure et la présence d'anomalie
 */
public class AttributPorte {
    
    // Les attributs de AttributPorte

    /**
     * Le tableau du nombre de passagers par heure
     */
    private int[] tabNbPassagesParHeure = new int[24];
    /**
     * La présence d'anomalie
     */
    private String presenceAnomalie;

    // Les relations de AttributPorte
    
    /**
     * Le compteur
     */
    private Compteur leCompteur;
    /**
     * Le jour
     */
    private Jour leJour;

    /**
     * BonneValeur pour vérifier les valeurs des attributs
     */
    private BonneValeur bonneValeur = new BonneValeur();

    /**
     * Constructeur de la classe AttributPorte
     * @param tabNbPassagesParHeure le tableau du nombre de passagers par heure
     * @param presenceAnomalie la présence d'anomalie
     * @param leCompteur le compteur
     * @param leJour le jour
     * @throws IllegalArgumentException si l'attribut presenceAnomalie n'est pas dans une bonne plage de valeur
     */
    public AttributPorte(int[] tabNbPassagesParHeure, String presenceAnomalie, Compteur leCompteur, Jour leJour) throws IllegalArgumentException{
        if (!(bonneValeur.anomalieIsValid(presenceAnomalie))) {
            throw new IllegalArgumentException("AttributPorte() : L'attribut presenceAnomalie n'est pas dans une bonne plage de valeur");
        }
        if (tabNbPassagesParHeure == null || presenceAnomalie == null || leCompteur == null || leJour == null) {
            throw new IllegalArgumentException("AttributPorte() : Un ou plusieurs paramètres sont null");
        }
        this.tabNbPassagesParHeure = tabNbPassagesParHeure;
        this.presenceAnomalie = presenceAnomalie;
        this.leCompteur = leCompteur;
        this.leJour = leJour;

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
     * @throws IllegalArgumentException si le paramètre tabNbPassagesParHeure est null
     */
    public void setTabNbPassagesParHeure(int[] tabNbPassagesParHeure) throws IllegalArgumentException {
        if (tabNbPassagesParHeure == null) {
            throw new IllegalArgumentException("setTabNbPassagesParHeure() : Le paramètre tabNbPassagesParHeure est null");
        }
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
     * @throws IllegalArgumentException si l'attribut presenceAnomalie n'est pas dans une bonne plage de valeur ou est null
     */
    public void setPresenceAnomalie(String presenceAnomalie) throws IllegalArgumentException {
        if (presenceAnomalie == null) {
            throw new IllegalArgumentException("setPresenceAnomalie() : Le paramètre presenceAnomalie est null");
        }
        if (!(bonneValeur.anomalieIsValid(presenceAnomalie))) {
            throw new IllegalArgumentException("setPresenceAnomalie() : L'attribut presenceAnomalie n'est pas dans une bonne plage de valeur");
        }
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
     * @throws IllegalArgumentException si le paramètre leCompteur est null
     */
    public void setLeCompteur(Compteur leCompteur) throws IllegalArgumentException {
        if (leCompteur == null) {
            throw new IllegalArgumentException("setLeCompteur() : Le paramètre leCompteur est null");
        }
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
     * @throws IllegalArgumentException si le paramètre leJour est null
     */
    public void setLeJour(Jour leJour) throws IllegalArgumentException {
        if (leJour == null) {
            throw new IllegalArgumentException("setLeJour() : Le paramètre leJour est null");
        }
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
     * Affiche toutes les informations de l'attribut porté
     * @return toutes les informations de l'attribut porté
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[33mInformations sur l'attribut porté :\u001B[0m\n");
        String[] tabHeures = {"00h-01h", "01h-02h", "02h-03h", "03h-04h", "04h-05h", "05h-06h", "06h-07h", "07h-08h", "08h-09h", "09h-10h", "10h-11h", "11h-12h", "12h-13h", "13h-14h", "14h-15h", "15h-16h", "16h-17h", "17h-18h", "18h-19h", "19h-20h", "20h-21h", "21h-22h", "22h-23h", "23h-00h"};
        for (int i = 0; i < tabHeures.length; i++) {
            sb.append("\u001B[32m" + tabHeures[i] + "\u001B[0m : " + this.tabNbPassagesParHeure[i] + "\n");
        }
        sb.append("\u001B[32mPrésence d'anomalie\u001B[0m : " + this.presenceAnomalie + "\n");
        return sb.toString();
    }
}
