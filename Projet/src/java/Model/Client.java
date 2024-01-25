/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;
import java.sql.Date;

/**
 *
 * @author user
 */
@TableInfo(name = "client",user = "postgres",pass = "1767",database = "meuble")
public class Client extends DAO{
    
    @Column (name = "id_client",isPrimary = true)
    String id_client;
    @Column (name = "nom")
    String nom;
    @Column (name = "adresse")
    String adresse;
    @Column (name = "contact")
    String contact;
    @Column (name = "date_de_naissance")
    Date date_de_naissance;

    public Client() {
    }

    public Client(String nom, String adresse, String contact, Date date_de_naissance) {
        //this.setId_client(id_client);
        this.setNom(nom);
        this.setAdresse(adresse);
        this.setContact(contact);
        this.setDate_de_naissance(date_de_naissance);
    }


    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }
    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
}
