package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

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
	public void testInitializationSingleton()
	{
		Environment.clearBoard();
		Environment.setupWorld(3,3);
		Environment e = Environment.getWorld();
		assertEquals(3, e.getNumberOfRows());
		assertEquals(3, e.getNumberOfColumns());
		assertEquals(9, e.getNumberOfCells());
		assertNull(e.getLifeForm(0, 0));
	}
	
	@Test
	public void testAddWeapons()
	{
		Environment.clearBoard();
		Environment.setupWorld(2,3);
		Environment e = Environment.getWorld();
		Weapon weap1 = new PlasmaCannon(20,10,1,4);
		Weapon weap2 = new PlasmaCannon(25,10,1,4);
		e.addWeapon1(weap1, 0, 1);
		assertEquals(weap1, e.getWeapon1(0, 1));
		e.addWeapon2(weap2, 0, 1);
		assertEquals(weap2, e.getWeapon2(0, 1));
	}
	
	/*
	 * Start Section for Decorator Pattern Tests
	 */ 

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * Tests a 1x1 environment can be created and populated with a single cell
	 */
	@Test
	public void testInitializationSingleCell()
	{
		Environment.clearBoard();
		Environment.setupWorld(1,1);
		Environment e = Environment.getWorld();
		assertEquals(1, e.getNumberOfRows());
		assertEquals(1, e.getNumberOfColumns());
		assertEquals(1, e.getNumberOfCells());
		assertNull(e.getLifeForm(0, 0));
	}

	/**
	 * Tests adding a LifeForm to the Environment at the specified location
	 */
	@Test
	public void testAddLifeForm()
	{
		Environment.clearBoard();
		Environment.setupWorld(2,3);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
	}

	/**
	 * Tests that a LifeForm cannot be added to a cell out of bounds of the
	 * Environment
	 */
	@Test
	public void testAddLifeFormOffGrid()
	{
		Environment.clearBoard();
		Environment.setupWorld(2,3);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, -1, 1);
		assertEquals(null, e.getLifeForm(-1, 1));
		e.addLifeForm(l, 0, 3);
		assertEquals(null, e.getLifeForm(0, 3));
	}

	/**
	 * Tests that a LifeForm can be removed from a specified location
	 */
	@Test
	public void testRemoveLifeForm()
	{
		Environment.clearBoard();
		Environment.setupWorld(2,3);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
		e.removeLifeForm(0, 1);
		assertNull(e.getLifeForm(0, 1));
	}
}
