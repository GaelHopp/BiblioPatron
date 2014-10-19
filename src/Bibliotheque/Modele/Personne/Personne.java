package Bibliotheque.Modele.Personne;

/**
 * Created by Gael on 14/10/2014.
 */
public class Personne {

    private int id;
    private String nom;
    private String prenom;
    private int age;
    private String adresse;


    public Personne(String nom, String prenom, int age, String adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
    }

    public String toString(){
        return("NOM : "+this.nom+"  PRENOM : "+this.prenom);
    }



    /*
    ############## GETTERS SETTERS ######################
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
