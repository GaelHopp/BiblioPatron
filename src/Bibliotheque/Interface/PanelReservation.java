package Bibliotheque.Interface;

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
public class PanelReservation extends PanelGeneral {

    public PanelReservation(){
        super();
    }


    public void listerReservation(){
        ArrayList<Reservation> listeReservation = Reservation.listerReservationEnCours();


        this.liste.setLayout(new BoxLayout(this.liste, BoxLayout.PAGE_AXIS));

        for(Reservation reservation : listeReservation){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));

           Usager usager = Usager.findById(reservation.getIdUsager());
           Oeuvre oeuvre = Oeuvre.findById(reservation.getIdOeuvre());

           // Usager usager = new Usager("Hopppp", "Gaelll", 21, "Adresse");
           // Oeuvre oeuvre = new Oeuvre("Titre de l'oeuvre", "Auteur");

            JLabel labelNomUsager = new JLabel(usager.getNom());
            labelNomUsager.setPreferredSize(new Dimension(150,20));

            JLabel labelPrenomUsager = new JLabel(usager.getPrenom());
            labelPrenomUsager.setPreferredSize(new Dimension(150,20));

            JLabel labelTitreOeuvre = new JLabel(oeuvre.getTitre());
            labelTitreOeuvre.setPreferredSize(new Dimension(200,20));

            String date = new SimpleDateFormat("MM/dd/yyyy").format(reservation.getDate());

            JLabel labelDate = new JLabel(date);
            labelDate.setPreferredSize(new Dimension(100,20));

            panel.add(labelNomUsager);
            panel.add(labelPrenomUsager);
            panel.add(labelTitreOeuvre);
            panel.add(labelDate);

            JButton emprunt = new JButton("Emprunter");
            panel.add(emprunt);


            this.liste.add(panel);

        }
    }


}
