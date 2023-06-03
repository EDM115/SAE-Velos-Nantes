package modele;

/**
 * Classe Compteur qui modélise un compteur avec pluseurs attributs et qui est relié à un quartier, un jour et des attributs portés
 */
public class Compteur {

    // Les attributs de Compteur
    private int idCompteur;
    private String libelle;
    private String sens;
    private double latitude;
    private double longitude;

    // Les relations de Compteur
    private Quartier leQuartier;

    /**
     * Constructeur de la classe Compteur
     * @param idCompteur l'identifiant du compteur
     * @param libelle le libelle du compteur
     * @param latitude la latitude du compteur
     * @param longitude la longitude du compteur
     * @param leQuartier le quartier du compteur
     * @throws IllegalArgumentException si l'identifiant du compteur est négatif, si le libelle est null, si le sens est null ou si le quartier est null
     */
    public Compteur(int idCompteur, String libelle, String sens, double latitude, double longitude, Quartier leQuartier) throws IllegalArgumentException {
        if (idCompteur < 0 || libelle == null || sens == null || leQuartier == null) {
            throw new IllegalArgumentException("Compteur() : Un ou plusieurs paramètres sont null");
        }
        this.idCompteur = idCompteur;
        this.libelle = libelle;
        this.sens = sens;
        this.latitude = latitude;
        this.longitude = longitude;
        this.leQuartier = leQuartier;
    }

    //////////////////////
    // Getter et Setter //
    //////////////////////

    /**
     * Getter de l'identifiant du compteur
     * @return l'identifiant du compteur
     */
    public int getIdCompteur() {
        return this.idCompteur;
    }

    /**
     * Setter de l'identifiant du compteur
     * @param idCompteur l'identifiant du compteur
     */
    public void setIdCompteur(int idCompteur) {
        this.idCompteur = idCompteur;
    }

    /**
     * Getter du libelle du compteur
     * @return le libelle du compteur
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Setter du libelle du compteur
     * @param libelle le libelle du compteur
     */
    public void setLibelle(String libelle) {
        this.libelle= libelle;
    }

    /**
     * Getter du sens du compteur
     * @return le sens du compteur
     */
    public String getSens() {
        return this.sens;
    }

    /**
     * Setter du sens du compteur
     * @param sens le sens du compteur
     */
    public void setSens(String sens) {
        this.sens= sens;
    }

    /**
     * Getter de la latitude du compteur
     * @return la latitude du compteur
     */
    public double getLattitude() {
        return this.latitude;
    }

    /**
     * Setter de la latitude du compteur
     * @param lattitude la latitude du compteur
     */
    public void setLattitude(double lattitude) {
        this.latitude= lattitude;
    }

    /**
     * Getter de la longitude du compteur
     * @return la longitude du compteur
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Setter de la longitude du compteur
     * @param longitude la longitude du compteur
     */
    public void setLongitude(double longitude) {
        this.longitude= longitude;
    }

    /**
     * Getter du quartier du compteur
     * @return le quartier du compteur
     */
    public Quartier getLeQuartier() {
        return this.leQuartier;
    }

    /**
     * Setter du quartier du compteur
     * @param leQuartier le quartier du compteur
     */
    public void setLeQuartier(Quartier leQuartier) {
        this.leQuartier = leQuartier;
    }

    ///////////////////////
    // Méthodes ajoutées //
    ///////////////////////

    /**
     * Affiche toutes les informations du Compteur
     * @return toutes les informations du Compteur
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[33mInformations du compteur :\u001B[0m\n");
        String[][] tableauAttributs = {
            {"\u001B[32mID compteur\u001B[0m", String.valueOf(this.idCompteur)},
            {"\u001B[32mLibellé\u001B[0m", this.libelle},
            {"\u001B[32mSens\u001B[0m", this.sens},
            {"\u001B[32mLatitude\u001B[0m", String.valueOf(this.latitude)},
            {"\u001B[32mLongitude\u001B[0m", String.valueOf(this.longitude)}
        };
        for (String[] attribut : tableauAttributs) {
            sb.append(String.format("%-30s: %s\n", attribut[0], attribut[1]));
        }
        return sb.toString();
    }
}