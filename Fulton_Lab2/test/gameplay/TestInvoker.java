package gameplay;


import static org.junit.Assert.assertEquals;


import javax.swing.JOptionPane;

import org.junit.Test;

import lifeform.LifeForm;
import lifeform.MockLifeForm;

/**
 * This will test the functionalities of the Invoker class.
 * @author Moumouni Noma
 */
public class TestInvoker 
{

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
	public void testSetLifeForm()
	{
		Invoker remote = new Invoker();
		LifeForm entity = new MockLifeForm("Fred",10);
		remote.setLifeForm(entity);
		assertEquals(entity, remote.getLifeForm());
	}
	
	@Test
	public void testButtonClick()
	{
		Invoker remote = new Invoker();
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
	
	@Test
	public void testTurnNorthCommand() 
	{
		Invoker remote = new Invoker();
		//remote.setBounds(200, 200, 400, 400);
		Command cmd = new MoveCommand();
		remote.setCommand(cmd,1);
		assertEquals(cmd, remote.getCommand(1));
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Does the Remote Control look right?"));
	}

}
