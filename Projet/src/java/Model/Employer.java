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
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Chan Kenny
 */
public class Employer extends Profil {

    String id_employer;
    String nom;
    String prenom;
    Date date_de_naissance;
    String adresse;
    String contact;
    Date date_embauche;
    int anciennete;
    float karama;
    String id_poste;

    public int getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(int anciennete) {
        this.anciennete = anciennete;
    }

    public float getKarama() {
        return karama;
    }

    public void setKarama(float karama) {
        this.karama = karama;
    }

    public Employer() {
    }

    public Employer(String id_employer, String nom, String prenom, Date date_de_naissance, String adresse, String contact, int anciennete) {
        this.id_employer = id_employer;
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.contact = contact;
        this.anciennete = anciennete;
    }

    public static Employer employerById(String id, Connection connection) throws Exception {
        String request = "";
        request = "select * from employer_detail WHERE id_employer = ?";

        boolean isOpen = false;
        Employer employer = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            PreparedStatement statement = connection.prepareStatement(request);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employer = new Employer(
                        resultSet.getString("id_employer"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date_de_naissance"),
                        resultSet.getString("adresse"),
                        resultSet.getString("contact"),
                        resultSet.getInt("anciennete")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return employer;
    }

    public String getId_employer() {
        return id_employer;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getContact() {
        return contact;
    }

    public void insertEmployer(Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO employer (nom,prenom,date_de_naissance,adresse,contact) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getNom());
            statement.setString(2, this.getPrenom());
            statement.setDate(3, this.getDate_de_naissance());
            statement.setString(4, this.getAdresse());
            statement.setString(5, this.getContact());
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

    public void insert_embauche_employer(Connection connection) throws Exception {
        boolean IsOpen = false;
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            IsOpen = true;
        }
        try {
            String sql = "INSERT INTO embauche_employer (id_employe,id_poste,karama, date) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getId_employer());
            statement.setString(2, this.getId_poste());
            statement.setFloat(3, this.getKarama());
            statement.setDate(4, this.getDate_embauche());
            System.out.println(statement.toString());
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

    public static Employer[] employers(Connection connection) throws Exception {
        String request = "";
        request = "select * from employer";
        boolean isOpen = false;
        ArrayList<Employer> employer_liste = null;
        Employer[] employer_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            employer_liste = new ArrayList();
            while (resultSet.next()) {
                employer_liste.add(
                        new Employer(
                                resultSet.getString("id_employer"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getDate("date_de_naissance"),
                                resultSet.getString("adresse"),
                                resultSet.getString("contact")
                        ));
            }
            employer_tableau = new Employer[employer_liste.size()];
            for (int i = 0; i < employer_liste.size(); i++) {
                employer_tableau[i] = employer_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return employer_tableau;
    }

    public static Employer[] employers_embauchers(Connection connection) throws Exception {
        String request = "";
        request = "select * from v_employer_embauche";
        boolean isOpen = false;
        ArrayList<Employer> employer_liste = null;
        Employer[] employer_tableau = null;
        try {
            if (connection == null) {
                connection = ConnectionPs.connexionPostgreSQL();
            } else {
                isOpen = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            employer_liste = new ArrayList();
            while (resultSet.next()) {
                employer_liste.add(
                        new Employer(
                                resultSet.getString("id_employe"),
                                resultSet.getString("nom"),
                                resultSet.getString("prenom"),
                                resultSet.getDate("date_de_naissance"),
                                resultSet.getString("adresse"),
                                resultSet.getString("contact"),
                                resultSet.getInt("annee_travail")
                        ));
            }
            employer_tableau = new Employer[employer_liste.size()];
            for (int i = 0; i < employer_liste.size(); i++) {
                employer_tableau[i] = employer_liste.get(i);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (!isOpen) {
                connection.close();
            }
        }
        return employer_tableau;
    }

    public static Employer[] employer_detail(String id, Connection connection) throws Exception {
        Profil[] profils = Profil.detail_profil(connection);
        Profil profil_sup = new Profil();
        Profil profil_min = null;
        for (int i = 0; i < profils.length; i++) {
            if (id.equalsIgnoreCase(profils[i].getId_profil())) {
                profil_min = profils[i];
                if (i < profils.length - 1) {
                    profil_sup = new Profil();
                    profil_sup = profils[i + 1];
                }
            }
        }
        System.out.println("MIN " + profil_min.getId_profil() + " - " + profil_min.getNiveau());
        System.out.println("SUP " + profil_sup + " - " + profil_sup.getNiveau());
        Employer[] emp = Employer.employers_embauchers(connection);
        ArrayList<Employer> emps = new ArrayList<>();
        System.out.println(emp.length);
        for (int i = 0; i < emp.length; i++) {
            Employer employe = new Employer();
            employe = emp[i];
            employe.setSalaire(profil_min.getSalaire());
            employe.setId_profil(profil_min.getId_profil());
            if (profil_sup.getId_profil() == null) {
                if (emp[i].getAnciennete() >= profil_min.getNiveau()) {
                    emps.add(employe);
                }
            } else {
                if (emp[i].getAnciennete() >= profil_min.getNiveau() && emp[i].getAnciennete() < profil_sup.getNiveau()) {
                    emps.add(employe);
                }
            }
        }
        return emps.toArray(new Employer[emps.size()]);
    }

    public Employer(String id_employer, String nom_, String prenom, Date date_de_naissance, String adresse, String contact, int anciennete, Date date_embauche, String id_ouvrier, String nom, float salaire, float duree_travail) {
        super(id_ouvrier, nom, salaire, duree_travail);
        this.id_employer = id_employer;
        this.nom = nom_;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.contact = contact;
        this.anciennete = anciennete;
        this.date_embauche = date_embauche;
    }

    public Employer(String id_employer, String nom, String prenom, Date date_de_naissance, String adresse, String contact) {
        this.id_employer = id_employer;
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.contact = contact;
    }

    public Employer(String id_employer, String nom) {
        this.id_employer = id_employer;
        this.nom = nom;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }

    public void setId_employer(String id_employer) {
        this.id_employer = id_employer;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId_poste() {
        return id_poste;
    }

    public void setId_poste(String id_poste) {
        this.id_poste = id_poste;
    }

}
