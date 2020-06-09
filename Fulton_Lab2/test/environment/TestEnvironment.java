package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * Tests the functionality provided by the Environment class
 *
 */
public class TestEnvironment
{
	@Test
	public void testInitializationSingleton()
	{
		//Environment e = new Environment(3, 3);
		Environment.reset();
		Environment.setupWorld(3,3);
		Environment e = Environment.getWorld();
		assertEquals(3, e.getNumberOfRows());
		assertEquals(3, e.getNumberOfColumns());
		assertEquals(9, e.getNumberOfCells());
		assertNull(e.getLifeForm(0, 0));
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
		Environment.reset();
		Environment.setupWorld(1,1);
		Environment e = Environment.getWorld();
		//e.setupWorld(1,1);
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
		Environment.reset();
		//Environment e = new Environment(2, 3);
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
		//Environment e = new Environment(2, 3);
		Environment.reset();
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
		//Environment e = new Environment(2, 3);
		Environment.reset();
		Environment.setupWorld(2,3);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
		e.removeLifeForm(0, 1);
		assertNull(e.getLifeForm(0, 1));
	}
}
