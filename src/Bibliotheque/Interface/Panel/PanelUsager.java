package Bibliotheque.Interface.Panel;

import Bibliotheque.Controleur.Controleur;
import Bibliotheque.Exception.UsagerExistantException;
import Bibliotheque.Interface.Fenetre.FenetreModifUsager;
import Bibliotheque.Interface.Fenetre.FenetreOeuvres;
import Bibliotheque.Interface.Fenetre.FenetreUsagers;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelUsager extends PanelGeneral {

    public PanelUsager(Controleur controleur){
        super(controleur);
    }

   public void listerUsager(){
       ArrayList<Usager> listeUsager = Usager.listerUsagers();



       this.liste.setLayout(new BoxLayout(this.liste, BoxLayout.PAGE_AXIS));

       for(Usager usager : listeUsager){
           JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

           panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
           panel.setPreferredSize(new Dimension(1000,30));
           JLabel labelID = new JLabel(usager.getIdPersonne()+"");
           labelID.setPreferredSize(new Dimension(30,20));

            JLabel labelNom = new JLabel(usager.getNom());
           labelNom.setPreferredSize(new Dimension(100,20));

           JLabel labelPrenom = new JLabel(usager.getPrenom()+"");
           labelPrenom.setPreferredSize(new Dimension(100,20));

           JLabel labelAge = new JLabel(usager.getAge()+"");
           labelAge.setPreferredSize(new Dimension(30,20));

           JLabel labelStatut = new JLabel(usager.getStatut()+"");
           labelStatut.setPreferredSize(new Dimension(20,20));


           panel.add(labelID);
           panel.add(labelNom);
           panel.add(labelPrenom);
           panel.add(labelAge);
           panel.add(labelStatut);

           final Usager usagerFinal = usager;


           JButton modifier = new JButton("Modifier");

           modifier.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   FenetreModifUsager fenetreModifUsager = new FenetreModifUsager(controleur);
                   fenetreModifUsager.afficherInfosUsager(usagerFinal);

               }
           });

           panel.add(modifier);

           if(usager.getStatut() == 1){

           JButton reservationEmprunt = new JButton("Réserver / Emprunter");



           reservationEmprunt.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {

                   FenetreOeuvres fenetre = new FenetreOeuvres(controleur);


                   fenetre.listerOeuvre(usagerFinal);
               }
           });



           panel.add(reservationEmprunt);


           JButton delete = new JButton("-");

           delete.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   controleur.supprimerUsager(usagerFinal);
                   liste.removeAll();
                   listerUsager();
               }
           });

               panel.add(delete);

           }
           else{
               JButton activer = new JButton("+");

               activer.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       controleur.activerUsager(usagerFinal);
                       liste.removeAll();
                       listerUsager();
                   }
               });

               panel.add(activer);

           }




           this.liste.add(panel);

       }
   }

    public void ajouterUsager(){

        this.ajout.setLayout(new BoxLayout(this.ajout , BoxLayout.PAGE_AXIS));


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

        JLabel labelTitre = new JLabel("Ajout d'un utilisateur");
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

        fieldNom.setPreferredSize(new Dimension(200,20));
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

        this.ajout.add(titre);
        this.ajout.add(nom);
        this.ajout.add(prenom);
        this.ajout.add(age);
        this.ajout.add(adresse);

        JButton ajouter = new JButton("Ajouter");



        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fieldNom.getText().length() > 0 && fieldPrenom.getText().length() > 0 && fieldAge.getText().length() > 0 && fieldAge.getText().length() < 4 && fieldAdresse.getText().length() > 0){

                    try{

                        controleur.ajouterUsager(fieldNom.getText(), fieldPrenom.getText(), Integer.parseInt(fieldAge.getText()), fieldAdresse.getText());

                        JOptionPane.showMessageDialog(null, "Usager inséré");
                        liste.removeAll();
                        listerUsager();
                        fieldNom.setText("");
                        fieldPrenom.setText("");
                        fieldAge.setText("");
                        fieldAdresse.setText("");
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

        this.ajout.add(ajouter);

    }


}
