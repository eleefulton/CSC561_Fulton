package ui;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

import environment.Cell;
import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Alien;
import lifeform.Human;


public class TestUI {

	
	@Test
	public void testWorldGridInitialization() throws ExistingWorldException 
	{
		Environment.clearBoard();
		Environment.setupWorld(10,10);
		Environment e = Environment.getWorld();
		UI ui = new UI(e);
		//UI ui = new UI();
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Does the grid look right?"));
	
	}
	
	@Test
	public void testWorldGridHumanAlienLocations() throws ExistingWorldException {
		Environment.clearBoard();
		Environment.setupWorld(10,10);
		Environment e = Environment.getWorld();
		Human bob = new Human("Bob", 10, 10);
		Alien aBob = new Alien("AlienBob", 10);
		e.addLifeForm(bob, 0, 0);
		e.addLifeForm(aBob, 4, 3);
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nHuman(0,0) facing North\nDoes it look right?"));
	
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nAlien(4,3) facing North\nDoes it look right?"));
	}

}
