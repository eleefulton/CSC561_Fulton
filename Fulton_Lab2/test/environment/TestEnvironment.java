package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import exceptions.EnvironmentExistsException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * Tests the functionality provided by the Environment class
 *
 */
public class TestEnvironment
{

	/**
	 * begin tests for Singleton Pattern
	 * 
	 * @throws EnvironmentExistsException
	 */
	@Test
	public void testInitializeSingleton() throws EnvironmentExistsException
	{
		Environment.resetEnvironment();
		Environment.setupWorld(1, 2);
		Environment e = Environment.getWorld();
		assertEquals(2, e.getNumberOfColumns());
		assertEquals(1, e.getNumberOfRows());
		Environment e2 = Environment.getWorld();
		assertEquals(e, e2);
	}

	@Test
	public void testGetEnvironmentBeforeSetup()
	{
		Environment.resetEnvironment();
		Environment e = Environment.getWorld();
		assertNull(e.getWorld());
	}

	@Test(expected = EnvironmentExistsException.class)
	public void testEnvironmentExistsBeforeSetup() throws EnvironmentExistsException
	{
		Environment.resetEnvironment();
		Environment.setupWorld(1, 2);
		Environment e = Environment.getWorld();
		Environment.setupWorld(2, 3);
	}

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * Tests a 1x1 environment can be created and populated with a single cell
	 * 
	 * @throws EnvironmentExistsException
	 */
	@Test
	public void testInitializationSingleCell() throws EnvironmentExistsException
	{
		Environment.resetEnvironment();
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
	 * @throws EnvironmentExistsException
	 */
	@Test
	public void testAddLifeForm() throws EnvironmentExistsException
	{
		Environment.resetEnvironment();
		Environment.setupWorld(3, 2);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
	}

	/**
	 * Tests that a LifeForm cannot be added to a cell out of bounds of the
	 * Environment
	 * 
	 * @throws EnvironmentExistsException
	 */
	@Test
	public void testAddLifeFormOffGrid() throws EnvironmentExistsException
	{
		Environment.resetEnvironment();
		Environment.setupWorld(3, 2);
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
	 * @throws EnvironmentExistsException
	 */
	@Test
	public void testRemoveLifeForm() throws EnvironmentExistsException
	{
		Environment.resetEnvironment();
		Environment.setupWorld(3, 2);
		Environment e = Environment.getWorld();
		LifeForm l = new MockLifeForm("L", 10);
		e.addLifeForm(l, 0, 1);
		assertEquals(l, e.getLifeForm(0, 1));
		e.removeLifeForm(0, 1);
		assertNull(e.getLifeForm(0, 1));
	}
}
