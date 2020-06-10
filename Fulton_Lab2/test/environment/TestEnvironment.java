package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import exceptions.EnvironmentConstructorException;
import exceptions.EnvironmentException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * Tests the functionality provided by the Environment class
 *
 */
public class TestEnvironment
{
	/**
	 * Reset the world before each test
	 */
	@Before
	public void resetWorld()
	{
		Environment.resetWorld();
	}

	/**
	 * begin tests for Singleton pattern
	 */
	@Test
	public void testNewWorld() throws EnvironmentConstructorException
	{
		Environment.createWorld(1,1);
		assertEquals(1, Environment.getWorld().getNumberOfCells());
		
	}
	
	@Test(expected = EnvironmentConstructorException.class)
	public void  testCreateWorld() throws EnvironmentConstructorException
	{
		Environment.createWorld(1,1);
		Environment.createWorld(2,2);
	}
	
	@Test
	public void testAddWeapons()
	{
		Environment e = new Environment(2, 3);
		MockWeapon w = new MockWeapon(5,5,5,5);
		MockWeapon w1 = new MockWeapon(5,5,5,5);
		e.addWeapon(w, 0, 1);
		e.addWeapon(w1, 0, 1);
		assertEquals(w, e.getWeapon(0, 1)[0]);
		assertEquals(w1, e.getWeapon(0, 1)[1]);
	}
	
	@Test
	public void testRemoveWeapons()
	{
		Environment e = new Environment(2, 3);
		MockWeapon w = new MockWeapon(5,5,5,5);
		MockWeapon w1 = new MockWeapon(5,5,5,5);
		e.addWeapon(w, 0, 1);
		e.addWeapon(w1, 0, 1);
		e.removeWeapon(w, 0, 1);
		e.removeWeapon(w1, 0, 1);
		assertNull(e.getWeapon(0, 1)[0]);
		assertNull(e.getWeapon(0, 1)[1]);
	}
	
	@Test(expected = EnvironmentException.class)
	public void testDistanceBetweenLifeForms() throws EnvironmentException
	{
		Environment e = new Environment(5, 5);
		LifeForm l1 = new MockLifeForm("L1", 10);
		LifeForm l2 = new MockLifeForm("L2", 10);
		e.addLifeForm(l1, 0, 0);
		e.addLifeForm(l2, 0, 2);
		assertEquals(10, e.getDistance(l1, l2));
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 2);
		
		e.addLifeForm(l1, 0, 2);
		e.addLifeForm(l2, 3, 2);
		assertEquals(15, e.getDistance(l1, l2));
		e.removeLifeForm(0, 2);
		e.removeLifeForm(3, 2);
		
		e.addLifeForm(l1, 0, 0);
		e.addLifeForm(l2, 3, 2);
		assertEquals(18, e.getDistance(l1, l2));
		e.removeLifeForm(3, 2);
		
		e.getDistance(l1, l2); //should throw exception
	}
	
	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * Tests a 1x1 environment can be created and populated with a single cell
	 */
	@Test
	public void testInitializationSingleCell() 
	{	
		Environment e = new Environment(1, 1);
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
		Environment e = new Environment(2, 3);
		LifeForm l = new MockLifeForm("L", 10);
		MockWeapon w = new MockWeapon(5,5,5,5);
		MockWeapon w1 = new MockWeapon(5,5,5,5);
		e.addLifeForm(l, 0, 1);
		e.addWeapon(w, 0, 1);
		e.addWeapon(w1, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
		assertEquals(w, e.getWeapon(0, 1)[0]);
		assertEquals(w1, e.getWeapon(0, 1)[1]);
	}

	/**
	 * Tests that a LifeForm cannot be added to a cell out of bounds of the
	 * Environment
	 */
	@Test
	public void testAddLifeFormAndWeaponsOffGrid()
	{
		Environment e = new Environment(2, 3);
		LifeForm l = new MockLifeForm("L", 10);
		MockWeapon w = new MockWeapon(5,5,5,5);
		MockWeapon w1 = new MockWeapon(5,5,5,5);
		e.addLifeForm(l, -1, 1);
		assertEquals(null, e.getLifeForm(-1, 1));
		e.addLifeForm(l, 0, 3);
		assertEquals(null, e.getLifeForm(0, 3));
		assertFalse(e.addWeapon(w, 3, 4));
		assertFalse(e.addWeapon(w1, -2, -1));
	}

	/**
	 * Tests that a LifeForm can be removed from a specified location
	 */
	@Test
	public void testRemoveLifeForm()
	{
		Environment e = new Environment(2, 3);
		LifeForm l = new MockLifeForm("L", 10);
		MockWeapon w = new MockWeapon(5,5,5,5);
		MockWeapon w1 = new MockWeapon(5,5,5,5);
		e.addLifeForm(l, 0, 1);
		e.addWeapon(w, 0, 1);
		e.addWeapon(w1, 0, 1);
		
		e.removeLifeForm(0, 1);
		e.removeWeapon(w, 0, 1);
		e.removeWeapon(w1, 0, 1);
		assertNull(e.getLifeForm(0, 1));
		assertNull(e.getWeapon(0, 1)[0]);
		assertNull(e.getWeapon(0, 1)[1]);
	}
	
	
}
