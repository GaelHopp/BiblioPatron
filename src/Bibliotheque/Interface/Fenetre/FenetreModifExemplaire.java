package Bibliotheque.Interface.Fenetre;

import Bibliotheque.Controleur.Controleur;
import Bibliotheque.Exception.UsagerExistantException;
import Bibliotheque.Modele.Entites.Exemplaire;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gael on 07/11/14.
 */
public class FenetreModifExemplaire extends JFrame {

    JPanel panelGeneral;
    Controleur controleur;

    public FenetreModifExemplaire(Controleur c){

        this.controleur = c;
        panelGeneral = new JPanel();

        this.setPreferredSize(new Dimension(600,250));

        panelGeneral.setPreferredSize(new Dimension(600,250));



        this.getContentPane().add(panelGeneral, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }




    public void afficherInfosExemplaire(Exemplaire exemplaire){

        this.panelGeneral.setLayout(new BoxLayout(this.panelGeneral, BoxLayout.PAGE_AXIS));


        JPanel titre = new JPanel();
        titre.setPreferredSize(new Dimension(500,30));
        titre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel etat = new JPanel();
        etat.setPreferredSize(new Dimension(500,30));
        etat.setLayout(new FlowLayout(FlowLayout.LEFT));


        JLabel labelTitre = new JLabel("Modification d'un Exemplaire");

        JLabel labelEtat = new JLabel("Etat : ");
        labelEtat.setPreferredSize(new Dimension(150,30));

        final JTextField fieldEtat = new JTextField();


        fieldEtat.setText(exemplaire.getEtat());



        fieldEtat.setPreferredSize(new Dimension(200, 20));



        titre.add(labelTitre);
        etat.add(labelEtat);
        etat.add(fieldEtat);


        this.panelGeneral.add(titre);
        this.panelGeneral.add(etat);


       final Exemplaire exemplaireFinal = exemplaire;

        JButton modifier = new JButton("Modifier");



        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldEtat.getText().length() > 0){



                       exemplaireFinal.setEtat(fieldEtat.getText());


                        controleur.modifierExemplaire(exemplaireFinal);

                        JOptionPane.showMessageDialog(null, "Exemplaire modifié");
                        dispose();

                }else{
                    JOptionPane.showMessageDialog(null, "Les champs ne sont pas remplis correctement !");
                }
            }
        });

        this.panelGeneral.add(modifier);

    }


}
