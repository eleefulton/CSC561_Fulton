package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * The test cases for the Cell class
 *
 */
public class TestCell
{

	/**
	 * begin tests for Singleton Pattern
	 */

	@Test
	public void testAddRemoveWeapons()
	{
		Cell cell = new Cell();
		Weapon w1 = new Pistol(10, 10, 1, 5);
		Weapon w2 = new ChainGun(10, 10, 1, 5);
		Weapon w3 = new PlasmaCannon(10, 10, 1, 5);
		cell.addWeapon(w1);
		cell.addWeapon(w2);
		assertEquals(w1, cell.getWeaponOne());
		assertEquals(w2, cell.getWeaponTwo());
		cell.removeWeapon(w1);
		assertEquals(null, cell.getWeaponOne());
		cell.addWeapon(w3);
		assertEquals(w3, cell.getWeaponOne());
		assertNull(cell.addWeapon(w1));
		cell.removeWeapon(w2);
		assertEquals(null, cell.getWeaponTwo());
		cell.addWeapon(w1);
		assertEquals(w1, cell.getWeaponTwo());
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
		assertNull(cell.getWeaponOne());
		assertNull(cell.getWeaponTwo());
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