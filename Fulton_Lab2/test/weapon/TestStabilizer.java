package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the functionalities of the Stabilizer Attachment.
 * @author Moumouni Noma
 */
public class TestStabilizer {

	@Test
	public void testInitialize()
	{
		Weapon w = new MockWeapon(50, 20, 1, 4);
		Stabilizer st = new Stabilizer(w);
		assertEquals(w, st.getWeapon());
	}
	
	@Test
	public void testPlasmaCannonStabilizerDamageAtDifferentRanges()
	{
		Weapon w = new PlasmaCannon(50, 20, 1, 4);
		Stabilizer st = new Stabilizer(w);
		assertEquals(62, st.fireWeapon(5));
		assertEquals(3, st.getRemainingAmmo());
		
		st.resetShotsFired();
		assertEquals(46, st.fireWeapon(3));
		assertEquals(2, st.getRemainingAmmo());
		
		st.resetShotsFired();
		assertEquals(31, st.fireWeapon(5));
		assertEquals(1, st.getRemainingAmmo());
		
		st.resetShotsFired();
		assertEquals(15, st.fireWeapon(3)); //the weapon reloads here
		assertEquals(4, st.getRemainingAmmo());
		
		st.resetShotsFired();
		assertEquals(62, st.fireWeapon(5));
		assertEquals(3, st.getRemainingAmmo());
		
	}

	@Test
	public void testPlasmaCannonScopeStabilzerDamage()
	{
		Weapon w = new PlasmaCannon(50, 20, 1, 4);
		Scope sc = new Scope(w);
		Stabilizer st = new Stabilizer(sc);
		assertEquals(108, st.fireWeapon(5));
	}
	
	@Test
	public void testPlasmaCannonStabilizerStabilzerDamage()
	{
		Weapon w = new PlasmaCannon(50, 20, 1, 4);
		Stabilizer st1 = new Stabilizer(w);
		Stabilizer st2 = new Stabilizer(st1);
		assertEquals(77, st2.fireWeapon(5));
	}
	
	@Test
	public void testPlasmaCannonPowerBoosterStabilzerDamage()
	{
		Weapon w = new PlasmaCannon(50, 20, 1, 4);
		PowerBooster pb = new PowerBooster(w);
		Stabilizer st = new Stabilizer(pb);
		assertEquals(77, st.fireWeapon(5));
	}
	
	@Test
	public void testPlasmaCannonPowerBoosterStabilzerMoreThan3Damage()
	{
		Weapon w = new PlasmaCannon(50, 20, 1, 4);
		PowerBooster pb = new PowerBooster(w);
		Stabilizer st = new Stabilizer(pb);
		Stabilizer st1 = new Stabilizer(st);
		assertNull(st1.myWeapon);
	}

}
