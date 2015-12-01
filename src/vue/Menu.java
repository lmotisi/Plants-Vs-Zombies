/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modele.Jeu;

/**
 *
 * @author lmotisi
 */
public class Menu extends JPanel implements ObservateurDispoPlantes {
    
    private JButton noix;
    private JButton pistoPois;
    private JButton tournesol;
    private JButton hypnotiseur;
    private JButton bombe;
    private Jeu jeu;

    public Menu(Jeu jeu) {
        this.jeu = jeu;
        this.tournesol = new JButton("Tournesol");
        this.noix = new JButton("Noix");
        this.pistoPois = new JButton("Pisto Pois");
        this.hypnotiseur = new JButton("Hypnotiseur");
        this.bombe = new JButton("Bombe");
        this.jeu.getEnvironnement().ajouterObsPlante(this);


        this.tournesol.addActionListener(new EcouteurBoutonTournesol());
        this.noix.addActionListener(new EcouteurBoutonNoix());
        this.pistoPois.addActionListener(new EcouteurBoutonPistoPois());
        this.hypnotiseur.addActionListener(new EcouteurBoutonHypno());
        this.bombe.addActionListener(new EcouteurBoutonBombe());
        this.add(noix);
        this.add(pistoPois);
        this.add(tournesol);
        this.add(hypnotiseur);
        this.add(bombe);

    }

    private class EcouteurBoutonTournesol implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Menu.this.jeu.getEnvironnement().setChoixPlante(1);
        }
    }

    private class EcouteurBoutonNoix implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Menu.this.jeu.getEnvironnement().setChoixPlante(2);
        }
    }

    private class EcouteurBoutonPistoPois implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Menu.this.jeu.getEnvironnement().setChoixPlante(3);
        }
    }

    private class EcouteurBoutonHypno implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Menu.this.jeu.getEnvironnement().setChoixPlante(4);
        }
    }

     private class EcouteurBoutonBombe implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            Menu.this.jeu.getEnvironnement().setChoixPlante(5);
        }
    }

   
    public void compterToursPlantes() {
        if (this.jeu.getEnvironnement().getTourNoix() != 0) {
            this.noix.setText("Noix (" + this.jeu.getEnvironnement().getTourNoix() + ")");
        } else {
            this.noix.setText("Noix");
        }
        if (this.jeu.getEnvironnement().getTourTournesol() != 0) {
            this.tournesol.setText("Tournesol (" + this.jeu.getEnvironnement().getTourTournesol() + ")");
        } else {
            this.tournesol.setText("Tournesol");
        }
        if (this.jeu.getEnvironnement().getTourPistoPois() != 0) {
            this.pistoPois.setText("Pisto Pois (" + this.jeu.getEnvironnement().getTourPistoPois() + ")");
        } else {
            this.pistoPois.setText("Pisto Pois");
        }
        if(this.jeu.getEnvironnement().getTourHypnotiseur() !=0) {
            this.hypnotiseur.setText("Hypnotiseur ("+ this.jeu.getEnvironnement().getTourHypnotiseur() + ")");
        } else {
            this.hypnotiseur.setText("Hypnotiseur");
        }
        
        if(this.jeu.getEnvironnement().getTourBombe() !=0) {
            this.bombe.setText("Bombe ("+ this.jeu.getEnvironnement().getTourBombe() + ")");
        } else {
            this.bombe.setText("Bombe");
        }

    }
}
