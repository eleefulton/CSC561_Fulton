package environment;

import exceptions.EnvironmentException;
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
		l.setRowCell(r); //set the row position of the lifeForm
		l.setColCell(c); //set the row position of the lifeForm
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
	 * Clears the board with a clean slate.
	 * This especially important for the JUnit testing prospective.
	 */
	static void clearBoard()
	{
		theWorld = null;
	}

	/**
	 * Sets the first weapon in a given cell.
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
	 * @param row - The row of the cell
	 * @param col - the column of the cell.
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
	 * @param row - The row of the cell
	 * @param col - the column of the cell.
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
	 * @param row - the row of the cell.
	 * @param col - the col of the cell
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
	 * @param row - the row of the cell.
	 * @param col - the column of the cell
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
		if(!life1.isInTheWorld() || !life2.isInTheWorld()) 
			throw new EnvironmentException();
		if(life1.getRowCell() == life2.getRowCell())
		{
			return 5 * Math.abs(life1.getColCell() - life2.getColCell());
		}
		else if (life1.getColCell() == life2.getColCell())
		{
			return 5 * Math.abs(life1.getRowCell() - life2.getRowCell());
		}
		else
		{
			int x = life1.getColCell() - life2.getColCell();
			int y = life1.getRowCell() - life2.getRowCell();
			return (int) (5 * Math.sqrt((x * x + y * y)));
		}

	}

}
