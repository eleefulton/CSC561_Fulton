package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * test the functionality of the Scope Attachment in combination with other
 * attachments and weapons
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

	@Test
	public void testPistolStabilizerScope()
	{
		Weapon w = new MockWeapon(10, 10, 1, 5);
		Stabilizer sb = new Stabilizer(w);
		Scope sc = new Scope(sb);
		assertEquals(18, sc.fireWeapon(5));
		sc.resetShotsFired();
		sc.setRemainingAmmo(1);
		assertEquals(1, sc.getRemainingAmmo());
		sc.fireWeapon(5);
		assertEquals(5, sc.getRemainingAmmo());
	}

	@Test
	public void testPistolPowerBoosterScope()
	{
		Weapon w = new Pistol(10, 10, 1, 5);
		PowerBooster pb = new PowerBooster(w);
		Scope sc = new Scope(pb);
		assertEquals(30, sc.fireWeapon(5));
		sc.resetShotsFired();
		assertEquals(27, sc.fireWeapon(5));
	}

}
