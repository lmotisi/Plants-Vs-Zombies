package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;

public class VueJardin implements ObservateurArgent, ObservateurTour {

    private Jeu jeu;
    private JFrame fenetre = new JFrame("une partie");
    private DessinJardin dessin;
    private Menu menu;
    private JLabel tour;
    private JLabel argent;

    /** Creates a new instance of vueJeu */
    public VueJardin(Jeu j) {
        jeu = j;

        this.tour = new JLabel("Tour : " + Integer.toString(this.jeu.getNbTour()));
        this.argent = new JLabel("Argent : " + Integer.toString(this.jeu.getEnvironnement().getArgent()));
        JPanel p1 = new JPanel();
        p1.add(tour);
        p1.add(argent);
        this.jeu.ajouterObs(this);
        this.jeu.getEnvironnement().ajouterObs(this);
        this.dessin = new DessinJardin(this.jeu);
        this.menu = new Menu(this.jeu);
        JPanel p = new JPanel(new BorderLayout());
        p.add(menu, BorderLayout.NORTH);
        p.add(dessin, BorderLayout.CENTER);
        p.add(p1,BorderLayout.SOUTH);
        fenetre.setTitle("Jardin");
        fenetre.setSize(new Dimension(jeu.getLargeurJardin(), jeu.getHauteurJardin() + 90));
        fenetre.setResizable(false);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.add(p);
        fenetre.setBackground(Color.WHITE);
        fenetre.setVisible(true);

        go1();
    }

    public void go1() {
        while (!jeu.estFini()) {
            dessin.repaint(); // mise à jour du dessin
            this.jeu.unTour();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (jeu.perdu()) {
            System.out.println("Perdu: ");
        } else {
            System.out.println("Gagne: ");
        }

    }

    public void augmenterTour() {
        this.tour.setText("Tour : " + Integer.toString(this.jeu.getNbTour()));
    }

    public void ramasserArgent() {
        this.argent.setText("Argent : " + Integer.toString(this.jeu.getEnvironnement().getArgent()));
    }
}
