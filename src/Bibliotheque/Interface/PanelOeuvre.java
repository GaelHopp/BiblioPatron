package Bibliotheque.Interface;

import Bibliotheque.Modele.Entites.Oeuvre;
import Bibliotheque.Modele.Personne.Usager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelOeuvre extends PanelGeneral {

    public PanelOeuvre(){
        super();
    }

    public void listerOeuvre(){
        ArrayList<Oeuvre> listeOeuvre = new ArrayList<Oeuvre>();

        listeOeuvre.add(new Oeuvre("Harry Potter 1", "JK Rowling"));
        listeOeuvre.add(new Oeuvre("Harry Potter 2", "JK Rowling"));
        listeOeuvre.add(new Oeuvre("Harry Potter 3", "JK Rowling"));
        listeOeuvre.add(new Oeuvre("Harry Potter 4", "JK Rowling"));
        listeOeuvre.add(new Oeuvre("Harry Potter 5", "JK Rowling"));
        listeOeuvre.add(new Oeuvre("Harry Potter 6", "JK Rowling"));
        listeOeuvre.add(new Oeuvre("OeuvreRandom1", "AuteurRandom"));
        listeOeuvre.add(new Oeuvre("OeuvreRandom2", "AuteurRandom"));
        listeOeuvre.add(new Oeuvre("OeuvreRandom3", "AuteurRandom"));
        listeOeuvre.add(new Oeuvre("OeuvreRandom4", "AuteurRandom"));
        listeOeuvre.add(new Oeuvre("OeuvreRandom5", "AuteurRandom"));
        listeOeuvre.add(new Oeuvre("OeuvreRandom6", "AuteurRandom"));


        this.liste.setLayout(new BoxLayout(this.liste, BoxLayout.PAGE_AXIS));

        for(Oeuvre oeuvre : listeOeuvre){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setPreferredSize(new Dimension(500,30));
            JLabel labelID = new JLabel(oeuvre.getIdOeuvre()+"");
            labelID.setPreferredSize(new Dimension(30,20));

            JLabel labelTitre = new JLabel(oeuvre.getTitre());
            labelTitre.setPreferredSize(new Dimension(300,20));

            JLabel labelAuteur = new JLabel(oeuvre.getAuteur());
            labelAuteur.setPreferredSize(new Dimension(200,20));

            panel.add(labelID);
            panel.add(labelTitre);
            panel.add(labelAuteur);

            JButton reservation = new JButton("Faire une réservation");
            panel.add(reservation);


            this.liste.add(panel);

        }
    }

    public void ajouterOeuvre(){

        this.ajout.setLayout(new BoxLayout(this.ajout , BoxLayout.PAGE_AXIS));


        JPanel titre = new JPanel();
        titre.setPreferredSize(new Dimension(500,30));
        titre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel titreOeuvre = new JPanel();
        titreOeuvre.setPreferredSize(new Dimension(500,30));
        titreOeuvre.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel auteur = new JPanel();
        auteur.setPreferredSize(new Dimension(500,30));
        auteur.setLayout(new FlowLayout(FlowLayout.LEFT));


        JLabel labelTitre = new JLabel("Ajout d'une oeuvre");
        labelTitre.setPreferredSize(new Dimension(500,30));
        JLabel labelTitreOeuvre = new JLabel("Titre : ");
        labelTitreOeuvre.setPreferredSize(new Dimension(150,30));
        JLabel labelAuteur = new JLabel("Auteur : ");
        labelAuteur.setPreferredSize(new Dimension(150,30));


        JTextField fieldTitre = new JTextField();
        JTextField fieldAuteur = new JTextField();


        fieldTitre.setPreferredSize(new Dimension(200,20));
        fieldAuteur.setPreferredSize(new Dimension(200,20));



        titre.add(labelTitre);
        titreOeuvre.add(labelTitreOeuvre);
        titreOeuvre.add(fieldTitre);
        auteur.add(labelAuteur);
        auteur.add(fieldAuteur);


        this.ajout.add(titre);
        this.ajout.add(titreOeuvre);
        this.ajout.add(auteur);


        JButton ajouter = new JButton("Ajouter");
        this.ajout.add(ajouter);

    }
}
