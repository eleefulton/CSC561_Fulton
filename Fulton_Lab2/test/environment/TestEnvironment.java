package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exceptions.EnvironmentException;
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
	
	@Test
	public void testRemoveWeapons()
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
		//Removing the weapons
		e.removeWeapon1(0, 1);
		assertNull(e.getWeapon1(0, 1));
		e.removeWeapon2(0, 1);
		assertNull(e.getWeapon2(0, 1));
	}
	
	@Test
	public void testBorderCasesWeapons()
	{
		Environment.clearBoard();
		Environment.setupWorld(2,2);
		Environment e = Environment.getWorld();
		Weapon weap1 = new PlasmaCannon(20,10,1,4);
		//Negative ranges
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
		
		//Ranges larger than the grid
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
		
		//Remove
		assertFalse(e.removeWeapon1(2, 1));
		assertFalse(e.removeWeapon1(1, 2));
		assertFalse(e.removeWeapon1(2, 2));
		
		assertFalse(e.removeWeapon2(2, 1));
		assertFalse(e.removeWeapon2(1, 2));
		assertFalse(e.removeWeapon2(2, 2));
	}
	
	@Test
	public void testLifeFormsRangeOnSameRow() throws EnvironmentException
	{
		Environment.clearBoard();
		Environment.setupWorld(4,4);
		Environment e = Environment.getWorld();
		LifeForm l1 = new MockLifeForm("L", 10);
		e.addLifeForm(l1, 0, 0);
		assertEquals(l1, e.getLifeForm(0, 0));
		LifeForm l2 = new MockLifeForm("L", 10);
		e.addLifeForm(l2, 0, 2);
		assertEquals(l2, e.getLifeForm(0, 2));
		assertEquals(10, e.getDistance(l1,l2));
	}
	
	@Test
	public void testLifeFormsRangeOnSameColumn() throws EnvironmentException
	{
		Environment.clearBoard();
		Environment.setupWorld(4,4);
		Environment e = Environment.getWorld();
		LifeForm l2 = new MockLifeForm("L", 10);
		e.addLifeForm(l2, 0, 2);
		assertEquals(l2, e.getLifeForm(0, 2));
		LifeForm l3 = new MockLifeForm("L", 10);
		e.addLifeForm(l3, 3, 2);
		assertEquals(l3, e.getLifeForm(3, 2));
		assertEquals(15, e.getDistance(l2,l3));
	}
	
	@Test
	public void testLifeFormsRangeNotOnSameRowOrColumn() throws EnvironmentException
	{
		Environment.clearBoard();
		Environment.setupWorld(4,4);
		Environment e = Environment.getWorld();
		LifeForm l1 = new MockLifeForm("L", 10);
		e.addLifeForm(l1, 0, 0);
		assertEquals(l1, e.getLifeForm(0, 0));
		LifeForm l3 = new MockLifeForm("L", 10);
		e.addLifeForm(l3, 3, 2);
		assertEquals(l3, e.getLifeForm(3, 2));
		assertEquals(18, e.getDistance(l1,l3));
	}
	
	@Test(expected = EnvironmentException.class)
	public void testLifeFormsRangeException() throws EnvironmentException
	{
		Environment.clearBoard();
		Environment.setupWorld(4,4);
		Environment e = Environment.getWorld();
		LifeForm l1 = new MockLifeForm("L", 10);
	
		LifeForm l3 = new MockLifeForm("L", 10);
		e.addLifeForm(l3, 3, 2);
		assertEquals(l3, e.getLifeForm(3, 2));
		assertEquals(25, e.getDistance(l1,l3)); //will throw an exception here.
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
		boolean success = e.removeLifeForm(0, 1);
		assertTrue(success);
		assertNull(e.getLifeForm(0, 1));
	}
}
