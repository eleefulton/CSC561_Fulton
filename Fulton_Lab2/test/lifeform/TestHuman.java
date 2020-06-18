package lifeform;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.ExistingWorldException;

public class TestHuman
{
	private static Environment e;
	
	@BeforeClass
    public static void setup() throws ExistingWorldException 
	{
		Environment.clearBoard();
		Environment.setupWorld(4,4);
        e = Environment.getWorld();
    }
	
	@AfterClass
    public static void CleanUp() 
	{
		Environment.clearBoard();
    }

	@Test
	public void testDefaultAttack() throws EnvironmentException
	{
		Human h1 = new Human("Bob", 40, 10);
		e.addLifeForm(h1, 0, 0);
		LifeForm fred = new MockLifeForm("Fred", 30, 2);
		e.addLifeForm(fred, 0, 1);//5 feet away
		assertEquals(5, h1.attack(fred));
		
		//Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
	}

	@Test
	public void testTakeDamageNoArmorFromAttack() throws EnvironmentException
	{
		Human h1 = new Human("Bob", 40, 0);
		LifeForm entity = new MockLifeForm("Fred", 40, 10);
		e.addLifeForm(h1, 0, 0);
		e.addLifeForm(entity, 0, 1);//5 feet away
		h1.takeHit(entity.attack(h1));
		assertEquals(30, h1.getCurrentLifePoints());
		
		//Clears the slots since the environment is unique.
		e.removeLifeForm(0, 0);
		e.removeLifeForm(0, 1);
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
