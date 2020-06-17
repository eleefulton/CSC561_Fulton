package gameplay;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;

/**
 * basic command for handling attack of lifeform in specified cell
 * @author Ethan Fulton
 *
 */
public class AttackCommand implements Command
{

	Environment e = Environment.getWorld();
	@Override
	public void execute(int r, int c)
	{
		LifeForm target = e.getTarget(r, c);
		LifeForm attacker = e.getLifeForm(r, c);
	    try {
			attacker.attack(target);
		} catch (EnvironmentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}

}