package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestHuman
{

	@Test
	public void testDefaultAttack()
	{
		Human h1 = new Human("Bob", 40, 10);
		assertEquals(5, h1.attack());
	}

	@Test
	public void testTakeDamageNoArmorFromAttack()
	{
		Human h1 = new Human("Bob", 40, 0);
		LifeForm entity = new MockLifeForm("Fred", 40, 10);
		h1.takeHit(entity.attack());
		assertEquals(30, h1.getCurrentLifePoints());
	}

	@Test
	public void testTakeDamageWithArmor()
	{
		Human h1 = new Human("Bob", 40, 10);
		h1.takeHit(1);
		assertEquals(40, h1.getCurrentLifePoints());
		h1.takeHit(10);
		assertEquals(40, h1.getCurrentLifePoints());
		h1.takeHit(11);
		assertEquals(39, h1.getCurrentLifePoints());
	}

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * Check that a human can be initialized with an armor value no less than 0
	 */
	@Test
	public void testInitalization()
	{
		Human human1 = new Human("Bob", 40, 10);
		assertEquals(10, human1.getArmorPoints());
		Human human2 = new Human("Dob", 40, -1);
		assertEquals(0, human2.getArmorPoints());
	}

	/**
	 * Check that a human's armor can be updated
	 */
	@Test
	public void testSetArmorPoints()
	{
		Human human = new Human("Bob", 40, 10);
		assertEquals(10, human.getArmorPoints());
		human.setArmorPoints(9);
		assertEquals(9, human.getArmorPoints());
	}

}
