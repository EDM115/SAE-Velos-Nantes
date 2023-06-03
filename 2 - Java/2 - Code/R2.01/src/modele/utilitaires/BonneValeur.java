package modele.utilitaires;

// PAS UTILISE POUR LINSTANT

/**
 * Classe BonneValeur qui vérifie qu'un attribut est bien dans son domaine
 */
public class BonneValeur {
    
    // Les domaines des attributs
    private final String[] DOMAINE_JOUR = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    private final String[] DOMAINE_VACANCES = {"Noel", "Ascension", "Hiver", "Ete", "Toussaint", "Printemps", ""}; 
    private final String[] DOMAINE_ANOMALIE = {"Forte", "Faible", ""};
    // guillemets vides pour les attributs qui peuvent être null

    /**
     * Constructeur de la classe BonneValeur
     */
    public BonneValeur() {
    }

    //////////////////////
    // Getter et Setter //
    //////////////////////

    /**
     * Getter du domaine de l'attribut jour
     * @return le domaine de l'attribut jour
     */
    public String[] getDOMAINE_JOUR() {
        return DOMAINE_JOUR;
    }

    /**
     * Getter du domaine de l'attribut vacances
     * @return le domaine de l'attribut vacances
     */
    public String[] getDOMAINE_VACANCES() {
        return DOMAINE_VACANCES;
    }

    /**
     * Getter du domaine de l'attribut anomalie
     * @return le domaine de l'attribut anomalie
     */
    public String[] getDOMAINE_ANOMALIE() {
        return DOMAINE_ANOMALIE;
    }

    ///////////////////////
    // Méthodes ajoutées //
    ///////////////////////

    /**
     * Vérifie que la valeur de l'attribut jour est bien dans son domaine
     * @param value la valeur de l'attribut jour
     * @return true si la valeur est dans le domaine, false sinon
     */
    public boolean jourIsValid(String value) {
        boolean ret = false;
        for (String validValue : DOMAINE_JOUR) {
            if (validValue.equals(value)) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * Vérifie que la valeur de l'attribut vacances est bien dans son domaine
     * @param value la valeur de l'attribut vacances
     * @return true si la valeur est dans le domaine, false sinon
     */
    public boolean vacancesIsValid(String value) {
        boolean ret = false;
        for (String validValue : DOMAINE_VACANCES) {
            if (validValue.equals(value)) {
                ret = true;
            }
        }
        return ret;
    }

    /**
     * Vérifie que la valeur de l'attribut anomalie est bien dans son domaine
     * @param value la valeur de l'attribut anomalie
     * @return true si la valeur est dans le domaine, false sinon
     */
    public boolean anomalieIsValid(String value) {
        boolean ret = false;
        for (String validValue : DOMAINE_ANOMALIE) {
            if (validValue.equals(value)) {
                ret = true;
            }
        }
        return ret;
    }
}
