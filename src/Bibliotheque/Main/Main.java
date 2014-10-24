package Bibliotheque.Main;

import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Personne.Usager;

import java.util.List;

/**
 * Created by Gael on 15/10/2014.
 */
public class Main {


    public static void main(String[] args) {


        Usager usager = new Usager("Hopp", "Gael", 21, "RUE X");

        usager.insert(); // OK

        Usager usagerTest = Usager.e_identification("Hopp", "Gael"); // OK


        Oeuvre oeuvre = new Oeuvre("Joris Favier", "Dieu");
        Oeuvre oeuvre2 = new Oeuvre("Thomas Kedziora", "BretelleMan");

        oeuvre.insert();
        oeuvre2.insert();

        oeuvre = Oeuvre.e_identification("Joris Favier");
        oeuvre2 = Oeuvre.e_identification("Thomas Kedziora");


        new Exemplaire(oeuvre.getIdOeuvre(), "Mauvais").insert();
        new Exemplaire(oeuvre.getIdOeuvre(), "Mauvais").insert();
        new Exemplaire(oeuvre.getIdOeuvre(), "Bon").insert();
        new Exemplaire(oeuvre2.getIdOeuvre(), "Mauvais").insert();
        new Exemplaire(oeuvre2.getIdOeuvre(), "Mauvais").insert();
        new Exemplaire(oeuvre2.getIdOeuvre(), "Tres bon").insert();



        oeuvre = Oeuvre.e_identification("Joris Favier");

        Usager gael = Usager.e_identification("Hopp", "Gael");

        System.out.println("IDGAEL "+gael.getIdPersonne());

        gael.reserve(oeuvre);

        List<Exemplaire> exemplaires = Exemplaire.e_exemplaireDispo(Oeuvre.e_identification("Thomas Kedziora"));

        if(!exemplaires.isEmpty())
            gael.emprunte(exemplaires.get(0));

}


}
