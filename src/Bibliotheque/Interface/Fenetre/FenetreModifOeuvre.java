package Bibliotheque.Interface.Fenetre;

import Bibliotheque.Controleur.Controleur;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Entites.Oeuvre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gael on 07/11/14.
 */
public class FenetreModifOeuvre extends JFrame {


    JPanel panelGeneral;
    Controleur controleur;

    public FenetreModifOeuvre(Controleur c){

        this.controleur = c;
        panelGeneral = new JPanel();

        this.setPreferredSize(new Dimension(600,250));

        panelGeneral.setPreferredSize(new Dimension(600,250));



        this.getContentPane().add(panelGeneral, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }




    public void afficherInfosOeuvre(Oeuvre oeuvre){

        this.panelGeneral.setLayout(new BoxLayout(this.panelGeneral, BoxLayout.PAGE_AXIS));


        JPanel titre = new JPanel();
        titre.setPreferredSize(new Dimension(500,30));
        titre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel titreOeuvre = new JPanel();
        titreOeuvre.setPreferredSize(new Dimension(500,30));
        titreOeuvre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel auteurOeuvre = new JPanel();
        auteurOeuvre.setPreferredSize(new Dimension(500,30));
        auteurOeuvre.setLayout(new FlowLayout(FlowLayout.LEFT));


        JLabel labelTitre = new JLabel("Modification d'une oeuvre");

        JLabel labelTitreOeuvre = new JLabel("Titre : ");
        labelTitreOeuvre.setPreferredSize(new Dimension(150,30));

        JLabel labelAuteur = new JLabel("Auteur : ");
        labelAuteur.setPreferredSize(new Dimension(150,30));

        final JTextField fieldTitreOeuvre = new JTextField();
        final JTextField fieldAuteurOeuvre = new JTextField();


        fieldTitreOeuvre.setText(oeuvre.getTitre());
        fieldAuteurOeuvre.setText(oeuvre.getAuteur());



        fieldTitreOeuvre.setPreferredSize(new Dimension(200, 20));
        fieldAuteurOeuvre.setPreferredSize(new Dimension(200, 20));



        titre.add(labelTitre);
        titreOeuvre.add(labelTitreOeuvre);
        titreOeuvre.add(fieldTitreOeuvre);
        auteurOeuvre.add(labelAuteur);
        auteurOeuvre.add(fieldAuteurOeuvre);


        this.panelGeneral.add(titre);
        this.panelGeneral.add(titreOeuvre);
        this.panelGeneral.add(auteurOeuvre);


        final Oeuvre oeuvreFinale = oeuvre;

        JButton modifier = new JButton("Modifier");



        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldTitreOeuvre.getText().length() > 0 && fieldAuteurOeuvre.getText().length() > 0){



                    oeuvreFinale.setTitre(fieldTitreOeuvre.getText());
                    oeuvreFinale.setAuteur(fieldAuteurOeuvre.getText());


                    controleur.modifierOeuvre(oeuvreFinale);

                    JOptionPane.showMessageDialog(null, "Oeuvre modifiée");
                    dispose();

                }else{
                    JOptionPane.showMessageDialog(null, "Les champs ne sont pas remplis correctement !");
                }
            }
        });

        this.panelGeneral.add(modifier);

    }

}
