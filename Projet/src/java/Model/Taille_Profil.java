/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author user
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Taille_Profil {

    private String id_taille_profil;
    private float nombre;
    private Date date;
    private Taille taille;

    public static Taille_Profil[] taille_profils(Taille id_taille, Connection connection) throws Exception {
    Taille t = id_taille.taille_by_id(connection);
    String request = "select * from taille_profil order by date desc limit 1";
    boolean isOpen = false;
    ArrayList<Taille_Profil> ouvrier_liste = null;
    Taille_Profil[] ouvrier_tableau = null;
    try {
        if (connection == null) {
            connection = ConnectionPs.connexionPostgreSQL();
        } else {
            isOpen = true;
        }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

        ouvrier_liste = new ArrayList<>();
        while (resultSet.next()) {
            Taille taille = new Taille();
            taille.setIdTaille(id_taille.getIdTaille());
            ouvrier_liste.add(new Taille_Profil(
                    resultSet.getString("id_taille_profil"),
                    resultSet.getFloat("nombre") * t.getNiveau(),
                    resultSet.getDate("date"),
                    taille
            ));
        }

        ouvrier_tableau = ouvrier_liste.toArray(new Taille_Profil[0]);
    } catch (Exception e) {
        System.out.println(e);
    } finally {
        if (!isOpen) {
            connection.close();
        }
    }

    return ouvrier_tableau;
}

    public Taille_Profil(String id_taille_profil, float nombre, Date date, Taille taille) {
        this.id_taille_profil = id_taille_profil;
        this.nombre = nombre;
        this.date = date;
        this.taille = taille;
    }

    public Taille_Profil() {
    }

    public String getId_taille_profil() {
        return id_taille_profil;
    }

    public void setId_taille_profil(String id_taille_profil) {
        this.id_taille_profil = id_taille_profil;
    }

    public float getNombre() {
        return nombre;
    }

    public void setNombre(float nombre) {
        this.nombre = nombre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Taille getTaille() {
        return taille;
    }

    public void setTaille(Taille taille) {
        this.taille = taille;
    }

}
