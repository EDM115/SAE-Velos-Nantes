package modele.utilitaires;

public class AttributsPortes {
    
    private int[] tabNbPassagesParHeure;
    private String presenceAnomalie;

    public AttributsPortes(int[] tabNbPassagesParHeure, String presenceAnomalie) throws IllegalArgumentException {
        if (presenceAnomalie == null) {
            throw new IllegalArgumentException("AttributsPortes() : presenceAnomalie ne peut pas être null");
        }
        this.tabNbPassagesParHeure = tabNbPassagesParHeure;
        this.presenceAnomalie = presenceAnomalie;
    }

    public int[] getTabNbPassagesParHeure() {
        return tabNbPassagesParHeure;
    }

    public void setTabNbPassagesParHeure(int[] tabNbPassagesParHeure) {
        this.tabNbPassagesParHeure = tabNbPassagesParHeure;
    }

    public String getPresenceAnomalie() {
        return presenceAnomalie;
    }

    public void setPresenceAnomalie(String presenceAnomalie) {
        this.presenceAnomalie = presenceAnomalie;
    }
}
