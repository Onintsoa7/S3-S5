/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Cout {
    
    private String idFormule;
    private String idStyleMateriel;
    private String style_nom;
    private String taille_nom;
    private String categorie_nom;
    private float quantite;
    private String idMateriel;
    private String materiel_nom;
    private float prix_unitaire;
    private Date date_achat;

    public Cout() {
    }

    public Cout(String idFormule, String idStyleMateriel, String style_nom, String taille_nom, String categorie_nom, float quantite, String idMateriel, String materiel_nom, float prix_unitaire, Date date_achat) {
        this.idFormule = idFormule;
        this.idStyleMateriel = idStyleMateriel;
        this.style_nom = style_nom;
        this.taille_nom = taille_nom;
        this.categorie_nom = categorie_nom;
        this.quantite = quantite;
        this.idMateriel = idMateriel;
        this.materiel_nom = materiel_nom;
        this.prix_unitaire = prix_unitaire;
        this.date_achat = date_achat;
    }

    public Cout(String style_nom, String taille_nom, String categorie_nom, float prix_unitaire) {
        this.style_nom = style_nom;
        this.taille_nom = taille_nom;
        this.categorie_nom = categorie_nom;
        this.prix_unitaire = prix_unitaire;
    }
    
    
    public static Cout[] tarification(float max, float min, Connection connection) throws Exception {
        String sql = "";
        sql = "select * from cout where total_price>= " + min + " and total_price<= " + max ;
        boolean isOpen = false;
        ArrayList<Cout> cout_liste = null;
        Cout[] cout_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            cout_liste = new ArrayList();
            while (resultSet.next()) {
                cout_liste.add(
                        new Cout(
                                resultSet.getString("style_nom"),
                                resultSet.getString("taille_nom"),
                                resultSet.getString("categorie_nom"),
                                resultSet.getFloat("total_price")
                        ));
            }
            cout_tableau = new Cout[cout_liste.size()];
            for (int i = 0; i < cout_liste.size(); i++) {
                cout_tableau[i] = cout_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return cout_tableau;
    }
    
    
    public String getIdFormule() {
        return idFormule;
    }

    public void setIdFormule(String idFormule) {
        this.idFormule = idFormule;
    }

    public String getIdStyleMateriel() {
        return idStyleMateriel;
    }

    public void setIdStyleMateriel(String idStyleMateriel) {
        this.idStyleMateriel = idStyleMateriel;
    }

    public String getStyle_nom() {
        return style_nom;
    }

    public void setStyle_nom(String style_nom) {
        this.style_nom = style_nom;
    }

    public String getTaille_nom() {
        return taille_nom;
    }

    public void setTaille_nom(String taille_nom) {
        this.taille_nom = taille_nom;
    }

    public String getCategorie_nom() {
        return categorie_nom;
    }

    public void setCategorie_nom(String categorie_nom) {
        this.categorie_nom = categorie_nom;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public String getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(String idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getMateriel_nom() {
        return materiel_nom;
    }

    public void setMateriel_nom(String materiel_nom) {
        this.materiel_nom = materiel_nom;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }
}