package Bibliotheque.Main;

import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Personne.Usager;

/**
 * Created by Gael on 15/10/2014.
 */
public class Main {


    public static void main(String[] args) {


        Usager usager = new Usager("Gael", "Hopp", 21, "RUE X");

        //usager.insert(); // OK

        //Usager usagerTest = Usager.e_identification("Gael", "Hopp"); // OK


        Oeuvre oeuvre = new Oeuvre("Joris Favier", "Dieu");

        oeuvre.insert();

        Oeuvre oeuvreTest = Oeuvre.e_identification("Joris Favier");

        System.out.println(oeuvreTest);








}


}