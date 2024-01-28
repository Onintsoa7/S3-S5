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
--------------------------------------------------------------------------------------------------------------------------------
create sequence seq_taille;
CREATE TABLE taille(
    idTaille  VARCHAR(50) default 'taille'||nextval('seq_taille')    ,
    nom varchar 
);


ALTER TABLE stylemateriel
ADD CONSTRAINT idstylemateriel PRIMARY KEY (idstylemateriel);


ALTER TABLE taille
ADD CONSTRAINT idtaille PRIMARY KEY (idtaille);


create sequence seq_mere start with 1;
create table mere(
        idmere varchar default 'mere'||nextval('seq_mere') primary key,
        idstyle varchar REFERENCES style(idstyle),
        idtaille varchar REFERENCES taille(idtaille),
        idcategorie varchar REFERENCES categorie(idcategorie)
);

create sequence seq_formule start with 1;
create table formule(
        idformule varchar default 'formule'||nextval('seq_formule') primary key,
        idmere varchar REFERENCES mere(idmere),
        idmateriel varchar REFERENCES materiel(idmateriel),
        quantite float
);

create view meuble as 
SELECT 
    m.idmere,
    s.idstyle,
    t.idtaille,
    c.idcategorie,
    s.nom AS style_nom,
    t.nom AS taille_nom,
    c.nom AS categorie_nom
FROM 
    mere m
JOIN 
    style s ON m.idstyle = s.idstyle
JOIN 
    taille t ON m.idtaille = t.idtaille
JOIN 
    categorie c ON m.idcategorie = c.idcategorie;

create view formule_detail as
SELECT 
    f.idformule,
    f.idmere,
    f.idmateriel,
    f.quantite,
    m.nom AS materiel_nom
FROM 
    formule f
JOIN 
    materiel m ON f.idmateriel = m.idmateriel;



create view meuble_detail as 
select meuble.*, formule_detail.idformule, formule_detail.idmateriel,formule_detail.materiel_nom, formule_detail.quantite
from meuble 
join formule_detail on meuble.idmere = formule_detail.idmere;

------------------------------------------------------------------------------------------------------------------------------
create sequence seq_prix_materiel start with 1;
create table prix_materiel(
    id_prix_materiel varchar default 'prix_materiel'||nextval('seq_prix_materiel') primary key,
    idmateriel varchar REFERENCES materiel(idmateriel),
    prix_unitaire float
);
alter table prix_materiel add column date_achat DATE;

create view meuble_materiel_prix as
select idmere,
 categorie_nom,
idcategorie,
 style_nom,
idstyle,
 taille_nom,
idtaille,
 materiel_nom,
 meuble_detail.quantite,
Coalesce(AVG(meuble_detail.quantite*prix_materiel.prix_unitaire), 0) as avg_montant
from meuble_detail 
left join prix_materiel on meuble_detail.idmateriel=prix_materiel.idmateriel
group by
idmere,
 categorie_nom,
idcategorie,
 style_nom,
idstyle,
 taille_nom,
idtaille,
 materiel_nom,
 meuble_detail.quantite;


create view cout_materiel as 
SELECT
    style_nom,
idstyle,
    taille_nom,
idtaille,
    categorie_nom,
idcategorie,
    SUM(avg_montant) AS total_price
FROM
    meuble_materiel_prix
GROUP BY
    style_nom,
idstyle, taille_nom,
idtaille, categorie_nom,
idcategorie;

------------------------------------------------------------------------------------------------------------------------------
create sequence seq_fournisseur start with 1;
create table fournisseur (
    id_fournisseur varchar default 'FRN'||nextval('seq_fournisseur') primary key,
    nom_fournisseur varchar not null,
    contact varchar not null,
    adresse varchar not null
);

alter table prix_materiel add column id_fourniseur varchar references fournisseur(id_fournisseur);
alter table prix_materiel add column quantite float default 0; 

create sequence seq_fabrication;
create table fabrication (
    id_fabrication varchar default 'FAB'||(nextval('seq_fabrication')) primary key,
    id_mere varchar references mere(idmere),
    quantite float not null,
    date_fabrication date default now()
);

create sequence seq_mouvement;
create table mouvement(
    id_mouvement varchar default 'mvmt'||(nextval('seq_mouvement')) primary key,
    id_materiel varchar references materiel(idmateriel),
    quantite_entree float default 0,
    quantite_sortie float default 0,
    date_mouvement date default now()
);

--------------------------------------------------------------------------------------------------------------------------------------
create sequence seq_profil start with 1;
create table profil(
    id_profil varchar default ('profil'||nextval('seq_profil')) primary key,
    nom varchar
);
alter table profil add column etat int default 1;

create sequence seq_profil_taux_salaire start with 1;
create table profil_taux_salaire(
    id_profil_taux_salaire varchar default ('profil_taux_salaire'||nextval('seq_profil_taux_salaire')) primary key,
    id_profil varchar references profil(id_profil),
    valeur int,
    "date" date default now()
);

alter table mere add column description varchar;

