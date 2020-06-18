package gameplay;


import static org.junit.Assert.assertEquals;


import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.PlasmaCannon;

/**
 * This will test the functionalities of the Invoker class.
 * @author Moumouni Noma
 */
public class TestInvoker 
{
	private Environment e;
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
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,6);
		assertEquals(cmd, remote.getCommand(6));
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
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,8);
		assertEquals(cmd, remote.getCommand(8));
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
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,7);
		assertEquals(cmd, remote.getCommand(7));
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
		
		Command cmd = new TurnCommand(1);
		remote.setCommand(cmd,9);
		assertEquals(cmd, remote.getCommand(9));
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
		remote.setCommand(cmd, 3);
		assertEquals(cmd, remote.getCommand(3));
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
		remote.setCommand(cmd, 3);
		assertEquals(cmd, remote.getCommand(3));
		remote.jbtAcquire.doClick();
		assertEquals("Acquire",remote.getClickName());
		assertEquals(pc, remote.getLifeForm().getWeapon());	
		
		//testing dropping the weapon
		cmd = new DropCommand();
		remote.setCommand(cmd, 4);
		assertEquals(cmd, remote.getCommand(4));
		remote.jbtDrop.doClick();
		assertEquals("Drop",remote.getClickName());
		assertEquals(pc, remote.getLifeForm().getWeapon());	
	}

	@Test
	public void testInitialization() 
	{
		remote.doLayout(); // This is just to remove the warning for unused component remote
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Does the Remote Control look right?"));
	}
	
	@Test
	public void testSetCommand()
	{
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
		Human entity = new Human("Bob", 10, 10);
		e.addLifeForm(entity, 0, 0);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
		assertEquals(0, remote.getLifeForm().getRowCell());
		assertEquals(0, remote.getLifeForm().getRowCell());
	}
	
	/*@Test
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
	}*/

}
