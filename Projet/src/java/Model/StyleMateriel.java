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
import java.util.ArrayList;

/**
 *
 * @author Chan Kenny
 */
public class StyleMateriel {

    String idStyleMateriel;
    Style style;
    Materiel materiel;

    public String getIdStyleMateriel() {
        return idStyleMateriel;
    }

    public StyleMateriel() {
    }

    public StyleMateriel(String idStyleMateriel, Style style, Materiel materiel) {
        this.idStyleMateriel = idStyleMateriel;
        this.style = style;
        this.materiel = materiel;
    }

    public StyleMateriel(Style style, Materiel materiel) {
        this.style = style;
        this.materiel = materiel;
    }

    public void setIdStyleMateriel(String idStyleMateriel) {
        this.idStyleMateriel = idStyleMateriel;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public static void insertStyleMateriel(StyleMateriel stylemateriel, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO stylemateriel (idStyle,idMateriel) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, stylemateriel.getStyle().getIdStyle());
            statement.setString(2, stylemateriel.getMateriel().getIdMateriel());
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

    public static StyleMateriel[] styleMateriels(String idStyle, Connection connection) throws Exception {
        String sql = "";
        sql = "select * from view_style_materiel where idStyle = ?";
        boolean isOpen = false;
        ArrayList<StyleMateriel> styleMateriel_liste = null;
        StyleMateriel[] styleMateriel_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idStyle);
            ResultSet resultSet = statement.executeQuery();
            styleMateriel_liste = new ArrayList();
            while (resultSet.next()) {
                Style style = new Style(resultSet.getString("idstyle"), resultSet.getString("style_nom"));
                Materiel materiel = new Materiel(resultSet.getString("idmateriel"), resultSet.getString("materiel_nom"));
                styleMateriel_liste.add(
                        new StyleMateriel(
                               resultSet.getString("idstylemateriel"),style, materiel
                        ));
            }
            styleMateriel_tableau = new StyleMateriel[styleMateriel_liste.size()];
            for (int i = 0; i < styleMateriel_liste.size(); i++) {
                styleMateriel_tableau[i] = styleMateriel_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return styleMateriel_tableau;
    }

    public static int modifyStyleMateriels(String idstylemateriel, String idstyle, String idmateriel, Connection connection) throws Exception {
        int resultat = 0;
        String sql = "";
        sql = "UPDATE stylemateriel SET idstyle = ?,idmateriel = ? where idstylemateriel = ?";
        boolean isOpen = false;
        try {

            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idstyle);
            statement.setString(2, idmateriel);
            statement.setString(3, idstylemateriel);
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

    public static int deleteStyleMateriels(String idStyle, Connection connection) throws Exception {
        int resultat = 0;
        String sql = "";
        sql = "DELETE FROM stylemateriel where idStyle = ?";
        boolean isOpen = false;
        try {

            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idStyle);
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

    public static boolean materielForStyle(String idMateriel, StyleMateriel[] styleMateriels) {
        boolean resultat = false;
        for (int i = 0; i < styleMateriels.length; i++) {
            if (styleMateriels[i].getMateriel().getIdMateriel().equals(idMateriel)) {
                resultat = true;
                break;
            }
        }
        return resultat;
    }

    public static String[] selectedMateriel(Materiel[] materiels, StyleMateriel[] styleMateriels) {
        String[] selected = new String[materiels.length];
        for (int i = 0; i < materiels.length; i++) {
            if(materielForStyle(materiels[i].getIdMateriel(),styleMateriels)){
                selected[i] = "checked";
            }
            else{
                selected[i] = "";
            }
        }
        return selected;
    }
    
    

    
}