create sequence seq_employer start with 1;
create table employer(
    id_employer varchar default ('employer'||nextval('seq_employer')) primary key,
    nom varchar,
    prenom varchar,
    date_de_naissance date,
    adresse varchar,
    contact varchar
);

alter table taille add column niveau serial;


create sequence seq_poste start with 1;
create table poste(
    id_poste  varchar default ('poste'||nextval('seq_poste')) primary key,
    nom varchar,
    etat int default 1
);

create sequence seq_embauche_employer start with 1;
create table embauche_employer(
    id_embauche_employer  varchar default ('embauche_employer'||nextval('seq_embauche_employer')) primary key,
    id_employe varchar references employer(id_employer),
    id_poste varchar references poste(id_poste),
    karama float,
    "date" date
);

create sequence seq_taille_profil start with 1;
create table taille_profil(
    id_taille_profil varchar default ('taille_profil'||nextval('seq_taille_profil')) primary key,
    nombre float,
    "date" date
);

create sequence seq_standard_taille start with 1;
create table standard_taille(
    id_standard_taille varchar default ('standard_taille'||nextval('seq_standard_taille')) primary key,
    valeur float
);

create sequence seq_pourcentage_vente start with 1;
create table pourcentage_vente(
    id_pourcentage_vente varchar default ('pourcentage_vente'||nextval('seq_pourcentage_vente')) primary key, 
    id_mere varchar references mere(idmere),
    pourcentage float
);
alter table pourcentage_vente add column date_pourcentage date default now();

create sequence seq_duree_style start with 1;
create table duree_style(
    id_duree_style varchar default ('duree_style'||nextval('seq_duree_style')) primary key, 
    id_profil varchar references profil(id_profil),
    nombre float,
    id_taille varchar references taille(idtaille),
    id_style varchar references style(idstyle),
    duree float
);

create sequence seq_salaire_profil start with 1;
create table salaire_profil(
    id_salaire_profil varchar default ('salaire_profil'||nextval('seq_salaire_profil')) primary key,
    id_profil varchar references profil(id_profil),
    taux_horaire float
);
alter table salaire_profil add column date_salaire timestamp default now();

create view v_cout_main_oeuvre_par_tete as
     SELECT
    ds.id_profil,
    ds.nombre,
    ds.id_taille,
    ds.id_style,
    (ds.nombre * COALESCE(so.taux_horaire, 0)) AS salaire,
    so.date_salaire
FROM
    duree_style ds
LEFT JOIN (
    SELECT
        id_profil,
        MAX(date_salaire) AS max_date
    FROM
        salaire_profil
    GROUP BY
        id_profil
) so_max ON ds.id_profil = so_max.id_profil
LEFT JOIN salaire_profil so ON so.id_profil = ds.id_profil AND so.date_salaire = so_max.max_date;

create view v_cout_main_oeuvre as
select id_taille, 
id_style, 
sum(salaire)
from v_cout_main_oeuvre_par_tete
group by id_taille, 
id_style;


create view v_detail_benefice as
SELECT pv.id_pourcentage_vente, pv.id_mere, pv.pourcentage, pv.date_pourcentage, m.idmere, m.idstyle, m.idtaille, m.idcategorie, m.description
FROM pourcentage_vente pv
JOIN mere m ON pv.id_mere = m.idmere;

---------------------------------------------------------------------------------------------------------------
create sequence seq_profil_grade start with 1;
create table profil_grade(
    id_profil_grade  varchar default ('profil_grade'||nextval('seq_profil_grade')) primary key,
    id_profil varchar references profil(id_profil),
    annee float,
    "date" date
);

--alter table embauche_employer add column id_poste varchar references poste(id_poste);


create view v_salaire_profil_state as
SELECT DISTINCT ON (p.id_profil)
    p.id_profil,
    p.nom,
    pts.valeur
FROM profil p
JOIN profil_taux_salaire pts ON p.id_profil = pts.id_profil
ORDER BY p.id_profil, pts.date DESC;

create view v_salaire_profil_grade as
SELECT DISTINCT ON (p.id_profil)
    p.id_profil,
    p.nom,
    pts.annee
FROM profil p
JOIN profil_grade pts ON p.id_profil = pts.id_profil
ORDER BY p.id_profil, pts.date DESC;


create view v_salaire_profil as
SELECT DISTINCT ON (p.id_profil)
    p.id_profil,
    p.nom,
    pts.taux_horaire
FROM profil p
JOIN salaire_profil pts ON p.id_profil = pts.id_profil
ORDER BY p.id_profil, pts.date_salaire DESC;

create view v_anciennete as
SELECT 
  id_embauche_employer,
  id_employe,
  id_poste,
  karama,
  date,
  EXTRACT(YEAR FROM NOW()) - EXTRACT(YEAR FROM date) AS annee_travail
FROM 
  embauche_employer;


create view v_employer_embauche as
SELECT v.id_embauche_employer, v.id_employe, v.date, v.annee_travail, e.id_employer, e.nom, e.prenom, e.date_de_naissance, e.adresse, e.contact
FROM v_anciennete v
JOIN employer e ON v.id_employe = e.id_employer;

