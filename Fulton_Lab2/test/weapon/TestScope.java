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
		weapon.Scope s = new weapon.Scope(w);
		assertEquals(w, s.getWeapon());
	}

	@Test
	public void testScopeDamage()
	{
		Weapon w = new MockWeapon(10, 10, 1, 5);
		weapon.Scope s = new weapon.Scope(w);
		assertEquals(15, s.fireWeapon(5));
	}

}
