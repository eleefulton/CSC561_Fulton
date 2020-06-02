package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameplay.SimpleTimer;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */
public class TestLifeForm
{

	@Test
	public void testInitializeWithStrength()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		assertEquals(2, entity.attack());
	}

	@Test
	public void testTakeHitFromLifeForm()
	{
		LifeForm entity = new MockLifeForm("Bob", 40, 2);
		LifeForm entity2 = new MockLifeForm("Dave", 40, 2);
		entity.takeHit(entity2.attack());
		assertEquals(38, entity.getCurrentLifePoints());
	}

	@Test
	public void testNoDamageDealtWhenDead()
	{
		LifeForm entity = new MockLifeForm("Bob", 0, 2);
		assertEquals(0, entity.attack());
	}

	@Test
	public void testTrackPassageOfTime()
	{
		MockLifeForm entity = new MockLifeForm("Bob", 0, 2);
		SimpleTimer timer = new SimpleTimer(1);
		timer.addTimeObserver(entity);
		assertEquals(0, entity.getTime());
		timer.timeChanged();
		assertEquals(1, entity.getTime());
	}

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * When a LifeForm is created, it should know its name and how many life points
	 * it has.
	 */
	@Test
	public void testInitialization()
	{
		LifeForm entity;
		entity = new MockLifeForm("Bob", 40);
		assertEquals("Bob", entity.getName());
		assertEquals(40, entity.getCurrentLifePoints());
	}

	/**
	 * Check a LifeForm can take damage but not go below 0 life points
	 */
	@Test
	public void testTakeDamage()
	{
		LifeForm entity = new MockLifeForm("Bob", 40);
		entity.takeHit(30);
		assertEquals(10, entity.getCurrentLifePoints());
		entity.takeHit(10);
		assertEquals(0, entity.getCurrentLifePoints());
		entity.takeHit(1);
		assertEquals(0, entity.getCurrentLifePoints());
	}

}
