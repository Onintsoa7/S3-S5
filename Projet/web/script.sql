create database meuble;
\c meuble

create sequence seq_categorie;
CREATE  TABLE categorie ( 
	idcategorie           VARCHAR(50) default 'categorie'||nextval('seq_categorie') ,
	nom                  varchar    ,
        etat                 INT,
	CONSTRAINT pk_categorie PRIMARY KEY ( idcategorie )
 );

create sequence seq_materiel;
CREATE  TABLE materiel ( 
	idmateriel          VARCHAR(50) default 'materiel'||nextval('seq_materiel')  ,
	nom                  varchar    ,
        etat                 INT,
	CONSTRAINT pk_materiel PRIMARY KEY ( idmateriel )
 );

create sequence seq_style;
CREATE  TABLE style ( 
	idstyle              VARCHAR(50) default 'style'||nextval('seq_style') ,
	nom                  varchar    ,
        etat                 INT,
	CONSTRAINT pk_syle PRIMARY KEY ( idstyle )
 );

create sequence seq_stylemateriel;
CREATE  TABLE stylemateriel ( 
	idstylemateriel      VARCHAR(50) default 'stylemateriel'||nextval('seq_stylemateriel')    ,
	idstyle              varchar    ,
	idmateriel           varchar    
 );

ALTER TABLE stylemateriel ADD CONSTRAINT fk_stylemateriel_materiel FOREIGN KEY ( idmateriel ) REFERENCES materiel( idmateriel );

ALTER TABLE stylemateriel ADD CONSTRAINT fk_stylemateriel_syle FOREIGN KEY ( idstyle ) REFERENCES style( idstyle );
CREATE VIEW view_style_materiel AS
SELECT
    sm.idstylemateriel,
    s.idstyle,
    s.nom AS style_nom,
    m.idmateriel,
    m.nom AS materiel_nom
FROM
    stylemateriel sm
    JOIN style s ON sm.idstyle = s.idstyle
    JOIN materiel m ON sm.idmateriel = m.idmateriel
    WHERE s.etat = 1 AND m.etat = 1;

create sequence seq_taille;
CREATE TABLE taille(
    idTaille  VARCHAR(50) default 'taille'||nextval('seq_taille')    ,
    nom varchar 
);


ALTER TABLE stylemateriel
ADD CONSTRAINT idstylemateriel PRIMARY KEY (idstylemateriel);


ALTER TABLE taille
ADD CONSTRAINT idtaille PRIMARY KEY (idtaille);


create sequence seq_formule start with 1;
create table formule(
        idformule varchar default 'formule'||nextval('seq_formule') primary key,
        idstylemateriel varchar REFERENCES stylemateriel(idstylemateriel),
        idtaille varchar REFERENCES taille(idtaille),
        idcategorie varchar REFERENCES categorie(idcategorie),
        quantite float
);


CREATE VIEW view_formule_detail AS
SELECT
    f.idformule,
    f.idstylemateriel,
    sm.idstyle,
    s.nom AS style_nom,
    m.idmateriel,
    m.nom AS materiel_nom,
    t.idtaille,
    t.nom AS taille_nom,
    c.idcategorie,
    c.nom AS categorie_nom,
    f.quantite
FROM
    formule f
    JOIN stylemateriel sm ON f.idstylemateriel = sm.idstylemateriel
    JOIN style s ON sm.idstyle = s.idstyle
    JOIN materiel m ON sm.idmateriel = m.idmateriel
    JOIN taille t ON f.idtaille = t.idtaille
    JOIN categorie c ON f.idcategorie = c.idcategorie;
