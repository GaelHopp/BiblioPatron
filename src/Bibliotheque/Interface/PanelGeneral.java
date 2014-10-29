package Bibliotheque.Interface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gael on 29/10/14.
 */
public class PanelGeneral extends JPanel {

    JPanel liste, ajout;

    public PanelGeneral(){

        this.setPreferredSize(new Dimension(800,700));

        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);



    }


    public void ajoutListe(){


        this.liste = new JPanel();

        JScrollPane scroll = new JScrollPane(this.liste, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(800, 300));

        this.add(scroll, BorderLayout.NORTH);
    }

    public void ajoutAjout(){
        this.ajout = new JPanel();
        this.ajout.setPreferredSize(new Dimension(800, 250));


        this.add(this.ajout, BorderLayout.SOUTH);

    }
}
