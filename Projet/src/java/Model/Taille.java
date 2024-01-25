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
public class Taille {
    String idTaille;
    String nom;
    Float niveau;

    public Taille(String idTaille, String nom) {
        this.idTaille = idTaille;
        this.nom = nom;
    }

    public Taille(String idTaille, String nom, Float niveau) {
        this.idTaille = idTaille;
        this.nom = nom;
        this.niveau = niveau;
    }

    public Taille() {

    }

    public String getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(String idTaille) {
        this.idTaille = idTaille;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public static void insertTaille(Taille Taille, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO Taille (nom) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Taille.getNom());
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

    public static void insert_taille_ouvrier_nombre(String nombre_str, Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            ConnectionPs.isFloat(nombre_str);
            ConnectionPs.isNotEmpty(nombre_str);
            ConnectionPs.isNotNegative(nombre_str);
            Float nombre = Float.valueOf(nombre_str);
            String sql = "INSERT INTO taille_profil (nombre, date) VALUES (?, now())";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setFloat(1, nombre);
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

    
    public Taille taille_by_id(Connection connection) throws Exception {
    String request = "select * from Taille where idtaille = ?";
    boolean isOpen = false;
    Taille taille = new Taille();
    try {
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }
        System.out.println(request);
        PreparedStatement statement = connection.prepareStatement(request);
        statement.setString(1, this.getIdTaille());
        ResultSet resultSet = statement.executeQuery();  // Fix this line, remove 'request' parameter
        if (resultSet.next()) {
            taille = new Taille(
                    resultSet.getString("idTaille"),
                    resultSet.getString("nom"),
                    resultSet.getFloat("niveau"));
        }
        System.out.println("OUTED");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (!isOpen) {
            connection.close();
        }
    }
    return taille;
}

    
    
    public static Taille[] Taille(Connection connection) throws Exception {
        String request = "";
        request = "select * from Taille ";
        boolean isOpen = false;
        ArrayList<Taille> Taille_liste = null;
        Taille[] Taille_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            Taille_liste = new ArrayList();
            while (resultSet.next()) {
                Taille_liste.add(
                        new Taille(
                                resultSet.getString("idTaille"),
                                resultSet.getString("nom")
                        ));
            }
            Taille_tableau = new Taille[Taille_liste.size()];
            for (int i = 0; i < Taille_liste.size(); i++) {
                Taille_tableau[i] = Taille_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return Taille_tableau;
    }

    public Float getNiveau() {
        return niveau;
    }

    public void setNiveau(Float niveau) {
        this.niveau = niveau;
    }
    
    
    
}
