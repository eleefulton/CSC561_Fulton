package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * The test cases for the Cell class.
 *
 */
public class TestCell
{
	@Test
	public void testAddWeapons()
	{
		Weapon weap1 = new PlasmaCannon(20,10,1,4);
		Weapon weap2 = new PlasmaCannon(25,10,1,4);
		Cell cell = new Cell();
		// The cell is empty so this should work.
		boolean success = cell.addWeapon1(weap1);
		assertTrue(success);
		assertEquals(weap1, cell.getWeapon1());
		
		success = cell.addWeapon2(weap2);
		assertTrue(success);
		assertEquals(weap2, cell.getWeapon2());
	}
	
	@Test
	public void testRemoveWeapons()
	{
		Weapon weap1 = new PlasmaCannon(20,10,1,4);
		Weapon weap2 = new PlasmaCannon(25,10,1,4);
		Cell cell = new Cell();
		// The cell is empty so this should work.
		boolean success = cell.addWeapon1(weap1);
		assertTrue(success);
		assertEquals(weap1, cell.getWeapon1());
		success = cell.addWeapon2(weap2);
		assertTrue(success);
		assertEquals(weap2, cell.getWeapon2());
		
		success = cell.removeWeapon1();
		assertTrue(success);
		assertNull(cell.getWeapon1());
		success = cell.removeWeapon2();
		assertTrue(success);
		assertNull(cell.getWeapon2());
	}
	
	@Test
	public void testCannotAddMoreThanOneWeaponPerSlot()
	{
		Weapon weap1 = new PlasmaCannon(20,10,1,4);
		Weapon weap2 = new PlasmaCannon(25,10,1,4);
		Weapon weap3 = new PlasmaCannon(10,5,1,4);
		Cell cell = new Cell();
		// The cell is empty so this should work.
		boolean success = cell.addWeapon1(weap1);
		assertTrue(success);
		assertEquals(weap1, cell.getWeapon1());
		success = cell.addWeapon2(weap2);
		assertTrue(success);
		assertEquals(weap2, cell.getWeapon2());
		
		success = cell.addWeapon1(weap3);
		assertFalse(success);                  //There is already a weapon in the slot.
		assertEquals(weap1, cell.getWeapon1());
		success = cell.addWeapon2(weap3);
		assertFalse(success);                   //There is already a weapon in the slot.
		assertEquals(weap2, cell.getWeapon2());
	}
	
	
	/*
	 * Start Section for Decorator Pattern Tests
	 */ 

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * At initialization, the Cell should be empty and not contain a LifeForm or weapons.
	 */
	@Test
	public void testInitialization()
	{
		Cell cell = new Cell();
		assertNull(cell.getLifeForm());
		assertNull(cell.getWeapon1());
		assertNull(cell.getWeapon2());
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