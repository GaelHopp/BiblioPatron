package Bibliotheque.Modele.Entites;

import Bibliotheque.Connexion.Connexion;
import Bibliotheque.Modele.Personne.Usager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Gael on 14/10/2014.
 */
public class Emprunt {

    private int idUsager;
    private int idExemplaire;
    private Timestamp dateEmprunt;
    private int statut;         // 0 en cours, 1 terminé


    public Emprunt(int idUsager, int idExemplaire, Timestamp dateEmprunt, int statut){
        this.idUsager = idUsager;
        this.idExemplaire = idExemplaire;
        this.dateEmprunt = dateEmprunt;
        this.statut = statut;
    }

    public Emprunt(int idUsager, int idExemplaire){
        this.idUsager = idUsager;
        this.idExemplaire = idExemplaire;
        this.dateEmprunt = new Timestamp((new Date()).getTime());
        this.statut = 0;
    }


    public void insert(){

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "INSERT INTO Emprunt (idUsager, idExemplaire, dateEmprunt, statut) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = null;


            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, this.idUsager);
            pstmt.setInt(2, this.idExemplaire);
            pstmt.setTimestamp(3, this.dateEmprunt);
            pstmt.setInt(4, this.statut);

            pstmt.executeUpdate();

            con.close();
        }

        catch(Exception e){
            e.printStackTrace();

        }
    }


    public void update(){
        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "UPDATE Exemplaire SET statut = ? WHERE idExemplaire = ? AND ";
            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, this.statut);
            pstmt.setInt(2, this.idExemplaire);

            pstmt.executeUpdate();

            con.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }


    public static Emprunt e_identification(Usager usager, Exemplaire exemplaire){
        Emprunt emprunt = null;

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Emprunt WHERE idUsager = ? AND idExemplaire = ? WHERE statut = 0";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, usager.getIdPersonne());
            pstmt.setInt(2, exemplaire.getIdExemplaire());
            results = pstmt.executeQuery();

            if(results.next()){

            int idUsager = results.getInt("idUsager");
            int idExemplaire = results.getInt("idExemplaire");
            Timestamp dateEmprunt = results.getTimestamp("dateEmprunt");
            int statut = results.getInt("statut");


            emprunt = new Emprunt(idUsager, idExemplaire, dateEmprunt, statut);
            }

            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }

        return(emprunt);
    }


    public void terminerEmprunt(){

        this.setStatut(1);
        this.update();

    }

/*
###################### GETTERS & SETTERS #########################
 */


    public int getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(int idUsager) {
        this.idUsager = idUsager;
    }

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Timestamp getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Timestamp dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
