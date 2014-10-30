package Bibliotheque.Interface;

import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelUsager extends PanelGeneral {

    public PanelUsager(){
        super();
    }

   public void listerUsager(){
       ArrayList<Usager> listeUsager = Usager.listerUsagers();



       this.liste.setLayout(new BoxLayout(this.liste, BoxLayout.PAGE_AXIS));

       for(Usager usager : listeUsager){
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

           JButton reservation = new JButton("Faire une réservation");
           panel.add(reservation);

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

        JTextField fieldNom = new JTextField();
        JTextField fieldPrenom = new JTextField();
        JTextField fieldAge = new JTextField();
        JTextField fieldAdresse = new JTextField();

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
        this.ajout.add(ajouter);

    }
}
