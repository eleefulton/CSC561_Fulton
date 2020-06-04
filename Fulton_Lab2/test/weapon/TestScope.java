package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * test the functionality of the Scope Attachment
 * 
 * @author Ethan Fulton
 *
 */
public class TestScope
{

	@Test
	public void testInitialize()
	{
		Weapon w = new MockWeapon(10, 10, 1, 5);
		Scope s = new Scope(w);
		assertEquals(w, s.getWeapon());
	}

	@Test
	public void testScopePistolDamageAtDifferentRanges()
	{
		Weapon w = new Pistol(10, 10, 1, 5);
		Scope s = new Scope(w);
		assertEquals(15, s.fireWeapon(5));
		s.resetShotsFired();
		assertEquals(20, s.fireWeapon(3));
	}

	@Test
	public void testScopeScopePistolDamage()
	{
		Weapon w = new Pistol(10, 10, 1, 5);
		Scope s1 = new Scope(w);
		Scope s2 = new Scope(s1);
		assertEquals(22, s2.fireWeapon(5));
	}

}
