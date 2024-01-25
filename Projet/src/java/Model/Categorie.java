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
    int etat;

    public Categorie() {

    }

    public Categorie(String idCategorie, String nom, int etatCat) {
        this.idCategorie = idCategorie;
        this.nom = nom;
        this.etat = etatCat;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public static void insertCategorie(Categorie catego, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO Categorie (nom,etat) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, catego.getNom());
            statement.setInt(2, catego.getEtat());
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
        request = "select * from categorie WHERE etat = 1";
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
                                resultSet.getString("nom"),
                                resultSet.getInt("etat")
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

    public static int changeStatCategorie(String idCategorie,int etat,Connection connection) throws SQLException {
        int resultat = 0;
        String sql = "";
        sql = "UPDATE categorie SET etat = ? where idcategorie = ?";
        boolean isOpen = false;
        try {

            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, etat);
            statement.setString(2, idCategorie);
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
