package weapon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * test basic shared functionality of attachments, i.e. they use the base stats
 * from the weapon they decorate
 * 
 * @author Ethan Fulton
 *
 */
public class TestAttachment
{

	@Test
	public void testInitialization()
	{
		Weapon w = new MockWeapon(10, 10, 1, 5);
		Attachment a = new MockAttachment(w);
		assertEquals(10, a.getBaseDamage());
		assertEquals(10, a.getRange());
		assertEquals(1, a.getRateOfFire());
		assertEquals(5, a.getMaxAmmo());
	}

	@Test
	public void testFireAttachment()
	{
		Weapon w = new MockWeapon(10, 10, 1, 5);
		Attachment a = new MockAttachment(w);
		assertEquals(10, a.fireWeapon(10));
		assertEquals(1, a.getShotsFired());
		assertEquals(4, a.getRemainingAmmo());
	}

}

class MockAttachment extends Attachment
{

	public MockAttachment(Weapon w)
	{
		super(w);
	}

	@Override
	public void updateTime(int time)
	{
		// TODO Auto-generated method stub

	}

}