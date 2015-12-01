package modele;

import java.awt.Point;

public class Perchiste extends ZombieDecore {

    public Perchiste(ZombieDeBase z) {
        zdb = z;
        possedeSonAccessoire = true;
        environnement = zdb.getEnvironnement();
        numero = zdb.numero;
    }

    public void evolue() {

        //A FAIRE : Perchiste qui ne va pas vers l'avant.
        int plusPetiteDistance = this.distance(this.obstaclePlusProche());
        Point avancer = new Point(this.getPosition().x + this.getLongueurPas(), this.getPosition().y);

        if (this.possedeSonAccessoire()) {
            if (plusPetiteDistance == -1) {
                if (this.getPosition().x != 0) {
                    if (this.getPosition().x + this.getLongueurPas() < 0) {
                        this.setPosition(new Point(0, this.getPosition().y));
                    } else {
                        this.setPosition(avancer);
                    }
                }
            } else {
                if (plusPetiteDistance > Math.abs(this.getLongueurPas())) {
                    this.setPosition(avancer);
                } else {
                    if (!this.obstaclePlusProche().estMort()) {
                        sauteApres(this.obstaclePlusProche());
                        this.perdAccessoire();
                    } else {
                        this.setPosition(avancer);
                    }
                }
            }
        } else {
            this.zdb.evolue();
        }
    }

    private void sauteApres(Perso p) {
        if (this.vaVersAvant()) {
            if (p.getPosition().x - this.getLargeur() - 20 > 0) {
                this.setPosition(new Point(p.getPosition().x - this.getLargeur() - 20, this.getPosition().y));
            } else {
                this.setPosition(new Point(0, this.getPosition().y));
            }
        } else {
            this.setPosition(new Point(p.getPosition().x + this.getLargeur() + 20, this.getPosition().y));
        }
    }

    @Override
    public void recoitAttaque(Projectile pe) {
        this.zdb.recoitDegat(pe.getDegatCauses());
        pe.meurt();

    }

    @Override
    public void recoitDegat(int degat) {
    }

    @Override
    public int getPv() {
        return zdb.getPv();
    }
}
