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
public class Style {

    String idStyle;
    String nom;
    int etat;

    public Style(String idStyle, String nom, int etat) {
        this.idStyle = idStyle;
        this.nom = nom;
        this.etat = etat;
    }

    public Style(String idStyle, String nom) {
        this.idStyle = idStyle;
        this.nom = nom;
        this.etat = 1;
    }

    public Style() {

    }

    public String getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(String idStyle) {
        this.idStyle = idStyle;
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

    public static void insertStyle(Style style, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO Style (nom,etat) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, style.getNom());
            statement.setInt(2, style.getEtat());
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

    public static void deleteStyle(String idStyle, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
            connection.setAutoCommit(false);
        } else {
            IsOpen = true;
        }
        try {
            String sql = "DELETE FROM  Style WHERE idStyle = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idStyle);
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

    public static Style[] style(Connection connection) throws Exception {
        String request = "";
        request = "select * from style WHERE etat = 1";
        boolean isOpen = false;
        ArrayList<Style> style_liste = null;
        Style[] style_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            style_liste = new ArrayList();
            while (resultSet.next()) {
                style_liste.add(
                        new Style(
                                resultSet.getString("idstyle"),
                                resultSet.getString("nom"),
                                resultSet.getInt("etat")
                        ));
            }
            style_tableau = new Style[style_liste.size()];
            for (int i = 0; i < style_liste.size(); i++) {
                style_tableau[i] = style_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return style_tableau;
    }

    public static int changeStatstyle(String idstyle, int etat, Connection connection) throws SQLException {
        int resultat = 0;
        String sql = "";
        sql = "UPDATE style SET etat = ? where idstyle = ?";
        boolean isOpen = false;
        try {

            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, etat);
            statement.setString(2, idstyle);
            resultat = statement.executeUpdate();
            connection.commit();

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
