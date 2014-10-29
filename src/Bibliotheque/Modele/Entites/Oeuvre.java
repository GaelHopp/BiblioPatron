package Bibliotheque.Modele.Entites;

import Bibliotheque.Connexion.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Gael on 14/10/2014.
 */
public class Oeuvre {

    private int idOeuvre;
    private String titre;
    private String auteur;
    private int statut;


    public Oeuvre(String titre, String auteur){
        this.titre = titre;
        this.auteur = auteur;
        this.statut = 1;
    }


    public void insert(){

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "INSERT INTO Oeuvre (titre, auteur,satut) VALUES (?, ?, ?)";

            PreparedStatement pstmt = null;


            pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.getTitre());
            pstmt.setString(2, this.getAuteur());
            pstmt.setInt(3, this.getStatut());

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

            String query = "UPDATE Oeuvre SET statut = ?, titre=?, auteur=? WHERE idOeuvre = ? ";
            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, this.statut);
            pstmt.setString(2, this.titre);
            pstmt.setString(3, this.auteur);
            pstmt.setInt(4, this.idOeuvre);


            pstmt.executeUpdate();

            con.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }


    public static Oeuvre e_identification(String titre){

        Oeuvre oeuvre = null;

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Oeuvre WHERE titre = ?";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setString(1, titre);
            results = pstmt.executeQuery();

            if(results.next()){

            int idOeuvre = results.getInt("idOeuvre");
            String titreOeuvre = results.getString("titre");
            String auteur = results.getString("auteur");


                oeuvre = new Oeuvre(titreOeuvre, auteur);
                oeuvre.setIdOeuvre(idOeuvre);

            }

            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }

        return(oeuvre);
    }


    public static Oeuvre findById(int id){

        Oeuvre oeuvre = null;

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Oeuvre WHERE idOeuvre = ?";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            results = pstmt.executeQuery();

            results.next();

            int idOeuvre = results.getInt("idOeuvre");
            String titreOeuvre = results.getString("titre");
            String auteur = results.getString("auteur");


            oeuvre = new Oeuvre(titreOeuvre, auteur);
            oeuvre.setIdOeuvre(idOeuvre);

            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }

        return(oeuvre);
    }


    public String toString(){
        return("Titre = "+this.titre+"   Auteur = "+this.auteur);
    }

    public void delete(){
        this.setStatut(0);
        this.update();

    }


    /*
       ################  GETTERS & SETTERS ###################
     */

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getStatut() { return statut; }

    public void setStatut(int statut) { this.statut = statut; }
}
