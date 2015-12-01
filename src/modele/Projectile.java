package modele;

import java.awt.Point;
import java.util.ArrayList;

public class Projectile extends PersoMobile {
    // pleins d'attributs à inventer et un constructeur

    private int deplacement;
    private Point position;
    private int largeur;
    private int degatsCauses;
    private boolean estMort;

    public Projectile(Point origine, Environnement e) {
        deplacement = 10;
        position = origine;
        largeur = 10;
        degatsCauses = 5;
        environnement = e;
        estMort = false;
    }

    @Override
    public void evolue() {
        int plusPetiteDistance = this.distance(this.obstaclePlusProche());
        Point avancer = new Point(this.position.x + this.deplacement, this.position.y);
        if (plusPetiteDistance == -1) {
            this.setPosition(avancer);
        } else {
            if (plusPetiteDistance > this.deplacement) {
                this.setPosition(avancer);
            } else {
                if (this.vaVersAvant()) {
                    this.setPosition(new Point(this.position.x + plusPetiteDistance, this.position.y));
                    ((Zombie) this.obstaclePlusProche()).recoitAttaque(this);
                } else {
                    this.setPosition(new Point(this.position.x - plusPetiteDistance, this.position.y));
                    ((Zombie) this.obstaclePlusProche()).recoitAttaque(this);
                }
            }
        }
    }

    @Override
    public void recoitDegat(int degat) {
    }

    @Override
    public int getLargeur() {
        return this.largeur;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public boolean estMort() {
        return estMort;
    }

    @Override
    public void meurt() {
        estMort = true;
    }

    @Override
    public int getDegatCauses() {
        return this.degatsCauses;
    }

    @Override
    public boolean estObstaclePour(Perso p) {
        // To DO
        return false;
    }

    @Override
    public void ralentir() {
        // TODO Auto-generated method stub
    }

    @Override
    public void changeDirection() {
        this.deplacement -= deplacement;
    }

    @Override
    public void setPosition(Point nouveau) {
        this.position = nouveau;
    }

    @Override
    public boolean vaVersAvant() {
        if (this.deplacement == 10) {
            return false;
        } else {
            return true;
        }
    }

    public int getLongueurPas() {
        return deplacement;
    }
}
