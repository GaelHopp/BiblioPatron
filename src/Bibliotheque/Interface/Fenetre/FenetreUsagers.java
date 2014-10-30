package Bibliotheque.Interface.Fenetre;

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
public class FenetreUsagers extends JFrame {

    JPanel panel;

    public FenetreUsagers(){



        panel = new JPanel();

        this.setPreferredSize(new Dimension(800,500));

        panel.setPreferredSize(new Dimension(800,500));


        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }


    public void listerUsager(Oeuvre oeuvre, boolean empruntOk){
        ArrayList<Usager> listeUsager = Usager.listerUsagers();

        final Oeuvre oeuvreFinal = oeuvre;

        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));

        for(final Usager usager : listeUsager){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));
            JLabel labelID = new JLabel(usager.getIdPersonne()+"");
            labelID.setPreferredSize(new Dimension(30,20));

            JLabel labelNom = new JLabel(usager.getNom());
            labelNom.setPreferredSize(new Dimension(100,20));

            JLabel labelPrenom = new JLabel(usager.getPrenom()+"");
            labelPrenom.setPreferredSize(new Dimension(100,20));

            JLabel labelAge = new JLabel(usager.getAge()+"");
            labelAge.setPreferredSize(new Dimension(30,20));


            panel.add(labelID);
            panel.add(labelNom);
            panel.add(labelPrenom);
            panel.add(labelAge);



            JButton reservation = new JButton("Réserver");

            reservation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Reservation res = new Reservation(usager.getIdPersonne(), oeuvreFinal.getIdOeuvre());
                    res.insert();
                    JOptionPane.showMessageDialog(null, "Réservation enregistrée !");
                    dispose();
                }
            });

            panel.add(reservation);

            if(empruntOk){

            JButton emprunt = new JButton("Emprunt");

            emprunt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    Exemplaire ex = Exemplaire.e_exemplaireDispo(oeuvreFinal).get(0);
                    Emprunt emp = new Emprunt(usager.getIdPersonne(), ex.getIdExemplaire());
                    emp.insert();
                    JOptionPane.showMessageDialog(null, "Emprunt enregistré !");
                    dispose();
                }
            });


            panel.add(emprunt);
            }

            this.panel.add(panel);

        }
    }


}
