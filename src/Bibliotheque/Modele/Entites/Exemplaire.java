package Bibliotheque.Modele.Entites;

import Bibliotheque.Connexion.Connexion;
import Bibliotheque.Modele.Personne.Usager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Gael on 14/10/2014.
 */
public class Exemplaire {

    private int idExemplaire;
    private int idOeuvre;
    private String etat;
    private int statut;


    public Exemplaire(int idOeuvre, String etat){
        this.idOeuvre = idOeuvre;
        this.etat = etat;
        this.statut = 1;
    }


    /**
     * Retourne la liste des exemplaires disponibles pour une oeuvre
     * @param oeuvre
     * @return la liste des exemplaires
     */
    public static ArrayList<Exemplaire> e_exemplaireDispo(Oeuvre oeuvre){

        ArrayList<Exemplaire> listeExemplaire = new ArrayList<Exemplaire>();


        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Exemplaire WHERE idOeuvre = ? AND statut = 1 AND idExemplaire NOT IN (SELECT idExemplaire FROM Emprunt WHERE statut = 0)";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, oeuvre.getIdOeuvre());

            results = pstmt.executeQuery();

            while(results.next()){

            int idExemplaire = results.getInt("idExemplaire");
            int idOeuvre = results.getInt("idOeuvre");
            String etat = results.getString("etat");
            int statut = results.getInt("statut");


            Exemplaire exemplaire = new Exemplaire(idOeuvre, etat);
            exemplaire.setIdExemplaire(idExemplaire);
            exemplaire.setStatut(statut);

            listeExemplaire.add(exemplaire);



            }

            String queryCount = "SELECT COUNT(*) AS nb FROM Reservation WHERE idOeuvre = ? AND statut = 0";


            PreparedStatement pstmt2 = null;


            ResultSet results2;

            pstmt2 = con.prepareStatement(queryCount);
            pstmt2.setInt(1, oeuvre.getIdOeuvre());

            results2 = pstmt2.executeQuery();

            results2.next();

            int nbExemplaireReserve = results2.getInt("nb");



            if(nbExemplaireReserve < listeExemplaire.size()){
                for(int i=0; i < nbExemplaireReserve; i++){
                    listeExemplaire.remove(listeExemplaire.size()-1);
                }
            }
            else{
                listeExemplaire.removeAll(listeExemplaire);
            }

        }catch(Exception e){
            e.printStackTrace();

        }

        return listeExemplaire;

    }


    public static ArrayList<Exemplaire> e_exemplaireReserve(Oeuvre oeuvre){

        ArrayList<Exemplaire> listeExemplaire = new ArrayList<Exemplaire>();


        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Exemplaire WHERE idOeuvre = ? AND idExemplaire NOT IN (SELECT idExemplaire FROM Emprunt WHERE statut = 0) AND idOeuvre IN (SELECT idOeuvre FROM Reservation WHERE statut=0)";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, oeuvre.getIdOeuvre());

            results = pstmt.executeQuery();

            while(results.next()){

                int idExemplaire = results.getInt("idExemplaire");
                int idOeuvre = results.getInt("idOeuvre");
                String etat = results.getString("etat");
                int statut = results.getInt("statut");


                Exemplaire exemplaire = new Exemplaire(idOeuvre, etat);
                exemplaire.setIdExemplaire(idExemplaire);
                exemplaire.setStatut(statut);

                listeExemplaire.add(exemplaire);



            }



        }catch(Exception e){
            e.printStackTrace();

        }

        return listeExemplaire;

    }


    public static ArrayList<Exemplaire> e_AllExemplaires(Oeuvre oeuvre){

        ArrayList<Exemplaire> listeExemplaire = new ArrayList<Exemplaire>();


        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Exemplaire WHERE idOeuvre = ?";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, oeuvre.getIdOeuvre());

            results = pstmt.executeQuery();

            while(results.next()){

                int idExemplaire = results.getInt("idExemplaire");
                int idOeuvre = results.getInt("idOeuvre");
                String etat = results.getString("etat");
                int statut = results.getInt("statut");


                Exemplaire exemplaire = new Exemplaire(idOeuvre, etat);
                exemplaire.setIdExemplaire(idExemplaire);
                exemplaire.setStatut(statut);

                listeExemplaire.add(exemplaire);



            }



        }catch(Exception e){
            e.printStackTrace();

        }

        return listeExemplaire;

    }




    public void insert(){
        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "INSERT INTO Exemplaire (idOeuvre, etat, statut) VALUES (?, ?, ?)";

            PreparedStatement pstmt = null;


            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, this.idOeuvre);
            pstmt.setString(2, this.etat);
            pstmt.setInt(3, this.statut);


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

            String query = "UPDATE Exemplaire SET etat = ?, statut = ? WHERE idExemplaire = ?";
            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.etat);
            pstmt.setInt(2, this.statut);
            pstmt.setInt(3, this.idExemplaire);

            pstmt.executeUpdate();

            con.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public static Exemplaire e_identification(int id){
        Exemplaire exemplaire = null;

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Exemplaire WHERE idExemplaire=?";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            results = pstmt.executeQuery();

            if(results.next()){

                int idOeuvre = results.getInt("idOeuvre");
                String etat = results.getString("etat");


                exemplaire = new Exemplaire(idOeuvre, etat);
                exemplaire.setIdExemplaire(id);
            }

            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }

        return(exemplaire);
    }

    public void delete(){
        this.setStatut(0);
        this.update();
    }

    public void activer(){
        this.setStatut(1);
        this.update();
    }


  /*  public HashMap<Integer, Integer> nombreExemplairesDisponibles(){

        HashMap<Integer, Integer> liste = new HashMap<Integer, Integer>();


        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT idOeuvre FROM Oeuvre WHERE statut = 1";
            PreparedStatement pstmt = null;


            ResultSet results;

            pstmt = con.prepareStatement(query);

            results = pstmt.executeQuery();

            while(results.next()){

                int idOeuvre = results.getInt("idOeuvre");


                String query2 = "SELECT COUNT(*) AS nb FROM Exemplaire WHERE idOeuvre = ? AND statut = 1";
                PreparedStatement pstmt2 = null;


                ResultSet results2;

                pstmt2 = con.prepareStatement(query2);

                results2 = pstmt2.executeQuery();

                results2.next();

                liste.put(idOeuvre, results2.getInt("nb"));
            }

            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }


        return(liste);
    }*/


    /*
      ############# GETTERS & SETTERS #################
     */

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
