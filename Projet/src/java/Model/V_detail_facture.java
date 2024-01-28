/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;

/**
 *
 * @author user
 */
@TableInfo(name = "v_detail_facture", user = "postgres", pass = "1767", database = "meuble")
public class V_detail_facture extends DAO {

    @Column(name = "id_vente_detail")
    String id_vente_detail;

    @Column(name = "id_vente")
    String id_vente;

    @Column(name = "idmere")
    String id_mere;

    @Column(name = "idstyle")
    String id_style;

    @Column(name = "idtaille")
    String id_taille;

    @Column(name = "idcategorie")
    String id_categorie;

    @Column(name = "description")
    String description;

    @Column(name = "quantite")
    Float quantite;

    @Column(name = "prix_unitaire")
    Float prix_unitaire;

    @Column(name = "montant")
    Float montant;

    public V_detail_facture() {
    }

    public V_detail_facture(String id_vente_detail, String id_vente, String id_mere, String id_style, String id_taille, String id_categorie, String description, Float quantite, Float prix_unitaire, Float montant) {
        this.id_vente_detail = id_vente_detail;
        this.id_vente = id_vente;
        this.id_mere = id_mere;
        this.id_style = id_style;
        this.id_taille = id_taille;
        this.id_categorie = id_categorie;
        this.description = description;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;
        this.montant = montant;
    }

    public String getId_vente_detail() {
        return id_vente_detail;
    }

    public void setId_vente_detail(String id_vente_detail) {
        this.id_vente_detail = id_vente_detail;
    }

    public String getId_vente() {
        return id_vente;
    }

    public void setId_vente(String id_vente) {
        this.id_vente = id_vente;
    }

    public String getId_mere() {
        return id_mere;
    }

    public void setId_mere(String id_mere) {
        this.id_mere = id_mere;
    }

    public String getId_style() {
        return id_style;
    }

    public void setId_style(String id_style) {
        this.id_style = id_style;
    }

    public String getId_taille() {
        return id_taille;
    }

    public void setId_taille(String id_taille) {
        this.id_taille = id_taille;
    }

    public String getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(String id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getQuantite() {
        return quantite;
    }

    public void setQuantite(Float quantite) {
        this.quantite = quantite;
    }

    public Float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(Float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    
}
