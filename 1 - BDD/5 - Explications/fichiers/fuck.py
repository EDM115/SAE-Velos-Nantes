import csv

# ouvrir le fichier CSV en mode lecture
with open('data_comptage_nettoyees_v2.csv', newline='', encoding='utf-8') as csvfile:
    # créer un objet DictReader pour parcourir les lignes du fichier CSV
    csv_reader = csv.DictReader(csvfile, delimiter=';')
    
    # initialiser un dictionnaire pour stocker les couples (boucle_num, jour) déjà rencontrés
    couples_deja_rencontres = {}
    
    # initialiser une liste pour stocker les numéros de ligne des couples dupliqués
    lignes_dupliquees = []
    
    # parcourir les lignes du fichier CSV
    for i, row in enumerate(csv_reader):
        # extraire les valeurs de la colonne "boucle_num" et de la colonne "jour"
        boucle_num = row['boucle_num']
        jour = row['jour']
        
        # vérifier si ce couple a déjà été rencontré
        if (boucle_num, jour) in couples_deja_rencontres:
            # si oui, ajouter le numéro de ligne aux lignes dupliquées
            lignes_dupliquees.append(i + 1)  # ajouter 1 pour prendre en compte l'en-tête du fichier CSV
        else:
            # sinon, ajouter le couple au dictionnaire des couples déjà rencontrés
            couples_deja_rencontres[(boucle_num, jour)] = i
    
    # afficher les numéros de ligne des couples dupliqués
    if lignes_dupliquees:
        print(f"Les couples (boucle_num, jour) suivants sont dupliqués : {lignes_dupliquees}")
    else:
        print("Il n'y a pas de couples (boucle_num, jour) dupliqués dans le fichier CSV.")
