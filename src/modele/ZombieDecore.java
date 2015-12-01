package modele;

import java.awt.Point;

public abstract class ZombieDecore extends Zombie {

    protected Zombie zdb;
    protected boolean possedeSonAccessoire;

    public ZombieDecore() {
        super();
    }

    @Override
    public int getDegatCauses() {
        return zdb.getDegatCauses();
    }

    @Override
    public void ralentir() {
        zdb.ralentir();

    }

    @Override
    public void changeDirection() {
        zdb.changeDirection();

    }

    @Override
    public boolean vaVersAvant() {
        return zdb.vaVersAvant();
    }

    @Override
    public int getLargeur() {
        return zdb.getLargeur();
    }

    @Override
    public Point getPosition() {
        return zdb.getPosition();
    }

    @Override
    public boolean estMort() {
        return zdb.estMort();
    }

    @Override
    public void meurt() {
        zdb.meurt();
    }

    @Override
    public void setPosition(Point nouveau) {
        zdb.setPosition(nouveau);
    }

    public void perdAccessoire() {
        this.possedeSonAccessoire = false;
    }

    public boolean possedeSonAccessoire() {
        return this.possedeSonAccessoire;
    }

    public int getLongueurPas() {
        return zdb.getLongueurPas();
    }
}
