package gameplay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;

/**
 * tests for drop command
 * 
 * @author Ethan Fulton
 *
 */
public class TestDropCommand
{

	@Test
	public void testDropWeaponInOpenCell() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 3, 0);
		Pistol p = new Pistol(10, 10, 10, 10);
		l1.pickWeapon(p);
		DropCommand dc = new DropCommand();
		assertNull(e.getWeapon1(3, 0));
		dc.execute(3, 0);
		assertEquals(p, e.getWeapon1(3, 0));
		e.removeWeapon1(3, 0);
		l1.pickWeapon(p);
		PlasmaCannon pc = new PlasmaCannon(10, 10, 10, 10);
		e.addWeapon1(pc, 3, 0);
		dc.execute(3, 0);
		assertEquals(p, e.getWeapon2(3, 0));
	}

	@Test
	public void testDropWeaponInFullCell() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 3, 0);
		Pistol p = new Pistol(10, 10, 10, 10);
		PlasmaCannon pc = new PlasmaCannon(10, 10, 10, 10);
		ChainGun cg = new ChainGun(10, 10, 10, 10);
		l1.pickWeapon(p);
		e.addWeapon1(pc, 3, 0);
		e.addWeapon2(cg, 3, 0);
		DropCommand dc = new DropCommand();
		dc.execute(3, 0);
		assertEquals(pc, e.getWeapon1(3, 0));
		assertEquals(cg, e.getWeapon2(3, 0));
		assertEquals(p, l1.getWeapon());

	}

}
