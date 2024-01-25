/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Poste {

    private String id_poste;
    private String nom;
    private int etat;
    
    public void insert(Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
            //connection.setAutoCommit(false);
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO poste (nom, etat) VALUES (?, 1)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getNom());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (IsOpen == false) {
                connection.close();
            }
        }
    }

    public static Poste[] postes(Connection connection) throws Exception {
        String request = "";
        request = "select * from poste WHERE etat = 1";
        boolean isOpen = false;
        ArrayList<Poste> poste = null;
        Poste[] poste_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            poste = new ArrayList();
            while (resultSet.next()) {
                poste.add(
                        new Poste(
                                resultSet.getString("id_poste"),
                                resultSet.getString("nom"),
                                resultSet.getInt("etat")
                        ));
            }
            poste_tableau = new Poste[poste.size()];
            for (int i = 0; i < poste.size(); i++) {
                poste_tableau[i] = poste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return poste_tableau;
    }
    
    
    public Poste() {
    }

    public Poste(String id_poste, String nom, int etat) {
        this.setId_poste(id_poste);
        this.setNom(nom);
        this.setEtat(etat);
    }

    public String getId_poste() {
        return id_poste;
    }

    public void setId_poste(String id_poste) {
        this.id_poste = id_poste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
}
