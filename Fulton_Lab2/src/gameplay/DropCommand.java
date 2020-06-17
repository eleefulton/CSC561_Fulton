package gameplay;

import environment.Environment;

/**
 * execute moving a lifeform at the given coordinates
 * 
 * @author Ethan Fulton
 *
 */
public class DropCommand implements Command
{

	Environment e = Environment.getWorld();

	@Override
	public void execute(int r, int c)
	{
		e.moveLifeForm(r, c);
	}

}
