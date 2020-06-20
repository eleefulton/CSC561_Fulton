package gameplay;

import environment.Environment;

/**
 * execute moving a lifeform at the given coordinates
 * 
 * @author Ethan Fulton
 *
 */
public class MoveCommand implements Command
{

	Environment e = Environment.getWorld();

	/**
	 * move the lifeform in specified cell
	 */
	@Override
	public void execute(int r, int c)
	{
		e.moveLifeForm(r, c);
		e.updateUI();
	}

}
