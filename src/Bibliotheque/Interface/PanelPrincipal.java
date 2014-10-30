package Bibliotheque.Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelPrincipal extends JPanel {

    public JTabbedPane onglets;


    public PanelPrincipal(){


        this.setPreferredSize(new Dimension(800,600));

        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.onglets = new JTabbedPane(SwingConstants.NORTH);



    }


    public void creationComposants(){


        PanelUsager panelUsager = new PanelUsager();
        PanelOeuvre panelOeuvre = new PanelOeuvre();
        PanelReservation panelReservation = new PanelReservation();
       // PanelExemplaire panelExemplaire = new PanelExemplaire();
        PanelEmprunt panelEmprunt = new PanelEmprunt();

        panelUsager.ajoutListe();
        panelOeuvre.ajoutListe();
        panelReservation.ajoutListe();
        //panelExemplaire.ajoutListe();
        panelEmprunt.ajoutListe();

        panelUsager.ajoutAjout();
        panelOeuvre.ajoutAjout();
        panelReservation.ajoutAjout();
        //panelExemplaire.ajoutAjout();
        panelEmprunt.ajoutAjout();

        panelUsager.listerUsager();
        panelUsager.ajouterUsager();
        panelOeuvre.listerOeuvre();
        panelOeuvre.ajouterOeuvre();
        panelReservation.listerReservation();

        this.onglets.addTab("Usagers", panelUsager);
        this.onglets.addTab("Oeuvres", panelOeuvre);
        this.onglets.addTab("Reservations", panelReservation);
        //this.onglets.addTab("Exemplaires", panelExemplaire);
        this.onglets.addTab("Emprunts", panelEmprunt);


        this.add(onglets);

    }

}
