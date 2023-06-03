package modele;

import java.time.LocalDate;
import modele.utilitaires.BonneValeur;

/**
 * Classe Jour qui modélise un jour avec plusieurs attributs
 */
public class Jour {
    
    // Les attributs de Jour
    private LocalDate date;
    private Double temperatureMoyenne;
    private String jour;
    private String vacances;

    // BonneValeur pour vérifier les valeurs
    private BonneValeur bonneValeur = new BonneValeur();

    /**
     * Constructeur de la classe Jour
     * @param date la date du jour
     * @param temperatureMoyenne la température moyenne du jour
     * @param jour le jour de la semaine
     * @param vacances si c'est les vacances ou non
     * @throws IllegalArgumentException si les valeurs ne sont pas dans une bonne plage de valeur
     */
    public Jour(LocalDate date, Double temperatureMoyenne, String jour, String vacances) throws IllegalArgumentException {
        if (!(bonneValeur.jourIsValid(jour))) {
            throw new IllegalArgumentException("Constructeur Jour : Le jour n'est pas dans une bonne plage de valeur");
        } else if (!(bonneValeur.vacancesIsValid(vacances))) {
            throw new IllegalArgumentException("Constructeur Jour : Les vacances ne sont pas dans une bonne plage de valeur");
        } else {
            this.date = date;
            this.temperatureMoyenne = temperatureMoyenne;
            this.jour = jour;
            this.vacances = vacances;
        }
    }

    //////////////////////
    // Getter et Setter //
    //////////////////////

    /**
     * Getter de la date du jour
     * @return la date du jour
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Setter de la date du jour
     * @param date la date du jour
     */
    public void setDate(LocalDate date) {
        this.date= date;
    }

    /**
     * Getter de la température moyenne du jour
     * @return la température moyenne du jour
     */
    public Double getTemperatureMoyenne() {
        return temperatureMoyenne;
    }

    /**
     * Setter de la température moyenne du jour
     * @param temperatureMoyenne la température moyenne du jour
     */
    public void setTemperatureMoyenne(Double temperatureMoyenne) {
        this.temperatureMoyenne= temperatureMoyenne;
    }

    /**
     * Getter du jour de la semaine
     * @return le jour de la semaine
     */
    public String getJour() {
        return this.jour;
    }

    /**
     * Setter du jour de la semaine
     * @param jour le jour de la semaine
     */
    public void setJour(String jour) {
        this.jour= jour;
    }

    /**
     * Getter si c'est les vacances ou non
     * @return si c'est les vacances ou non
     */
    public String getVacances() {
        return vacances;
    }

    /**
     * Setter si c'est les vacances ou non
     * @param vacances si c'est les vacances ou non
     */
    public void setVacances(String vacances) {
        this.vacances= vacances;
    }

    ///////////////////////
    // Méthodes ajoutées //
    ///////////////////////

    /**
     * Affiche toute les informations du jour
     * @return les informations du jour
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[33mInformations du jour :\u001B[0m\n");
        String[][] tableauAttributs = {
            {"\u001B[32mdate\u001B[0m", String.valueOf(this.date)},
            {"\u001B[32mtemperatureMoyenne\u001B[0m", String.valueOf(this.temperatureMoyenne)},
            {"\u001B[32mjour\u001B[0m", this.jour},
            {"\u001B[32mvacances\u001B[0m", this.vacances}
        };
        for (String[] attribut : tableauAttributs) {
            sb.append(String.format("%-30s: %s\n", attribut[0], attribut[1]));
        }
        return sb.toString();
    }
}
