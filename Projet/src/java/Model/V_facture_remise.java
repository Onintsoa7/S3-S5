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
@TableInfo(name = "v_facture_remise", user = "postgres", pass = "1767", database = "meuble")
public class V_facture_remise extends DAO {

    @Column(name = "id_facture_remise")
    String id_facture_remise;

    @Column(name = "id_vente")
    String id_vente;

    @Column(name = "nom_remise")
    String nom_remise;

    @Column(name = "montant_remise")
    Float montant_remise;

    public V_facture_remise() {
    }

    public V_facture_remise(String id_facture_remise, String id_vente, String nom_remise, Float montant_remise) {
        this.id_facture_remise = id_facture_remise;
        this.id_vente = id_vente;
        this.nom_remise = nom_remise;
        this.montant_remise = montant_remise;
    }

    public String getId_facture_remise() {
        return id_facture_remise;
    }

    public void setId_facture_remise(String id_facture_remise) {
        this.id_facture_remise = id_facture_remise;
    }

    public String getId_vente() {
        return id_vente;
    }

    public void setId_vente(String id_vente) {
        this.id_vente = id_vente;
    }

    public String getNom_remise() {
        return nom_remise;
    }

    public void setNom_remise(String nom_remise) {
        this.nom_remise = nom_remise;
    }

    public Float getMontant_remise() {
        return montant_remise;
    }

    public void setMontant_remise(Float montant_remise) {
        this.montant_remise = montant_remise;
    }

    
}
