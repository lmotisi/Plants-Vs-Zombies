package modele;

import java.awt.Point;
import java.util.ArrayList;

/*
 * Un pistoPois se declenche lorsqu'un zombie est 
 * sous sa portée (toute la ligne). Il tire des projectiles tous les 5
 * temps
 */
public class PistoPois extends Plante {

    private int compteurTemps;

    public PistoPois(int x, int y, Environnement e) {
        super(x, y, 30, e);
        this.compteurTemps = 0;
    }

    @Override
    public void evolue() {
        if (this.obstaclePlusProche() != null && this.compteurTemps % 5 == 0) {
            this.tire();
        }
        this.compteurTemps++;
    }


    /*
     * crée un projectile et l'ajoute à l'environnement
     */
    private void tire() {
        Projectile p = new Projectile((new Point(this.col + this.getLargeur() / 2, this.ligne)), this.environnement);
        this.environnement.ajoute(p);
    }
}
