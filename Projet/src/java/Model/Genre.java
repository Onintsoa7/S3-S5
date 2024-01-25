/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;

/**
 *
 * @author user
 */
@TableInfo(name = "genre" ,user = "postgres",pass = "1767",database = "meuble")
public class Genre extends DAO
{
    @Column(name = "id_genre", isPrimary = true)
    private String id_genre;
    
    @Column(name = "nom")
    private String nom;

    public Genre(String id_genre, String nom) {
        this.id_genre = id_genre;
        this.nom = nom;
    }

    public Genre() {
    }

    public String getId_genre() {
        return id_genre;
    }

    public void setId_genre(String id_genre) {
        this.id_genre = id_genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
