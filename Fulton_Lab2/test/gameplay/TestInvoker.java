package gameplay;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.ExistingWorldException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;
import weapon.PlasmaCannon;

/**
 * This will test the functionalities of the Invoker class.
 * @author Moumouni Noma
 */
public class TestInvoker 
{
	Environment e;
	private Invoker remote;

	@Before
	public void setup() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		e = Environment.getWorld();
		remote = new Invoker();
	}

	@After
	public void CleanUp()
	{
		Environment.clearBoard();
	}
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
	public void testTurnNorthCommand()
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		entity.changeDirection(LifeForm.SOUTH);
		assertEquals(LifeForm.SOUTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(LifeForm.NORTH);
		remote.setCommand(cmd, Command.NORTH);
		assertEquals(cmd, remote.getCommand(Command.NORTH));
		remote.jbtNorth.doClick();
		assertEquals("North",remote.getClickName());
		assertEquals(LifeForm.NORTH, remote.getLifeForm().getCurrentDirection());
		
	}
	
	@Test
	public void testTurnEastCommand()
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(LifeForm.EAST);
		remote.setCommand(cmd, Command.EAST);
		assertEquals(cmd, remote.getCommand(Command.EAST));
		remote.jbtEast.doClick();
		assertEquals("East",remote.getClickName());
		assertEquals(LifeForm.EAST, remote.getLifeForm().getCurrentDirection());	
	}
	
	@Test
	public void testTurnSouthCommand()
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(LifeForm.SOUTH);
		remote.setCommand(cmd, Command.SOUTH);
		assertEquals(cmd, remote.getCommand(Command.SOUTH));
		remote.jbtSouth.doClick();
		assertEquals("South",remote.getClickName());
		assertEquals(LifeForm.SOUTH, remote.getLifeForm().getCurrentDirection());	
	}
	
	@Test
	public void testTurnWestCommand()
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		assertEquals(LifeForm.NORTH, entity.getCurrentDirection());
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Command cmd = new TurnCommand(LifeForm.WEST);
		remote.setCommand(cmd, Command.WEST);
		assertEquals(cmd, remote.getCommand(Command.WEST));
		remote.jbtWest.doClick();
		assertEquals("West",remote.getClickName());
		assertEquals(LifeForm.WEST, remote.getLifeForm().getCurrentDirection());	
	}
	
	@Test
	public void testAcquireCommand()
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		PlasmaCannon pc = new PlasmaCannon(10, 10, 10, 10);
		e.addWeapon1(pc, 0, 0);
		
		Command cmd = new AcquireCommand();
		remote.setCommand(cmd, Command.ACQUIRE);
		assertEquals(cmd, remote.getCommand(Command.ACQUIRE));
		remote.jbtAcquire.doClick();
		assertEquals("Acquire",remote.getClickName());
		assertEquals(pc, remote.getLifeForm().getWeapon());
	
	}
	
	@Test
	public void testDropCommand() 
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		PlasmaCannon pc = new PlasmaCannon(10, 10, 10, 10);
		e.addWeapon1(pc, 0, 0);
		
		Command cmd = new AcquireCommand();
		remote.setCommand(cmd, Command.ACQUIRE);
		assertEquals(cmd, remote.getCommand(Command.ACQUIRE));
		remote.jbtAcquire.doClick();
		assertEquals("Acquire",remote.getClickName());
		assertEquals(pc, remote.getLifeForm().getWeapon());	
		
		//testing dropping the weapon
		cmd = new DropCommand();
		remote.setCommand(cmd, Command.DROP);
		assertEquals(cmd, remote.getCommand(Command.DROP));
		remote.jbtDrop.doClick();
		assertEquals("Drop",remote.getClickName());
		assertNull(remote.getLifeForm().getWeapon());	
	}
	
	@Test
	public void testMoveCommand() 
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		entity.changeDirection(LifeForm.SOUTH);
		
		Command cmd = new MoveCommand();
		remote.setCommand(cmd, Command.MOVE);
		assertEquals(cmd, remote.getCommand(Command.MOVE));
		remote.jbtMovePlayer.doClick();
		assertEquals("Move",remote.getClickName());
		assertEquals(3, remote.getLifeForm().getRowCell());
		assertEquals(0, remote.getLifeForm().getColCell());
		assertEquals(entity, e.getLifeForm(3, 0));
		
	}
	
	@Test
	public void testAttackCommand() throws EnvironmentException
	{
		LifeForm target = new Human("Bob", 10, 0);
		LifeForm attacker = new Alien("Jim", 10);
		attacker.changeDirection(LifeForm.NORTH);
		e.addLifeForm(target, 0, 0);
		e.addLifeForm(attacker, 1, 0);
		remote.setLifeForm(attacker);
		assertEquals(attacker, remote.getLifeForm());
		
		Command cmd = new AttackCommand();
		remote.setCommand(cmd, Command.ATTACK);
		assertEquals(cmd, remote.getCommand(Command.ATTACK));
		remote.jbtAttack.doClick();
		assertEquals("Attack",remote.getClickName());
		assertEquals(target, e.getTarget(1, 0));	
		assertEquals(0, e.getLifeForm(0, 0).getCurrentLifePoints());	
	}
	
	@Test
	public void testReloadCommand() throws EnvironmentException
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 3, 0);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		
		Pistol p = new Pistol(10, 10, 10, 10);
		p.setRemainingAmmo(0);
		e.addWeapon1(p, 3, 0);
		
		Command cmd = new AcquireCommand();
		remote.setCommand(cmd, Command.ACQUIRE);
		assertEquals(cmd, remote.getCommand(Command.ACQUIRE));
		remote.jbtAcquire.doClick();
		assertEquals("Acquire",remote.getClickName());
		assertEquals(p, remote.getLifeForm().getWeapon());	
		
		cmd = new ReloadCommand();
		remote.setCommand(cmd, Command.RELOAD);
		assertEquals(cmd, remote.getCommand(Command.RELOAD));
		remote.jbtReload.doClick();
		assertEquals("Reload",remote.getClickName());
		assertEquals(remote.getLifeForm().getWeapon().getMaxAmmo(), remote.getLifeForm().getWeapon().getRemainingAmmo());
		
	}

	@Test
	public void testInitialization() 
	{
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Does the Remote Control look right?"));
	}
	
	@Test
	public void testSetCommand()
	{
		Command cmd = new MoveCommand();
		remote.setCommand(cmd, Command.MOVE);
		assertEquals(cmd, remote.getCommand(Command.MOVE));
		String name = "Test";
		MockCommand cmd2 = new MockCommand(name);
		remote.setCommand(cmd2, Command.RELOAD);
		assertEquals(name, cmd2.getName());	
	}
	
	@Test
	public void testSetLifeForm() throws ExistingWorldException
	{
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		assertEquals(0, remote.getLifeForm().getRowCell());
		assertEquals(0, remote.getLifeForm().getRowCell());
	}
	
}
