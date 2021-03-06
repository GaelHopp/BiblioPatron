package Bibliotheque.Interface.Panel;

import Bibliotheque.Controleur.Controleur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelGeneral extends JPanel {

    public JPanel liste, ajout;
    public Controleur controleur;

    public PanelGeneral(Controleur controleur){

        this.controleur = controleur;

        this.setPreferredSize(new Dimension(1000,700));

        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.liste = new JPanel();

        JScrollPane scroll = new JScrollPane(this.liste, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(1000, 300));

        this.add(scroll, BorderLayout.NORTH);

        this.ajout = new JPanel();
        this.ajout.setPreferredSize(new Dimension(1000, 250));


        this.add(this.ajout, BorderLayout.SOUTH);



    }




}
