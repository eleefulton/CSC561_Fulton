package gameplay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;

/**
 * tests for attack command
 * 
 * @author Ethan Fulton
 *
 */
public class TestAttackCommand
{

	@Test
	public void test() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 0);
		LifeForm l2 = new Alien("Jim", 10);
		l2.changeDirection(LifeForm.NORTH);
		e.addLifeForm(l1, 0, 0);
		e.addLifeForm(l2, 1, 0);
		AttackCommand ac = new AttackCommand();
		ac.execute(1, 0);
		assertEquals(0, l1.getCurrentLifePoints());
	}

}
