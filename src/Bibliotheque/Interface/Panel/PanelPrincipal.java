package Bibliotheque.Interface.Panel;

import Bibliotheque.Controleur.Controleur;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelPrincipal extends JPanel {

    public JTabbedPane onglets;
    boolean paneSet = false;
    final Controleur controleur;


    public PanelPrincipal(){


        this.controleur = new Controleur();

        this.setPreferredSize(new Dimension(800,600));

        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.onglets = new JTabbedPane(SwingConstants.NORTH);

        this.onglets.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if(paneSet){

                    PanelUsager panelUsager = new PanelUsager(controleur);
                    PanelOeuvre panelOeuvre = new PanelOeuvre(controleur);
                    PanelReservation panelReservation = new PanelReservation(controleur);
                    PanelEmprunt panelEmprunt = new PanelEmprunt(controleur);

                    panelUsager.listerUsager();
                    panelUsager.ajouterUsager();
                    panelOeuvre.listerOeuvre();
                    panelOeuvre.ajouterOeuvre();
                    panelReservation.listerReservation();
                    panelEmprunt.listerEmprunt();

                    panelUsager.setName("Usagers");
                    panelOeuvre.setName("Oeuvres");
                    panelReservation.setName("Reservations");
                    panelEmprunt.setName("Emprunts");


                    onglets.setComponentAt(0, panelUsager);
                    onglets.setComponentAt(1, panelOeuvre);
                    onglets.setComponentAt(2, panelReservation);
                    onglets.setComponentAt(3, panelEmprunt);



                }
                else{
                    paneSet = true;
                }

            }
        });



    }


    public void creationComposants(){



        PanelUsager panelUsager = new PanelUsager(this.controleur);
        PanelOeuvre panelOeuvre = new PanelOeuvre(this.controleur);
        PanelReservation panelReservation = new PanelReservation(this.controleur);
        PanelEmprunt panelEmprunt = new PanelEmprunt(this.controleur);

        panelUsager.listerUsager();
        panelUsager.ajouterUsager();
        panelOeuvre.listerOeuvre();
        panelOeuvre.ajouterOeuvre();
        panelReservation.listerReservation();
        panelEmprunt.listerEmprunt();

        panelUsager.setName("Usagers");
        panelOeuvre.setName("Oeuvres");
        panelReservation.setName("Reservations");
        panelEmprunt.setName("Emprunts");

        this.onglets.add(panelUsager,0);
        this.onglets.add(panelOeuvre, 1);
        this.onglets.add(panelReservation, 2);
        this.onglets.add(panelEmprunt, 3);


        this.add(onglets);

    }


    public void maj(){
        onglets.removeAll();
        creationComposants();
    }





}
