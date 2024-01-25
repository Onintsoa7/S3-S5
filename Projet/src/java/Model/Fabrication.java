/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
/**
 *
 * @author P15A-Mirado-159
 */
@TableInfo(name = "fabrication",user = "postgres",pass = "root",database = "meuble2")
public class Fabrication extends DAO{
//     id_fabrication | id_mere | quantite | date_fabrication
    @Column (name = "id_fabrication",isPrimary = true)
    String id_fabrication;
    
    @Column (name = "id_mere")
    String idmere;
    
    @Column (name = "quantite")
    float quantite;
    
    @Column (name = "date_fabrication")
    Date date_fabrication;
    
    public Materiel[] insertion_fabrication (Fabrication fabrication,Connection connection) throws Exception{
        Materiel[] valiny = null;
        boolean isOpen = false;
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
        }else{
            isOpen = true;
        }
        try {
            valiny = this.fabrication(connection);
            System.out.println("valiiny "+valiny.length);
            if (valiny.length == 0) {
                System.out.println(this.getIdmere());
                Formule[] liste = Formule.get_materiel_et_quantite_par_meuble(this.getIdmere(), connection);
                for (int i = 0; i < liste.length; i++) {
                    Mouvement m = new Mouvement();
                    m.setId_materiel(liste[i].getMateriel().getIdMateriel());
                    m.setQuantite_entree(0);
                    m.setQuantite_sortie(liste[i].getQuantite()*this.getQuantite());
                    m.insererMouvement(connection);
                }
                fabrication.insert_fabrication(connection);
            }else{
                return valiny;   
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valiny;
    }
    
    public void insert_fabrication(Connection connection ) throws Exception{
        boolean isOpen = false;
        String sql = "INSERT INTO fabrication (id_mere,quantite) values(?,?) ";
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
            connection.setAutoCommit(false);
        }else{
            isOpen = true;
        }
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, this.getIdmere());
            statement.setFloat(2, this.getQuantite());
            statement.executeUpdate();
            
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
    }
    
        public Materiel[] fabrication(Connection connection) throws Exception{
        boolean isOpen = false;
        if (connection == null) {
            connection = Model.ConnectionPs.connexionPostgreSQL();
        }else{
            isOpen = true;
        }
        Materiel[] valiny = null;
        try {
            Formule[] listeFormule = Formule.get_materiel_et_quantite_par_meuble(this.getIdmere(), connection);
            if (listeFormule.length == 0) {
                throw new Exception("pas de formule inserer pour ce meuble");
            }
            Vector<Materiel> listeMateriels = new Vector<>();
            System.out.println("id_mere "+this.getIdmere());
            System.out.println("tonga ato");
            for (int i = 0; i < listeFormule.length; i++) {
                Materiel materiel = new Materiel();
                materiel = materiel.verifyStock(listeFormule[i].getMateriel().getIdMateriel(), connection);
                float newReste = 0;
                newReste = materiel.getReste() - (listeFormule[i].getQuantite() * this.getQuantite());
                System.out.println(listeFormule[i].getMateriel().getIdMateriel() + " : " + materiel.getReste() + " - " + (listeFormule[i].getQuantite()* this.getQuantite()));
                if (newReste < 0 ) {
                    System.out.println("verif: "+ newReste*-1);
                    materiel.setReste(newReste * (-1)); 
                    listeMateriels.add(materiel);
                }
            }
                
            valiny = listeMateriels.toArray(new Materiel[listeMateriels.size()]);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (isOpen == false) {
//                connection.commit();
                connection.close();
            }
        }
        return valiny;
    }
        
    public Fabrication() {
    }

    public String getIdmere() {
        return idmere;
    }

    public void setIdmere(String idmere) {
        this.idmere = idmere;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public Date getDate_fabrication() {
        return date_fabrication;
    }

    public void setDate_fabrication(Date date_fabrication) {
        this.date_fabrication = date_fabrication;
    }

    public void setQuantite(String Quantite) throws Exception{
        if(Float.valueOf(Quantite) <= 0 ){
            throw new Exception("Quantite negatif");
        }
        this.setQuantite(Float.valueOf(Quantite));
    }
    
//    public String insertFabrication( Connection connection) throws Exception {
//        String generatedId = null;
//        boolean IsOpen = false;
//        if (connection == null) {
//            connection = Model.ConnectionPs.connexionPostgreSQL;
//        } else {
//            IsOpen = true;
//        }
//        try {
//            String sql = "INSERT INTO fabrication ( idmere, date_fabrication, quantite) VALUES (?,now(),?) returning id_fabrication ";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, this.getIdmere());
//            statement.setFloat(2, this.getQuantite());
//            // Execute the update
//            ResultSet resultSet = statement.executeQuery();
//
//            // Retrieve the generated id
//            if (resultSet.next()) {
//                generatedId = resultSet.getString("idmere");
//            }
//        } catch (SQLException e) {
//            //connection.rollback();
//            e.printStackTrace();
//        } finally {
//            if (IsOpen == false) {
//                connection.close();
//            }
//        }
//        return generatedId;
//    }
}
