import csv

with open('data_comptageVelo_nettoye_2.csv', 'r', encoding='UTF-8') as csv_file:
    csv_reader = csv.DictReader(csv_file, delimiter=';')
    rows_to_remove = []
    for row in csv_reader:
        if row['H4'] and not row['H5']:
            rows_to_remove.append(row)
    if rows_to_remove:
        with open('data_comptageVelo_nettoye_3.csv', 'w', encoding='UTF-8', newline='') as new_csv_file:
            fieldnames = csv_reader.fieldnames
            csv_writer = csv.DictWriter(new_csv_file, fieldnames=fieldnames, delimiter=';')
            csv_writer.writeheader()
            for row in csv_reader:
                if row not in rows_to_remove:
                    csv_writer.writerow(row)
    else:
        print('Aucune ligne à supprimer.')
