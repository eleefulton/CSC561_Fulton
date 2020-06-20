package gameplay;

/**
 * simple command interface to interact with an Environment
 * 
 * @author Ethan Fulton
 * @author Moumouni Noma
 */
public interface Command
{
	public static final int RELOAD = 0;
	public static final int ATTACK = 1;
	public static final int ACQUIRE = 2;
	public static final int DROP = 3;
	public static final int MOVE = 4;
	public static final int NORTH = 5;
	public static final int SOUTH = 6;
	public static final int EAST = 7;
	public static final int WEST = 8;
	public void execute(int r, int c);
}
