/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Mizuki
 */
public class Hypnotiseur extends Plante {

    private int tourAttaque;
    private boolean hypnotise;

    public Hypnotiseur(int x, int y, Environnement e) {
        super(x, y, 20, e);
        this.tourAttaque = 0;
        this.hypnotise = true;
    }

    @Override
    public void evolue() {
        if (this.hypnotise) {
            if (this.obstaclePlusProche() != null && this.distance(this.obstaclePlusProche()) == 0) {
                ((Zombie)this.obstaclePlusProche()).changeDirection();
                this.hypnotise=false;
            }
        }
    }
}
