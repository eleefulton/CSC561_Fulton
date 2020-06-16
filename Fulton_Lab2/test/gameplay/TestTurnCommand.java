package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Human;
import lifeform.LifeForm;

public class TestTurnCommand 
{

	@Test
	public void testTurnNorthCommand() throws ExistingWorldException 
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		l1.changeDirection(LifeForm.SOUTH);
		e.addLifeForm(l1, 3, 0);
		TurnCommand tc = new TurnCommand(LifeForm.NORTH);
		tc.execute(3,0);
		assertEquals(LifeForm.NORTH, l1.getCurrentDirection());

	}
	
	@Test
	public void testTurnSouthCommand() throws ExistingWorldException 
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 3, 0);
		TurnCommand tc = new TurnCommand(LifeForm.SOUTH);
		tc.execute(3,0);
		assertEquals(LifeForm.SOUTH, l1.getCurrentDirection());

	}
	
	@Test
	public void testTurnEastCommand() throws ExistingWorldException 
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 3, 0);
		TurnCommand tc = new TurnCommand(LifeForm.EAST);
		tc.execute(3,0);
		assertEquals(LifeForm.EAST, l1.getCurrentDirection());

	}
	
	@Test
	public void testTurnWestCommand() throws ExistingWorldException 
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 3, 0);
		TurnCommand tc = new TurnCommand(LifeForm.WEST);
		tc.execute(3,0);
		assertEquals(LifeForm.WEST, l1.getCurrentDirection());

	}

}
