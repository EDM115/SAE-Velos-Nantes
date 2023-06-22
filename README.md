# SAE Vélos Nantes
Tous les fichiers ici, c'est plus simple et collaboratif

## Tâches à faire
- BDD
  - [x] Diagramme de conception
  - [x] Script de création
  - [x] Script de remplissage
  - [x] Requêtes
  - [x] Powerpoint de stats
  - [x] Explications
- Java
  - [x] Diagramme de conception
  - [x] Dossier ergonomie
  - [x] Code (anglais)
- Graphes
  - [x] Jupyter Notebook
  - [x] Compte-rendu
- Stats
  - [x] Jupyter Notebook
  - [x] Diaporama
  - [x] Vidéo
- Oral
  - [x] Présentation
  - [x] Préparation aux questions
  
  
## TODO (finito)

- [x] Page ResultatsRecherche (récupérer les données depuis les paramètres)
- [x] Intégration Google Maps
- [x] ResultatsRecherche pour Affluence
- [x] Style cohérent partout
- [x] Graphes
- [x] Partie graphique StationProche
- [x] Nantes open site
- [x] saisie/modif de données : récupérer l'affichage du terminal et l'afficher
- [x] pas admin = message quand on clique sur les boutons saisie/modif
- [x] popup connexion bdd
- [x] Gérer les exceptions
- [x] JavaDoc
- [x] Page ModificationDonnees
- [x] Page SaisieDonnees
- [x] Utiliser `utils.WindowDrag()`
- [x] Clean code


## How to run the app ?

1) Download and install MySQL
2) Go in the MySQL Shell and run the following :
```bash
\connect root@localhost
\sql
\source SAE_creation.sql
\source SAE_remplissage_Quartier_Compteur_Date.sql
\source SAE_remplissage_Comptage.sql
CREATE USER admin@localhost IDENTIFIED BY 'mdp_admin';
CREATE USER user@localhost IDENTIFIED BY 'mdp_user';
GRANT ALL PRIVILEGES ON bd_velos_nantes.* TO admin@localhost WITH GRANT OPTION;
GRANT SELECT ON bd_velos_nantes.* TO user@localhost;
```
SQL files :  
[SAE_creation.sql](https://raw.githubusercontent.com/EDM115/school-codes-v2/master/BUT1/Moodle/S2/R2.06/SAE_creation.sql)  
[SAE_remplissage_Quartier_Compteur_Date.sql](https://github.com/EDM115/school-codes-v2/raw/master/BUT1/Moodle/S2/R2.06/SAE_remplissage_Quartier_Compteur_Date.sql)  
[SAE_remplissage_Comptage.sql](https://github.com/EDM115/school-codes-v2/raw/master/BUT1/Moodle/S2/R2.06/SAE_remplissage_Comptage.sql)  

3) Go in [2 - Java/2 - Code/Final](./2%20-%20Java/2%20-%20Code/Final) and open a terminal there
  1) Linux : `chmod +x run.sh && ./run.sh` (it seems that the class can't be found...)
  2) Windows :  
    - CMD : `run.bat`  
    - PowerShell : `.\run.bat`  
Alternatively, open that folder in VS Code (Ctrl + K, Ctrl + O), get Java JDK 20 installed, and edit the module path in `.vscode/launch.json` to the /lib folder  
Then go on Run and Debug tab, and F5

