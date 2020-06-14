package ui;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

import environment.Environment;

public class TestUI {

	@Test
	public void testWorldGridInitialization() {
		UI ui = new UI();
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nHuman(0,0) facing North\nDoes it look right?"));
	
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nAlien(4,3) facing West\nDoes it look right?"));
	}

}
