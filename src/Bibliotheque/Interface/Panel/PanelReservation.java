package Bibliotheque.Interface.Panel;

import Bibliotheque.Controleur.Controleur;
import Bibliotheque.Interface.Fenetre.FenetreExemplaires;
import Bibliotheque.Modele.Entites.Emprunt;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Entites.Reservation;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelReservation extends PanelGeneral {

    public PanelReservation(Controleur controleur){
        super(controleur);
    }


    public void listerReservation(){
        ArrayList<Reservation> listeReservation = Reservation.listerReservationEnCours();


        this.liste.setLayout(new BoxLayout(this.liste, BoxLayout.PAGE_AXIS));

        for(final Reservation reservation : listeReservation){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));

          final Usager usager = Usager.findById(reservation.getIdUsager());
          final Oeuvre oeuvre = Oeuvre.findById(reservation.getIdOeuvre());

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

            int nombreExemplaireDispo = Exemplaire.e_exemplaireDispo(oeuvre).size();

            final Usager usagerFinal = Usager.findById(reservation.getIdUsager());
            final Oeuvre oeuvreFinal = Oeuvre.findById(reservation.getIdOeuvre());



            JButton emprunt = new JButton("Emprunter");
            JButton annuler = new JButton("X");

            emprunt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FenetreExemplaires fenetre = new FenetreExemplaires(controleur);
                    fenetre.listerExemplaires(usagerFinal, oeuvreFinal, true);

                }
            });

            annuler.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.annulerReservation(reservation);
                }
            });

            panel.add(emprunt);



            this.liste.add(panel);

        }
    }


}
