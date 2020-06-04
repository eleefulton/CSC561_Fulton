/**
 * 
 */
package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameplay.SimpleTimer;

/**
 * @author Ethan Fulton
 *
 */
public class TestGenericWeapon
{

	@Test
	public void testInitialization()
	{
		MockWeapon w = new MockWeapon(10, 10, 1, 5);
		assertEquals(10, w.getBaseDamage());
		assertEquals(10, w.getRange());
		assertEquals(1, w.getRateOfFire());
		assertEquals(5, w.getMaxAmmo());
	}

	@Test
	public void testFireAndReloadWeapon()
	{
		MockWeapon w = new MockWeapon(10, 10, 1, 5);
		assertEquals(10, w.fireWeapon(5));
		assertEquals(4, w.getRemainingAmmo());
		w.Reload();
		assertEquals(5, w.getRemainingAmmo());
		w.setRemainingAmmo(0);
		w.Reload();
		assertEquals(5, w.getRemainingAmmo());
	}

	@Test
	public void testRateOfFire()
	{
		SimpleTimer t = new SimpleTimer(1);
		MockWeapon w = new MockWeapon(10, 10, 1, 5);
		t.addTimeObserver(w);
		assertEquals(10, w.fireWeapon(5));
		assertEquals(1, w.getShotsFired());
		assertEquals(0, w.fireWeapon(5));
		t.timeChanged();
		assertEquals(10, w.fireWeapon(5));
	}

	@Test
	public void testOutOfRange()
	{
		MockWeapon w = new MockWeapon(10, 10, 1, 5);
		assertEquals(0, w.fireWeapon(11));
		assertEquals(4, w.getRemainingAmmo());

	}

}

class MockWeapon extends GenericWeapon
{

	public MockWeapon(int bd, int mr, int rof, int ma)
	{
		super(bd, mr, rof, ma);
	}

}
