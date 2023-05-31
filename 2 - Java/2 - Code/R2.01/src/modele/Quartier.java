package modele;

public class Quartier {
    
    private int idQuartier;
    private String nomQuartier;
    private double lgPisteCyclable;

    public Quartier(int idQuartier, String nomQuartier, double lgPisteCyclable) {
        this.idQuartier = idQuartier;
        this.nomQuartier = nomQuartier;
        this.lgPisteCyclable = lgPisteCyclable;
    }

    public int getIdQuartier() {
        return idQuartier;
    }

    public void setIdQuartier(int idQuartier) {
        this.idQuartier = idQuartier;
    }

    public String getNomQuartier() {
        return nomQuartier;
    }

    public void setNomQuartier(String nomQuartier) {
        this.nomQuartier= nomQuartier;
    }

    public double getLgPisteCyclable() {
        return lgPisteCyclable;
    }

    public void setLgPisteCyclable(double lgPisteCyclable) {
        this.lgPisteCyclable= lgPisteCyclable;
    }
}
