-- Schéma relationnel de la base de données
/*
ComptageVelo(boucle_num(1), libelle(NN), jour(NN), h0, h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, h11, h12, h13, h14, h15, h16, h17, h18, h19, h20, h21, h22, h23, total(NN), probabilite_presence_anomalie, jour_de_la_semaine(NN),  vacances_zone_b(NN))

GeolocalisationCompteur(numero(1), libelle(NN), observations, geolocalisation(NN))

LongueurPistesVelo(code_quartier(1), amenagement_cyclable(NN)(UQ))

QuartierCompteur(idCompteur(1), idQuartier)

QuartiersNantes(identifiant(1), quartier(NN)(UQ))

Temperature(laDate(1), tMoy(NN))
*/

-- Script de création de tables
DROP TABLE ComptageVelo;
DROP TABLE GeolocalisationCompteur;
DROP TABLE LongueurPistesVelo;
DROP TABLE QuartierCompteur;
DROP TABLE QuartiersNantes;
DROP TABLE Temperature;

CREATE TYPE GEO_COORDINATE AS OBJECT (
  longitude NUMERIC(3,16),
  latitude NUMERIC(3,16)
);

CREATE TABLE ComptageVelo (
    boucle_num NUMBER
        CONSTRAINT pk_ComptageVelo PRIMARY KEY,
    libelle VARCHAR2(100)
        CONSTRAINT nn_libelle_ComptageVelo NOT NULL,
    jour DATE
        CONSTRAINT nn_jour NOT NULL,
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
    jour_de_la_semaine NUMBER
        CONSTRAINT nn_jour_de_la_semaine NOT NULL,
    vacances_zone_b VARCHAR2(100)
        CONSTRAINT nn_vacances_zone_b NOT NULL
);

CREATE TABLE GeolocalisationCompteur (
    numero NUMBER
        CONSTRAINT pk_GeolocalisationCompteur PRIMARY KEY,
    libelle VARCHAR2(100)
        CONSTRAINT nn_libelle_GeolocalisationCompteur NOT NULL,
    observations VARCHAR2(100),
    geolocalisation GEO_COORDINATE
        CONSTRAINT nn_geolocalisation NOT NULL
);

CREATE TABLE LongueurPistesVelo (
    code_quartier NUMBER
        CONSTRAINT pk_LongueurPistesVelo PRIMARY KEY,
    amenagement_cyclable NUMBER
        CONSTRAINT nn_amenagement_cyclable NOT NULL,
        CONSTRAINT uq_amenagement_cyclable UNIQUE (amenagement_cyclable)
);

CREATE TABLE QuartierCompteur (
    idCompteur NUMBER
        CONSTRAINT pk_QuartierCompteur PRIMARY KEY,
    idQuartier NUMBER
);

CREATE TABLE QuartiersNantes (
    identifiant NUMBER
        CONSTRAINT pk_QuartiersNantes PRIMARY KEY,
    quartier VARCHAR2(100)
        CONSTRAINT nn_quartier NOT NULL,
        CONSTRAINT uq_quartier UNIQUE (quartier)
);

CREATE TABLE Temperature (
    laDate DATE
        CONSTRAINT pk_Temperature PRIMARY KEY,
    tMoy NUMBER
        CONSTRAINT nn_tMoy NOT NULL
);