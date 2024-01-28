/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;
import java.sql.Date;

/**
 *
 * @author user
 */
@TableInfo(name = "v_detail_paiement_facture", user = "postgres", pass = "1767", database = "meuble")
public class V_detail_paiement_facture extends DAO {

    @Column(name = "id_facture")
    String id_facture;

    @Column(name = "id_vente")
    String id_vente;

    @Column(name = "montant_paye")
    Float montant_paye;

    @Column(name = "montant_restant")
    Float montant_restant;

    @Column(name = "date_paiement")
    Date date_paiement;

    @Column(name = "id_client")
    String id_client;

    @Column(name = "montant_vente")
    Float montant_vente;

    @Column(name = "date_vente")
    Date date_vente;

    @Column(name = "montant_sans_remise")
    Float montant_sans_remise;

    public V_detail_paiement_facture() {
    }

    public V_detail_paiement_facture(String id_facture, String id_vente, Float montant_paye, Float montant_restant, Date date_paiement, String id_client, Float montant_vente, Date date_vente, Float montant_sans_remise) {
        this.id_facture = id_facture;
        this.id_vente = id_vente;
        this.montant_paye = montant_paye;
        this.montant_restant = montant_restant;
        this.date_paiement = date_paiement;
        this.id_client = id_client;
        this.montant_vente = montant_vente;
        this.date_vente = date_vente;
        this.montant_sans_remise = montant_sans_remise;
    }

    public String getId_facture() {
        return id_facture;
    }

    public void setId_facture(String id_facture) {
        this.id_facture = id_facture;
    }

    public String getId_vente() {
        return id_vente;
    }

    public void setId_vente(String id_vente) {
        this.id_vente = id_vente;
    }

    public Float getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(Float montant_paye) {
        this.montant_paye = montant_paye;
    }

    public Float getMontant_restant() {
        return montant_restant;
    }

    public void setMontant_restant(Float montant_restant) {
        this.montant_restant = montant_restant;
    }

    public Date getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(Date date_paiement) {
        this.date_paiement = date_paiement;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public Float getMontant_vente() {
        return montant_vente;
    }

    public void setMontant_vente(Float montant_vente) {
        this.montant_vente = montant_vente;
    }

    public Date getDate_vente() {
        return date_vente;
    }

    public void setDate_vente(Date date_vente) {
        this.date_vente = date_vente;
    }

    public Float getMontant_sans_remise() {
        return montant_sans_remise;
    }

    public void setMontant_sans_remise(Float montant_sans_remise) {
        this.montant_sans_remise = montant_sans_remise;
    }
    
    
}
