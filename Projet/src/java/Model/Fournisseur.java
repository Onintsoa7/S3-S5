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
public class Fournisseur {

    String id_fournisseur;
    String nom;
    String contact;

    public Fournisseur(String id_fournisseur, String nom, String contact, String adresse) {
        this.id_fournisseur = id_fournisseur;
        this.nom = nom;
        this.contact = contact;
        this.adresse = adresse;
    }
       public Fournisseur( String nom, String contact, String adresse) {
        this.nom = nom;
        this.contact = contact;
        this.adresse = adresse;
    }
    String adresse;

    public String getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(String id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public static void insertFournisseur(Fournisseur fournisseur, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection.setAutoCommit(false);
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO fournisseur (nom_fournisseur,contact,adresse) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fournisseur.getNom());
            statement.setString(2, fournisseur.getContact());
            statement.setString(3, fournisseur.getAdresse());
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

    public static Fournisseur[] fournisseurs(Connection connection) throws Exception {

        String request = "";
        request = "select * from fournisseur";
        boolean isOpen = false;
        ArrayList<Fournisseur> fournisseurs_liste = null;
        Fournisseur[] fournisseurs_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            fournisseurs_liste = new ArrayList();
            while (resultSet.next()) {
                fournisseurs_liste.add(
                        new Fournisseur(
                                resultSet.getString("id_fournisseur"),
                                resultSet.getString("nom_fournisseur"),
                                resultSet.getString("contact"),
                                resultSet.getString("adresse")
                        ));
            }
            fournisseurs_tableau = new Fournisseur[fournisseurs_liste.size()];
            for (int i = 0; i < fournisseurs_liste.size(); i++) {
                fournisseurs_tableau[i] = fournisseurs_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return fournisseurs_tableau;
    }
}
