package Bibliotheque.Modele.Entites;

import Bibliotheque.Connexion.Connexion;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Created by Gael on 14/10/2014.
 */
public class Emprunt {

    private int idUsager;
    private int idExemplaire;
    private Timestamp dateEmprunt;
    private int statut;


    public Emprunt(int idUsager, int idExemplaire, Timestamp dateEmprunt, int statut){
        this.idUsager = idUsager;
        this.idExemplaire = idExemplaire;
        this.dateEmprunt = dateEmprunt;
        this.statut = statut;
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


}
