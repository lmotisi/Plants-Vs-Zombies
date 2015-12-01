package modele;

import java.awt.Point;

public abstract class Zombie extends PersoMobile {
    // numero permet d'identifier les zombies : les zombies de base auront tous
    // un numero different
    // les autres auront le meme numero que leur zombie de base.

    protected int numero;

    public int getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Zombie && numero == ((Zombie) obj).numero) {
            return true;
        } else {
            return false;
        }
    }

    protected void mange(Perso p) {
        p.recoitDegat(this.getDegatCauses());
    }

    /*
     * les zombies reagissent differement aux attaques de perso : certaines
     * armures par exemple ne protègent pas de certains projectiles On laisse au
     * Zombie qui recoit l'attaque se debrouiller pour savoir les degats qu'il
     * doit s'infliger en fonction du perso qui lui inflige.
     */
    public abstract void recoitAttaque(Projectile p);

    /*
     * definit pour qui un zombie constitue un obstacle
     */
    @Override
    public boolean estObstaclePour(Perso pe) {
        boolean retour = false;
        if (pe instanceof Zombie && !this.equals(pe)) {
            if (!this.vaVersAvant() && ((Zombie) pe).vaVersAvant() && this.getPosition().x < pe.getPosition().x) {
                return true;
            }
            if (this.vaVersAvant() && !((Zombie) pe).vaVersAvant() && this.getPosition().x > pe.getPosition().x) {
                return true;
            }
        }
        if (pe instanceof Projectile && this.vaVersAvant()) {
            retour = true;
        }
        if (pe instanceof PistoPois && this.vaVersAvant()) {
            retour = true;
        }

        if (pe instanceof Hypnotiseur && this.vaVersAvant()) {
            retour = true;
        }
        
        if(pe instanceof Bombe && this.vaVersAvant()) {
            retour = true;
        }
        return retour;


    }

    public abstract int getPv();
}
