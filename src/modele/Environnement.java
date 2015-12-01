package modele;

import java.util.ArrayList;
import vue.ObservateurArgent;
import vue.ObservateurDispoPlantes;

public class Environnement {
    // chaque ligne est une liste de persos contenant les plantes,
    // projectiles et zombies de la ligne

    private int nbreCaseLargeurJardin;
    private int nbreCaseHauteurJardin;
    private ArrayList<ArrayList<Perso>> lesPersos;
    private ArrayList<Soleil> lesSoleils;
    private int argentSoleil;
    private int choixPlante;
    private ArrayList<ObservateurArgent> listeObs;
    private ArrayList<ObservateurDispoPlantes> listeObsplante;
    public int prixNoix = 50;
    public int prixTournesol = 50;
    public int prixPistoPois = 100;
    public int prixHypnotiseur = 75;
    public int prixBombe = 150;
    private int compteurTourTournesol;
    private int compteurTourNoix;
    private int compteurTourPistoPois;
    private int compteurTourHypnotiseur;
    private int compteurTourBombe;

    public ArrayList<Perso> getLignePersos(int i) {
        return lesPersos.get(i);
    }

    public int nbreLigne() {
        return lesPersos.size();
    }

    public Environnement() {
        lesPersos = new ArrayList<ArrayList<Perso>>();
        lesSoleils = new ArrayList<Soleil>();
    }

    public Environnement(int lignes, int cols) {
        nbreCaseLargeurJardin = cols;
        nbreCaseHauteurJardin = lignes;
        lesSoleils = new ArrayList<Soleil>();
        lesPersos = new ArrayList<ArrayList<Perso>>();
        for (int i = 0; i < lignes; i++) {
            ArrayList<Perso> uneLigne = new ArrayList<Perso>();
            lesPersos.add(uneLigne);
        }
        this.argentSoleil = 50;
        this.listeObs = new ArrayList<ObservateurArgent>();
        this.listeObsplante = new ArrayList<ObservateurDispoPlantes>();
        this.choixPlante = 0;
        this.compteurTourNoix = 0;
        this.compteurTourTournesol = 0;
        this.compteurTourPistoPois = 0;
        this.compteurTourHypnotiseur = 0;
    }

    public int getNbreCaseLargeurJardin() {
        return nbreCaseLargeurJardin;
    }

    public int getNbreCaseHauteurJardin() {
        return nbreCaseHauteurJardin;
    }


    /*
     * Fait evoluer tous les persos, ramasse les morts causes par cette
     * evolution, et retourne les sous gagnes par les clics sur les soleils
     */
    public int evolue() {
        this.ramasseLesMorts();
        for (int i = 0; i < this.nbreLigne(); i++) {
            for (int j = 0; j < this.lesPersos.get(i).size(); j++) {
                this.lesPersos.get(i).get(j).evolue();
            }
        }

        for (Soleil s : this.lesSoleils) {
            s.evolue();
        }

        this.ramasserRessources();

        if (compteurTourTournesol > 0) {
            this.setTourTournesol(this.compteurTourTournesol - 1);
        }
        if (compteurTourNoix > 0) {
            this.setTourNoix(this.compteurTourNoix - 1);
        }
        if (compteurTourPistoPois > 0) {
            this.setTourPistoPois(this.compteurTourPistoPois - 1);
        }
        if (compteurTourHypnotiseur > 0) {
            this.setTourHypnotiseur(this.compteurTourHypnotiseur - 1);
        }

         if (compteurTourBombe > 0) {
            this.setTourBombe(this.compteurTourBombe - 1);
        }
        //System.out.println("Argent Soleil: " + this.argentSoleil);
        return 0;
    }

    /*
     * Enleve de l'environnement les persos morts
     */
    private void ramasseLesMorts() {
        for (int i = 0; i < this.nbreLigne(); i++) {
            for (int j = this.lesPersos.get(i).size() - 1; j >= 0; j--) {
                if (this.lesPersos.get(i).get(j).estMort()) {
                    this.lesPersos.get(i).remove(j);
                }
            }

        }
    }

    /*
     * Enleve de l'environnement les soleils qui ont ete cliques. Retourne les
     * sous que cela rapporte
     */
    private void ramasserRessources() {
        int valeur = 0;
        int i = 0;
        while (i < this.lesSoleils.size()) {
            Soleil s = this.lesSoleils.get(i);
            if (s.estMort()) {
                valeur += s.getValeur();
                this.lesSoleils.remove(i);

            }
            i++;

        }
        this.setArgent(this.getArgent() + valeur);

    }

    /*
     * ajoute un perso a l'environnement
     */
    public void ajoute(Perso p) {
        this.lesPersos.get(p.getPosition().y).add(p);
    }

    public ArrayList<Soleil> getSoleils() {
        return lesSoleils;
    }

    /*
     * pour test
     */
    public boolean contains(Perso p) {
        int numLigne = p.getPosition().y;
        return this.getLignePersos(numLigne).contains(p);
    }

    public int compteurZombies() {
        int compteurZombie = 0;
        for (int i = 0; i < this.lesPersos.size(); i++) {
            for (int j = 0; j < this.lesPersos.get(i).size(); j++) {
                if (this.lesPersos.get(i).get(j) instanceof Zombie) {
                    compteurZombie++;
                }
            }

        }
        return compteurZombie;
    }

    public int getArgent() {
        return this.argentSoleil;
    }

    public void setArgent(int argent) {
        this.argentSoleil = argent;
        this.notifierObs();
    }

    public void setChoixPlante(int choixPlante) {
        this.choixPlante = choixPlante;
    }

    public int getChoixPlante() {
        return this.choixPlante;
    }

    public void ajouterObs(ObservateurArgent o) {
        this.listeObs.add(o);
    }

    public void ajouterObsPlante(ObservateurDispoPlantes o) {
        this.listeObsplante.add(o);
    }

    public void notifierObs() {
        for (int i = 0; i < this.listeObs.size(); i++) {
            this.listeObs.get(i).ramasserArgent();

        }
    }

    public void notifierObsPlante() {

        for (int i = 0; i < this.listeObsplante.size(); i++) {
            this.listeObsplante.get(i).compterToursPlantes();

        }

    }

    public void setTourTournesol(int tour) {
        this.compteurTourTournesol = tour;
        this.notifierObsPlante();
    }

    public void setTourNoix(int tour) {
        this.compteurTourNoix = tour;
        this.notifierObsPlante();
    }

    public void setTourPistoPois(int tour) {
        this.compteurTourPistoPois = tour;
        this.notifierObsPlante();
    }

    public void setTourBombe(int tour) {
        this.compteurTourBombe = tour;
        this.notifierObsPlante();
    }

    public int getTourTournesol() {
        return this.compteurTourTournesol;
    }

    public int getTourNoix() {
        return this.compteurTourNoix;
    }

    public int getTourPistoPois() {
        return this.compteurTourPistoPois;
    }

    public int getTourHypnotiseur() {
        return this.compteurTourHypnotiseur;
    }

    public int getTourBombe() {
        return this.compteurTourBombe;
    }

     public void setTourHypnotiseur(int tour) {
        this.compteurTourHypnotiseur = tour;
        this.notifierObsPlante();
    }
}
