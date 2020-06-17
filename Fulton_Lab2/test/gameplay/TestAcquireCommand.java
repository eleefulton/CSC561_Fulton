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

public class TestAcquireCommand
{
	@Test
	public void testAcquireWhenNotHoldingWeapon() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 0);
		Pistol p = new Pistol(10, 10, 10, 10);
		e.addLifeForm(l1, 0, 0);
		e.addWeapon1(p, 0, 0);
		AcquireCommand ac = new AcquireCommand();
		ac.execute(0, 0);
		assertEquals(p, l1.getWeapon());
		assertNull(e.getWeapon1(0, 0));
		l1.dropWeapon();
		e.addWeapon2(p, 0, 0);
		ac.execute(0, 0);
		assertEquals(p, l1.getWeapon());
		assertNull(e.getWeapon2(0, 0));
	}

	@Test
	public void testAcquireWhileHoldingAWeapon() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 0);
		Pistol p = new Pistol(10, 10, 10, 10);
		PlasmaCannon pc = new PlasmaCannon(10, 10, 10, 10);
		e.addLifeForm(l1, 0, 0);
		e.addWeapon1(p, 0, 0);
		l1.pickWeapon(pc);
		AcquireCommand ac = new AcquireCommand();
		ac.execute(0, 0);
		assertEquals(p, l1.getWeapon());
		assertEquals(pc, e.getWeapon1(0, 0));
		e.removeWeapon1(0, 0);
		e.addWeapon2(pc, 0, 0);
		ac.execute(0, 0);
		assertEquals(pc, l1.getWeapon());
		assertEquals(p, e.getWeapon1(0, 0));
	}

	@Test
	public void testAcquireWithTwoWeaponsInCell() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 0);
		Pistol p = new Pistol(10, 10, 10, 10);
		PlasmaCannon pc = new PlasmaCannon(10, 10, 10, 10);
		ChainGun cg = new ChainGun(10, 10, 10, 10);
		e.addLifeForm(l1, 0, 0);
		l1.pickWeapon(p);
		e.addWeapon1(pc, 0, 0);
		e.addWeapon2(cg, 0, 0);
		AcquireCommand ac = new AcquireCommand();
		ac.execute(0, 0);
		assertEquals(pc, l1.getWeapon());
		assertEquals(cg, e.getWeapon1(0, 0));
		assertEquals(p, e.getWeapon2(0, 0));
		ac.execute(0, 0);
		assertEquals(cg, l1.getWeapon());
		assertEquals(p, e.getWeapon1(0, 0));
		assertEquals(pc, e.getWeapon2(0, 0));
	}

}
