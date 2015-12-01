package modele;

import java.awt.Point;

public abstract class Plante extends Perso {

    protected int ligne;
    protected int col;
    protected int pv;

    public Plante(int x, int y, int pvie, Environnement e) {
        ligne = y;
        col = x;
        pv = pvie;
        environnement = e;
    }

    @Override
    public void recoitDegat(int degat) {
        if (this.pv - degat <= 0) {
            this.meurt();
        } else {
            this.pv -= degat;
        }
    }

    @Override
    public boolean estObstaclePour(Perso p) {
        if (p instanceof Zombie && ((Zombie) p).vaVersAvant() && ((Zombie) p).getPosition().x
                > this.getPosition().x) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getLargeur() {
        // les plantes occupent toute la case
        return 99;


    }

    public int getPv() {
        return pv;


    }

    @Override
    public Point getPosition() {
        return new Point(col, ligne);


    }

    @Override
    public boolean estMort() {
        if (this.pv == 0) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    public void meurt() {
        this.pv = 0;


    }

    @Override
    public int getDegatCauses() {
        // par defaut, une plante ne cause aucun degat.
        return 0;

    }
}
