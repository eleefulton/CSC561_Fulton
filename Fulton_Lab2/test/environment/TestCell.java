package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.GenericWeapon;

/**
 * The test cases for the Cell class
 *
 */
public class TestCell
{

	@Test
	public void testAddTwoWeapons()
	{
		Cell cell = new Cell();
		MockWeapon w = new MockWeapon(5,5,5,5);
		MockWeapon w1 = new MockWeapon(5,5,5,5);
		cell.addWeapon(w);
		cell.addWeapon(w1);
		assertEquals(w, cell.getWeapons()[0]);
		assertEquals(w1, cell.getWeapons()[1]);
	}
	
	@Test
	public void testRemoveTwoWeapons()
	{
		Cell cell = new Cell();
		MockWeapon w = new MockWeapon(5,5,5,5);
		MockWeapon w1 = new MockWeapon(5,5,5,5);
		cell.addWeapon(w);
		cell.addWeapon(w1);
		cell.removeWeapon(w);
		cell.removeWeapon(w1);
		assertNull(cell.getWeapons()[0]);
		assertNull(cell.getWeapons()[1]);
	}
	
	
	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * At initialization, the Cell should be empty and not contain a LifeForm.
	 */
	@Test
	public void testInitialization()
	{
		Cell cell = new Cell();
		assertNull(cell.getLifeForm());
		assertNull(cell.getWeapons()[0]);
		assertNull(cell.getWeapons()[1]);
	}

	/**
	 * Checks to see if we change the LifeForm held by the Cell that getLifeForm
	 * properly responds to this change.
	 */
	@Test
	public void testSetLifeForm()
	{
		LifeForm bob = new MockLifeForm("Bob", 40);
		LifeForm fred = new MockLifeForm("Fred", 40);
		Cell cell = new Cell();
		// The cell is empty so this should work.
		boolean success = cell.addLifeForm(bob);
		assertTrue(success);
		assertEquals(bob, cell.getLifeForm());
		// The cell is not empty so this should fail.
		success = cell.addLifeForm(fred);
		assertFalse(success);
		assertEquals(bob, cell.getLifeForm());
	}

	/**
	 * Checks if a Cell is empty after a LifeForm is removed
	 */
	@Test
	public void testRemoveLifeForm()
	{
		LifeForm bob = new MockLifeForm("Bob", 40);
		Cell cell = new Cell();
		cell.addLifeForm(bob);
		assertEquals(bob, cell.getLifeForm());
		cell.removeLifeForm();
		assertNull(cell.getLifeForm());
	}
}

class MockWeapon extends GenericWeapon
{

	public MockWeapon(int bd, int mr, int rof, int ma)
	{
		super(bd, mr, rof, ma);
	}

}
