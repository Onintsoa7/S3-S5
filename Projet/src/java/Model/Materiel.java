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
 * @author Chan Kenny
 */
public class Materiel {

    String idMateriel;
    String nom;
    int etat;



    public Materiel(String idMateriel, String nom,int etat) {
        this.idMateriel = idMateriel;
        this.nom = nom;
        this.etat = etat;
    }
    public Materiel(String idMateriel, String nom) {
        this.idMateriel = idMateriel;
        this.nom = nom;
        this.etat = 1;
    }

    public Materiel() {

    }

    public String getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(String idMateriel) {
        this.idMateriel = idMateriel;
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
    
    
    

    public static void insertMateriel(Materiel materiel, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection.setAutoCommit(false);
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO Materiel (nom,etat) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, materiel.getNom());
            statement.setInt(2, materiel.getEtat());
            statement.executeUpdate();
        } catch (SQLException e) {
            //connection.rollback();
            e.printStackTrace();
        } finally {
            if (IsOpen == false) {
                connection.close();
            }
        }
    }
    public static Materiel[] materiel(Connection connection) throws Exception {
        String request = "";
        request = "select * from materiel WHERE etat = 1";
        boolean isOpen = false;
        ArrayList<Materiel> Materiel_liste = null;
        Materiel[] Materiel_tableau = null;
        try {
            if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            Materiel_liste = new ArrayList();
            while (resultSet.next()) {
                Materiel_liste.add(
                        new Materiel(
                        resultSet.getString("idmateriel"),
                        resultSet.getString("nom"),
                        resultSet.getInt("etat")
                    ));
            }
            Materiel_tableau = new Materiel[Materiel_liste.size()];
            for (int i = 0; i < Materiel_liste.size(); i++) {
                Materiel_tableau[i] = Materiel_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return Materiel_tableau;
    }
    
    public static int changeStatMateriel(String idMateriel,int etat,Connection connection) throws SQLException {
        int resultat = 0;
        String sql = "";
        sql = "UPDATE materiel SET etat = ? where idMateriel = ?";
        boolean isOpen = false;
        try {

            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, etat);
            statement.setString(2, idMateriel);
            resultat = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return resultat;
    }
}
