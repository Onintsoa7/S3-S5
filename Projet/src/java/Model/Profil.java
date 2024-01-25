/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.ConnectionPs;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Profil {

    private String id_profil;
    private String nom;
    private int etat;
    private float salaire;
    private int niveau;
    private float duree_travail;

    public void insert(Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
            //connection.setAutoCommit(false);
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO profil (nom, etat) VALUES (?, 1)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getNom());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (IsOpen == false) {
                connection.close();
            }
        }
    }

    public void insert_grade(Date date, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
            //connection.setAutoCommit(false);
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO profil_grade (id_profil, annee, date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getId_profil());
            statement.setInt(2, this.getNiveau());
            statement.setDate(3, date);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (IsOpen == false) {
                connection.close();
            }
        }
    }

    public void insert_taux_profil(Connection connection) throws Exception {
        Profil[] profils = get_parametre_salaire(connection);
        boolean IsOpen = false;
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
            //connection.setAutoCommit(false);
        } else {
            IsOpen = true;
        }
        try {
            for (int i = 0; i < profils.length; i++) {
                String sql = "INSERT INTO salaire_profil (id_profil, taux_horaire) VALUES (?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, profils[i].getId_profil());
                statement.setFloat(2, this.getSalaire() * profils[i].getNiveau());
                System.out.println(profils[i].getNiveau());
                statement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            //connection.rollback();
            e.printStackTrace();
        } finally {
            if (IsOpen == false) {
                connection.close();
            }
        }
    }

    public void insert_profil_taux_salaire(Date date, Connection connection) throws Exception {
        Profil[] profils = profils(connection);
        boolean IsOpen = false;
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
            //connection.setAutoCommit(false);
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO profil_taux_salaire (id_profil, valeur, date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getId_profil());
            statement.setInt(2, this.getNiveau());
            if (date == null) {
                statement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            } else {
                statement.setDate(3, date);
            }
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

    public static Profil[] profils(Connection connection) throws Exception {
        String request = "";
        request = "select * from profil WHERE etat = 1";
        boolean isOpen = false;
        ArrayList<Profil> profil = null;
        Profil[] profil_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            profil = new ArrayList();
            while (resultSet.next()) {
                profil.add(new Profil(
                        resultSet.getString("id_profil"),
                        resultSet.getString("nom"),
                        resultSet.getInt("etat")
                ));
            }
            profil_tableau = new Profil[profil.size()];
            for (int i = 0; i < profil.size(); i++) {
                profil_tableau[i] = profil.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return profil_tableau;
    }

    public static Profil[] get_parametre_salaire(Connection connection) throws Exception {
        String request = "";
        request = "select * from v_salaire_profil_state";
        boolean isOpen = false;
        ArrayList<Profil> profil = null;
        Profil[] profil_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            profil = new ArrayList();
            while (resultSet.next()) {
                profil.add(new Profil(
                        resultSet.getString("id_profil"),
                        resultSet.getString("nom"),
                        resultSet.getInt("valeur")
                ));
            }
            profil_tableau = new Profil[profil.size()];
            for (int i = 0; i < profil.size(); i++) {
                profil_tableau[i] = profil.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return profil_tableau;
    }

    
    public static Profil[] detail_profil(Connection connection) throws Exception {
        String request = "";
        request = "select * from v_detail_profil order by annee_min";
        boolean isOpen = false;
        ArrayList<Profil> profil = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            profil = new ArrayList();
            while (resultSet.next()) {
                profil.add(new Profil(
                        resultSet.getString("id_profil"),
                        resultSet.getString("nom_profil"),
                        resultSet.getFloat("salaire"),
                        resultSet.getInt("annee_min")
                ));
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return profil.toArray(new Profil[profil.size()]);
    }

    
    public Profil() {
    }

    public void insert_duree_travail(float nombre, String id_taille, String id_style, Connection connection) throws SQLException {
        boolean isOpen = false;

        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }

        try {
            String sql = "INSERT INTO duree_style (id_profil, nombre, id_taille, id_style, duree) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getId_profil());
            statement.setFloat(2, nombre);
            statement.setString(3, id_taille);
            statement.setString(4, id_style);
            statement.setFloat(5, this.getDuree_travail());
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }

    }

    public Profil(String nom) {
        this.setNom(nom);
    }

    public Profil(String id_profil, String nom, float salaire, int niveau) {
        this.id_profil = id_profil;
        this.nom = nom;
        this.salaire = salaire;
        this.niveau = niveau;
    }

    
    public Profil(String id_profil, String nom, float salaire, float duree_travail) {
        this.id_profil = id_profil;
        this.nom = nom;
        this.salaire = salaire;
        this.duree_travail = duree_travail;
    }

    public Profil(String id_profil, String salaire) throws Exception {
        this.setId_profil(id_profil);
        this.setSalaire(salaire);
    }

    public String getId_profil() {
        return id_profil;
    }

    public void setId_profil(String id_profil) {
        this.id_profil = id_profil;
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

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public Profil(String id_profil, float duree_travail) {
        this.id_profil = id_profil;
        this.duree_travail = duree_travail;
    }

    public float getDuree_travail() {
        return duree_travail;
    }

    public void setDuree_travail(float duree_travail) {
        this.duree_travail = duree_travail;
    }

    public void setSalaire(String salaire) throws Exception {
        try {
            ConnectionPs.isFloat(salaire);
            ConnectionPs.isNotNegative(salaire);
            this.setSalaire(Float.valueOf(salaire));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Profil(String id_profil, String nom, int niveau) {
        this.id_profil = id_profil;
        this.nom = nom;
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

}
