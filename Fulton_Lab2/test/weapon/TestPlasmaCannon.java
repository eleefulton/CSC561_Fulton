package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This will hold the testing of the functionalities of the Plasma Cannon Weapon.
 * @author Moumouni Noma
 */
public class TestPlasmaCannon {

	@Test
	public void testInitialization() 
	{
		PlasmaCannon pc = new PlasmaCannon(50,20,1,4);
		assertEquals(50, pc.getBaseDamage());
		assertEquals(20, pc.getRange());
		assertEquals(1, pc.getRateOfFire());
		assertEquals(4, pc.getMaxAmmo());
	}
	
	@Test
	public void testFireWeaponInRange() 
	{
		PlasmaCannon pc = new PlasmaCannon(50,20,1,4);
		assertEquals(50, pc.fireWeapon(10)); // distance in range
		assertEquals(3, pc.getRemainingAmmo());
	}
	
	@Test
	public void testFireWeaponOutOfRange() 
	{
		PlasmaCannon pc = new PlasmaCannon(50,20,1,4);
		assertEquals(0, pc.fireWeapon(25)); // distance out of range
		assertEquals(3, pc.getRemainingAmmo());
	}
	
	@Test
	public void testFireWeaponEgdeCases() 
	{
		PlasmaCannon pc = new PlasmaCannon(50,20,1,4);
		assertEquals(0, pc.fireWeapon(-1)); // negative distance
		assertEquals(4, pc.getRemainingAmmo());
	}

}
