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
		assertEquals(3, b.fireWeapon(5));
	}
	

	@Test
	public void testChainGunPowerBoosterStabilzerDamage()
	{
		Weapon cg = new ChainGun(15,30,4,30);
		Scope s = new Scope(cg);
		Booster b = new Booster(s);
		assertEquals(29, b.fireWeapon(30));
	}
	
	@Test
	public void testChainGunPowerBoosterScopeDamage()
	{
		Weapon cg = new ChainGun(15,30,4,30);
		Booster b = new Booster(cg);
		Booster b1 = new Booster(b);
		assertEquals(57, b1.fireWeapon(30));
	}
	
	@Test
	public void testChainGunBoosterBoosterDamage()
	{
		Weapon cg = new ChainGun(15,30,4,30);
		Booster b = new Booster(cg);
		assertEquals(1, b.getNumAttachments());
		Booster b1 = new Booster(b);
		assertEquals(2, b1.getNumAttachments());
	}
	
	@Test
	public void testBoosterAttachmentNum()
	{
		Weapon cg = new ChainGun(15,30,4,30);
		Booster b = new Booster(cg);
		assertEquals(1, b.getNumAttachments());
		Booster b1 = new Booster(b);
		assertEquals(2, b1.getNumAttachments());
	}
}