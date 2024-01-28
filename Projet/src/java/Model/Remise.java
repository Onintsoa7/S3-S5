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
@TableInfo(name = "remise", user = "postgres", pass = "1767", database = "meuble")
public class Remise extends DAO {

    @Column(name = "id_remise", isPrimary = true)
    private String id_remise;

    @Column(name = "nom")
    private String nom;
    
    @Column(name = "montant")
    private Float montant;

    public Remise() {
    }

    public String getId_remise() {
        return id_remise;
    }

    public String getNom() {
        return nom;
    }

    public Float getMontant() {
        return montant;
    }

    public void setId_remise(String id_remise) {
        this.id_remise = id_remise;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMontant(String montant)throws Exception{
        ConnectionPs.isNotEmpty(montant);
        ConnectionPs.isFloat(montant);
        ConnectionPs.isNotNegative(montant);
        this.setMontant(Float.valueOf(montant));
    }
    public void setMontant(Float montant) {
        this.montant = montant;
    }
    

}
