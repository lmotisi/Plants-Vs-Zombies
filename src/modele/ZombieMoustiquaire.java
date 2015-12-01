package modele;

public class ZombieMoustiquaire extends ZombieDecore {

    private int pvArmure;

    public ZombieMoustiquaire(Zombie z) {
        zdb = z;
        possedeSonAccessoire = true;
        environnement = zdb.environnement;
        numero = zdb.numero;
        pvArmure = 55;
    }

    @Override
    public void recoitAttaque(Projectile pe) {
        if (this.sensInverse(pe) && this.possedeSonAccessoire) {
            this.recoitDegat(pe.getDegatCauses());
            pe.meurt();
        } else {
            this.zdb.recoitDegat(pe.getDegatCauses());
            pe.meurt();
        }
    }

    @Override
    public void evolue() {
        this.zdb.evolue();
        //System.out.println("pv armure :"+this.pvArmure+"pv :"+this.zdb.getPv());
    }

    // pour test
    public int getPvArmure() {
        return pvArmure;
    }

    public Zombie getZdb() {
        return zdb;
    }

    @Override
    public void recoitDegat(int degat) {
        if (this.pvArmure > 0) {
            if (this.pvArmure - degat > 0) {
                this.pvArmure -= degat;
            } else {
                this.zdb.recoitDegat(degat - this.pvArmure);
                this.pvArmure = 0;
                this.possedeSonAccessoire = false;
            }
        } else {
            this.zdb.recoitDegat(degat);
        }
    }

    @Override
    public int getPv() {
        return zdb.getPv();
    }

    @Override
    public int getLongueurPas() {
        return zdb.getLongueurPas();
    }
}
