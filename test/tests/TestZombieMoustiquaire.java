package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import modele.Environnement;
import modele.Zombie;
import modele.ZombieDeBase;
import modele.ZombieMoustiquaire;

import org.junit.Before;
import org.junit.Test;

public class TestZombieMoustiquaire {

	@Before
	public void setUp() throws Exception {
	}

	/*
	 * on vérifie que l'armure absorbe les degats tant qu'elle peut
	 */
	@Test
	public void testCombatZombie() {
		Environnement e = new Environnement(6, 6);
		ZombieMoustiquaire z2 = new ZombieMoustiquaire(new ZombieDeBase(
				new Point(600, 2), 2, e));
		ZombieDeBase z3 = new ZombieDeBase(new Point(580, 2), 5, e);
		e.ajoute(z3);
		e.ajoute(z2);
		z3.changeDirection();// z3 va vers arriere et z2 vers l'avant.
		z3.setDegatsCauses(30); // pour accelerer le test
		// Z3 cause 30 degats
		// z2 : pvarmure = 55 pvZombieDeBase =50
		// z2 et z3 sont en contact
		z3.evolue();
		// z3 doit manger Z2 : c'est l'armure qui prend
		assertEquals(580, z3.getPosition().x);
		assertEquals(25, z2.getPvArmure());
		assertEquals(50, z2.getZdb().getPv());
		z3.evolue();
		// l'armure absorbe 25 degats et zombie de base le reste (5)
		assertEquals(0, z2.getPvArmure());
		assertEquals(45, z2.getZdb().getPv());
		z3.evolue();
		// seul le zombie prends
		assertEquals(0, z2.getPvArmure());
		assertEquals(15, z2.getZdb().getPv());
	}

}
