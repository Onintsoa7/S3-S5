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
 */@TableInfo(name = "facture_remise" ,user = "postgres",pass = "1767",database = "meuble")
public class Facture_remise extends DAO{
    @Column(name = "id_facture_remise", isPrimary = true)
    String id_facture_remise;
    @Column(name = "id_vente")
    String id_vente;
    @Column(name = "id_remise")
    String id_remise;

    public Facture_remise() {
    }

    public Facture_remise(String id_facture_remise, String id_vente, String id_remise) {
        this.id_facture_remise = id_facture_remise;
        this.id_vente = id_vente;
        this.id_remise = id_remise;
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

    public String getId_remise() {
        return id_remise;
    }

    public void setId_remise(String id_remise) {
        this.id_remise = id_remise;
    }
}
