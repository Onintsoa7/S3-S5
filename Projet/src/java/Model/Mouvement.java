/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author P15A-Mirado-159
 */
public class Mouvement {
//    id_mouvement | id_materiel | quantite_entree | quantite_sortie | date_mouvement
    String id_mouvement;
    String id_materiel;
    float quantite_entree;
    float quantite_sortie;
    String id_fabrication;
    Date date_mouvement;

    public void insererMouvement(Connection connection) throws SQLException, Exception {
        boolean isOpen = false;

        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
            connection.setAutoCommit(false);
        } else {
            isOpen = true;
        }

        try {
            String sql = "INSERT INTO mouvement (id_materiel, quantite_entree, quantite_sortie) VALUES (?, ?, ?)";
            System.out.println("blablagsadegkerjhgiojgoirejgioejgierjioejg");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getId_materiel());
            statement.setFloat(2, this.getQuantite_entree());
            statement.setFloat(3, this.getQuantite_sortie());
            statement.executeUpdate();
            System.out.println(statement.toString());
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (!isOpen) {
                connection.commit();
                connection.close();
            }
        }
    }
    
    public Mouvement() {
    }

    
    
    public Mouvement(String id_mouvement, String id_materiel, float quantite_entree, float quantite_sortie, String id_fabrication, Date date_mouvement) {
        this.id_mouvement = id_mouvement;
        this.id_materiel = id_materiel;
        this.quantite_entree = quantite_entree;
        this.quantite_sortie = quantite_sortie;
        this.id_fabrication = id_fabrication;
        this.date_mouvement = date_mouvement;
    }

    public String getId_mouvement() {
        return id_mouvement;
    }

    public void setId_mouvement(String id_mouvement) {
        this.id_mouvement = id_mouvement;
    }

    public String getId_materiel() {
        return id_materiel;
    }

    public void setId_materiel(String id_materiel) {
        this.id_materiel = id_materiel;
    }

    public float getQuantite_entree() {
        return quantite_entree;
    }

    public void setQuantite_entree(float quantite_entree) {
        this.quantite_entree = quantite_entree;
    }

    public float getQuantite_sortie() {
        return quantite_sortie;
    }

    public void setQuantite_sortie(float quantite_sortie) {
        this.quantite_sortie = quantite_sortie;
    }

    public String getId_fabrication() {
        return id_fabrication;
    }

    public void setId_fabrication(String id_fabrication) {
        this.id_fabrication = id_fabrication;
    }

    public Date getDate_mouvement() {
        return date_mouvement;
    }

    public void setDate_mouvement(Date date_mouvement) {
        this.date_mouvement = date_mouvement;
    }
        
}
