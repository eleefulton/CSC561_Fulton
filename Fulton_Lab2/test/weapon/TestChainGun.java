package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AlienConstructorException;
import exceptions.NegativeDistanceException;

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
	public void testFireWeaponDamage()
	{
		ChainGun cg = new ChainGun(15,30,4,30);
		assertEquals(12, cg.fireWeapon(25));
		assertEquals(15, cg.fireWeapon(30));
	}
	
	@Test
	public void testFireWeaponTooFar()
	{
		ChainGun cg = new ChainGun(15,30,4,30);
		assertEquals(0, cg.fireWeapon(31));
	}
	
	@Test (expected = NegativeDistanceException.class)
	public void testFireWeaponNegative() throws NegativeDistanceException 
	{
		ChainGun cg = new ChainGun(15,30,4,30);
		cg.fireWeapon(-1);
	}

}
