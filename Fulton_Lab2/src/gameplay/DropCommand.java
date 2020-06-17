package gameplay;

import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * simple command for dropping a weapon in the cell
 * 
 * @author Ethan Fulton
 *
 */
public class DropCommand implements Command
{
	Environment e = Environment.getWorld();

	/**
	 * let lifeform drop weapon in cell only if there is space available
	 */
	@Override
	public void execute(int r, int c)
	{
		LifeForm l = e.getLifeForm(r, c);
		Weapon w = l.getWeapon();
		l.dropWeapon();
		if (e.getWeapon1(r, c) == null)
		{
			e.addWeapon1(w, r, c);
		} else if (e.getWeapon2(r, c) == null)
		{
			e.addWeapon2(w, r, c);
		} else
		{
			l.pickWeapon(w);
		}
	}

}
