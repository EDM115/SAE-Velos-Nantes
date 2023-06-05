package modele;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import modele.utilitaires.BonneValeur;

/**
 * Classe Jour qui modélise un jour avec plusieurs attributs
 */
public class Jour {
    
    // Les attributs de Jour

    /**
     * La date du jour
     */
    private LocalDate date;
    /**
     * La température moyenne du jour
     */
    private Double temperatureMoyenne;
    /**
     * Le jour de la semaine
     */
    private String jour;
    /**
     * Si c'est les vacances ou non, et si oui, lesquelles
     */
    private String vacances;

    /**
     * BonneValeur pour vérifier les valeurs des attributs
     */
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
        if (date == null || temperatureMoyenne == null || jour == null || vacances == null) {
            throw new IllegalArgumentException("Jour() : Un ou plusieurs paramètres sont null");
        }
        String newJour = bonneValeur.jourEnglishToFrench(jour);
        if (newJour == null || !(bonneValeur.jourIsValid(newJour))) {
            throw new IllegalArgumentException("Jour() : Le jour n'est pas dans une bonne plage de valeur");
        }
        if (!(bonneValeur.vacancesIsValid(vacances))) {
            throw new IllegalArgumentException("Jour() : Les vacances ne sont pas dans une bonne plage de valeur");
        }
        this.date = date;
        this.temperatureMoyenne = temperatureMoyenne;
        this.jour = newJour;
        this.vacances = vacances;
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
     * @throws IllegalArgumentException si le paramètre date est null
     */
    public void setDate(LocalDate date) throws IllegalArgumentException {
        if (date == null) {
            throw new IllegalArgumentException("setDate() : La date est null");
        }
        this.date = date;
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
        this.temperatureMoyenne = temperatureMoyenne;
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
     * @throws IllegalArgumentException si le paramètre jour est null ou si le paramètre jour n'est pas dans une bonne plage de valeur
     */
    public void setJour(String jour) throws IllegalArgumentException {
        if (jour == null) {
            throw new IllegalArgumentException("setJour() : Le jour est null");
        }
        if (!(bonneValeur.jourIsValid(jour))) {
            throw new IllegalArgumentException("setJour() : Le jour n'est pas dans une bonne plage de valeur");
        }
        this.jour = jour;
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
     * @throws IllegalArgumentException si le paramètre vacances est null ou si le paramètre vacances n'est pas dans une bonne plage de valeur
     */
    public void setVacances(String vacances) throws IllegalArgumentException {
        if (vacances == null) {
            throw new IllegalArgumentException("setVacances() : Les vacances sont null");
        }
        if (!(bonneValeur.vacancesIsValid(vacances))) {
            throw new IllegalArgumentException("setVacances() : Les vacances ne sont pas dans une bonne plage de valeur");
        }
        this.vacances = vacances;
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

    /**
     * Donne le nombre de jours entre la date actuelle et la date du jour
     * @return le nombre de jours entre la date actuelle et la date du jour
     */
    public int getNombreJours() {
        LocalDate dateActuelle = LocalDate.now();
        return (int)ChronoUnit.DAYS.between(this.date, dateActuelle);
    }

    /**
     * Donne le nombre de jours entre une date spécifique et la date du jour
     * @param date la date à comparer
     * @return le nombre de jours entre une date spécifique et la date du jour
     */
    public int getNombreJours(LocalDate date) {
        return (int)ChronoUnit.DAYS.between(this.date, date);
    }
}
