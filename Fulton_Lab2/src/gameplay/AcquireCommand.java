package gameplay;

import environment.Environment;
import weapon.Weapon;

/**
 * simple command for picking up a weapon in a cell
 * 
 * @author Ethan Fulton
 *
 */
public class AcquireCommand implements Command
{

	Environment e = Environment.getWorld();

	@Override
	public void execute(int r, int c)
	{
		Weapon w = e.getLifeForm(r, c).getWeapon();
		e.getLifeForm(r, c).dropWeapon();
		e.getLifeForm(r, c).pickWeapon(e.swapWeapon(w, r, c));
	}

}
