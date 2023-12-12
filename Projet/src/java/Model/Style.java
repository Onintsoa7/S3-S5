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

    public Style(String idStyle, String nom) {
        this.idStyle = idStyle;
        this.nom = nom;
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

    public static void insertStyle(Style style, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection.setAutoCommit(false);
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO Style (nom) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, style.getNom());
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
        request = "select * from style";
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
                                resultSet.getString("nom")
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
}
