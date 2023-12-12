create database meuble;
\c meuble

create sequence seq_categorie;
CREATE  TABLE categorie ( 
	idcategorie           VARCHAR(50) default 'categorie'||nextval('seq_categorie') ,
	nom                  varchar    ,
	CONSTRAINT pk_categorie PRIMARY KEY ( idcategorie )
 );

create sequence seq_materiel;
CREATE  TABLE materiel ( 
	idmateriel          VARCHAR(50) default 'materiel'||nextval('seq_materiel')  ,
	nom                  varchar    ,
	CONSTRAINT pk_materiel PRIMARY KEY ( idmateriel )
 );

create sequence seq_style;
CREATE  TABLE style ( 
	idstyle              VARCHAR(50) default 'style'||nextval('seq_style') ,
	nom                  varchar    ,
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
    JOIN materiel m ON sm.idmateriel = m.idmateriel;