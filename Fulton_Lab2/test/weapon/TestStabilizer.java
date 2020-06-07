package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

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
		st.resetShotsFired();
		assertEquals(62, st.fireWeapon(3));
	}

	@Test
	public void testPlasmaCannonStabilizerStabilzerDamage()
	{
		Weapon w = new PlasmaCannon(50, 20, 1, 4);
		Stabilizer st1 = new Stabilizer(w);
		Stabilizer st2 = new Stabilizer(st1);
		assertEquals(77, st2.fireWeapon(5));
	}

}
