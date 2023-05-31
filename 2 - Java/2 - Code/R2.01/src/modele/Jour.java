package modele;

import java.sql.Date;

public class Jour {
    
    private Date date;
    private Double temperatureMoyenne;
    private String jour;
    private String vacances;


    public Jour(Date date, Double temperatureMoyenne, String jour, String vacances) {
        this.date = date;
        this.temperatureMoyenne = temperatureMoyenne;
        this.jour = jour;
        this.vacances = vacances;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date= date;
    }

    public Double getTemperatureMoyenne() {
        return temperatureMoyenne;
    }

    public void setTemperatureMoyenne(Double temperatureMoyenne) {
        this.temperatureMoyenne= temperatureMoyenne;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour= jour;
    }

    public String getVacances() {
        return vacances;
    }

    public void setVacances(String vacances) {
        this.vacances= vacances;
    }
}
