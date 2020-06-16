package gameplay;

import environment.Environment;

/**
 * basic command for turning a lifeform in a cell
 * @author Ethan Fulton
 *
 */
public class TurnCommand implements Command
{
	private int turnDir;
	Environment e = Environment.getWorld();

	public TurnCommand(int newDir)
	{
		turnDir = newDir;
	}
	
	@Override
	public void execute(int r, int c) 
	{
		e.getLifeForm(r, c).changeDirection(turnDir);
	}

}
