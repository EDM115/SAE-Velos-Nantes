package backend;

import frontend.*;

public class MenuB {
    private Menu menu;

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
        /* 
        menu.getOption4().setOnAction(event -> {
            // Handle option 4 button click
            PrecedentesRecherches precedentesRecherches = new PrecedentesRecherches();
            precedentesRecherches.show();
            menu.hide();
        });

        menu.getAddDataButton().setOnAction(event -> {
            // Handle add data button click
            SaisieDonnees saisieDonnees = new SaisieDonnees();
            saisieDonnees.show();
            menu.hide();
        });

        menu.getEditDataButton().setOnAction(event -> {
            // Handle edit data button click
            ModificationDonnees modificationDonnees = new ModificationDonnees();
            modificationDonnees.show();
            menu.hide();
        }); */
    }
}
