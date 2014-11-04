package Bibliotheque.Interface.Fenetre;

import Bibliotheque.Controleur.Controleur;
import Bibliotheque.Modele.Entites.Emprunt;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Entites.Reservation;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Gael on 30/10/14.
 */
public class FenetreOeuvres extends JFrame{

    JPanel panel;
    Controleur controleur;

    public FenetreOeuvres(){

        panel = new JPanel();

        this.setPreferredSize(new Dimension(800,500));

        panel.setPreferredSize(new Dimension(800,500));

        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);


    }


    public void listerOeuvre(Usager usager){
        ArrayList<Oeuvre> listeOeuvre = Oeuvre.listerOeuvres();

        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));

        for(final Oeuvre oeuvre : listeOeuvre){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));
            JLabel labelID = new JLabel(oeuvre.getIdOeuvre()+"");
            labelID.setPreferredSize(new Dimension(30,20));

            JLabel labelTitre = new JLabel(oeuvre.getTitre());
            labelTitre.setPreferredSize(new Dimension(200,20));

            JLabel labelAuteur = new JLabel(oeuvre.getAuteur());
            labelAuteur.setPreferredSize(new Dimension(100,20));

            int nombreExemplaire = Exemplaire.e_exemplaireDispo(oeuvre).size();

            JLabel labelNombre = new JLabel(nombreExemplaire+"");
            labelNombre.setPreferredSize(new Dimension(50,20));

            panel.add(labelID);
            panel.add(labelTitre);
            panel.add(labelAuteur);
            panel.add(labelNombre);

            if(Reservation.e_identification(usager, oeuvre) == null && Emprunt.e_identification(usager, oeuvre) == null){

            JButton reservation = new JButton("Réserver");

            final Usager usagerFinal = usager;

            reservation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.reserver(usagerFinal,oeuvre);
                    JOptionPane.showMessageDialog(null, "Réservation enregistrée !");
                    dispose();
                }
            });

            panel.add(reservation);

            if(nombreExemplaire > 0){
                JButton emprunt = new JButton("Emprunter");
                emprunt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FenetreExemplaires fenetre = new FenetreExemplaires();
                        fenetre.listerExemplaires(usagerFinal, oeuvre, false);
                        dispose();

                    }
                });

                panel.add(emprunt);
            }
            }



            this.panel.add(panel);

        }
    }
}
