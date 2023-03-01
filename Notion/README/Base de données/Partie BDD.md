# Partie BDD

## les données à mettre :

- *Numéro de boucle*: boucle_num Numéro de boucle (NUMBER)
- *Libellé*: libelle Libellé du compteur (VARCHAR2(100))
- *Jour*: jour Jour (DATE)

- *Total*: total Somme des vélos comptés sur la journée (NUMBER)
- *Probabilité de présence d’anomalies*: probabilite_presence_anomalie Informe d’éventuels problèmes sur les comptages. La probabilité d’anomalies est évaluée à faible ou à forte, selon la règle figurant dans la description du jeu de données. (VARCHAR2(100))
- *Jour de la semaine*: jour_de_la_semaine  (NUMBER)
- 1 : Lundi
- 2 : Mardi
- 3 : Mercredi
- 4 : Jeudi
- 5 : Vendredi
- 6 : Samedi
- 7 : Dimanche

  CONSTRAINT ck_Table CHECK (jour de la semaine < 8)

- *Vacances*: vacances_zone_b Vacances (VARCHAR2(100))

  

Comptage GPS

[244400404_comptages-velo-nantes-metropole-boucles-comptage.csv](Partie%20BDD/244400404_comptages-velo-nantes-metropole-boucles-comptage.csv)

[244400404_comptages-velo-nantes-metropole-boucles-comptage.json](Partie%20BDD/244400404_comptages-velo-nantes-metropole-boucles-comptage.json)

[244400404_comptages-velo-nantes-metropole-boucles-comptage.xlsx](Partie%20BDD/244400404_comptages-velo-nantes-metropole-boucles-comptage.xlsx)

Heures de passage

[244400404_comptages-velo-nantes-metropole.csv](Partie%20BDD/244400404_comptages-velo-nantes-metropole.csv)

[244400404_comptages-velo-nantes-metropole.json](Partie%20BDD/244400404_comptages-velo-nantes-metropole.json)