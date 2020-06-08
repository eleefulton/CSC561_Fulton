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
	
	@Test
	public void testBoosterAttachmentNum()
	{
		Weapon cg = new ChainGun(15,30,4,30);
		Booster b = new Booster(cg);
		assertEquals(1, cg.getNumAttachments());
		Booster b1 = new Booster(cg);
		assertEquals(2, cg.getNumAttachments());
		Booster b2 = new Booster(cg); //can't add more than 2 attachments
		assertEquals(2, cg.getNumAttachments());

	}
}