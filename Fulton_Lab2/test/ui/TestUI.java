package ui;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Test;

import environment.Cell;
import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Alien;
import lifeform.Human;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Scope;
import weapon.Stabilizer;

/**
 * Test UI class
 * @author CASquires
 *
 */

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

	@Test
	public void testWorldWeaponLocations() throws ExistingWorldException {
		Environment.clearBoard();
		Environment.setupWorld(10,10);
		Environment e = Environment.getWorld();
		Human bob = new Human("Bob", 10, 10);
		e.addLifeForm(bob, 0, 0);
		
		PlasmaCannon pc = new PlasmaCannon(10,10,10,10);
		e.addWeapon1(pc, 0, 0);
		ChainGun cg = new ChainGun(10,10,10,10);
		e.addWeapon1(cg, 1, 1);
		Pistol p = new Pistol(10,10,10,10);
		e.addWeapon1(p, 2, 2);
		
		e.addWeapon2(p, 0, 0);
		UI ui = new UI(e);
		
		//assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n Weapon(0,0) \n Does it look green?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Are there two wepaons at(0,0)? \n Does it look green and yellow?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n Weapon(1,1) \n Does it look Orange?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n Weapon(2,2) \n Does it look yellow?"));
		
	}
	
	@Test
	public void testWorldWeaponLifeFormHoldingWeapon() throws ExistingWorldException {
		Environment.clearBoard();
		Environment.setupWorld(10,10);
		Environment e = Environment.getWorld();
		Human bob = new Human("Bob", 10, 10);
		PlasmaCannon pc = new PlasmaCannon(10,10,10,10);
		bob.pickWeapon(pc);
		e.addLifeForm(bob, 0, 0);
				
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Human at (0,0)? \n Does it the weapon have one green weapon?"));	
	}
	
	@Test
	public void testWorldWeaponWithAttachment() throws ExistingWorldException {
		Environment.clearBoard();
		Environment.setupWorld(10,10);
		Environment e = Environment.getWorld();
				
		PlasmaCannon pc = new PlasmaCannon(10,10,10,10);
		Scope s = new Scope(pc);
		Stabilizer s2 = new Stabilizer(s);
		e.addWeapon2(s2, 0, 0);
		
		Human bob = new Human("Bob", 10, 10);
		bob.pickWeapon(s2);
		e.addLifeForm(bob, 2, 2);
		
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Weapon at (0,0)? \n Does it have two attachments?"));	
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Human at (2,2)? \n Does it havea weapon with two attachments?"));		
		
	}
	
}