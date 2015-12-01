/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author lmotisi
 */
public class Bombe extends Plante {

    private int compteurExplo;

    public Bombe(int x, int y, Environnement e) {
        super(x, y, 10, e);
        this.compteurExplo = 5;
    }

    @Override
    public void evolue() {
        this.compteurExplo--;
        if (this.compteurExplo == 0) {
            int i;
            this.exploBombe();
            this.meurt();
        }

    }

    public void exploBombe() {
        int numLigne = this.getPosition().y;
        ArrayList<Perso> ligne = this.environnement.getLignePersos(numLigne);
        if (numLigne > 0) {
            ArrayList<Perso> ligne2 = this.environnement.getLignePersos(numLigne - 1);
            for (int i = 0; i < ligne2.size(); i++) {
                if (ligne2.get(i).estObstaclePour(this) && (ligne2.get(i).getPosition().x) >= ((this.getPosition().x) - 100) && (ligne2.get(i).getPosition().x) <= ((this.getPosition().x) + 100)) {
                    ligne2.get(i).meurt();
                }
            }
        }
        if (numLigne < this.getEnvironnement().getNbreCaseHauteurJardin()-1) {
            ArrayList<Perso> ligne3 = this.environnement.getLignePersos(numLigne+1);
            for (int i = 0; i < ligne3.size(); i++) {
                if (ligne3.get(i).estObstaclePour(this) && (ligne3.get(i).getPosition().x) >= ((this.getPosition().x) - 100) && (ligne3.get(i).getPosition().x) <= ((this.getPosition().x) + 100)) {

                    ligne3.get(i).meurt();
                }
            }

        }
        for (int i = 0; i < ligne.size(); i++) {
            if (ligne.get(i).estObstaclePour(this) && (ligne.get(i).getPosition().x) >= ((this.getPosition().x) - 100) && (ligne.get(i).getPosition().x) <= ((this.getPosition().x) + 100)) {

                ligne.get(i).meurt();

            }
        }
    }
}

