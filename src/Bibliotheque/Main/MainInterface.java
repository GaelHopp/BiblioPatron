package Bibliotheque.Main;

import Bibliotheque.Interface.PanelPrincipal;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gael on 29/10/14.
 */
public class MainInterface {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bibliotheque");

        PanelPrincipal panel = new PanelPrincipal();
        panel.creationComposants();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
