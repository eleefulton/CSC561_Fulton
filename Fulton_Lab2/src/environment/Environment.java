package environment;

import exceptions.EnvironmentConstructorException;
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
	private static Environment world;
	
	public Environment(int r, int c)
	{
		rows = r;
		cols = c;
		cells = new Cell[rows][cols];
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				cells[i][j] = new Cell();
			}
		}
	}
	
	public static void createWorld(int r, int c) throws EnvironmentConstructorException
	{
		if (world == null)
			world = new Environment(r, c);
		else
			throw new EnvironmentConstructorException();
	}
	
	public static void resetWorld() {
		world = null;
		
	}
	
	public static Environment getWorld()
	{
		return world;
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
	 * @return Returns the weapon if present in the cell (r,c)
	 */
	public Weapon[] getWeapon(int r, int c)
	{
		if (r < 0 || c < 0 || r >= rows || c >= cols)
		{
			return null;
		}
		return cells[r][c].getWeapons();
	}

	/**
	 * Checks that the weapon doens't already exist at that cell
	 * @param w weapon to add to the cell
	 * @param r row location 
	 * @param c column location
	 * @return
	 */
	public boolean addWeapon(Weapon w, int r, int c)
	{
		if (r >= 0 && c >= 0 && r < rows && c < cols )
		{
			if (this.getWeapon(r, c)[0] != w && this.getWeapon(r, c)[1] != w)
			{
				cells[r][c].addWeapon(w);
				return true;
			}else
				return false;
		}else
		{
			return false;
		}
	}

	public boolean removeWeapon(Weapon w, int r, int c) {
		if (r >= 0 && c >= 0 && r < rows && c < cols )
		{
			if (this.getWeapon(r, c)[0] == w)
			{
				cells[r][c].removeWeapon(w);
				return true;
			}
			else if (this.getWeapon(r, c)[1] == w)
			{
				cells[r][c].removeWeapon(w);
				return true;
			}else
				return false;
		}else
			return false;
		
	}

	public int getDistance(LifeForm l1, LifeForm l2) throws EnvironmentException
	{
		int [] location1 = this.getLifeFormLocation(l1);
		int [] location2 = this.getLifeFormLocation(l2);
		if (location1 != null && location2 != null)
		{
			int a = (int) Math.pow((5 * Math.abs(location1[0] - location2[0])), 2);
			int b = (int) Math.pow((5 * Math.abs(location1[1] - location2[1])), 2);		
			int c = (int) Math.pow((a+b), (double)1/2);
			return c;
		} else
			throw new EnvironmentException();
	}
	
	public int[] getLifeFormLocation(LifeForm l)
	{
		int [] loc = new int [2];
		for (int i = 0; i < rows; i ++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (cells[i][j].getLifeForm() == l)
				{
					loc[0] = i;
					loc[1] = j;
					return loc;
				}
			}
		}
		return null;
	}
}
