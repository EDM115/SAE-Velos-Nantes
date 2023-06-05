package modele.utilitaires;

/**
 * Classe BonneValeur qui vérifie qu'un attribut est bien dans son domaine
 */
public class BonneValeur {
    
    // Les domaines des attributs

    /**
     * Les jours en anglais
     */
    private final String[] JOURS_ANGLAIS = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    /**
     * Les jours en français
     */
    private final String[] DOMAINE_JOUR = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    /**
     * Les vacances
     */
    private final String[] DOMAINE_VACANCES = {"Noel", "Ascension", "Hiver", "Ete", "Toussaint", "Printemps", "Hors Vacances", ""};
    /**
     * Les anomalies
     */
    private final String[] DOMAINE_ANOMALIE = {"Forte", "Faible", ""};
    // guillemets vides pour les attributs qui peuvent être null

    /**
     * Constructeur de la classe BonneValeur
     */
    public BonneValeur() {
        super();
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
     * Méthode qui tranforme un jour anglais en jour français (à cause de LocalDate.getDayOfWeek())
     * @param value le jour en anglais
     * @return le jour en français
     */
    public String jourEnglishToFrench(String value) {
        String ret = null;
        if (value.equals(JOURS_ANGLAIS[0])) {
            ret = DOMAINE_JOUR[0];
        } else if (value.equals(JOURS_ANGLAIS[1])) {
            ret = DOMAINE_JOUR[1];
        } else if (value.equals(JOURS_ANGLAIS[2])) {
            ret = DOMAINE_JOUR[2];
        } else if (value.equals(JOURS_ANGLAIS[3])) {
            ret = DOMAINE_JOUR[3];
        } else if (value.equals(JOURS_ANGLAIS[4])) {
            ret = DOMAINE_JOUR[4];
        } else if (value.equals(JOURS_ANGLAIS[5])) {
            ret = DOMAINE_JOUR[5];
        } else if (value.equals(JOURS_ANGLAIS[6])) {
            ret = DOMAINE_JOUR[6];
        }
        return ret;
    }

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
