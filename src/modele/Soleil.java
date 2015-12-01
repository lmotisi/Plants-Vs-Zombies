package modele;

import java.awt.Point;

/*
 * Les soleils ont une durée de vie et font gagner une somme fixe
 * lorsqu'ils sont cliqués.
 */
public class Soleil extends Perso {

    // sans doute des attributs et des méthodes propres aux soleils
    // en plus des méthodes abstraites des super classes
    private Point position;
    private int dureeDeVie;
    private int valeur;
    private boolean aEteClique;

    public Soleil(Point position, int valeur) {
        this.valeur = valeur;
        this.dureeDeVie = 40;
        this.position = position;
        this.aEteClique = false;
    }

    public int getValeur() {

        if (this.aEteClique) {
            return this.valeur;
        } else {
            return 0;
        }

    }

    public void clique() {
        this.meurt();
        this.aEteClique = true;
    }

    @Override
    public boolean estMort() {
        return this.dureeDeVie == 0;

    }

   @Override
    public void evolue() {
        this.dureeDeVie--;
        if (this.dureeDeVie < 0) {
            this.dureeDeVie = 0;
        }
         //System.out.println("Vie Restante: " + this.dureeDeVie);

    }

    @Override
    public void recoitDegat(int degat) {
        // TO DO
    }

    @Override
    public int getLargeur() {
        return 20;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void meurt() {
        this.dureeDeVie = 0;
    }

    @Override
    public int getDegatCauses() {
        // TO DO
        return 0;
    }

    @Override
    public boolean estObstaclePour(Perso p) {
        // TO DO
        return false;
    }
}
