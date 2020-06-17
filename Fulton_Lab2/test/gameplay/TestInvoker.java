package gameplay;


import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import javax.swing.JOptionPane;

import org.junit.Test;

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
		assertEquals(cmd, remote.getCommand(1));
		String name = "Test";
		MockCommand cmd2 = new MockCommand(name);
		remote.setCommand(cmd2, 1);
		assertEquals(name, cmd2.getName());
		
		remote.jbtAttack.doClick();
		assertEquals(name,cmd2.getClickName());
		
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
		
		// The '\n' represents the new line or for our test purposes the
		// return key.
		 String testData = "3\nyes\n";
		// The ByteArrayInputStream class only works with arrays of bytes
		// so we need turn our String into an array of bytes.
		 byte[] byteData = testData.getBytes();
		// Create the ready to go input.
		 ByteArrayInputStream testInput = new ByteArrayInputStream(byteData);
		// Have System.in pull data from our new ByteArrayInputStream. This way
		// we won't have to type on the keyboard to get our test to run.
		 System.setIn(testInput);
		// Run the method and see if it worked correctly as expected.
		 String output = remote.inputMethod(remote.getCommand(1));
		 //assertEquals("w",testInput);
		 assertEquals("worked",output); 
	}

}
