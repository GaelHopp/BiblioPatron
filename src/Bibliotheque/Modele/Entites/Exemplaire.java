package Bibliotheque.Modele.Entites;

import Bibliotheque.Connexion.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gael on 14/10/2014.
 */
public class Exemplaire {

    private int idExemplaire;
    private int idOeuvre;
    private String etat;


    public Exemplaire(int idOeuvre, String etat){
        this.idOeuvre = idOeuvre;
        this.etat = etat;

    }


    /**
     * Retourne la liste des exemplaires disponibles pour une oeuvre
     * @param oeuvre
     * @return la liste des exemplaires
     */
    public static List<Exemplaire> e_exemplaireDispo(Oeuvre oeuvre){

        ArrayList<Exemplaire> listeExemplaire = new ArrayList<Exemplaire>();


        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "SELECT * FROM Exemplaire WHERE idOeuvre = ? AND idExemplaire NOT IN (SELECT idExemplaire FROM Emprunt)";
            PreparedStatement pstmt = null;


            ResultSet results;



            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, oeuvre.getIdOeuvre());

            results = pstmt.executeQuery();

            while(results.next()){

            int idExemplaire = results.getInt("idExemplaire");
            int idOeuvre = results.getInt("idOeuvre");
            String etat = results.getString("etat");


            Exemplaire exemplaire = new Exemplaire(idOeuvre, etat);
            exemplaire.setIdExemplaire(idExemplaire);

            listeExemplaire.add(exemplaire);


            }
        }catch(Exception e){
            e.printStackTrace();

        }

        return listeExemplaire;

    }


    public void update(){

        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "UPDATE Exemplaire SET etat = ? WHERE idExemplaire = ?";
            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.etat);
            pstmt.setInt(2, this.idExemplaire);

            pstmt.executeUpdate();

            con.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }


    public void retourEnRayon(){



    }

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


}
