package gameplay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Human;
import lifeform.LifeForm;

public class TestMoveCommand
{

	@Test
	public void testMoveLifeFormNorth() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 3, 0);
		Command m1 = new MoveCommand();
		m1.execute(3, 0);
		assertEquals(l1, e.getLifeForm(0, 0));
	}

	@Test
	public void testMoveLifeFormSouth() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 0, 0);
		l1.changeDirection(LifeForm.SOUTH);
		Command m1 = new MoveCommand();
		m1.execute(0, 0);
		assertEquals(l1, e.getLifeForm(3, 0));
	}

	@Test
	public void testMoveLifeFormEast() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 0, 0);
		l1.changeDirection(LifeForm.EAST);
		Command m1 = new MoveCommand();
		m1.execute(0, 0);
		assertEquals(l1, e.getLifeForm(0, 2));
	}

	@Test
	public void testMoveLifeFormWest() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 0, 2);
		l1.changeDirection(LifeForm.WEST);
		Command m1 = new MoveCommand();
		m1.execute(0, 2);
		assertEquals(l1, e.getLifeForm(0, 0));
	}

}
