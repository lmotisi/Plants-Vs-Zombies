package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import modele.Environnement;
import modele.Noix;
import modele.Perchiste;
import modele.ZombieDeBase;

import org.junit.Test;

public class TestPerchiste {

	/*
	 * On vérifie le deplacement du perchiste
	 */
	@Test
	public void test() {

		Environnement environnement = new Environnement(6, 6);
		Noix plante1 = new Noix(0, 0, environnement);
		Noix p = new Noix(200, 0, environnement); // p est devant plante1
		Noix p2 = new Noix(335, 0, environnement); // p2 est derriere z3
		ZombieDeBase z = new ZombieDeBase(new Point(312, 0), 5, environnement);
		Perchiste z3 = new Perchiste(z);
		// le premier obstacle que va rencontrer z3 doit être p il va sauter
		// derriere.
		// un zombiedebase aune largeur de 20 et un pas de 10.
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
		// on fait evoluer z3
		z3.evolue();
		// z3 a fait un pas normal
		assertEquals(302, z3.getPosition().x);
		// on fait evoluer z3 : il doit sauter: p.x-largeur z3-20. Doit
		// donc se retrouver en 200-20-20=160
		z3.evolue();
		assertEquals(160, z3.getPosition().x);
		assertFalse(z3.possedeSonAccessoire());
		// maintenant il doit se comporter comme son zombie de base:
		// Il peut marcher 6 fois
		z3.evolue();
		z3.evolue();
		z3.evolue();
		z3.evolue();
		z3.evolue();
		z3.evolue();
		// il est en 100
		assertEquals(100, z3.getPosition().x);
		z3.evolue();
		// : va se cogner sur plante 1
		assertEquals(99, z3.getPosition().x);

	}
}
