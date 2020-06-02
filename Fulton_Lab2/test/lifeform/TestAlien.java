package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import exceptions.AlienConstructorException;
import gameplay.SimpleTimer;
import recovery.RecoveryLinear;

public class TestAlien
{

	@Test
	public void testRecoverOverTime() throws AlienConstructorException
	{
		RecoveryLinear rl = new RecoveryLinear(1);
		Alien alien1 = new Alien("alie", 40, rl, 2);
		SimpleTimer timer = new SimpleTimer();
		alien1.takeHit(30);
		timer.timeChanged();
		timer.addTimeObserver(alien1);
		assertEquals(1, timer.getRound());
		assertEquals(10, alien1.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(2, timer.getRound());
		assertEquals(11, alien1.getCurrentLifePoints());
		alien1.takeHit(11);
		timer.timeChanged();
		assertEquals(0, alien1.getCurrentLifePoints());
	}

	@Test
	public void testRecoveryAfterRemovedFromObservers() throws AlienConstructorException
	{
		RecoveryLinear rl = new RecoveryLinear(1);
		Alien alien1 = new Alien("alie", 40, rl, 2);
		SimpleTimer timer = new SimpleTimer();
		alien1.takeHit(30);
		timer.timeChanged();
		timer.addTimeObserver(alien1);
		assertEquals(1, timer.getRound());
		assertEquals(10, alien1.getCurrentLifePoints());
		timer.timeChanged();
		assertEquals(2, timer.getRound());
		assertEquals(11, alien1.getCurrentLifePoints());
		timer.removeTimeObserver(alien1);
		timer.timeChanged();
		assertEquals(3, timer.getRound());
		assertEquals(11, alien1.getCurrentLifePoints());
	}

	@Test(expected = AlienConstructorException.class)
	public void testInitalizeWithRecoveryRate() throws AlienConstructorException
	{
		RecoveryLinear rl = new RecoveryLinear(1);
		Alien alien1 = new Alien("alie", 40, rl, 1);
		assertEquals(1, alien1.getRecoveryRate());
		alien1.setRecoveryRate(5);
		assertEquals(5, alien1.getRecoveryRate());
		Alien alien2 = new Alien("balie", 40, rl, 0);
	}

	@Test(expected = AlienConstructorException.class)
	public void testInitalizeWithNegativeRecoveryRate() throws AlienConstructorException
	{
		RecoveryLinear rl = new RecoveryLinear(1);
		Alien alien2 = new Alien("balie", 40, rl, -1);
	}

	@Test
	public void testInitializeWithDefaultAttack()
	{
		Alien alien = new Alien("alie", 40);
		assertEquals(10, alien.attack());
	}

	@Test
	public void testTakeDamageFromAttack()
	{
		Alien alien = new Alien("Alie", 40);
		LifeForm entity = new MockLifeForm("Bob", 40, 10);
		alien.takeHit(entity.attack());
		assertEquals(30, alien.getCurrentLifePoints());
	}

	@Test
	public void testSetRecoveryRate()
	{
		Alien alien = new Alien("Alie", 40);
		alien.setRecoveryRate(1);
		assertEquals(1, alien.getRecoveryRate());
	}

	/**
	 * begin tests for Strategy Pattern
	 */

	/**
	 * check that an Alien can be initialized with only a name and max life points
	 */
	@Test
	public void testSimpleInitalization()
	{
		Alien alien = new Alien("alie", 40);
		assertEquals(40, alien.getCurrentLifePoints());
		assertEquals("alie", alien.getName());
	}

	/**
	 * check that an Alien can be initialized with a RecoveryLinear recovery
	 */
	@Test
	public void testRecoveryLinear()
	{
		RecoveryLinear rl = new RecoveryLinear(3);
		Alien alien = new Alien("alie", 40, rl);
		assertEquals(40, alien.getCurrentLifePoints());
		assertEquals("alie", alien.getName());
		alien.takeHit(10);
		assertEquals(30, alien.getCurrentLifePoints());
		alien.recover();
		assertEquals(33, alien.getCurrentLifePoints());
	}

}
