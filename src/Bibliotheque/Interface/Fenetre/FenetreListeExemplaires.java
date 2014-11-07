package Bibliotheque.Interface.Fenetre;

import Bibliotheque.Controleur.Controleur;
import Bibliotheque.Interface.Panel.PanelGeneral;
import Bibliotheque.Interface.Panel.PanelUsager;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Gael on 29/10/14.
 */
public class FenetreListeExemplaires extends JFrame {

    PanelGeneral panelGeneral;
    Controleur controleur;

        public FenetreListeExemplaires(Controleur c){

            this.controleur = c;
            panelGeneral = new PanelGeneral(controleur);

            this.setPreferredSize(new Dimension(800,500));

            panelGeneral.setPreferredSize(new Dimension(800,500));



            this.getContentPane().add(panelGeneral, BorderLayout.CENTER);
            this.pack();
            this.setVisible(true);

        }


    public void listerExemplaire(Oeuvre oeuvre){
        ArrayList<Exemplaire> listeExemplaires = Exemplaire.e_AllExemplaires(oeuvre);



        panelGeneral.liste.setLayout(new BoxLayout(this.panelGeneral.liste, BoxLayout.PAGE_AXIS));

        for(final Exemplaire exemplaire : listeExemplaires){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));

            JLabel labelTitreOeuvre = new JLabel(oeuvre.getTitre());
            labelTitreOeuvre.setPreferredSize(new Dimension(150,20));

            JLabel labelAuteur = new JLabel(oeuvre.getAuteur());
            labelTitreOeuvre.setPreferredSize(new Dimension(150,20));

            JLabel labelStatut = new JLabel(exemplaire.getStatut()+"");
            labelStatut.setPreferredSize(new Dimension(20,20));


            panel.add(labelTitreOeuvre);
            panel.add(labelAuteur);
            panel.add(labelStatut);

            final Oeuvre oeuvreFinale = oeuvre;

            JButton modif = new JButton("Modifier");
            JButton supp = new JButton("-");
            JButton activer = new JButton("+");

            modif.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            panel.add(modif);

            if(exemplaire.getStatut() == 1){

            supp.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controleur.supprimerExemplaire(exemplaire);

                    

                    listerExemplaire(oeuvreFinale);

                }
            });

                panel.add(supp);

            }else{

                activer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controleur.activerExemplaire(exemplaire);

                        listerExemplaire(oeuvreFinale);

                    }
                });

                panel.add(activer);
            }





            panelGeneral.liste.add(panel);

        }
    }

}
