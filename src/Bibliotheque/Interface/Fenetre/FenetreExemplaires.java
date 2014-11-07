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
 * Created by Gael on 03/11/14.
 */
public class FenetreExemplaires extends JFrame {

    JPanel panel;
    Controleur controleur;

    public FenetreExemplaires(Controleur c){
        this.controleur = c;
        panel = new JPanel();



        this.setPreferredSize(new Dimension(800,500));

        panel.setPreferredSize(new Dimension(800,500));

        JScrollPane scroll = new JScrollPane(this.panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(800, 500));



        this.getContentPane().add(scroll, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);


    }


    public void listerExemplaires(Usager usager, Oeuvre oeuvre, boolean res){
        ArrayList<Exemplaire> listeExemplaire = new ArrayList<Exemplaire>();

       if(!res)
        listeExemplaire = Exemplaire.e_exemplaireDispo(oeuvre);
       else
        listeExemplaire = Exemplaire.e_exemplaireReserve(oeuvre);

        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));

        for(final Exemplaire exemplaire : listeExemplaire){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));
            JLabel labelID = new JLabel(exemplaire.getIdExemplaire()+"");
            labelID.setPreferredSize(new Dimension(30,20));

            JLabel labelTitre = new JLabel(oeuvre.getTitre());
            labelTitre.setPreferredSize(new Dimension(200,20));

            JLabel labelAuteur = new JLabel(oeuvre.getAuteur());
            labelAuteur.setPreferredSize(new Dimension(100,20));



            panel.add(labelID);
            panel.add(labelTitre);
            panel.add(labelAuteur);




                JButton emprunt = new JButton("Emprunter");

                final Usager usagerFinal = usager;
                final Oeuvre oeuvreFinale = oeuvre;


                emprunt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controleur.emprunter(usagerFinal,exemplaire);
                        Reservation reservationTheorique = Reservation.e_identification(usagerFinal, oeuvreFinale);
                        if(reservationTheorique != null){
                            controleur.annulerReservation(reservationTheorique);
                        }
                        JOptionPane.showMessageDialog(null, "Emprunt enregistré !");
                        dispose();
                    }
                });

                panel.add(emprunt);





            this.panel.add(panel);

        }
    }
}

