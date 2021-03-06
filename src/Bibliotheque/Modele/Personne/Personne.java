package Bibliotheque.Modele.Personne;

import Bibliotheque.Connexion.Connexion;

import java.sql.PreparedStatement;

/**
 * Created by Gael on 14/10/2014.
 */
public class Personne {

    private int idPersonne;
    private String nom;
    private String prenom;
    private int age;
    private String adresse;
    private int statut;


    public Personne(String nom, String prenom, int age, String adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.statut = 1;
    }

    public String toString(){
        return("NOM : "+this.nom+"  PRENOM : "+this.prenom);
    }

    public void update(){
        try {
            java.sql.Connection con = Connexion.connexion();

            String query = "UPDATE Personne SET nom=?, prenom=?, age=?, adresse=?, statut = ? WHERE idPersonne = ?";
            PreparedStatement pstmt = null;

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.prenom);
            pstmt.setInt(3, this.age);
            pstmt.setString(4, this.adresse);
            pstmt.setInt(5, this.statut);
            pstmt.setInt(6, this.idPersonne);

            pstmt.executeUpdate();

            con.close();
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public void delete(){
        this.setStatut(0);
        this.update();
    }

    public void activer(){
        this.setStatut(1);
        this.update();
    }



    /*
    ############## GETTERS SETTERS ######################
     */

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
