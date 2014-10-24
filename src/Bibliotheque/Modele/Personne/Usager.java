package Bibliotheque.Modele.Personne;

import Bibliotheque.Connexion.Connexion;
import Bibliotheque.Modele.Entites.Emprunt;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Entites.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Gael on 14/10/2014.
 */
public class Usager extends Personne {

    public Usager(String nom, String prenom, int age, String adresse){
        super(nom,prenom,age,adresse);
    }


    public Usager ajoutUsager(String nom, String prenom, int age, String adresse){

        Usager usager = new Usager(nom,prenom,age,adresse);


        return usager;

    }


    /**
     * Insertion d'un usager dans la base
     */
    public void insert(){

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "INSERT INTO Personne (nom, prenom, age, adresse) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = null;


            pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, this.getNom());
            pstmt.setString(2, this.getPrenom());
            pstmt.setInt(3, this.getAge());
            pstmt.setString(4, this.getAdresse());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();



             rs.first();

             int idInsert = rs.getInt(1);



            String query2 = "INSERT INTO Usager (idUsager) VALUES (?)";

            PreparedStatement pstmt2 = null;

            pstmt2 = con.prepareStatement(query2);
            pstmt2.setInt(1,idInsert);

            pstmt2.executeUpdate();






            con.close();
        }

        catch(Exception e){
            e.printStackTrace();

        }

}


    /**
     * Identification d'un usager par son nom et prénom
     * @param nom
     * @param prenom
     * @return
     */
    public static Usager e_identification(String nom, String prenom){

        Usager usager = null;

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Personne WHERE nom = ? AND prenom = ?";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            results = pstmt.executeQuery();

            results.next();

                int idPersonne = results.getInt("idPersonne");
                String nomPersonne = results.getString("nom");
                String prenomPersonne = results.getString("prenom");
                int agePersonne = results.getInt("age");
                String adressePersonne = results.getString("adresse");

                String query2 = "SELECT * FROM Usager WHERE idUsager = ?";
                PreparedStatement pstmt2 = null;
                pstmt2 = con.prepareStatement(query2);
                pstmt2.setInt(1, idPersonne);

                ResultSet results2 = pstmt.executeQuery();

            System.out.println(idPersonne);

                if(results2.next()){

                    usager = new Usager(nomPersonne, prenomPersonne, agePersonne, adressePersonne);
                    usager.setIdPersonne(idPersonne);
                }




            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }

        return(usager);




    }


    /**
     * find a user by an id
     * @param id
     * @return
     */
    public static Usager findById(int id){

        Usager usager = null;

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Usager WHERE idUsager = ?";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            results = pstmt.executeQuery();

            if(results.next()){


                String query2 = "SELECT * FROM Personne WHERE idPersonne = ?";
                PreparedStatement pstmt2 = null;
                pstmt2 = con.prepareStatement(query2);
                pstmt2.setInt(1, id);

                ResultSet results2 = pstmt2.executeQuery();

                results2.next();

                int idPersonne = results2.getInt("idPersonne");
                String nomPersonne = results2.getString("nom");
                String prenomPersonne = results2.getString("prenom");
                int agePersonne = results2.getInt("age");
                String adressePersonne = results2.getString("adresse");

                usager = new Usager(nomPersonne, prenomPersonne, agePersonne, adressePersonne);
                usager.setIdPersonne(idPersonne);

            }


            con.close();

        }

        catch(Exception e){
            e.printStackTrace();

        }

        return(usager);


    }


    public void emprunte(Exemplaire exemplaire){

        Emprunt emprunt = new Emprunt(this.getIdPersonne(), exemplaire.getIdExemplaire(), new Timestamp(new Date().getTime()), 0);
        emprunt.insert();

    }


    public void reserve(Oeuvre oeuvre){

        Reservation reservation = new Reservation(this.getIdPersonne(),oeuvre.getIdOeuvre(), new Timestamp(new Date().getTime()), 0);
        reservation.insert();

    }

}
