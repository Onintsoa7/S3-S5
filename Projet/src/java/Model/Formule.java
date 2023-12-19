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
public class Formule {

    String idformule;
    String style;
    String idstylemateriel;
    String idtaille;
    String idcategorie;
    float quantite;

    public Formule() {
    }

    public Formule(String idformule, String style, String idstylemateriel, String idtaille, String idcategorie, float quantite) {
        this.idformule = idformule;
        this.style = style;
        this.idstylemateriel = idstylemateriel;
        this.idtaille = idtaille;
        this.idcategorie = idcategorie;
        this.quantite = quantite;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getIdformule() {
        return idformule;
    }

    public void setIdformule(String idformule) {
        this.idformule = idformule;
    }

    public String getIdstylemateriel() {
        return idstylemateriel;
    }

    public void setIdstylemateriel(String idstylemateriel) {
        this.idstylemateriel = idstylemateriel;
    }

    public String getIdtaille() {
        return idtaille;
    }

    public void setIdtaille(String idtaille) {
        this.idtaille = idtaille;
    }

    public String getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(String idcategorie) {
        this.idcategorie = idcategorie;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) throws Exception {
        if (Float.valueOf(quantite) < 0) {
            throw new Exception("valeur quantite invalide");
        }
        this.setQuantite(Float.valueOf(quantite));
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public static void insertFormule(Formule formule, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO formule (idstylemateriel,idtaille,idcategorie,quantite) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, formule.getIdstylemateriel());
            statement.setString(2, formule.getIdtaille());
            statement.setString(3, formule.getIdcategorie());
            statement.setFloat(4, formule.getQuantite());
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

    public static Formule[] formuleAffiche(String idmateriel, Connection connection) throws Exception {
        String sql = "";
        sql = "select * from view_formule_detail where idmateriel = ?";
        boolean isOpen = false;
        ArrayList<Formule> formules_liste = null;
        Formule[] formules_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idmateriel);
            ResultSet resultSet = statement.executeQuery();
            formules_liste = new ArrayList();
            while (resultSet.next()) {
                formules_liste.add(
                        new Formule(
                                resultSet.getString("idformule"),
                                resultSet.getString("style_nom"),
                                resultSet.getString("idstylemateriel"),
                                resultSet.getString("taille_nom"),
                                resultSet.getString("categorie_nom"),
                                resultSet.getInt("quantite")
                        ));
            }
            formules_tableau = new Formule[formules_liste.size()];
            for (int i = 0; i < formules_liste.size(); i++) {
                formules_tableau[i] = formules_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return formules_tableau;
    }
}
