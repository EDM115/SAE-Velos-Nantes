package modele.utilitaires;

public class AttributsPortees {
    
    private int[] tabNbPassagesParHeure;
    private String presenceAnomalie;

    public AttributsPortees(int[] tabNbPassagesParHeure, String presenceAnomalie) {
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
