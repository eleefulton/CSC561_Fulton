package gameplay;


import static org.junit.Assert.assertEquals;


import javax.swing.JOptionPane;

import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Human;
import lifeform.LifeForm;

/**
 * This will test the functionalities of the Invoker class.
 * @author Moumouni Noma
 */
public class TestInvoker 
{
	/* Commands slots
	 * 1 - Reload
	 * 2 - Attack
	 * 3 - Acquire
	 * 4 - Drop
	 * 5 - Move
	 * 6 - North
	 * 7 - South
	 * 8 - East
	 * 9 - West
	*/
	@Test
	public void testTurnNorthCommand() throws ExistingWorldException 
	{
		Invoker remote = new Invoker();
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		entity.changeDirection(LifeForm.SOUTH);
		assertEquals(LifeForm.SOUTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,6);
		assertEquals(cmd, remote.getCommand(6));
		remote.jbtNorth.doClick();
		assertEquals("North",remote.getClickName());
		assertEquals(LifeForm.NORTH, remote.getLifeForm().getCurrentDirection());
		
	}
	
	@Test
	public void testTurnEastCommand() throws ExistingWorldException 
	{
		Invoker remote = new Invoker();
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,8);
		assertEquals(cmd, remote.getCommand(8));
		remote.jbtEast.doClick();
		assertEquals("East",remote.getClickName());
		assertEquals(LifeForm.EAST, remote.getLifeForm().getCurrentDirection());	
	}
	
	@Test
	public void testTurnSouthCommand() throws ExistingWorldException 
	{
		Invoker remote = new Invoker();
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,7);
		assertEquals(cmd, remote.getCommand(7));
		remote.jbtSouth.doClick();
		assertEquals("South",remote.getClickName());
		assertEquals(LifeForm.SOUTH, remote.getLifeForm().getCurrentDirection());	
	}
	
	@Test
	public void testTurnWestCommand() throws ExistingWorldException 
	{
		Invoker remote = new Invoker();
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,9);
		assertEquals(cmd, remote.getCommand(9));
		remote.jbtWest.doClick();
		assertEquals("West",remote.getClickName());
		assertEquals(LifeForm.WEST, remote.getLifeForm().getCurrentDirection());	
	}

	@Test
	public void testInitialization() 
	{
		Invoker remote = new Invoker();
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Does the Remote Control look right?"));
	}
	
	@Test
	public void testSetCommand()
	{
		Invoker remote = new Invoker();
		//remote.setBounds(200, 200, 400, 400);
		Command cmd = new MoveCommand();
		remote.setCommand(cmd, 2);
		assertEquals(cmd, remote.getCommand(2));
		String name = "Test";
		MockCommand cmd2 = new MockCommand(name);
		remote.setCommand(cmd2, 1);
		assertEquals(name, cmd2.getName());	
	}
	
	@Test
	public void testSetLifeForm() throws ExistingWorldException
	{
		Invoker remote = new Invoker();
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		assertEquals(0, remote.getRow());
		assertEquals(0, remote.getCol());
	}
	
	@Test
	public void testButtonClick() throws ExistingWorldException
	{
		Invoker remote = new Invoker();
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		//test that the button click works
		remote.jbtReload.doClick();
		assertEquals("Reload",remote.getClickName());
		remote.jbtAttack.doClick();
		assertEquals("Attack",remote.getClickName());
		remote.jbtAcquire.doClick();
		assertEquals("Acquire",remote.getClickName());
		remote.jbtDrop.doClick();
		assertEquals("Drop",remote.getClickName());
		remote.jbtMovePlayer.doClick();
		assertEquals("Move",remote.getClickName());
		remote.jbtNorth.doClick();
		assertEquals("North",remote.getClickName());
		remote.jbtSouth.doClick();
		assertEquals("South",remote.getClickName());
		remote.jbtEast.doClick();
		assertEquals("East",remote.getClickName());
		remote.jbtWest.doClick();
		assertEquals("West",remote.getClickName());
	}

}
