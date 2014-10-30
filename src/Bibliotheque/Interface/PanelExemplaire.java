package Bibliotheque.Interface;

import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Entites.Reservation;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelExemplaire extends PanelGeneral {

    public PanelExemplaire(){
        super();
    }


    public void listerExemplaire(){
        ArrayList<Exemplaire> listeExemplaires = new ArrayList<Exemplaire>();

        listeExemplaires.add(new Exemplaire(1, "bon"));

        this.liste.setLayout(new BoxLayout(this.liste, BoxLayout.PAGE_AXIS));

        for(Exemplaire exemplaire : listeExemplaires){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));


            Oeuvre oeuvre = Oeuvre.findById(exemplaire.getIdOeuvre());

            // Usager usager = new Usager("Hopppp", "Gaelll", 21, "Adresse");
            // Oeuvre oeuvre = new Oeuvre("Titre de l'oeuvre", "Auteur");


            JLabel labelTitreOeuvre = new JLabel(oeuvre.getTitre());
            labelTitreOeuvre.setPreferredSize(new Dimension(200,20));

            JLabel labelAuteur = new JLabel(oeuvre.getAuteur());
            labelTitreOeuvre.setPreferredSize(new Dimension(200,20));


            panel.add(labelTitreOeuvre);
            panel.add(labelAuteur);

            JButton emprunt = new JButton("Emprunter");
            panel.add(emprunt);


            this.liste.add(panel);

        }
    }

}
