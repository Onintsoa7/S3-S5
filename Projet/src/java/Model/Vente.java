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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
@TableInfo(name = "vente" ,user = "postgres",pass = "1767",database = "meuble")
public class Vente extends DAO{
     @Column(name = "id_vente", isPrimary = true)
    private String id_vente;
    @Column(name = "id_client")
    private String id_client;
    @Column(name = "montant")
    private Float montant;
    @Column(name = "montant_sans_remise")
    private Float montant_sans_remise;
    @Column (name = "date_vente")
    private Date date_vente;

    public Vente() {
    }

    
    public static String id_vente(Connection connection) throws Exception {
        String request = "";
        request = "select max(id_vente) as max from vente";
        boolean isOpen = false;
        String id = null; 
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            if (resultSet.next()) {
                id = resultSet.getString("max");
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return id;
    }
    
    public Vente(String id_vente, String id_client, Float montant, Date date_vente) {
        this.id_vente = id_vente;
        this.id_client = id_client;
        this.montant = montant;
        this.date_vente = date_vente;
    }

    public Float getMontant_sans_remise() {
        return montant_sans_remise;
    }

    public void setMontant_sans_remise(Float montant_sans_remise) {
        this.montant_sans_remise = montant_sans_remise;
    }

    
    public String getId_vente() {
        return id_vente;
    }

    public String getId_client() {
        return id_client;
    }

    public Float getMontant() {
        return montant;
    }

    public Date getDate_vente() {
        return date_vente;
    }

    public void setId_vente(String id_vente) {
        this.id_vente = id_vente;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public void setMontant_sans_remise(String montant_sans_remise) throws Exception {
        ConnectionPs.isNotEmpty(montant_sans_remise);
        ConnectionPs.isFloat(montant_sans_remise);
        ConnectionPs.isNotNegative(montant_sans_remise);
        this.setMontant_sans_remise(Float.valueOf(montant_sans_remise));
    }
    public void setMontant(String montant) throws Exception {
        ConnectionPs.isNotEmpty(montant);
        ConnectionPs.isFloat(montant);
        ConnectionPs.isNotNegative(montant);
        this.setMontant(Float.valueOf(montant));
    }
    public void setDate_vente(Date date_vente) {
        this.date_vente = date_vente;
    }
}
