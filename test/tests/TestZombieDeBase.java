package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import modele.Environnement;
import modele.Jeu;
import modele.Noix;
import modele.ZombieDeBase;

import org.junit.Test;
import vue.VueJardin;

public class TestZombieDeBase {

	/*
	 * on verifie qu'un zdb se deplace bien jusqu'a la plante puis est bloqué et
	 * mange la plante
	 */
	@Test
	public void testDeplacementObstacleAvant() {
		Environnement e = new Environnement(6, 6);
		ZombieDeBase z3 = new ZombieDeBase(new Point(315, 2), 5, e);
		// un zombiedebase a une largeur de 20 et un pas de 10.
		// une planteconcrete a une largeur de 99.
		z3.setDegatsCauses(6);// pour accelerer le test
		Noix p = new Noix(200, 2, e);
		e.ajoute(p);
		e.ajoute(z3);

		assertEquals(315, z3.getPosition().x);
		assertEquals(200, p.getPosition().x);
		assertEquals(99, p.getLargeur());
		// z3 peut faire un pas normal
		z3.evolue();
		// on vérifie qu'il l'a fait :
		assertEquals(305, z3.getPosition().x);
		// z3 ne peut plus faire un pas normal
		z3.evolue();
		// on vérifie qu'il se cogne sur la noix (qui se termine en 299)
		assertEquals(299, z3.getPosition().x);
		// z3 est maintenant bloqué et cause 6 degats. la noix en a 10
		assertEquals(10, p.getPv());
		assertEquals(6, z3.getDegatCauses());
		z3.evolue();
		// on verifie qu'il a "mangé" la noix et n'a pas bougé
		assertEquals(299, z3.getPosition().x);
		assertEquals(4, p.getPv());
		// même chose mais cette fois la noix est morte
		z3.evolue();
		assertEquals(299, z3.getPosition().x);
		assertTrue(p.estMort());
		// maintenant z3 peut bouger à nouveau d'un pas normal
                z3.evolue();
		assertEquals(289, z3.getPosition().x);
	}

	// on verifie qu'un zdb n'est pas derangé par une plante derriere lui
	@Test
	public void testDeplacementObstacleArriere2() {
		Environnement e = new Environnement(6, 6);
		ZombieDeBase z3 = new ZombieDeBase(new Point(160, 2), 5, e);
		Noix p = new Noix(200, 2, e);
		e.ajoute(z3);
		e.ajoute(p);
		assertEquals(160, z3.getPosition().x);
		assertEquals(200, p.getPosition().x);
		assertEquals(99, p.getLargeur());
		z3.evolue();
		assertEquals(150, z3.getPosition().x);

	}

	/*
	 * On vérifie qu'un zombie de base a bien pour obstacle la plante la plus
	 * proche de lui dans sa direction
	 */
	@Test
	public void testPlusieursObstacle() {

		Environnement environnement = new Environnement(6, 6);
		Noix plante1 = new Noix(0, 0, environnement);
		Noix p = new Noix(200, 0, environnement); // p est devant plante1
		Noix p2 = new Noix(335, 0, environnement); // p2 est derriere z3
		ZombieDeBase z3 = new ZombieDeBase(new Point(312, 0), 5, environnement);
		// le premier obstacle que va rencontrer z3 doit être p.
		// un zombiedebase a une largeur de 20 et un pas de 10.
		// une planteconcrete a une largeur de 99.
		environnement.ajoute(plante1);
		environnement.ajoute(p);
		environnement.ajoute(p2);
		environnement.ajoute(z3);
		// on vérifie qu'on est dans le cas de test qu'on veut examiner
		assertEquals(312, z3.getPosition().x);
		assertEquals(200, p.getPosition().x);
		assertEquals(99, p.getLargeur());
		assertEquals(0, plante1.getPosition().x);
		// Chemin d = z3.espaceLibre();
		// on fait evoluer z3
		z3.evolue();
		// z3 a fait un pas normal
		assertEquals(302, z3.getPosition().x);
		// on fait evoluer z3 : il doit se cogner sur p
		z3.evolue();
		assertEquals(299, z3.getPosition().x);

	}

	/*
	 * On vérifie qu'un zombie de base a bien pour obstacle un zombie qui va en
	 * sens inverse et dans ce cas là le mange
	 */
	@Test
	public void testZombieObstacle() {

                System.out.println("TestZombieObstacle");
		Environnement environnement = new Environnement(6, 6);
		ZombieDeBase obstacle = new ZombieDeBase(new Point(340, 0), 5,
				environnement);
		ZombieDeBase z3 = new ZombieDeBase(new Point(312, 0), 5, environnement);
		z3.changeDirection();
		// le premier obstacle que va rencontrer z3 doit être obstacle.
		// un zombiedebase a une largeur de 20 et un pas de 10.
		environnement.ajoute(obstacle);
		environnement.ajoute(z3);
		// on fait evoluer z3
		z3.evolue();
		// z3 doit se cogner sur obstacle en se deplacant sur la droite
		assertEquals(320, z3.getPosition().x);
		assertEquals(50, obstacle.getPv());
		assertEquals(2, z3.getDegatCauses());
		// on fait evoluer z3 : il doit manger obstacle
		z3.evolue();
		assertEquals(48, obstacle.getPv());

	}

	/*
	 * On vérifie qu'un zombie de base n' a pas pour obstacle un zombie qui va
	 * en va dans le même sens
	 */
	@Test
	public void testZombieMemeSens() {

		Environnement environnement = new Environnement(6, 6);
		ZombieDeBase z = new ZombieDeBase(new Point(290, 0), 5, environnement);
		ZombieDeBase z3 = new ZombieDeBase(new Point(312, 0), 5, environnement);

		environnement.ajoute(z);
		environnement.ajoute(z3);
		// on fait evoluer z3
		z3.evolue();
		// z3 doit se déplacer normalement
		assertEquals(302, z3.getPosition().x);
		// on fait evoluer z3 : idem
		z3.evolue();
		assertEquals(292, z3.getPosition().x);

	}

}
