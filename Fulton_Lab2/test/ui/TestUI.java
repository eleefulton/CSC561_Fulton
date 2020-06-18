package ui;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.Before;
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
 * Test UI class, lifeforms, weapons, and attachments
 * @author Caitlin Squires
 *
 */

public class TestUI {

	@Before
	public void clearEnvironment() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(10,10);
	}
	
	@Test
	public void testWorldGridInitialization()
	{
		Environment e = Environment.getWorld();
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Does the grid look right?"));
	}
	
	@Test
	public void testWorldGridHumanAlienDirections()
	{
		Environment e = Environment.getWorld();
		Human bob = new Human("Bob", 10, 10);
		Alien aBob = new Alien("AlienBob", 10);
		aBob.changeDirection(aBob.SOUTH);
		e.addLifeForm(bob, 0, 0);
		e.addLifeForm(aBob, 4, 3);
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nHuman(0,0) facing North\nDoes it look right?"));
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nAlien(4,3) facing South\nDoes it look right?"));
	}

	@Test
	public void testWorldWeaponLifeFormHoldingWeapon()
	{
		Environment e = Environment.getWorld();
		Human bob = new Human("Bob", 10, 10);
		PlasmaCannon pc = new PlasmaCannon(10,10,10,10);
		bob.pickWeapon(pc);
		e.addLifeForm(bob, 0, 0);
		
		Alien aBob = new Alien("AlienBob", 10);
		PlasmaCannon pc1 = new PlasmaCannon(10,10,10,10);
		aBob.pickWeapon(pc1);
		e.addLifeForm(aBob, 1, 1);
		
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Human at (0,0)? \n Does it the weapon have one green weapon?"));	
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Alien at (1,1)? \n Does it the weapon have one green weapon?"));	
	}
	
	@Test
	public void testWorldWeaponIcons() 
	{
		Environment e = Environment.getWorld();
	
		PlasmaCannon pc = new PlasmaCannon(10,10,10,10);
		e.addWeapon1(pc, 0, 0);
		ChainGun cg = new ChainGun(10,10,10,10);
		e.addWeapon1(cg, 1, 1);
		Pistol p = new Pistol(10,10,10,10);
		e.addWeapon1(p, 2, 2);
		
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n PlasmaCannon(0,0)? \n Does it look Green?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n ChainGun(1,1) \n Does it look Orange?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n Pistol(2,2) \n Does it look yellow?"));
	}
	
	@Test
	public void testWorldTwoWeaponsInOneCell() 
	{
		Environment e = Environment.getWorld();
	
		PlasmaCannon pc = new PlasmaCannon(10,10,10,10);
		e.addWeapon1(pc, 0, 0);
		ChainGun cg = new ChainGun(10,10,10,10);
		e.addWeapon2(cg, 0, 0);
		
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n PlasmaCannon(0,0)? \n Does it look Green (top left of cell)?"));
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Create Weapon For\n ChainGun(0,0) \n Does it look Orange (bottom right of cell)?"));
		
	}
	
	@Test
	public void testWorldUpdates()
	{
		Environment e = Environment.getWorld();
		Human bob = new Human("Bob", 10, 10);
		Alien aBob = new Alien("AlienBob", 10);
		e.addLifeForm(bob, 0, 0);
		e.addLifeForm(aBob, 4, 3);
		UI ui = new UI(e);
		
		bob.changeDirection(bob.WEST);
		aBob.changeDirection(aBob.EAST);
		ui.updateGrid();
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nHuman(0,0) facing west\nDoes it look right?"));
		assertEquals(JOptionPane.YES_OPTION,JOptionPane.showConfirmDialog(null, 
				"Create Cell Image Icon Correct For\nAlien(4,3) facing East\nDoes it look right?"));
	}
	
	@Test
	public void testWorldWeaponWithAttachment()
	{
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
	
	@Test
	public void testWorldLifeFormWithWeaponCellTwoWeapon()
	{
		Environment e = Environment.getWorld();
		
		ChainGun cg = new ChainGun(10,10,10,10);
		e.addWeapon1(cg, 0, 0);
		Pistol p = new Pistol(10,10,10,10);
		e.addWeapon2(p, 0, 0);
		
		PlasmaCannon pc = new PlasmaCannon(10,10,10,10);	
		Human bob = new Human("Bob", 10, 10);
		bob.pickWeapon(pc);
		e.addLifeForm(bob, 0, 0);
		
		UI ui = new UI(e);
		
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Weapon1 at (0,0)? \n Is it Orange?"));	
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Weapon2 at (0,0)? \n Is it Yellow?"));	
		assertEquals(JOptionPane.YES_OPTION, JOptionPane.showConfirmDialog(null, "Human at (0,0)? \n Does it have a green weapon?"));		
		
	}
}