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
@TableInfo(name = "v_quantite_par_genre_et_mere ", user = "postgres", pass = "1767", database = "meuble")
public class V_detail_client_vente extends DAO {

    @Column(name = "id_mere")
    String id_mere;

    @Column(name = "somme_quantite")
    Float somme_quantite;

    @Column(name = "id_genre")
    String id_genre;

    public V_detail_client_vente(String id_mere, Float somme_quantite, String id_genre) {
        this.id_mere = id_mere;
        this.somme_quantite = somme_quantite;
        this.id_genre = id_genre;
    }

    public String getId_mere() {
        return id_mere;
    }

    public void setId_mere(String id_mere) {
        this.id_mere = id_mere;
    }

    public V_detail_client_vente() {
    }

    public Float getSomme_quantite() {
        return somme_quantite;
    }

    public void setSomme_quantite(Float somme_quantite) {
        this.somme_quantite = somme_quantite;
    }

    public String getId_genre() {
        return id_genre;
    }

    public void setId_genre(String id_genre) {
        this.id_genre = id_genre;
    }

}
