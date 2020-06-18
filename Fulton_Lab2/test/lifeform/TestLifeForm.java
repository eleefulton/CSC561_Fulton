package lifeform;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.ExistingWorldException;
import gameplay.SimpleTimer;
import weapon.Pistol;
import weapon.PlasmaCannon;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm
{
	private static Environment e;

	@BeforeClass
	public static void setup() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 4);
		e = Environment.getWorld();
	}

	@AfterClass
	public static void CleanUp()
	{
		Environment.clearBoard();
	}

	@Test
	public void testInitializeWithSpeedAndDirection()
	{
		LifeForm entity;
		entity = new MockLifeForm("Bob", 40);
		assertEquals("Bob", entity.getName());
		assertEquals(40, entity.getCurrentLifePoints());
		assertEquals(0, entity.getMaxSpeed());
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
	}

	@Test
	public void testChangeDirection()
	{
		LifeForm entity;
		entity = new MockLifeForm("Bob", 40);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		entity.changeDirection(LifeForm.EAST);
		assertEquals(LifeForm.EAST, entity.getCurrentDirection());
		entity.changeDirection(LifeForm.WEST);
		assertEquals(LifeForm.WEST, entity.getCurrentDirection());
		entity.changeDirection(LifeForm.SOUTH);
		assertEquals(LifeForm.SOUTH, entity.getCurrentDirection());
		entity.changeDirection(LifeForm.NORTH);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		entity.changeDirection(LifeForm.NORTH);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
	}

	/**
	 * begin lab 5 tests
	 * 
	 * @throws EnvironmentException
	 */
	@Test()
	public void testLifeFormAttackWithRange() throws EnvironmentException
	{

		LifeForm bob = new MockLifeForm("Bob", 40, 2);
		e.addLifeForm(bob, 0, 0);
		PlasmaCannon pc = new PlasmaCannon(50, 20, 1, 4);
		bob.pickWeapon(pc);

		LifeForm fred = new MockLifeForm("Fred", 30, 2);
		e.addLifeForm(fred, 0, 1);// 5 feet away

		assertEquals(50, bob.attack(fred));
		pc.reload();
		pc.resetShotsFired();
		assertEquals(50, bob.attack(fred));
		pc.resetShotsFired();
		pc.setRemainingAmmo(0);

		fred.setColCell(2); // 10 feet away
		assertEquals(0, bob.attack(fred));

		// Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
		e.removeLifeForm(0, 2);
	}

	/*
	 * Start Section for Decorator Pattern Tests
	 */
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
	public void testLifeFormAttackWithPlasmaCannon() throws EnvironmentException
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		PlasmaCannon pc = new PlasmaCannon(50, 20, 1, 4);
		entity.pickWeapon(pc);
		e.addLifeForm(entity, 0, 0);
		LifeForm fred = new MockLifeForm("Fred", 30, 2);
		e.addLifeForm(fred, 0, 2);// 10 feet away
		LifeForm joe = new MockLifeForm("Joe", 30, 2);
		e.addLifeForm(joe, 0, 1);// 5 feet away
		assertEquals(50, entity.attack(fred));
		pc.reload();
		pc.resetShotsFired();
		assertEquals(50, entity.attack(joe));
		pc.resetShotsFired();
		pc.setRemainingAmmo(0);
		assertEquals(0, entity.attack(fred));

		// Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
		e.removeLifeForm(0, 2);
	}

	@Test
	public void testLifeFormAttackWithPistol() throws EnvironmentException
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		Pistol p = new Pistol(50, 20, 1, 4);
		entity.pickWeapon(p);
		e.addLifeForm(entity, 0, 0);
		LifeForm fred = new MockLifeForm("Fred", 30, 2);
		e.addLifeForm(fred, 0, 2);// 10 feet away
		LifeForm joe = new MockLifeForm("Joe", 30, 2);
		e.addLifeForm(joe, 0, 1);// 5 feet away
		assertEquals(37, entity.attack(fred));
		p.reload();
		p.resetShotsFired();

		assertEquals(50, entity.attack(joe));
		p.resetShotsFired();
		p.setRemainingAmmo(0);
		assertEquals(0, entity.attack(fred));
		p.resetShotsFired();
		assertEquals(2, entity.attack(joe));

		// Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
		e.removeLifeForm(0, 2);
	}

	@Test
	public void testLifeFormDamageNoWeapon() throws EnvironmentException
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		e.addLifeForm(entity, 0, 0);
		LifeForm fred = new MockLifeForm("Fred", 30, 2);
		e.addLifeForm(fred, 0, 1);// 5 feet away
		assertNull(entity.getWeapon());
		assertEquals(2, entity.attack(fred));

		fred.setRowCell(1); // 7 feet away
		assertEquals(0, entity.attack(fred));

		// Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
		e.removeLifeForm(1, 1);
	}

	@Test
	public void testLifeFormCanReload()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		Pistol p = new Pistol(50, 20, 1, 4);
		entity.pickWeapon(p);
		p.setRemainingAmmo(0);
		entity.reload();
		assertEquals(4, p.getRemainingAmmo());
	}
	/*
	 * Start Section for Observer Pattern Tests
	 */

	@Test
	public void testInitializeWithStrength() throws EnvironmentException
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		e.addLifeForm(entity, 0, 0);
		LifeForm fred = new MockLifeForm("Fred", 30, 2);
		e.addLifeForm(fred, 0, 1);// 5 feet away
		assertEquals(2, entity.attack(fred));

		// Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
	}

	@Test
	public void testTakeHitFromLifeForm() throws EnvironmentException
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		LifeForm entity2 = new MockLifeForm("Dave", 40, 2);
		e.addLifeForm(entity, 0, 0);
		e.addLifeForm(entity2, 0, 1);// 5 feet away
		entity.takeHit(entity2.attack(entity));
		assertEquals(38, entity.getCurrentLifePoints());

		// Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
	}

	@Test
	public void testNoDamageDealtWhenDead() throws EnvironmentException
	{
		LifeForm entity = new MockLifeForm("Bob", 0, 2);
		LifeForm entity2 = new MockLifeForm("Dave", 40, 2);
		e.addLifeForm(entity, 0, 0);
		e.addLifeForm(entity2, 0, 1);// 5 feet away
		assertEquals(0, entity.attack(entity2));

		// Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
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
