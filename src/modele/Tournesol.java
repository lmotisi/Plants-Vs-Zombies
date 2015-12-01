package modele;

import java.util.Random;

public class Tournesol extends Plante {

    private int ProductionSoleil; // le nb de tour a attendre avant qu'un soleil repop

    // sans doute des attributs propre aux tournesols
    public Tournesol(int x, int y, Environnement e) {
        // Un tournesol a 30 pv
        super(x, y, 30, e);
        Random r = new Random();
        this.ProductionSoleil = 20 + r.nextInt(40 - 20);
    }

    public void evolue() {
        this.produireSoleils();
    }

    public void produireSoleils() {

        ProductionSoleil--;
        if (ProductionSoleil == 0) {
            Random r = new Random();
            this.ProductionSoleil = 20 + r.nextInt(40 - 20);
            this.environnement.getSoleils().add(new Soleil(this.getPosition(), 50));
        }
    }
}
