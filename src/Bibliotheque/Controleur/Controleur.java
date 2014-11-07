package Bibliotheque.Controleur;

import Bibliotheque.Exception.OeuvreExistanteException;
import Bibliotheque.Exception.UsagerExistantException;
import Bibliotheque.Modele.Entites.Emprunt;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Entites.Reservation;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;

/**
 * Created by Gael on 30/10/14.
 */
public class Controleur {


    public Controleur(){

    }

    public void emprunter(Usager usager, Exemplaire exemplaire){

        Emprunt emp = new Emprunt(usager.getIdPersonne(), exemplaire.getIdExemplaire());
        Reservation.e_identification(usager, Oeuvre.findById(exemplaire.getIdOeuvre())).reservationTerminee();

        emp.insert();

    }

    public void reserver(Usager usager, Oeuvre oeuvre){
        Reservation reservation = new Reservation(usager.getIdPersonne(), oeuvre.getIdOeuvre());
        reservation.insert();

    }

    public void ajouterUsager(String nom, String prenom, int age, String adresse) throws UsagerExistantException {
        Usager usager = new Usager(nom,prenom,age,adresse);
        usager.insert();
    }

    public void ajouterExemplaire(Oeuvre oeuvre){
        Exemplaire exemplaire = new Exemplaire(oeuvre.getIdOeuvre(), "Bon");
        exemplaire.insert();
    }

    public void ajouterOeuvre(String titre, String auteur) throws OeuvreExistanteException {
        Oeuvre oeuvre = new Oeuvre(titre, auteur);
        oeuvre.insert();
    }

    public void annulerReservation(Reservation reservation){

        reservation.reservationTerminee();

    }

    public void terminerEmprunt(Emprunt emprunt){
        emprunt.terminerEmprunt();
    }


    public void modifierUsager(Usager usager) throws UsagerExistantException {
        usager.update();
    }






}
