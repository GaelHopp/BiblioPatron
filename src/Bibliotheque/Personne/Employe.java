package Bibliotheque.Personne;

import Bibliotheque.Connexion.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Gael on 14/10/2014.
 */
public class Employe extends Personne {

    public Employe(String nom, String prenom, int age, String adresse){
        super(nom,prenom,age,adresse);
    }


    /**
     * Insertion d'un employé dans la base
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



            String query2 = "INSERT INTO Employe (idUsager) VALUES (?)";

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



}
