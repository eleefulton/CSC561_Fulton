package gameplay;

import environment.Environment;

/**
 * handle reload of weapon held by lifeform in provided cell
 * @author Ethan Fulton
 *
 */
public class ReloadCommand implements Command{

	Environment e = Environment.getWorld();
	
	@Override
	public void execute(int r, int c) 
	{
		e.getLifeForm(r, c).reload();
	}

}