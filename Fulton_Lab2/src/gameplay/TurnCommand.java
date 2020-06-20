package gameplay;

import environment.Environment;

/**
 * basic command for turning a lifeform in a cell
 * 
 * @author Ethan Fulton
 *
 */
public class TurnCommand implements Command
{
	private int turnDir;
	Environment e = Environment.getWorld();

	/**
	 * create a new turn command
	 * 
	 * @param newDir
	 *            the direction to change to
	 */
	public TurnCommand(int newDir)
	{
		turnDir = newDir;
	}

	/**
	 * change the lifeforms direction
	 */
	@Override
	public void execute(int r, int c)
	{
		e.getLifeForm(r, c).changeDirection(turnDir);
		e.updateUI();
	}

}
