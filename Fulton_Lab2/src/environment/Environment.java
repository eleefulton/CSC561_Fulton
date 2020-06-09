package environment;

import lifeform.LifeForm;

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
	 * @param width the width of the world
	 * @param height the height of the world
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
	 * @param width the width of the world
	 * @param height the height of the world
	 */
	public static void setupWorld(int width, int height) 
	{
		if (theWorld == null)
		{
			theWorld = new Environment(width, height);
		}
	}
	
	/**
	 * @return return the unique instance of the class.
	 */
	public static Environment getWorld() 
	{
		/*if (theWorld == null)
		{
			theWorld = new Environment();
		}*/
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
	 * Resets the static instance.
	 */
	static void clearBoard()
	{
		theWorld = null;
	}

}
