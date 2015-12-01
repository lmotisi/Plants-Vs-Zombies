package modele;

import java.awt.Point;
import java.util.ArrayList;
import vue.ObservateurTour;

public class Jeu {

    private Environnement environnement;
    private int hauteurJardin;
    private int largeurJardin;
    private int nbTour = 0;
    private ArrayList<ObservateurTour> listeObservateur;


    // vous aurez sans doute des attributs a ajouter pour gerer les sous,
    // le depart des zombies etc ...
    public Jeu(int lignes, int cols) {
        largeurJardin = cols * 100;
        hauteurJardin = lignes * 100;
        environnement = new Environnement(lignes, cols);
        this.listeObservateur= new ArrayList<ObservateurTour>();
      
    }

    /*
     * Pour avoir une notion de temps
     */
    public int getNbTour() {
        return nbTour;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public void setEnvironnement(Environnement environnement) {
        this.environnement = environnement;
    }

    public void Initialisation() {
        Noix plante1 = new Noix(0, 0, environnement);
        Noix plante2 = new Noix(400, 1, environnement);
        Plante plante3 = new PistoPois(0, 2, environnement);
        Plante plante4 = new PistoPois(0, 3, environnement);
        Noix plante5 = new Noix(0, 4, environnement);
        Plante plante6 = new PistoPois(200, 2, environnement);
        //Noix plante7 = new Noix(300, 1, environnement);
        Plante plante8 = new PistoPois(100, 3, environnement);
        Plante plante9 = new PistoPois(200, 3, environnement);
        Plante plante10 = new PistoPois(100, 0, environnement);
        Tournesol p2 = new Tournesol(200, 1, environnement);
        Tournesol p3 = new Tournesol(100, 2, environnement);
        Noix p4 = new Noix(300, 3, environnement);
        environnement.ajoute(plante1);
        environnement.ajoute(plante2);
        environnement.ajoute(plante3);
        environnement.ajoute(plante4);
        environnement.ajoute(plante5);
        environnement.ajoute(plante6);
        //environnement.ajoute(plante7);
        environnement.ajoute(plante8);
        environnement.ajoute(plante9);
        environnement.ajoute(plante10);
        environnement.ajoute(p2);
        environnement.ajoute(p3);
        environnement.ajoute(p4);

    }

    public void Initialisation2() {
        // pour l'instant, on fait une initialisation à la main :

        Noix plante1 = new Noix(0, 0, environnement);
        Noix plante2 = new Noix(400, 1, environnement);
        Plante plante3 = new PistoPois(0, 2, environnement);
        Plante plante4 = new PistoPois(0, 3, environnement);
        Noix plante5 = new Noix(0, 4, environnement);
        Plante plante6 = new PistoPois(200, 2, environnement);
        Noix plante7 = new Noix(300, 1, environnement);
        Plante plante8 = new PistoPois(100, 3, environnement);
        Plante plante9 = new PistoPois(200, 3, environnement);
        Tournesol p2 = new Tournesol(200, 1, environnement);
        Tournesol p3 = new Tournesol(100, 2, environnement);
        Noix p4 = new Noix(300, 3, environnement);
        Zombie z = new Perchiste(new ZombieDeBase(new Point(largeurJardin, 1), 1, environnement));
        Zombie z2 = new ZombieDeBase(new Point(largeurJardin, 2), 2, environnement);
        ZombieDeBase z3 = new ZombieDeBase(new Point(largeurJardin, 4), 5, environnement);
        ZombieDeBase z4 = new ZombieDeBase(new Point(250, 4), 5, environnement);
        Zombie z5 = new ZombieMoustiquaire(new ZombieDeBase(new Point(largeurJardin, 3), 3, environnement));

        z4.changeDirection();
        environnement.ajoute(plante1);
        environnement.ajoute(plante2);
        environnement.ajoute(plante3);
        environnement.ajoute(plante4);
        environnement.ajoute(plante5);
        environnement.ajoute(plante6);
        environnement.ajoute(plante7);
        environnement.ajoute(plante8);
        environnement.ajoute(plante9);
        environnement.ajoute(p2);
        environnement.ajoute(p3);
        environnement.ajoute(p4);
        environnement.ajoute(z);
        environnement.ajoute(z2);
        environnement.ajoute(z3);
        environnement.ajoute(z4);
        environnement.ajoute(z5);

    }

    public boolean estFini() {
        boolean fini;
        if (this.perdu() || this.gagne()) {
            fini = true;
        } else {
            fini = false;
        }
        return fini;
    }

    public boolean perdu() {
        int i = 0, j = 0;
        boolean perdu = false;
        while (i < (this.hauteurJardin / 100) && !perdu) {
            j = 0;
            while (j < this.environnement.getLignePersos(i).size() && !perdu) {
                if (this.environnement.getLignePersos(i).get(j) instanceof Zombie
                        && this.environnement.getLignePersos(i).get(j).getPosition().x == 0) {
                    perdu = true;
                }
                j++;
            }
            i++;
        }
        return perdu;
    }

    public boolean gagne() {
        boolean gagne = false;
        if (this.nbTour > 500 && this.environnement.compteurZombies() == 0) {
            gagne = true;
        }
        return gagne;
    }

    public void unTour() {
        this.environnement.evolue();
        nbTour++;
        this.produireSoleils();
        this.produireZombies();
        this.notifierObs();
     

    }

    public int getHauteurJardin() {
        return hauteurJardin;
    }

    public int getLargeurJardin() {
        return largeurJardin;
    }

    public void produireSoleils() {
        if (this.nbTour % 20 == 0) {
            int x = (int) (Math.random() * this.environnement.getNbreCaseLargeurJardin() * 100);
            int y = (int) (Math.random() * this.environnement.nbreLigne());
            Soleil soleil = new Soleil(new Point(x, y), 50);
            this.environnement.getSoleils().add(soleil);
        }
    }

    public void produireZombies() {
        if (this.nbTour % 20 == 0 && this.environnement.compteurZombies() < (this.hauteurJardin / 100) * 3) {
            while (creerZombie() == null) {
                this.creerZombie();
            }
        }

    }

    public Zombie creerZombie() {
        Zombie zombie = null;
        int x = largeurJardin;
        int y = (int) (Math.random() * (this.hauteurJardin / 100 - 0)) + 0;
        int compteurZombieLigne = 0;
        int compteurZombieDeBase = 0;
        for (int i = 0; i < this.environnement.getLignePersos(y).size(); i++) {
            if (this.environnement.getLignePersos(y).get(i) instanceof Zombie) {
                compteurZombieLigne++;
                if (this.environnement.getLignePersos(y).get(i) instanceof ZombieDeBase) {
                    compteurZombieDeBase++;
                }
            }
        }
        if (compteurZombieLigne < 3) {
            if (compteurZombieDeBase > 0) {
                int random = (int) (Math.random() * (2));
                if (random == 1) {
                    zombie = new Perchiste(new ZombieDeBase(new Point(x, y),
                            1, environnement));
                    this.environnement.ajoute(zombie);
                } else {
                    zombie = new ZombieMoustiquaire(new ZombieDeBase(new Point(x, y), 1, environnement));
                    this.environnement.ajoute(zombie);
                }
            } else {
                zombie = new ZombieDeBase(new Point(x, y), 1, environnement);
                this.environnement.ajoute(zombie);
            }
        }
        return zombie;
    }

    public Point verifCasePlante(int x, int y) {

        int i = 100;
        int j;
        int abscisse = 0;
        int ordonnée = 0;
        boolean trouveOrdonnee = false;
        boolean trouveAbscisse = false;
        boolean caseVide = true;
        Point point = new Point(-1, -1);

        while (i <= this.hauteurJardin && !trouveOrdonnee) {
            if (y < i) {
                ordonnée = (i - 100) / 100;
                trouveOrdonnee = true;
            }
            i += 100;
        }
        i = 100;
        while (i <= this.largeurJardin && !trouveAbscisse) {
            if (x < i) {
                abscisse = i - 100;
                trouveAbscisse = true;
            }
            i += 100;
        }
        i = 0;
        if (!this.getEnvironnement().getLignePersos(ordonnée).isEmpty()) {
            while (i < this.environnement.getLignePersos(ordonnée).size()) {
                if (this.environnement.getLignePersos(ordonnée).get(i).getPosition().x == abscisse && this.environnement.getLignePersos(ordonnée).get(i) instanceof Plante) {
                    caseVide = false;
                }
                i++;
            }
        }
        if (caseVide) {
            point = new Point(abscisse, ordonnée);
        }
        return point;
    }

    public void ajouterObs(ObservateurTour o) {
        this.listeObservateur.add(o);
    }

    public void notifierObs() {
        for(int i = 0; i< this.listeObservateur.size(); i++) {
            this.listeObservateur.get(i).augmenterTour();
        }
    }


}
