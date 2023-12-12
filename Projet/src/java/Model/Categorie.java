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
public class Categorie {

    String idCategorie;
    String nom;

    public Categorie() {

    }

    public Categorie(String idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static void insertCategorie(Categorie catego, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection.setAutoCommit(false);
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO Categorie (nom) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, catego.getNom());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            //connection.rollback();
            e.printStackTrace();
        } finally {
            if (IsOpen == false) {
                connection.close();
            }
        }
    }
        public static Categorie[] categorie(Connection connection) throws Exception {
        String request = "";
        request = "select * from categorie";
        boolean isOpen = false;
        ArrayList<Categorie> categorie_liste = null;
        Categorie[] categorie_tableau = null;
        try {
            if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            categorie_liste = new ArrayList();
            while (resultSet.next()) {
                categorie_liste.add(
                        new Categorie(
                        resultSet.getString("idcategorie"),
                        resultSet.getString("nom")
                    ));
            }
            categorie_tableau = new Categorie[categorie_liste.size()];
            for (int i = 0; i < categorie_liste.size(); i++) {
                categorie_tableau[i] = categorie_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return categorie_tableau;
    }

}
