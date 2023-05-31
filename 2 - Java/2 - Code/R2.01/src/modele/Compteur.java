package modele;

import modele.utilitaires.AttributsPortees;

public class Compteur {

    private int idCompteur;
    private String libelle;
    private String sens;
    private double lattitude;
    private double longitude;

    private Quartier leQuartier;
    private Jour leJour;
    private AttributsPortees lesAttributsPortees;

    public Compteur(int idCompteur, String libelle, String sens, double lattitude, double longitude, Quartier leQuartier, Jour leJour, AttributsPortees lesAttributsPortees) {
        this.idCompteur = idCompteur;
        this.libelle = libelle;
        this.sens = sens;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.leQuartier = leQuartier;
        this.leJour = leJour;
        this.lesAttributsPortees = lesAttributsPortees;
    }


    public int getIdCompteur() {
        return idCompteur;
    }

    public void setIdCompteur(int idCompteur) {
        this.idCompteur = idCompteur;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle= libelle;
    }

    public String getSens() {
        return sens;
    }

    public void setSens(String sens) {
        this.sens= sens;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude= lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude= longitude;
    }




    public String getNbPassagersTotal() {
        String ret = "";
        int[] tabNbPassagesParHeure = lesAttributsPortees.getTabNbPassagesParHeure();
        int somme = 0;
        for (int i = 0; i < tabNbPassagesParHeure.length; i++) {
            somme += tabNbPassagesParHeure[i];
        }
        ret += "Nombre de passages total : " + somme + "\n";
        return ret;
    }


}