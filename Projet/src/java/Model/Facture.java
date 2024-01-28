/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
@TableInfo(name = "facture", user = "postgres", pass = "1767", database = "meuble")
public class Facture extends DAO {

    @Column(name = "id_facture", isPrimary = true)
    String id_facture;
    @Column(name = "id_vente")
    String id_vente;
    @Column(name = "montant_paye")
    Float montant_paye;
    @Column(name = "montant_restant")
    Float montant_restant;
    @Column(name = "date_paiement")
    Date date_paiement;

    
     
    public String max_id_facture(Connection connection) throws Exception {
    String request = "select * from v_facture_reste where id_vente = ?";
    boolean isOpen = false;
    String max_id_facture = null;
    try {
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }
        System.out.println(request);
        PreparedStatement statement = connection.prepareStatement(request);
        statement.setString(1, this.getId_vente());
        ResultSet resultSet = statement.executeQuery();  // Fix this line, remove 'request' parameter
        if (resultSet.next()) {
            max_id_facture = resultSet.getString("max_id_facture");
        }
        System.out.println("OUTED");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (!isOpen) {
            connection.close();
        }
    }
    return max_id_facture;
}

    
    
    public Facture() {
    }

    public Facture(String id_facture, String id_vente, Float montant_paye, Float montant_restant, Date date_paiement) {
        this.id_facture = id_facture;
        this.id_vente = id_vente;
        this.montant_paye = montant_paye;
        this.montant_restant = montant_restant;
        this.date_paiement = date_paiement;
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

    
    public void setMontant_paye(String montant_paye) throws Exception {
        ConnectionPs.isNotEmpty(montant_paye);
        ConnectionPs.isFloat(montant_paye);
        ConnectionPs.isNotNegative(montant_paye);
        this.setMontant_paye(Float.valueOf(montant_paye));
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
    
}
