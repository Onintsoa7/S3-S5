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
 * @author user
 */
public class Formule extends Mere {

    Materiel materiel;
    float quantite;
    public Formule() {
    }

    public Formule(Materiel materiel, float quantite) {
        this.materiel = materiel;
        this.quantite = quantite;
    }

    public Formule(Materiel materiel, float quantite, String idformule, Style idstyle, Taille idtaille, Categorie idcategorie) {
        super(idformule, idstyle, idtaille, idcategorie);
        this.materiel = materiel;
        this.quantite = quantite;
    }

    public void insertFormule(Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO formule (idmere,idmateriel,quantite) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getIdmere());
            statement.setString(2, this.getMateriel().getIdMateriel());
            statement.setFloat(3, this.getQuantite());
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

    public static Formule[] compose_meuble(String idmateriel, Connection connection) throws Exception {
        String sql = "";
        sql = "select * from meuble_detail where idmateriel = ?";
        boolean isOpen = false;
        ArrayList<Formule> meres_liste = null;
        Formule[] meres_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idmateriel);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            meres_liste = new ArrayList();
            Materiel[] materiel = Materiel.materiel_by_id(idmateriel, connection);
            while (resultSet.next()) {
                Style style = new Style();
                style.setNom(resultSet.getString("style_nom"));
                Taille taille = new Taille();
                taille.setNom(resultSet.getString("taille_nom"));
                Categorie categorie = new Categorie();
                categorie.setNom(resultSet.getString("categorie_nom"));
                meres_liste.add(new Formule(
                        materiel[0],
                        resultSet.getFloat("quantite"),
                        resultSet.getString("idformule"),
                        style,
                        taille,
                        categorie
                ));
            }
            meres_tableau = new Formule[meres_liste.size()];
            for (int i = 0; i < meres_liste.size(); i++) {
                meres_tableau[i] = meres_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return meres_tableau;
    }

    
    public static Formule[] get_materiel_et_quantite_par_meuble(String idmere, Connection connection) throws Exception {
        String request = "";
        request = "select * from formule WHERE idmere = ?";
        boolean isOpen = false;
        ArrayList<Formule> formule_liste = null;
        Formule[] formule_tab = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, idmere);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            formule_liste = new ArrayList();
            while (resultSet.next()) {
                Materiel materiel = new Materiel();
                materiel.setIdMateriel(resultSet.getString("idmateriel"));
                formule_liste.add(
                        new Formule(
                                materiel,
                                resultSet.getFloat("quantite")
                        ));
            }
            formule_tab = new Formule[formule_liste.size()];
            for (int i = 0; i < formule_liste.size(); i++) {
                formule_tab[i] = formule_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return formule_tab;
    }

    
    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public void setQuantite(String quantite) throws Exception {
        ConnectionPs.isFloat(quantite);
        ConnectionPs.isNotNegative(quantite);
        this.setQuantite(Float.valueOf(quantite));
    }
}
