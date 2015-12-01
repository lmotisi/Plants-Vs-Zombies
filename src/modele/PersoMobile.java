package modele;

import java.awt.Point;

public abstract class PersoMobile extends Perso {

    /*
     * permet de connaitre la direction du deplacement : true ssi va vers
     * l'avant (gauche)
     */
    public abstract boolean vaVersAvant();

    /*
     * fait ralentir
     */
    public abstract void ralentir();

    /*
     * fait aller le perso en sens inverse
     */
    public abstract void changeDirection();

    /*
     * affecte au perso la position nouvelle
     */
    public abstract void setPosition(Point nouvelle);

    /*
     * repond true ssi le perso et z vont dans des directions inverses l'une de
     * l'autre
     */
    public boolean sensInverse(PersoMobile z) {
        if (this.vaVersAvant() != z.vaVersAvant()) {
            return true;
        } else {
            return false;
        }
    }

    public abstract int getLongueurPas();
    // il faudra sans doute ajouter des méthodes pour détecter les persos
    // qui se trouvent sur la route du perso mobile
}
