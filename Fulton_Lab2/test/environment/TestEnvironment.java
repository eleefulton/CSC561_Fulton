package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.EnvironmentException;
import exceptions.ExistingWorldException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * Tests the functionality provided by the Environment class
 *
 */
public class TestEnvironment
{
	@Test
	public void testMoveHumanNorth() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("l1", 10, 10);
		LifeForm l2 = new Human("l2", 10, 10);
		LifeForm l3 = new Human("l3", 10, 10);
		// test no obstacles
		e.addLifeForm(l1, 3, 0);
		e.moveLifeForm(3, 0);
		// test moving off board
		assertEquals(l1, e.getLifeForm(0, 0));
		e.removeLifeForm(0, 0);
		e.addLifeForm(l1, 2, 0);
		e.moveLifeForm(2, 0);
		// test lifeform in landing position
		assertEquals(l1, e.getLifeForm(0, 0));
		e.removeLifeForm(0, 0);
		e.addLifeForm(l1, 3, 0);
		e.addLifeForm(l2, 0, 0);
		e.moveLifeForm(3, 0);
		assertEquals(l1, e.getLifeForm(1, 0));
		// test lifeform in way but not blocking landing position
		e.removeLifeForm(1, 0);
		e.removeLifeForm(0, 0);
		e.addLifeForm(l2, 1, 0);
		e.addLifeForm(l1, 3, 0);
		e.moveLifeForm(3, 0);
		assertEquals(l1, e.getLifeForm(0, 0));
		// test two lifeforms in way and landing position
		e.removeLifeForm(0, 0);
		e.addLifeForm(l3, 0, 0);
		e.addLifeForm(l1, 3, 0);
		e.moveLifeForm(3, 0);
		assertEquals(l1, e.getLifeForm(2, 0));
	}

	@Test
	public void testMoveHumanSouth() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("l1", 10, 10);
		l1.changeDirection(LifeForm.SOUTH);
		LifeForm l2 = new Human("l2", 10, 10);
		LifeForm l3 = new Human("l3", 10, 10);
		// test no obstacles
		e.addLifeForm(l1, 0, 0);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(3, 0));
		// test moving off board
		e.removeLifeForm(3, 0);
		e.addLifeForm(l1, 1, 0);
		e.moveLifeForm(1, 0);
		assertEquals(l1, e.getLifeForm(3, 0));
		// test lifeform in landing position
		e.removeLifeForm(3, 0);
		e.addLifeForm(l1, 0, 0);
		e.addLifeForm(l2, 3, 0);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(2, 0));
		// test lifeform in way but not blocking landing position
		e.removeLifeForm(2, 0);
		e.removeLifeForm(3, 0);
		e.addLifeForm(l2, 2, 0);
		e.addLifeForm(l1, 0, 0);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(3, 0));
		// test two lifeforms in way and landing position
		e.removeLifeForm(3, 0);
		e.addLifeForm(l3, 3, 0);
		e.addLifeForm(l1, 0, 0);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(1, 0));
	}

	@Test
	public void testMoveAlienEast() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Alien("l1", 10);
		l1.changeDirection(LifeForm.EAST);
		LifeForm l2 = new Alien("l2", 10);
		LifeForm l3 = new Alien("l3", 10);
		// test no obstacles
		e.addLifeForm(l1, 0, 0);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(0, 2));
		// test moving off board
		e.removeLifeForm(0, 2);
		e.addLifeForm(l1, 0, 1);
		e.moveLifeForm(0, 1);
		assertEquals(l1, e.getLifeForm(0, 2));
		// test lifeform in landing position
		e.removeLifeForm(0, 2);
		e.addLifeForm(l1, 0, 0);
		e.addLifeForm(l2, 0, 2);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(0, 1));
		// test lifeform in way but not blocking landing position
		e.removeLifeForm(0, 1);
		e.removeLifeForm(0, 2);
		e.addLifeForm(l2, 0, 1);
		e.addLifeForm(l1, 0, 0);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(0, 2));
		// test two lifeforms in way and landing position
		e.removeLifeForm(0, 2);
		e.addLifeForm(l3, 0, 2);
		e.addLifeForm(l1, 0, 0);
		e.moveLifeForm(0, 0);
		assertEquals(l1, e.getLifeForm(0, 0));
	}

	@Test
	public void testMoveAlienWest() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Alien("l1", 10);
		l1.changeDirection(LifeForm.WEST);
		LifeForm l2 = new Alien("l2", 10);
		LifeForm l3 = new Alien("l3", 10);
		// test no obstacles
		e.addLifeForm(l1, 0, 2);
		e.moveLifeForm(0, 2);
		assertEquals(l1, e.getLifeForm(0, 0));
		// test moving off board
		e.removeLifeForm(0, 0);
		e.addLifeForm(l1, 0, 1);
		e.moveLifeForm(0, 1);
		assertEquals(l1, e.getLifeForm(0, 0));
		// test lifeform in landing position
		e.removeLifeForm(0, 0);
		e.addLifeForm(l1, 0, 2);
		e.addLifeForm(l2, 0, 0);
		e.moveLifeForm(0, 2);
		assertEquals(l1, e.getLifeForm(0, 1));
		// test lifeform in way but not blocking landing position
		e.removeLifeForm(0, 1);
		e.removeLifeForm(0, 0);
		e.addLifeForm(l2, 0, 1);
		e.addLifeForm(l1, 0, 2);
		e.moveLifeForm(0, 2);
		assertEquals(l1, e.getLifeForm(0, 0));
		// test two lifeforms in way and landing position
		e.removeLifeForm(0, 0);
		e.addLifeForm(l3, 0, 0);
		e.addLifeForm(l1, 0, 2);
		e.moveLifeForm(0, 2);
		assertEquals(l1, e.getLifeForm(0, 2));
	}

	/**
	 * begin lab 5 tests
	 */
	@Test
	public void testInitializationSingleton() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(3, 3);
		Environment e = Environment.getWorld();
		assertEquals(3, e.getNumberOfRows());
		assertEquals(3, e.getNumberOfColumns());
		assertEquals(9, e.getNumberOfCells());
		assertNull(e.getLifeForm(0, 0));
	}

	@Test
	public void testAddWeapons() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(2, 3);
		Environment e = Environment.getWorld();
		Weapon weap1 = new PlasmaCannon(20, 10, 1, 4);
		Weapon weap2 = new PlasmaCannon(25, 10, 1, 4);
		e.addWeapon1(weap1, 0, 1);
		assertEquals(weap1, e.getWeapon1(0, 1));
		e.addWeapon2(weap2, 0, 1);
		assertEquals(weap2, e.getWeapon2(0, 1));
	}

	@Test
	public void testRemoveWeapons() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(2, 3);
		Environment e = Environment.getWorld();
		Weapon weap1 = new PlasmaCannon(20, 10, 1, 4);
		Weapon weap2 = new PlasmaCannon(25, 10, 1, 4);
		e.addWeapon1(weap1, 0, 1);
		assertEquals(weap1, e.getWeapon1(0, 1));
		e.addWeapon2(weap2, 0, 1);
		assertEquals(weap2, e.getWeapon2(0, 1));
		// Removing the weapons
		e.removeWeapon1(0, 1);
		assertNull(e.getWeapon1(0, 1));
		e.removeWeapon2(0, 1);
		assertNull(e.getWeapon2(0, 1));
	}

	@Test
	public void testBorderCasesWeapons() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(2, 2);
		Environment e = Environment.getWorld();
		Weapon weap1 = new PlasmaCannon(20, 10, 1, 4);
		// Negative ranges
		e.addWeapon1(weap1, -1, 1);
		assertNull(e.getWeapon1(-1, 1));
		e.addWeapon1(weap1, 1, -1);
		assertNull(e.getWeapon1(1, -1));
		e.addWeapon1(weap1, -1, -1);
		assertNull(e.getWeapon1(-1, -1));

		e.addWeapon2(weap1, -1, 1);
		assertNull(e.getWeapon2(-1, 1));
		e.addWeapon2(weap1, 1, -1);
		assertNull(e.getWeapon2(1, -1));
		e.addWeapon2(weap1, -1, -1);
		assertNull(e.getWeapon2(-1, -1));

		// Ranges larger than the grid
		e.addWeapon1(weap1, 2, 1);
		assertNull(e.getWeapon1(2, 1));
		e.addWeapon1(weap1, 1, 2);
		assertNull(e.getWeapon1(1, 2));
		e.addWeapon1(weap1, 2, 2);
		assertNull(e.getWeapon1(2, 2));

		e.addWeapon2(weap1, 2, 1);
		assertNull(e.getWeapon2(2, 1));
		e.addWeapon2(weap1, 1, 2);
		assertNull(e.getWeapon2(1, 2));
		e.addWeapon2(weap1, 2, 2);
		assertNull(e.getWeapon2(2, 2));

		// Remove
		assertFalse(e.removeWeapon1(2, 1));
		assertFalse(e.removeWeapon1(1, 2));
		assertFalse(e.removeWeapon1(2, 2));

		assertFalse(e.removeWeapon2(2, 1));
		assertFalse(e.removeWeapon2(1, 2));
		assertFalse(e.removeWeapon2(2, 2));
	}

	@Test
	public void testLifeFormsRangeOnSameRow() throws EnvironmentException, ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 4);
		Environment e = Environment.getWorld();
		LifeForm l1 = new MockLifeForm("L", 10);
		e.addLifeForm(l1, 0, 0);
		assertEquals(l1, e.getLifeForm(0, 0));
		LifeForm l2 = new MockLifeForm("L", 10);
		e.addLifeForm(l2, 0, 2);
		assertEquals(l2, e.getLifeForm(0, 2));
		assertEquals(10, e.getDistance(l1, l2));
	}

	@Test
	public void testLifeFormsRangeOnSameColumn() throws EnvironmentException, ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 4);
		Environment e = Environment.getWorld();
		LifeForm l2 = new MockLifeForm("L", 10);
		e.addLifeForm(l2, 0, 2);
		assertEquals(l2, e.getLifeForm(0, 2));
		LifeForm l3 = new MockLifeForm("L", 10);
		e.addLifeForm(l3, 3, 2);
		assertEquals(l3, e.getLifeForm(3, 2));
		assertEquals(15, e.getDistance(l2, l3));
	}

	@Test
	public void testLifeFormsRangeNotOnSameRowOrColumn() throws EnvironmentException, ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 4);
		Environment e = Environment.getWorld();
		LifeForm l1 = new MockLifeForm("L", 10);
		e.addLifeForm(l1, 0, 0);
		assertEquals(l1, e.getLifeForm(0, 0));
		LifeForm l3 = new MockLifeForm("L", 10);
		e.addLifeForm(l3, 3, 2);
		assertEquals(l3, e.getLifeForm(3, 2));
		assertEquals(18, e.getDistance(l1, l3));
	}

	@Test(expected = EnvironmentException.class)
	public void testLifeFormsRangeException() throws EnvironmentException, ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 4);
		Environment e = Environment.getWorld();
		LifeForm l1 = new MockLifeForm("L", 10);

		LifeForm l3 = new MockLifeForm("L", 10);
		e.addLifeForm(l3, 3, 2);
		assertEquals(l3, e.getLifeForm(3, 2));
		assertEquals(25, e.getDistance(l1, l3)); // will throw an except
	}

	/*
	 * Start Section for Decorator Pattern Tests
	 */

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * Tests a 1x1 environment can be created and populated with a single cell
	 * 
	 * @throws ExistingWorldException
	 */
	@Test
	public void testInitializationSingleCell() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(1, 1);
		Environment e = Environment.getWorld();
		assertEquals(1, e.getNumberOfRows());
		assertEquals(1, e.getNumberOfColumns());
		assertEquals(1, e.getNumberOfCells());
		assertNull(e.getLifeForm(0, 0));
	}

	/**
	 * Tests adding a LifeForm to the Environment at the specified location
	 * 
	 * @throws ExistingWorldException
	 */
	@Test
	public void testAddLifeForm() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(2, 3);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
	}

	/**
	 * Tests that a LifeForm cannot be added to a cell out of bounds of the
	 * Environment
	 * 
	 * @throws ExistingWorldException
	 */
	@Test
	public void testAddLifeFormOffGrid() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(2, 3);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, -1, 1);
		assertEquals(null, e.getLifeForm(-1, 1));
		e.addLifeForm(l, 0, 3);
		assertEquals(null, e.getLifeForm(0, 3));
	}

	/**
	 * Tests that a LifeForm can be removed from a specified location
	 * 
	 * @throws ExistingWorldException
	 */
	@Test
	public void testRemoveLifeForm() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(2, 3);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
		boolean success = e.removeLifeForm(0, 1);
		assertTrue(success);
		assertNull(e.getLifeForm(0, 1));
	}
}
