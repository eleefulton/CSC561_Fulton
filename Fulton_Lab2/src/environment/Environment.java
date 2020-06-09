package environment;

import exceptions.EnvironmentExistsException;
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
	private static Environment theWorld;
	private static boolean created = false;

	private Environment(int w, int h)
	{
		rows = h;
		cols = w;
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
		return cells.length;
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
	}

	/**
	 * Removes the LifeForm from the specified cell
	 * 
	 * @param r
	 *            The row index
	 * @param c
	 *            The column index
	 */
	public void removeLifeForm(int r, int c)
	{
		if (r < 0 || c < 0 || r >= rows || c >= cols)
		{
			return;
		}
		cells[r][c].removeLifeForm();
	}

	/**
	 * create the single instance of the Environment if this method hasn't already
	 * been called
	 * 
	 * @param w
	 *            the width of the environment
	 * @param h
	 *            the height of the environment
	 * @throws EnvironmentExistsException
	 */
	public static void setupWorld(int w, int h) throws EnvironmentExistsException
	{
		if (created == true)
		{
			throw new EnvironmentExistsException();
		} else
		{
			theWorld = new Environment(w, h);
			created = true;
		}
	}

	/**
	 * 
	 * @return Return the instance of the world
	 */
	public static Environment getWorld()
	{
		return theWorld;
	}

	/**
	 * FOR TESTING PURPOSES ONLY: reset the single world instance
	 */
	public static void resetEnvironment()
	{
		theWorld = null;
		created = false;
	}

	/**
	 * add a weapon to the specified cell
	 * 
	 * @param w
	 *            the weapon to add
	 * @param r
	 *            the row of the cell
	 * @param c
	 *            the column of the cell
	 */
	public Weapon addWeapon(Weapon w, int r, int c)
	{
		if (r >= 0 && c >= 0 && r < rows && c < cols && w.getInCell() == false)
		{
			cells[r][c].addWeapon(w);
			return w;
		} else
		{
			return null;
		}
	}

	/**
	 * get weaponOne from the specified cell
	 * 
	 * @param r
	 *            the row of the cell
	 * @param c
	 *            the column of the cell
	 * @return the weapon in slot one of the cell
	 */
	public Weapon getWeaponOne(int r, int c)
	{
		if (r >= 0 && c >= 0 && r < rows && c < cols)
		{
			return cells[r][c].getWeaponOne();
		} else
			return null;
	}

	/**
	 * get weaponTwo from the specified cell
	 * 
	 * @param r
	 *            the row of the cell
	 * @param c
	 *            the column of the cell
	 * @return the weapon in slot one of the cell
	 */
	public Weapon getWeaponTwo(int r, int c)
	{
		if (r >= 0 && c >= 0 && r < rows && c < cols)
		{
			return cells[r][c].getWeaponTwo();
		} else
			return null;
	}

	/**
	 * remove the specified weapon from the specified cell
	 * 
	 * @param w
	 *            the weapon to remove
	 * @param r
	 *            the row of the cell
	 * @param c
	 *            the column of the cell
	 */
	public void removeWeapon(Weapon w, int r, int c)
	{
		if (r >= 0 && c >= 0 && r < rows && c < cols)
		{
			cells[r][c].removeWeapon(w);
		}
	}

}
