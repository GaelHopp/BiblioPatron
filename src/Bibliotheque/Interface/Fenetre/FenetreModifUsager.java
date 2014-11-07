package Bibliotheque.Interface.Fenetre;

import Bibliotheque.Controleur.Controleur;
import Bibliotheque.Exception.UsagerExistantException;
import Bibliotheque.Interface.Panel.PanelGeneral;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gael on 07/11/14.
 */
public class FenetreModifUsager extends JFrame{

    JPanel panelGeneral;
    Controleur controleur;

    public FenetreModifUsager(Controleur c){

        this.controleur = c;
        panelGeneral = new JPanel();

        this.setPreferredSize(new Dimension(600,250));

        panelGeneral.setPreferredSize(new Dimension(600,250));



        this.getContentPane().add(panelGeneral, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }




    public void afficherInfosUsager(Usager usager){

        this.panelGeneral.setLayout(new BoxLayout(this.panelGeneral, BoxLayout.PAGE_AXIS));


        JPanel titre = new JPanel();
        titre.setPreferredSize(new Dimension(500,30));
        titre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel nom = new JPanel();
        nom.setPreferredSize(new Dimension(500,30));
        nom.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel prenom = new JPanel();
        prenom.setPreferredSize(new Dimension(500,30));
        prenom.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel age = new JPanel();
        age.setPreferredSize(new Dimension(500,30));
        age.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel adresse = new JPanel();
        adresse.setPreferredSize(new Dimension(500,30));
        adresse.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel labelTitre = new JLabel("Modification d'un utilisateur");
        labelTitre.setPreferredSize(new Dimension(500,30));
        JLabel labelNom = new JLabel("Nom : ");
        labelNom.setPreferredSize(new Dimension(150,30));
        JLabel labelPrenom = new JLabel("Prénom : ");
        labelPrenom.setPreferredSize(new Dimension(150,30));
        JLabel labelAge = new JLabel("Age : ");
        labelAge.setPreferredSize(new Dimension(150,30));
        JLabel labelAdresse = new JLabel("Adresse : ");
        labelAdresse.setPreferredSize(new Dimension(150,30));

        final JTextField fieldNom = new JTextField();
        final JTextField fieldPrenom = new JTextField();
        final JTextField fieldAge = new JTextField();
        final JTextField fieldAdresse = new JTextField();

        fieldNom.setText(usager.getNom());
        fieldPrenom.setText(usager.getPrenom());
        fieldAge.setText(""+usager.getAge());
        fieldAdresse.setText(usager.getAdresse());


        fieldNom.setPreferredSize(new Dimension(200, 20));
        fieldPrenom.setPreferredSize(new Dimension(200,20));
        fieldAge.setPreferredSize(new Dimension(50,20));
        fieldAdresse.setPreferredSize(new Dimension(200,20));


        titre.add(labelTitre);
        nom.add(labelNom);
        nom.add(fieldNom);
        prenom.add(labelPrenom);
        prenom.add(fieldPrenom);
        age.add(labelAge);
        age.add(fieldAge);
        adresse.add(labelAdresse);
        adresse.add(fieldAdresse);

        this.panelGeneral.add(titre);
        this.panelGeneral.add(nom);
        this.panelGeneral.add(prenom);
        this.panelGeneral.add(age);
        this.panelGeneral.add(adresse);

        final Usager usagerFinal = usager;

        JButton modifier = new JButton("Modifier");



        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldNom.getText().length() > 0 && fieldPrenom.getText().length() > 0 && fieldAge.getText().length() > 0 && fieldAge.getText().length() < 4 && fieldAdresse.getText().length() > 0){

                    try{

                        usagerFinal.setNom(fieldNom.getText());
                        usagerFinal.setPrenom(fieldPrenom.getText());
                        usagerFinal.setAge(Integer.parseInt(fieldAge.getText()));
                        usagerFinal.setAdresse(fieldAdresse.getText());


                        controleur.modifierUsager(usagerFinal);

                        JOptionPane.showMessageDialog(null, "Usager modifié");

                        fieldNom.setText("");
                        fieldPrenom.setText("");
                        fieldAge.setText("");
                        fieldAdresse.setText("");
                        dispose();
                    }catch(UsagerExistantException uee){
                        JOptionPane.showMessageDialog(null, "L'usager existe déjà");
                    }catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "L'âge saisi n'est pas correct");
                    }


                }else{
                    JOptionPane.showMessageDialog(null, "Les champs ne sont pas remplis correctement !");
                }
            }
        });

        this.panelGeneral.add(modifier);

    }

}
