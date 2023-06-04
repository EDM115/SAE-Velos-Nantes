package modele;

/**
 * Classe Quartier qui modélise un quartier avec plusieurs attributs
 */
public class Quartier {
    
    // Les attributs de Quartier
    private int idQuartier;
    private String nomQuartier;
    private double lgPisteCyclable;

    /**
     * Constructeur de la classe Quartier
     * @param idQuartier l'identifiant du quartier
     * @param nomQuartier le nom du quartier
     * @param lgPisteCyclable la longueur de la piste cyclable du quartier
     */
    public Quartier(int idQuartier, String nomQuartier, double lgPisteCyclable) throws IllegalArgumentException {
        if (idQuartier < 0 || nomQuartier == null || lgPisteCyclable < 0) {
            throw new IllegalArgumentException("Quartier() : Un ou plusieurs paramètres sont null");
        }
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.lgPisteCyclable = lgPisteCyclable;
    }

    //////////////////////
    // Getter et Setter //
    //////////////////////

    /**
     * Getter de l'identifiant du quartier
     * @return l'identifiant du quartier
     */
    public int getIdQuartier() {
        return this.idQuartier;
    }

    /**
     * Setter de l'identifiant du quartier
     * @param idQuartier l'identifiant du quartier
     */
    public void setIdQuartier(int idQuartier) {
        this.idQuartier = idQuartier;
    }

    /**
     * Getter du nom du quartier
     * @return le nom du quartier
     */
    public String getNomQuartier() {
        return this.nomQuartier;
    }

    /**
     * Setter du nom du quartier
     * @param nomQuartier le nom du quartier
     */
    public void setNomQuartier(String nomQuartier) {
        this.nomQuartier= nomQuartier;
    }

    /**
     * Getter de la longueur de la piste cyclable du quartier
     * @return la longueur de la piste cyclable du quartier
     */
    public double getLgPisteCyclable() {
        return this.lgPisteCyclable;
    }

    /**
     * Setter de la longueur de la piste cyclable du quartier
     * @param lgPisteCyclable la longueur de la piste cyclable du quartier
     */
    public void setLgPisteCyclable(double lgPisteCyclable) {
        this.lgPisteCyclable= lgPisteCyclable;
    }

    ///////////////////////
    // Méthodes ajoutées //
    ///////////////////////

    /**
     * Méthode qui convertit la longueur de la piste cyclable du quartier en kilomètre
     * @return la longueur de la piste cyclable du quartier en kilomètre
     */
    public double conversionEnKilometre() {
        return this.lgPisteCyclable / 1000;
    }

    /**
     * Affiche toutes les informations du quartier
     * @return les informations du quartier
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[33mInformations du quartier :\u001B[0m\n");
        String[][] tableauAttributs = {
            {"\u001B[32mID quartier\u001B[0m", String.valueOf(this.idQuartier)},
            {"\u001B[32mNom quartier\u001B[0m", this.nomQuartier},
            {"\u001B[32mLongueur piste \u001B[0m", String.valueOf(this.lgPisteCyclable)},
        };
        for (String[] attribut : tableauAttributs) {
            sb.append(String.format("%-30s: %s\n", attribut[0], attribut[1]));
        }
        return sb.toString();
    }

    /**
     * Méthode qui compare deux quartiers
     * @param quartier le quartier à comparer
     * @return true si les deux quartiers sont identiques, false sinon
     */
    public boolean equals(Quartier quartier) {
        return this.idQuartier == quartier.getIdQuartier() && this.nomQuartier.equals(quartier.getNomQuartier()) && this.lgPisteCyclable == quartier.getLgPisteCyclable();
    }
}
