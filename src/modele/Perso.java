package modele;

import java.awt.Point;
import java.util.ArrayList;

/* tous les perso du Jeu : zombies ET plantes
 * 
 */
public abstract class Perso {

    protected Environnement environnement; // la référence vers le jardin

    public abstract int getDegatCauses(); // les degats que cause la plante

    public abstract int getLargeur(); // la largeur de la plante

    /*
     * retourne la position du perso dans le jardin sous la forme d'un
     * point(x,y). x est entre 0 et largeurTerrain*100. y est entre 0 et
     * hauteurTerrain. Par exemple, si le jardin est de 6 lignes et 7 cols, une
     * position possible est(250,0) : ce point se situe dans la première ligne
     * du jardin (0) et au milieu de la 3e colonne du jardin.
     */
    public abstract Point getPosition();

    public Environnement getEnvironnement() {
        return environnement;
    }

    public void setEnvironnement(Environnement env) {
        environnement = env;
    }

    /*
     * retourne true ssi le perso est mort
     */
    public abstract boolean estMort();

    /*
     * fait mourir le perso
     */
    public abstract void meurt();

    /*
     * le perso recoit degat
     */
    public abstract void recoitDegat(int degat);

    /*
     * Le perso evolue
     */
    public abstract void evolue();

    public int getLigne() {
        return this.getPosition().y;
    }

    public int getCol() {
        return this.getPosition().x / 100;
    }

    /*
     * Définit pour qui le type de perso est un obstacle
     */
    public abstract boolean estObstaclePour(Perso p);

    public int distance(Perso p2) {
        int distance;
        if (p2 != null) {
            if (this.getPosition().x > p2.getPosition().x) {
                distance = this.getPosition().x - (p2.getPosition().x + p2.getLargeur());
            } else {
                distance = p2.getPosition().x - (this.getPosition().x + this.getLargeur());
            }
        } else {
            distance = -1;
        }
        return distance;
    }

    public Perso obstaclePlusProche() {
        int numLigne = this.getPosition().y;
        ArrayList<Perso> ligne = this.environnement.getLignePersos(numLigne);
        int distanceCourante = this.environnement.getNbreCaseLargeurJardin() * 100;
        Perso retour = null;
        for (int i = 0; i < ligne.size(); i++) {
            if (ligne.get(i).estObstaclePour(this) && this.distance(ligne.get(i)) < distanceCourante) {
                distanceCourante = this.distance(ligne.get(i));
                retour = ligne.get(i);
            }
        }
        return retour;
    }

 
}
