/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
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
    String id_prix_materiel;
    String nom;
    int etat;
    float prix;
    Date date_achat;
    String idFournisseurs;
    float quantite;
    float reste;

    public Materiel(String idMateriel, float reste) {
        this.idMateriel = idMateriel;
        this.reste = reste;
    }

    public String getIdFournisseurs() {
        return idFournisseurs;
    }

    public float getReste() {
        return reste;
    }

    public void setReste(float reste) {
        this.reste = reste;
    }

    public void setIdFournisseurs(String idFournisseurs) {
        this.idFournisseurs = idFournisseurs;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) throws Exception {
        ConnectionPs.isFloat(quantite);
        ConnectionPs.isNotNegative(quantite);
        this.setQuantite(Float.valueOf(quantite));
    }

    public void setQuantite(float quantite) throws Exception {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setPrix(String prix) throws Exception {
        ConnectionPs.isFloat(prix);
        ConnectionPs.isNotNegative(prix);
        this.setPrix(Float.valueOf(prix));
    }

    public Materiel(String idMateriel, float prix, String idFournisseur, float quantite) {
        this.idMateriel = idMateriel;
        this.prix = prix;
        this.idFournisseurs = idFournisseur;
        this.quantite = quantite;
    }

    public Materiel(String idMateriel, String nom, int etat) {
        this.idMateriel = idMateriel;
        this.nom = nom;
        this.etat = etat;
    }

    public Materiel(String idMateriel, String nom, float reste) {
        this.idMateriel = idMateriel;
        this.nom = nom;
        this.reste = reste;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
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

    public float reste_materiel(Materiel[] materiel) {
        float reste = 0;
        for (int i = 0; i < materiel.length; i++) {
            reste = reste + materiel[i].getReste();
        }
        return reste;
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

    public static void insertPrixMateriel(Materiel materiel, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection.setAutoCommit(false);
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO prix_materiel (idmateriel,prix_unitaire,date_achat,id_fourniseur,quantite) VALUES (?,?,NOW(),?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, materiel.getIdMateriel());
            statement.setFloat(2, materiel.getPrix());
            statement.setString(3, materiel.getIdFournisseurs());
            statement.setFloat(4, materiel.getQuantite());
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

    public static Materiel[] materiel_by_id(String idmateriel, Connection connection) throws Exception {
        String request = "SELECT * FROM materiel WHERE idmateriel = ?";
        boolean isOpen = false;
        ArrayList<Materiel> Materiel_liste = new ArrayList<>();
        Materiel[] Materiel_tableau = null;

        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, idmateriel);
            ResultSet resultSet = statement.executeQuery();  // Remove the 'request' parameter here
            System.out.println("request " + request);
            while (resultSet.next()) {
                Materiel_liste.add(new Materiel(
                        resultSet.getString("idmateriel"),
                        resultSet.getString("nom"),
                        resultSet.getInt("etat")
                ));
            }

            Materiel_tableau = Materiel_liste.toArray(new Materiel[0]);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen && connection != null) {
                connection.close();
            }
        }

        return Materiel_tableau;
    }

    public static int changeStatMateriel(String idMateriel, int etat, Connection connection) throws SQLException {
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

    public Materiel verifyStock(String id_materiel, Connection connection) {
        String request = "SELECT COALESCE(SUM(quantite_entree), 0) - COALESCE(SUM(quantite_sortie), 0) "
                + "AS reste,? AS id_materiel FROM mouvement WHERE id_materiel = ? OR id_materiel IS NULL";
        boolean isOpen = false;
        ArrayList<Materiel> Materiel_liste = null;

        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }

            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, id_materiel);
            statement.setString(2, id_materiel);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();

            Materiel_liste = new ArrayList<>();
            while (resultSet.next()) {
                Materiel_liste.add(new Materiel(resultSet.getString("id_materiel"), resultSet.getFloat("reste")));
            }
            System.out.println("mat liste: " + Materiel_liste.size());

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (!isOpen) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (Materiel_liste != null && !Materiel_liste.isEmpty()) {
            return Materiel_liste.get(0);
        } else {
            return null;
        }
    }
    
    public String insert_materiel(Connection connection ) throws Exception{
        boolean isOpen = false;
        String valiny = "";
        String sql = "insert into materiel (nom,etat) values (?,?) returning idmateriel";
        if (connection == null) {
            connection =ConnectionPs.connexionPostgreSQL();
            connection.setAutoCommit(false);
        }else{
            isOpen = true;
        }
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,this.getNom());
            statement.setInt(2, this.getEtat());
            statement.execute(sql);
            ResultSet resultSet = statement.executeQuery(statement.toString());
            while (resultSet.next()) {
                 valiny = resultSet.getString(1);
            }
            System.out.println(valiny + "id materiel inserted");
            System.out.println(statement.toString());
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }finally{
            if (isOpen == false) {
                connection.commit();
                connection.close();
            }
        }
        return valiny;
    }

    public String getId_prix_materiel() {
        return id_prix_materiel;
    }

    public void setId_prix_materiel(String id_prix_materiel) {
        this.id_prix_materiel = id_prix_materiel;
    }

}
