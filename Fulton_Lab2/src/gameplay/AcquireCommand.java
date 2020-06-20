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

	/**
	 * drop weapon currently holding in cell and swap for first available in cell
	 * (if no weapons in cell it will pick back up it's weapon)
	 */
	@Override
	public void execute(int r, int c)
	{
		Weapon w = e.getLifeForm(r, c).getWeapon();
		e.getLifeForm(r, c).dropWeapon();
		e.getLifeForm(r, c).pickWeapon(e.swapWeapon(w, r, c));
		e.updateUI();
	}

}