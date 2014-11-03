package Bibliotheque.Controleur;

import Bibliotheque.Modele.Entites.Emprunt;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;

/**
 * Created by Gael on 30/10/14.
 */
public class Controleur {


    public Controleur(){

    }

    public void emprunter(Usager usager, Oeuvre oeuvre){
        Exemplaire ex = Exemplaire.e_exemplaireDispo(oeuvre).get(0);
        Emprunt emp = new Emprunt(usager.getIdPersonne(), ex.getIdExemplaire());
        emp.insert();

    }

}
