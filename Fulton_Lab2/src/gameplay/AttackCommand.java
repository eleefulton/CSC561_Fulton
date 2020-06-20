package gameplay;

import environment.Environment;
import exceptions.EnvironmentException;
import lifeform.LifeForm;

/**
 * basic command for handling attack of lifeform in specified cell
 * 
 * @author Ethan Fulton
 * @author Moumounni Noma
 */
public class AttackCommand implements Command
{

	Environment e = Environment.getWorld();

	/**
	 * attack the first target in line of sight of the lifeform in the selected cell
	 */
	@Override
	public void execute(int r, int c)
	{
		LifeForm target = e.getTarget(r, c);
		LifeForm attacker = e.getLifeForm(r, c);
		if(target != null)
		{
			try
			{
				attacker.attack(target);
				e.updateUI();
			} catch (EnvironmentException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}

}