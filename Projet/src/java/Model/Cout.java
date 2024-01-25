/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static java.lang.System.in;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author user
 */
public class Cout {

    private String style_nom;
    private String taille_nom;
    private String idStyle;
    private String idTaille;
    private String categorie_nom;
    private float total_price;

    public Cout() {
    }

    public static Cout[] tarification(float max, float min, Connection connection) throws Exception {
        String sql = "";
        sql = "select * from cout_materiel where total_price>= " + min + " and total_price<= " + max;
        boolean isOpen = false;
        ArrayList<Cout> cout_liste = null;
        Cout[] cout_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            cout_liste = new ArrayList();
            while (resultSet.next()) {
                cout_liste.add(
                        new Cout(
                                resultSet.getString("style_nom"),
                                resultSet.getString("taille_nom"),
                                resultSet.getString("categorie_nom"),
                                resultSet.getFloat("total_price")
                        ));
            }
            cout_tableau = new Cout[cout_liste.size()];
            for (int i = 0; i < cout_liste.size(); i++) {
                cout_tableau[i] = cout_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return cout_tableau;
    }

    public Cout cout_main_oeuvre(Connection connection) throws Exception {
        String sql = "";
        sql = "select * from v_cout_main_oeuvre where id_style = ? and id_taille = ? ";
        boolean isOpen = false;
        Cout cout = new Cout();
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getIdStyle());
            statement.setString(2, this.getIdTaille());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cout = new Cout(
                        resultSet.getString("id_style"),
                        resultSet.getString("id_taille"),
                        resultSet.getFloat("sum")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return cout;
    }

    public Cout cout_materiel(Connection connection) throws Exception {
        String sql = "";
        sql = "select * from cout_materiel where idstyle = ? and idtaille = ? ";
        boolean isOpen = false;
        Cout cout = new Cout();
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getIdStyle());
            statement.setString(2, this.getIdTaille());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cout = new Cout(
                        resultSet.getString("style_nom"),
                        resultSet.getString("taille_nom"),
                        resultSet.getString("categorie_nom"),
                        resultSet.getFloat("total_price")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return cout;
    }

    public Cout pourcentage_vente(Connection connection) throws Exception {
        String sql = "";
        sql = "select * from v_detail_benefice where idstyle = ? and idtaille = ? ";
        boolean isOpen = false;
        Cout cout = new Cout();
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getIdStyle());
            statement.setString(2, this.getIdTaille());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cout = new Cout(
                        resultSet.getString("idstyle"),
                        resultSet.getString("idtaille"),
                        resultSet.getFloat("pourcentage")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return cout;
    }

    public float prix_de_revient(String id_style, String id_taille, Connection connection) throws Exception {
        float prix_revient = 0;
        Cout cout = new Cout();
        cout.setIdStyle(id_style);
        cout.setIdTaille(id_taille);
        Cout cout_materiel = cout.cout_materiel(connection);
        Cout cout_main_oeuvre = cout.cout_main_oeuvre(connection);
        prix_revient = cout_materiel.getTotal_price() + cout_main_oeuvre.getTotal_price();
        return prix_revient;
    }

    public float prix_vente(String id_style, String id_taille, Connection connection) throws Exception {
        float prix_revient = prix_de_revient(id_style, id_taille, connection);
        Cout cout = new Cout();
        cout.setIdStyle(id_style);
        cout.setIdTaille(id_taille);
        Cout benefice_pourcentage = cout.pourcentage_vente(connection);
        float pourcentage = benefice_pourcentage.getTotal_price() / 100;
        float prix_vente = prix_revient + (prix_revient * pourcentage);
        return prix_vente;
    }

    public float benefice(String id_style, String id_taille, Connection connection) throws Exception {
        float prix_vente = prix_vente(id_style, id_taille, connection);
        //float prix_vente = 0;
        float prix_revient = prix_de_revient(id_style, id_taille, connection);
        System.out.println(prix_vente + " prix_vente");
        System.out.println(prix_revient + " pri_revient");
        float benefice = prix_vente - prix_revient;
        System.out.println(benefice + " benefice");
        return benefice;
    }

    public Cout[] benefice_entre_2(String mini, String maxi, Connection connection) throws Exception {
        Mere[] mere = Mere.meuble(connection);
        Vector<Cout> vect_cout = new Vector();
        if (mini.isEmpty() && !maxi.isEmpty()) {
            mini = "0";
        } else if (!mini.isEmpty() && maxi.isEmpty()) {
            maxi = "50000000000000000";
        }
        float min = Float.valueOf(mini);
        float max = Float.valueOf(maxi);
        for (int i = 0; i < mere.length; i++) {
            float benefice = benefice(mere[i].getIdstyle().getIdStyle(), mere[i].getIdtaille().getIdTaille(), connection);
            if (benefice > 0) {
                if (benefice <= max && benefice >= min) {
                    Cout cout = new Cout();
                    cout.setStyle_nom(mere[i].getIdstyle().getNom());
                    cout.setCategorie_nom(mere[i].getIdcategorie().getNom());
                    cout.setTaille_nom(mere[i].getIdtaille().getNom());
                    cout.setTotal_price(benefice);
                    vect_cout.add(cout);
                }
            }
        }
        return vect_cout.toArray(new Cout[vect_cout.size()]);
    }

    public Cout(String idStyle, String idTaille, float total_price) {
        this.idStyle = idStyle;
        this.idTaille = idTaille;
        this.total_price = total_price;
    }

    public Cout(String style_nom, String taille_nom, String categorie_nom, float total_price) {
        this.style_nom = style_nom;
        this.taille_nom = taille_nom;
        this.categorie_nom = categorie_nom;
        this.total_price = total_price;
    }

    public String getStyle_nom() {
        return style_nom;
    }

    public void setStyle_nom(String style_nom) {
        this.style_nom = style_nom;
    }

    public String getTaille_nom() {
        return taille_nom;
    }

    public void setTaille_nom(String taille_nom) {
        this.taille_nom = taille_nom;
    }

    public String getCategorie_nom() {
        return categorie_nom;
    }

    public void setCategorie_nom(String categorie_nom) {
        this.categorie_nom = categorie_nom;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(String idStyle) {
        this.idStyle = idStyle;
    }

    public String getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(String idTaille) {
        this.idTaille = idTaille;
    }

}
