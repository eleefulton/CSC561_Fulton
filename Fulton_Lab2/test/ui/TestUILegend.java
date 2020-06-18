package ui;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;

/**
 * Test the UI legend
 * @author Caitlin Squires
 *
 */
public class TestUILegend {

	@Before
	public void clearEnvironment() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(10,10);
	}
	
	@Test
	public void testLegedInitialization()
	{
		Environment e = Environment.getWorld();
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a legend to the right of the grid?"));
	}
	
	@Test
	public void testLegendLifeFormDisplay()
	{
		Environment e = Environment.getWorld();
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a Human Icon and label in the Legend?"));
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a Alien Icon and label in the Legend?"));
	}

	@Test
	public void testLegendWeaponDisplay()
	{
		Environment e = Environment.getWorld();
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a Pistol Icon and label in the Legend?"));
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a ChainGun Icon and label in the Legend?"));
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a PlasmaCannon Icon and label in the Legend?"));
	}
	
	@Test
	public void testLegendWeaponWithAttachmentDisplay()
	{
		Environment e = Environment.getWorld();
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a Weapon Icon with one attachment and label in the Legend?"));
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Is there a Weapon Icon with two attachments and label in the Legend?"));
	}
}
