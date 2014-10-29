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
public class Reservation {

    private int idUsager;
    private int idOeuvre;
    private Timestamp dateRes;
    private int statut;         // 0 en cours 1 terminée


    public Reservation(int idUsager, int idOeuvre, Timestamp dateRes, int statut){
        this.idUsager = idUsager;
        this.idOeuvre = idOeuvre;
        this.dateRes = dateRes;
        this.statut = statut;
    }


    /**
     * Insertion de la reservation dans la base
     */
    public void insert(){

        try {
            java.sql.Connection con = Connexion.connexion();


           Usager usager = Usager.findById(this.idUsager);
           Oeuvre oeuvre = Oeuvre.findById(this.idOeuvre);

            if(e_identification(usager, oeuvre) == null){

                String query = "INSERT INTO Reservation (idUsager, idOeuvre, dateRes, statut) VALUES (?, ?, ?, ?)";

                PreparedStatement pstmt = null;


                pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, this.idUsager);
                pstmt.setInt(2, this.idOeuvre);
                pstmt.setTimestamp(3, this.dateRes);
                pstmt.setInt(4, this.statut);

                pstmt.executeUpdate();

                con.close();
            }



        }

        catch(Exception e){
            e.printStackTrace();

        }

    }





    /**
     * Identification d'une reservation pour un usager et une oeuvre
     * @param usager
     * @param oeuvre
     * @return la reservation
     */
    public static Reservation e_identification(Usager usager, Oeuvre oeuvre){


        Reservation reservation = null;

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Reservation WHERE idUsager = ? AND idOeuvre = ? AND statut = 0";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, usager.getIdPersonne());
            pstmt.setInt(2, oeuvre.getIdOeuvre());
            results = pstmt.executeQuery();

            if(results.next()){

            int idUsager = results.getInt("idUsager");
            int idOeuvre = results.getInt("idOeuvre");
            Timestamp dateRes = results.getTimestamp("dateRes");
            int statut = results.getInt("statut");


            reservation = new Reservation(idUsager, idOeuvre, dateRes, statut);
            }

            con.close();


        }

        catch(Exception e){
            e.printStackTrace();

        }

        return(reservation);

    }


    /**
     * update de la reservation dans la base
     */
    public void update(){

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "UPDATE Reservation SET statut = ? WHERE idUsager = ? AND idOeuvre = ?";
            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, this.statut);
            pstmt.setInt(2, this.idUsager);
            pstmt.setInt(3, this.idOeuvre);

            pstmt.executeUpdate();

            con.close();
    }catch(Exception e){
            e.printStackTrace();

        }
    }


    /**
     * MAJ de la reservation comme terminée
     */
    public void reservationTerminee(){

         this.setStatut(1);
         this.update();

    }





    /*
    ############### GETTERS & SETTERS ###################
     */

    public int getIdUsager() {
        return idUsager;
    }

    public void setIdUsager(int idUsager) {
        this.idUsager = idUsager;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(int idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public Timestamp getDate() {
        return dateRes;
    }

    public void setDate(Timestamp dateRes) {
        this.dateRes = dateRes;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
