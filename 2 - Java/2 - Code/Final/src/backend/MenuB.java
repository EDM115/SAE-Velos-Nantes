package backend;

import frontend.*;
import utils.GlobalVar;

public class MenuB {
    private Menu menu;
    private GlobalVar globalVar = new GlobalVar();

    public MenuB(Menu menu) {
        this.menu = menu;
        setupButtonActions();
    }

    private void setupButtonActions() {
        menu.getHomeButton().setOnAction(event -> {
            // Handle home button click
            Accueil accueil = new Accueil();
            accueil.setFirstTime(false);
            accueil.start(this.menu.getPreviousStage());
            menu.hide();
        });

        menu.getOption1().setOnAction(event -> {
            // Handle option 1 button click
            RechercheTrajet rechercheTrajet = new RechercheTrajet();
            rechercheTrajet.start(this.menu.getPreviousStage());
            menu.hide();
        });

        menu.getOption2().setOnAction(event -> {
            // Handle option 2 button click
            RechercheAffluence rechercheAffluence = new RechercheAffluence();
            rechercheAffluence.start(this.menu.getPreviousStage());
            menu.hide();
        });

        menu.getOption3().setOnAction(event -> {
            // Handle option 3 button click
            StationProche stationProche = new StationProche();
            stationProche.start(this.menu.getPreviousStage());
            menu.hide();
        });

        menu.getAddDataButton().setOnAction(event -> {
            // Handle add data button click
            if (globalVar.isAdmin()) {
                SaisieDonnees saisieDonnees = new SaisieDonnees();
                saisieDonnees.start(this.menu.getPreviousStage());
                menu.hide();
            } else {
                System.out.println("\u001B[31mERREUR PAS ADMIN\u001B[0m");
            }
        });


        
        menu.getGraph().setOnAction(event -> {
            Graphes graphes = new Graphes();
            graphes.start(this.menu.getPreviousStage());
            menu.hide();
        });

        /* menu.getEditDataButton().setOnAction(event -> {
            // Handle edit data button click
            ModificationDonnees modificationDonnees = new ModificationDonnees();
            modificationDonnees.show();
            menu.hide();
        }); */
    }
}
