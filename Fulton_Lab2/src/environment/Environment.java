package environment;

import exceptions.EnvironmentException;
import exceptions.ExistingWorldException;
import lifeform.LifeForm;
import weapon.Weapon;

/**
 * Keeps track of a grid of cells and information about the number of cells per
 * row and column
 *
 */
public class Environment
{
	private int rows;
	private int cols;
	private Cell[][] cells;
	private static Environment theWorld; // the unique Environment instance.

	/**
	 * private class constructor.
	 * 
	 * @param width
	 *            the width of the world
	 * @param height
	 *            the height of the world
	 */
	private Environment(int width, int height)
	{
		rows = width;
		cols = height;
		cells = new Cell[rows][cols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				cells[i][j] = new Cell();
			}
		}
	}

	/**
	 * Sets up the world if one does not exist.
	 * 
	 * @param width
	 *            the width of the world
	 * @param height
	 *            the height of the world
	 */
	public static void setupWorld(int width, int height) throws ExistingWorldException
	{
		if (theWorld == null)
		{
			theWorld = new Environment(width, height);
		} else
			throw new ExistingWorldException();
	}

	/**
	 * @return return the unique instance of the class.
	 */
	public static Environment getWorld()
	{
		return theWorld;
	}

	/**
	 * @return Returns the number of columns in the Environment
	 */
	public int getNumberOfColumns()
	{
		return rows;
	}

	/**
	 * @return Returns the number of rows in the Environment
	 */
	public int getNumberOfRows()
	{
		return cols;
	}

	/**
	 * @return Returns the number of cells in the Environment
	 */
	public int getNumberOfCells()
	{
		return rows * cols;
	}

	/**
	 * @return Returns the LifeForm if present in the cell (r,c)
	 */
	public LifeForm getLifeForm(int r, int c)
	{
		if (r < 0 || c < 0 || r >= rows || c >= cols)
		{
			return null;
		}
		return cells[r][c].getLifeForm();
	}

	/**
	 * adds a LifeForm to the specified cell
	 * 
	 * @param l
	 *            The LifeForm to add
	 * @param r
	 *            The row to add it to
	 * @param c
	 *            The column to add it to
	 */
	public void addLifeForm(LifeForm l, int r, int c)
	{
		if (r < 0 || c < 0 || r >= rows || c >= cols)
		{
			return;
		}
		cells[r][c].addLifeForm(l);
		l.setRowCell(r); // set the row position of the lifeForm
		l.setColCell(c); // set the row position of the lifeForm
	}

	/**
	 * Removes the LifeForm from the specified cell
	 * 
	 * @param r
	 *            The row index
	 * @param c
	 *            The column index
	 */
	public boolean removeLifeForm(int r, int c)
	{
		if (r < 0 || c < 0 || r >= rows || c >= cols)
		{
			return false;
		}
		return cells[r][c].removeLifeForm();
	}

	/**
	 * Clears the board with a clean slate. This especially important for the JUnit
	 * testing prospective.
	 */
	public static void clearBoard()
	{
		theWorld = null;
	}

	/**
	 * Sets the first weapon in a given cell.
	 * 
	 * @param weap
	 * @param row
	 * @param col
	 */
	public void addWeapon1(Weapon weap, int row, int col)
	{
		if (row < 0 || col < 0 || row >= rows || col >= cols)
		{
			return;
		}
		cells[row][col].addWeapon1(weap);
	}

	/**
	 * @param row
	 *            - The row of the cell
	 * @param col
	 *            - the column of the cell.
	 * @return Returns the first weapon in the given cell.
	 */
	public Weapon getWeapon1(int row, int col)
	{
		if (row < 0 || col < 0 || row >= rows || col >= cols)
		{
			return null;
		}
		return cells[row][col].getWeapon1();
	}

	/**
	 * Sets the second weapon in a given cell.
	 * 
	 * @param weap
	 * @param row
	 * @param col
	 */
	public void addWeapon2(Weapon weap, int row, int col)
	{
		if (row < 0 || col < 0 || row >= rows || col >= cols)
		{
			return;
		}
		cells[row][col].addWeapon2(weap);
	}

	/**
	 * @param row
	 *            - The row of the cell
	 * @param col
	 *            - the column of the cell.
	 * @return Returns the second weapon in the given cell.
	 */
	public Weapon getWeapon2(int row, int col)
	{
		if (row < 0 || col < 0 || row >= rows || col >= cols)
		{
			return null;
		}
		return cells[row][col].getWeapon2();
	}

	/**
	 * Removes the first weapon in the given cell.
	 * 
	 * @param row
	 *            - the row of the cell.
	 * @param col
	 *            - the col of the cell
	 */
	public boolean removeWeapon1(int row, int col)
	{
		if (row < 0 || col < 0 || row >= rows || col >= cols)
		{
			return false;
		}
		return cells[row][col].removeWeapon1();
	}

	/**
	 * Removes the second weapon in the given cell.
	 * 
	 * @param row
	 *            - the row of the cell.
	 * @param col
	 *            - the column of the cell
	 */
	public boolean removeWeapon2(int row, int col)
	{
		if (row < 0 || col < 0 || row >= rows || col >= cols)
		{
			return false;
		}
		return cells[row][col].removeWeapon2();
	}

	/**
	 * @param life1
	 * @param life2
	 * @return - returns the distance between to LifeForms
	 */
	public int getDistance(LifeForm life1, LifeForm life2) throws EnvironmentException
	{
		if (!life1.isInTheWorld() || !life2.isInTheWorld())
			throw new EnvironmentException();
		if (life1.getRowCell() == life2.getRowCell())
		{
			return 5 * Math.abs(life1.getColCell() - life2.getColCell());
		} else if (life1.getColCell() == life2.getColCell())
		{
			return 5 * Math.abs(life1.getRowCell() - life2.getRowCell());
		} else
		{
			int x = life1.getColCell() - life2.getColCell();
			int y = life1.getRowCell() - life2.getRowCell();
			return (int) (5 * Math.sqrt((x * x + y * y)));
		}

	}

	/**
	 * move a lifeform if there is one in the given cell based on it's speed and
	 * current direction
	 * 
	 * @param r
	 *            the row the lifeform should be in
	 * @param c
	 *            the column the lifeform should be in
	 */
	public void moveLifeForm(int r, int c)
	{
		LifeForm l = getLifeForm(r, c);
		removeLifeForm(r, c);
		if (l != null)
		{
			int dir = l.getCurrentDirection();
			if (dir == LifeForm.NORTH)
			{
				// move north
				int newRow = r - l.getMaxSpeed();
				if (newRow < 0)
				{
					newRow = 0;
				}
				while (getLifeForm(newRow, c) != null && newRow < r)
				{
					newRow++;
				}
				addLifeForm(l, newRow, c);
			}
			if (dir == LifeForm.SOUTH)
			{
				// move south
				int newRow = r + l.getMaxSpeed();
				if (newRow >= rows)
				{
					newRow = rows - 1;
				}
				while (getLifeForm(newRow, c) != null && newRow > r)
				{
					newRow--;
				}
				addLifeForm(l, newRow, c);
			}
			if (dir == LifeForm.EAST)
			{
				// move east
				int newCol = c + l.getMaxSpeed();
				if (newCol >= cols)
				{
					newCol = cols - 1;
				}
				while (getLifeForm(r, newCol) != null && newCol < cols)
				{
					newCol--;
				}
				addLifeForm(l, r, newCol);
			}
			if (dir == LifeForm.WEST)
			{
				// move west
				int newCol = c - l.getMaxSpeed();
				if (newCol < 0)
				{
					newCol = 0;
				}
				while (getLifeForm(r, newCol) != null && newCol < c)
				{
					newCol++;
				}
				addLifeForm(l, r, newCol);
			}
		}
	}

	/**
	 * return the first lifeform in the attackers line of sight
	 * 
	 * @param r
	 *            the row the attacker is in
	 * @param c
	 *            the column the attacker is in
	 * @return the target lifeform
	 */
	public LifeForm getTarget(int r, int c)
	{
		// East by Default
		int rUpdate = 0;
		int cUpdate = 1;
		LifeForm attacker = getLifeForm(r, c);
		LifeForm target = null;
		// TODO Make into case/switch.
		if (attacker.getCurrentDirection() == LifeForm.NORTH)
		{
			rUpdate = -1;
			cUpdate = 0;
		}
		if (attacker.getCurrentDirection() == LifeForm.SOUTH)
		{
			rUpdate = 1;
			cUpdate = 0;
		}
		if (attacker.getCurrentDirection() == LifeForm.WEST)
		{
			rUpdate = 0;
			cUpdate = -1;
		}
		while (target == null && (r < rows && r >= 0) && (c < cols && c >= 0))
		{
			target = getLifeForm(r + rUpdate, c + cUpdate);
			r += rUpdate;
			c += cUpdate;
		}
		return target;

	}

	/**
	 * get the first available weapon in the cell and give it to the lifeform. If
	 * another weapon is in the cell, shift it to slot 1. Drop the lifeforms weapon
	 * in the first available slot.
	 * 
	 * @param lifeFormWeapon
	 * @return
	 */

	// 1. set lifeform's weapon to the side
	// 2. pickup weapon in s1 if available
	// 2.b. if s1 weapon isn't available pickup weapon in s2
	// 2.c. if no weapons, keep current weapon
	// 3. if there is a weapon is s2, move it to s1
	// 4. if s1 is empty, put lifeforms weapon in s1
	// 4.b if s1 is full, put lifeforms weapon in s2
	public Weapon swapWeapon(Weapon w, int r, int c)
	{
		Weapon returnWeapon = getWeapon1(r, c);
		removeWeapon1(r, c);
		if (returnWeapon == null)
		{
			returnWeapon = getWeapon2(r, c);
			removeWeapon2(r, c);
			if (returnWeapon == null)
			{
				return w;
			}
		}
		if (getWeapon2(r, c) != null)
		{
			Weapon w2 = getWeapon2(r, c);
			removeWeapon2(r, c);
			addWeapon1(w2, r, c);
		}
		if (getWeapon1(r, c) == null)
		{
			addWeapon1(w, r, c);
		} else
		{
			addWeapon2(w, r, c);
		}
		return returnWeapon;

	}
}
