-- Schéma relationnel de la base de données
/*
ComptageVelo(boucle_num(1), libelle(NN), jour = @Temperature.laDate(1) , h0, h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21, h22, h23, total(NN), probabilite_presence_anomalie, jour_de_la_semaine(NN),  vacances_zone_b(NN))

GeolocalisationCompteur(numero = ComptageVelo.boucle_num(1), libelle(NN), observations, geolocalisation(NN), idQuartier = @QuartiersNantes.identifiant)

LongueurPistesVelo(code_quartier = @QuartiersNantes.identifiant(1), amenagement_cyclable(NN)(UQ))

QuartiersNantes(identifiant(1), quartier(NN)(UQ))

Temperature(laDate(1), tMoy(NN))
*/

-- Script de création de tables
DROP TABLE LongueurPistesVelo;
DROP TABLE ComptageVelo;
DROP TABLE GeolocalisationCompteur;
DROP TABLE QuartiersNantes;
DROP TABLE Temperature;

CREATE TABLE Temperature (
    laDate DATE
        CONSTRAINT pk_Temperature PRIMARY KEY,
    tMoy NUMBER
        CONSTRAINT nn_tMoy NOT NULL
);

CREATE TABLE QuartiersNantes (
    identifiant NUMBER
        CONSTRAINT pk_QuartiersNantes PRIMARY KEY,
    quartier VARCHAR2(100)
        CONSTRAINT nn_quartier NOT NULL
        CONSTRAINT uq_quartier UNIQUE
);

CREATE TABLE GeolocalisationCompteur (
    numero NUMBER
        CONSTRAINT pk_GeolocalisationCompteur PRIMARY KEY,
    libelle VARCHAR2(100)
        CONSTRAINT nn_libelle_GC NOT NULL,
    observations VARCHAR2(100),
    geolocalisation GEO_COORDINATE
        CONSTRAINT nn_geolocalisation NOT NULL,
    idQuartier NUMBER
        CONSTRAINT fk_GC_QuartiersNantes REFERENCES QuartiersNantes(identifiant)
);


CREATE TABLE ComptageVelo (
    boucle_num NUMBER,
    jour DATE
        CONSTRAINT fk_ComptageVelo_Temp REFERENCES Temperature(laDate),
    CONSTRAINT pk_ComptageVelo PRIMARY KEY (boucle_num, jour),
    libelle VARCHAR2(100)
        CONSTRAINT nn_libelle_comptageVelo NOT NULL,
    h0 NUMBER,
    h1 NUMBER,
    h2 NUMBER,
    h3 NUMBER,
    h4 NUMBER,
    h5 NUMBER,
    h6 NUMBER,
    h7 NUMBER,
    h8 NUMBER,
    h9 NUMBER,
    h10 NUMBER,
    h11 NUMBER,
    h12 NUMBER,
    h13 NUMBER,
    h14 NUMBER,
    h15 NUMBER,
    h16 NUMBER,
    h17 NUMBER,
    h18 NUMBER,
    h19 NUMBER,
    h20 NUMBER,
    h21 NUMBER,
    h22 NUMBER,
    h23 NUMBER,
    total NUMBER
        CONSTRAINT nn_total NOT NULL,
    probabilite_presence_anomalie VARCHAR2(100),
    jour_de_la_semaine VARCHAR2(100)
        CONSTRAINT nn_jour_de_la_semaine NOT NULL,
    vacances_zone_b VARCHAR2(100)
        CONSTRAINT nn_vacances_zone_b NOT NULL
);

CREATE TABLE LongueurPistesVelo (
    code_quartier NUMBER
        CONSTRAINT fk_LongueurPistesVelo_QN REFERENCES QuartiersNantes(identifiant)
        CONSTRAINT pk_LongueurPistesVelo PRIMARY KEY,
    amenagement_cyclable VARCHAR2(100)
        CONSTRAINT nn_amenagement_cyclable NOT NULL
        CONSTRAINT uq_amenagement_cyclable UNIQUE
);




-- exemple insertion :
INSERT INTO GeolocalisationCompteur
VALUES (665, 'Bonduelle vers Nord', null, GEO_COORDINATE(47.58545, -1.8965));