package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBooster {

	@Test
	public void testInitialization() {
		MockWeapon w = new MockWeapon(10, 10, 1, 5);
		Booster b = new Booster(w);
		assertEquals(w, b.getWeapon());
	}

	@Test
	public void testBoosterDamage()
	{
		Weapon cg = new ChainGun(15,30,4,30);
		Booster b = new Booster(cg);
		assertEquals(3, b.fireWeapon(5));
	}
	
	@Test
	public void testBoosterDamageWithChainGunMuliFire()
	{
		Weapon cg = new ChainGun(15,30,4,30);
		Booster b = new Booster(cg);
		assertEquals(3, b.fireWeapon(5));
		assertEquals(2, cg.fireWeapon(5));
	}
}