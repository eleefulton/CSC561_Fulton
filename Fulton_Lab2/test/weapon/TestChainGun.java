package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestChainGun 
{

	@Test
	public void testInitialization() 
	{
		ChainGun cg = new ChainGun(15,30,4,30);
		assertEquals(15, cg.getBaseDamage());
		assertEquals(30, cg.getMaxAmmo());
		assertEquals(4, cg.getRateOfFire());
	}
	
	@Test
	public void testFireWeapon()
	{
		ChainGun cg = new ChainGun(15,30,4,30);
		assertEquals(15, cg.fireWeapon(30));
		assertEquals(30, cg.fireWeapon(60));
	}
	
	@Test
	public void testFireWeaponInvalid()
	{
		ChainGun cg = new ChainGun(15,30,4,30);
		assertEquals(0, cg.fireWeapon(-1));
	}

}