create view v_detail_profil as
SELECT sp.id_profil, sp.nom AS nom_profil, sp.taux_horaire as salaire, sg.annee as annee_min
FROM v_salaire_profil sp
JOIN v_salaire_profil_grade sg ON sp.id_profil = sg.id_profil;

---------------------------------------------------------------------------------------------------------------------------------------
create sequence seq_client start with 1;
create table client(
    id_client varchar default ('client'||nextval('seq_client')) primary key,
    nom varchar,
    adresse varchar,
    contact varchar
);

alter table client add column date_de_naissance date ;
------------------------------------------------------------------------------------------------------------------------------------------
create sequence seq_genre start with 1;
create table genre(
    id_genre varchar default ('genre'||nextval('seq_genre')) primary key,
    nom varchar
);
alter table client add column id_genre varchar references genre(id_genre);

create sequence seq_vente start with 1;
create table vente(
    id_vente varchar default ('vente'||nextval('seq_vente')) primary key,
    id_client varchar references client(id_client),
    montant float,
    date_vente date
);


create sequence seq_vente_detail start with 1;
create table vente_detail(
    id_vente_detail varchar default ('vente_detail'||nextval('seq_vente_detail')) primary key,
    id_vente varchar references vente(id_vente),
    id_mere varchar references mere(idmere),
    quantite float,
    prix_unitaire float
);



create view v_client_detail_vente as 
select vente_detail.*, vente.id_client,vente.montant,vente.date_vente,client.nom as nom_client,client.adresse,client.contact,client.date_de_naissance,
client.id_genre,genre.nom as nom_genre
from vente_detail 
join vente
on vente.id_vente = vente_detail.id_vente 
join client 
on client.id_client = vente.id_client
join genre
on genre.id_genre = client.id_genre;

CREATE VIEW v_quantite_par_genre_et_mere AS
SELECT
  id_mere,
  id_genre,
  SUM(quantite) AS somme_quantite
FROM
  v_client_detail_vente
GROUP BY
  id_mere, id_genre;
-------------------------------------------------------------------------------------------------------------------------------------------------
alter table vente add column montant_sans_remise float;
update vente set montant_sans_remise = montant;

create sequence seq_facture start with 1;
create table facture(
    id_facture varchar default ('facture'||nextval('seq_facture')) primary key,
    id_vente varchar references vente(id_vente),
    montant_paye float,
    montant_restant float
);
alter table facture add column date_paiement date default now();

create sequence seq_remise start with 1;
create table remise(
    id_remise varchar default ('remise'||nextval('seq_remise')) primary key,
    nom varchar,
    montant float
);

create sequence seq_facture_remise start with 1;
create table facture_remise(
    id_facture_remise varchar default ('facture_remise'||nextval('seq_facture_remise')) primary key,
    id_vente varchar references vente(id_vente),
    id_remise varchar references remise(id_remise)
);

create or replace view v_facture_restante as
SELECT DISTINCT ON (f.id_vente)
    f.id_facture,
    v.id_client,
    c.nom as nom_client,
    f.montant_restant,
    v.montant,
    f.id_vente,
    f.date_paiement
FROM facture f
JOIN vente v ON v.id_vente = f.id_vente
JOIN client c ON c.id_client = v.id_client
ORDER BY f.id_vente,v.id_client, f.date_paiement desc;



create view v_facture_reste as
WITH max_id_cte AS (
  SELECT
    id_vente,
    MAX(CAST(SUBSTRING(id_facture FROM 'facture([0-9]+)') AS INTEGER)) AS max_id_numeric
  FROM facture
  GROUP BY id_vente
)
SELECT
  id_vente,
  'facture' || max_id_numeric AS max_id_facture
FROM max_id_cte;



create view v_detail_vente_client as
SELECT v.*, c.nom AS client_name, c.date_de_naissance
FROM vente v
JOIN client c ON v.id_client = c.id_client;








create view v_detail_facture as
SELECT
    vd.id_vente_detail,
    vd.id_vente,
    m.idmere,
    m.idstyle,
    m.idtaille,
    m.idcategorie,
    m.description,
    vd.quantite,
    vd.prix_unitaire,
    (vd.quantite*vd.prix_unitaire) as montant
FROM
    vente_detail vd
JOIN
    mere m ON vd.id_mere = m.idmere;

create view v_detail_paiement_facture as
SELECT
    f.id_facture,
    f.id_vente,
    f.montant_paye,
    f.montant_restant,
    f.date_paiement,
    v.id_client,
    v.montant AS montant_vente,
    v.date_vente,
    v.montant_sans_remise
FROM
    facture f
JOIN
    vente v ON f.id_vente = v.id_vente;

create view v_facture_remise as
SELECT fr.id_facture_remise, fr.id_vente, r.nom as nom_remise, r.montant as montant_remise
FROM facture_remise fr
JOIN remise r ON fr.id_remise = r.id_remise;
