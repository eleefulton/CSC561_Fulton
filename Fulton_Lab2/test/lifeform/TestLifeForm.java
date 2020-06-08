package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import gameplay.SimpleTimer;
import weapon.PlasmaCannon;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm
{

	@Test
	public void testLifeFormPickingAWeapon()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		PlasmaCannon pc = new PlasmaCannon(50, 20, 1, 4);
		entity.pickWeapon(pc);
		assertEquals(pc, entity.getWeapon());
	}

	@Test
	public void testLifeFormCannotPickUpAWeaponWhileHoldOne()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		PlasmaCannon pc = new PlasmaCannon(50, 20, 1, 4);
		entity.pickWeapon(pc);
		assertEquals(pc, entity.getWeapon());

		PlasmaCannon pc2 = new PlasmaCannon(20, 10, 2, 5);
		entity.pickWeapon(pc2); // Already holds one cannot pick up a second one
		assertEquals(pc, entity.getWeapon());
	}

	@Test
	public void testLifeFormDopAWeapon()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		PlasmaCannon pc = new PlasmaCannon(50, 20, 1, 4);
		entity.pickWeapon(pc);
		assertEquals(pc, entity.getWeapon());
		entity.dropWeapon();
		assertNull(entity.getWeapon());
	}

	@Test
	public void testLifeFormAttackWithPlasmaCannon()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		PlasmaCannon pc = new PlasmaCannon(50, 20, 1, 4);
		entity.pickWeapon(pc);
		assertEquals(50, entity.attack(10));
		pc.Reload();
		pc.resetShotsFired();
		assertEquals(50, entity.attack(5));
		pc.resetShotsFired();
		pc.setRemainingAmmo(0);
		assertEquals(0, entity.attack(5));
	}

	@Test
	public void testLifeFormAttackWithPistol()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		Pistol p = new Pistol(50, 20, 1, 4);
		entity.pickWeapon(p);
		assertEquals(37, entity.attack(10));
		p.Reload();
		p.resetShotsFired();
		assertEquals(50, entity.attack(5));
		p.resetShotsFired();
		p.setRemainingAmmo(0);
		assertEquals(0, entity.attack(5));
	}

	@Test
	public void testLifeFormDamageNoWeapon()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		assertNull(entity.getWeapon());
		assertEquals(2, entity.attack(5));
		assertEquals(0, entity.attack(6));
	}

	/*
	 * Start Section for Observer Pattern Tests
	 */

	@Test
	public void testInitializeWithStrength()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		assertEquals(2, entity.attack(5));
	}

	@Test
	public void testTakeHitFromLifeForm()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		LifeForm entity2 = new MockLifeForm("Dave", 40, 2);
		entity.takeHit(entity2.attack(5));
		assertEquals(38, entity.getCurrentLifePoints());
	}

	@Test
	public void testNoDamageDealtWhenDead()
	{
		LifeForm entity = new MockLifeForm("Bob", 0, 2);
		assertEquals(0, entity.attack(5));
	}

	@Test
	public void testTrackPassageOfTime()
	{
		MockLifeForm entity = new MockLifeForm("Bob", 0, 2);
		SimpleTimer timer = new SimpleTimer(1);
		timer.addTimeObserver(entity);
		assertEquals(0, entity.getTime());
		timer.timeChanged();
		assertEquals(1, entity.getTime());
	}

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * When a LifeForm is created, it should know its name and how many life points
	 * it has.
	 */
	@Test
	public void testInitialization()
	{
		LifeForm entity;
		entity = new MockLifeForm("Bob", 40);
		assertEquals("Bob", entity.getName());
		assertEquals(40, entity.getCurrentLifePoints());
	}

	/**
	 * Check a LifeForm can take damage but not go below 0 life points
	 */
	@Test
	public void testTakeDamage()
	{
		LifeForm entity = new MockLifeForm("Bob", 40);
		entity.takeHit(30);
		assertEquals(10, entity.getCurrentLifePoints());
		entity.takeHit(10);
		assertEquals(0, entity.getCurrentLifePoints());
		entity.takeHit(1);
		assertEquals(0, entity.getCurrentLifePoints());
	}

}
