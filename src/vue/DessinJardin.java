package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import javax.swing.JPanel;

import modele.*;

public class DessinJardin extends JPanel {

    private Jeu leJeu;

    public DessinJardin(Jeu j) {
        this.leJeu = j;
        setFocusable(true);
        requestFocus();
        // on ecoute les clics de souris (sur les soleils etc...)
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                testPressed(e.getX(), e.getY());
            }
        });
    }

    private void testPressed(int x, int y) {
        // pour l'instant on ne teste que si on a clique sur un soleil.
        // il y aura autre chose après
        testClicSoleil(x, y);
        testClicPlante(x, y);
    }

    public void testClicSoleil(int x, int y) {
        for (Soleil s : this.leJeu.getEnvironnement().getSoleils()) {
            if (x >= s.getPosition().x - s.getLargeur() && x <= s.getPosition().x + s.getLargeur()
                    && Math.floor(y / 100) == s.getPosition().y) {
                s.clique();
            }
        }
    }

    public void testClicPlante(int x, int y) {
        int choixPlante = this.leJeu.getEnvironnement().getChoixPlante();
        System.out.println(choixPlante);
        int prixTournesol = this.leJeu.getEnvironnement().prixTournesol;
        int prixNoix = this.leJeu.getEnvironnement().prixNoix;
        int prixPistoPois = this.leJeu.getEnvironnement().prixPistoPois;
        int prixHypnotiseur = this.leJeu.getEnvironnement().prixHypnotiseur;
        int prixBombe = this.leJeu.getEnvironnement().prixBombe;
        int tourTournesol = this.leJeu.getEnvironnement().getTourTournesol();
        int tourNoix = this.leJeu.getEnvironnement().getTourNoix();
        int tourPistoPois = this.leJeu.getEnvironnement().getTourPistoPois();
        int tourHypno = this.leJeu.getEnvironnement().getTourHypnotiseur();
        int tourBombe = this.leJeu.getEnvironnement().getTourBombe();

        if (choixPlante != 0) {
            if (choixPlante == 1) {
                if (this.leJeu.verifCasePlante(x, y).x != -1 && this.leJeu.getEnvironnement().getArgent() >= prixTournesol && tourTournesol == 0) {
                    Plante tournesol = new Tournesol(this.leJeu.verifCasePlante(x, y).x, this.leJeu.verifCasePlante(x, y).y, this.leJeu.getEnvironnement());
                    this.leJeu.getEnvironnement().ajoute(tournesol);
                    this.leJeu.getEnvironnement().setArgent(this.leJeu.getEnvironnement().getArgent() - prixTournesol);
                    this.leJeu.getEnvironnement().setChoixPlante(0);
                    this.leJeu.getEnvironnement().setTourTournesol(10);
                }

            } else if (choixPlante == 2) {
                if (this.leJeu.verifCasePlante(x, y).x != -1 && this.leJeu.getEnvironnement().getArgent() >= prixNoix && tourNoix == 0) {
                    Plante noix = new Noix(this.leJeu.verifCasePlante(x, y).x, this.leJeu.verifCasePlante(x, y).y, this.leJeu.getEnvironnement());
                    this.leJeu.getEnvironnement().ajoute(noix);
                    this.leJeu.getEnvironnement().setArgent(this.leJeu.getEnvironnement().getArgent() - prixNoix);
                    this.leJeu.getEnvironnement().setChoixPlante(0);
                    this.leJeu.getEnvironnement().setTourNoix(50);

                }
            } else if (choixPlante == 3) {
                if (this.leJeu.verifCasePlante(x, y).x != -1 && this.leJeu.getEnvironnement().getArgent() >= prixPistoPois && tourPistoPois == 0) {
                    Plante pisto = new PistoPois(this.leJeu.verifCasePlante(x, y).x, this.leJeu.verifCasePlante(x, y).y, this.leJeu.getEnvironnement());
                    this.leJeu.getEnvironnement().ajoute(pisto);
                    this.leJeu.getEnvironnement().setArgent(this.leJeu.getEnvironnement().getArgent() - prixPistoPois);
                    this.leJeu.getEnvironnement().setChoixPlante(0);
                    this.leJeu.getEnvironnement().setTourPistoPois(10);
                }
            } else if (choixPlante == 4) {
                if (this.leJeu.verifCasePlante(x, y).x != -1 && this.leJeu.getEnvironnement().getArgent() >= prixHypnotiseur && tourHypno == 0) {
                    Plante hypno = new Hypnotiseur(this.leJeu.verifCasePlante(x, y).x, this.leJeu.verifCasePlante(x, y).y, this.leJeu.getEnvironnement());
                    this.leJeu.getEnvironnement().ajoute(hypno);
                    this.leJeu.getEnvironnement().setArgent(this.leJeu.getEnvironnement().getArgent() - prixPistoPois);
                    this.leJeu.getEnvironnement().setChoixPlante(0);
                    this.leJeu.getEnvironnement().setTourHypnotiseur(70);
                }
            } else if (choixPlante == 5) {
                if (this.leJeu.verifCasePlante(x, y).x != -1 && this.leJeu.getEnvironnement().getArgent() >= prixHypnotiseur && tourHypno == 0) {
                    Plante bombe = new Bombe(this.leJeu.verifCasePlante(x, y).x, this.leJeu.verifCasePlante(x, y).y, this.leJeu.getEnvironnement());
                    this.leJeu.getEnvironnement().ajoute(bombe);
                    this.leJeu.getEnvironnement().setArgent(this.leJeu.getEnvironnement().getArgent() - prixBombe);
                    this.leJeu.getEnvironnement().setChoixPlante(0);
                    this.leJeu.getEnvironnement().setTourBombe(70);
                }
            }
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        Perso p;
        // couleur de fond pour le rectangle qui est affiché pour
        // ne pas avoir de trainées mais des objets qui bougent
        g.setColor(Color.WHITE);
        // on dessine le rectangle afin qu'il prenne toute la surface
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        // on dessine les persos du jeu
        for (int i = 0; i < this.leJeu.getEnvironnement().nbreLigne(); i++) {
            for (int j = 0; j < this.leJeu.getEnvironnement().getLignePersos(i).size(); j++) {
                p = leJeu.getEnvironnement().getLignePersos(i).get(j);
                if (p instanceof Plante) {
                    dessinerPlante((Plante) p, g);
                }
                if (p instanceof Zombie) {
                    dessinerZombie((Zombie) p, g);
                }
                if (p instanceof Projectile) {
                    dessinerProjectile((Projectile) p, g);
                }
                if (p instanceof Soleil) {
                    dessinerSoleil((Soleil) p, g);
                }
            }
        }
        for (Soleil s : this.leJeu.getEnvironnement().getSoleils()) {
            dessinerSoleil(s, g);
        }

    }

    private void dessinerSoleil(Soleil p, Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(p.getPosition().x, (p.getPosition().y * 100) + 50,
                p.getLargeur(), 20);
    }

    public void dessinerPlante(Plante p, Graphics g) {
        if (p instanceof PistoPois) {
            g.setColor(Color.blue);
        } else if (p instanceof Tournesol) {
            g.setColor(Color.orange);
        } else if (p instanceof Noix) {
            g.setColor(Color.pink);
        } else {
            g.setColor(Color.green);
        }


        g.fillOval(p.getPosition().x, (p.getPosition().y * 100), 100, 100);
    }

    public void dessinerZombie(Zombie z, Graphics g) {
        if (z instanceof ZombieDeBase) {
            if (((ZombieDeBase) z).vaVersAvant()) {
            }
            g.setColor(Color.black);
        } else if (z instanceof Perchiste) {
            g.setColor(Color.magenta);
        } else {
            g.setColor(Color.red);
        }
        g.fillRect(z.getPosition().x, (z.getPosition().y * 100) + 50,
                z.getLargeur(), 30);
    }

    public void dessinerProjectile(Projectile p, Graphics g) {
        g.setColor(Color.cyan);
        g.fillOval(p.getPosition().x, (p.getPosition().y * 100) + 50, 10, 10);
    }
}
