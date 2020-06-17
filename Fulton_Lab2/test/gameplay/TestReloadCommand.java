package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.ExistingWorldException;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Pistol;

public class TestReloadCommand 
{
	@Test
	public void testReloadWeapon() throws ExistingWorldException
	{
		Environment.clearBoard();
		Environment.setupWorld(4, 3);
		Environment e = Environment.getWorld();
		LifeForm l1 = new Human("Bob", 10, 10);
		e.addLifeForm(l1, 3, 0);
		Pistol p = new Pistol(10,10,10,10);
		p.setRemainingAmmo(0);
		l1.pickWeapon(p);
		ReloadCommand rc = new ReloadCommand();
		rc.execute(3,0);
		assertEquals(p.getMaxAmmo(), p.getRemainingAmmo());
	}
}

