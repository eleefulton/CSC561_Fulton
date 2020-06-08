package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.NegativeDistanceException;

/**
 * Test functionality of pistol
 * 
 * @author Ethan Fulton
 *
 */
public class TestPistol
{

	@Test
	public void testInitalize()
	{
		Pistol p = new Pistol(10, 5, 1, 5);
		assertEquals(10, p.getBaseDamage());
		assertEquals(5, p.getRange());
		assertEquals(1, p.getRateOfFire());
		assertEquals(5, p.getMaxAmmo());
		assertEquals(0, p.getShotsFired());
	}

	@Test
	public void testDamageAtDifferentRanges()
	{
		Pistol p = new Pistol(10, 5, 1, 5);
		assertEquals(10, p.fireWeapon(5));
		p.resetShotsFired();
		assertEquals(14, p.fireWeapon(3));
		p.resetShotsFired();
		assertEquals(0, p.fireWeapon(6));
	}

	@Test(expected = NegativeDistanceException.class)
	public void testFireWeaponEgdeCases()
	{
		Pistol p = new Pistol(10, 5, 1, 5);
		assertEquals(0, p.fireWeapon(-1)); // negative distance it will throw an exception
	}

	@Test
	public void testFirePistolOutOfAmmo()
	{
		Pistol p = new Pistol(10, 5, 1, 5);
		p.setRemainingAmmo(0);
		assertEquals(0, p.fireWeapon(5));
	}

}
