package Model;

import MyDAO.DAO.DAO;
import MyDAO.TableMapping.Column;
import MyDAO.TableMapping.TableInfo;

@TableInfo(name = "v_detail_vente_client", user = "postgres", pass = "1767", database = "meuble")
public class V_detail_vente_client extends DAO {

    @Column(name = "id_vente")
    String id_vente;

    @Column(name = "id_client")
    String id_client;

    @Column(name = "montant")
    Float montant;

    @Column(name = "date_vente")
    String date_vente;

    @Column(name = "montant_sans_remise")
    Float montant_sans_remise;

    @Column(name = "client_name")
    String client_name;

    @Column(name = "date_de_naissance")
    String date_de_naissance;

    public V_detail_vente_client() {
    }

    public V_detail_vente_client(String id_vente, String id_client, Float montant, String date_vente, Float montant_sans_remise, String client_name, String date_de_naissance) {
        this.id_vente = id_vente;
        this.id_client = id_client;
        this.montant = montant;
        this.date_vente = date_vente;
        this.montant_sans_remise = montant_sans_remise;
        this.client_name = client_name;
        this.date_de_naissance = date_de_naissance;
    }

    public String getId_vente() {
        return id_vente;
    }

    public void setId_vente(String id_vente) {
        this.id_vente = id_vente;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getDate_vente() {
        return date_vente;
    }

    public void setDate_vente(String date_vente) {
        this.date_vente = date_vente;
    }

    public Float getMontant_sans_remise() {
        return montant_sans_remise;
    }

    public void setMontant_sans_remise(Float montant_sans_remise) {
        this.montant_sans_remise = montant_sans_remise;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }
    
}
