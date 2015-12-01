package modele;

import java.awt.Point;
import java.util.ArrayList;

public class ZombieDeBase extends Zombie {

    private int longueurPasNormal;
    private int pv;
    private Point position;
    private int largeur;
    private int degatsCauses;
    private boolean estRalenti = false;
    public static int compteur = 0;

    public ZombieDeBase(Point origine, int depart, Environnement e) {
        longueurPasNormal = -10;
        position = origine;
        largeur = 20;
        pv = 50;
        degatsCauses = 2;
        environnement = e;
        numero = compteur;
        compteur++;
    }

    public void evolue() {

        int plusPetiteDistance = this.distance(this.obstaclePlusProche());
        Point avancer = new Point(this.position.x + this.longueurPasNormal, this.position.y);

        if (plusPetiteDistance == -1) {
            if (this.position.x != 0) {
                if (this.position.x + longueurPasNormal < 0) {
                    this.setPosition(new Point(0, this.position.y));
                } else {
                    this.setPosition(avancer);
                }
            }
        } else {
            if (plusPetiteDistance > Math.abs(this.longueurPasNormal)) {
                this.setPosition(avancer);
            } else {

                if (!this.obstaclePlusProche().estMort()) {
                    if (plusPetiteDistance > 0) {
                        if (this.vaVersAvant()) {
                            this.setPosition(new Point(this.position.x - plusPetiteDistance, this.position.y));
                        } else {
                            this.setPosition(new Point(this.position.x + plusPetiteDistance, this.position.y));
                        }
                    } else {
                        this.mange(this.obstaclePlusProche());


                    }
                } else {
                    this.setPosition(avancer);
                }
            }
        }
    }

    public int getDegatsCauses() {
        return degatsCauses;
    }

    // pour tests
    public void setDegatsCauses(int degatsCauses) {
        this.degatsCauses = degatsCauses;
    }

    @Override
    public void recoitAttaque(Projectile pe) {
        if (this.pv - pe.getDegatCauses() < 0) {
            this.meurt();
            pe.meurt();
        } else {
            this.pv -= pe.getDegatCauses();
            pe.meurt();
        }
    }

    @Override
    public void ralentir() {
        // To DO
    }

    @Override
    public void changeDirection() {
        this.longueurPasNormal = -this.longueurPasNormal;
    }

    @Override
    public int getLargeur() {
        return largeur;
    }

    @Override
    public Point getPosition() {
        return position;
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
    public boolean vaVersAvant() {
        if (this.longueurPasNormal == -10) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getDegatCauses() {
        // TO DO
        return degatsCauses;
    }

    @Override
    public void recoitDegat(int i) {
        if (this.pv - i < 0) {
            this.meurt();
        } else {
            this.pv -= i;
        }
    }

    public int getPv() {
        return pv;
    }

    @Override
    public void setPosition(Point nouveau) {
        this.position = nouveau;
    }

    public int getLongueurPas() {
        return longueurPasNormal;
    }
}
