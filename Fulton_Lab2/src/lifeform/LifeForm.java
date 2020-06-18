  
package lifeform;

import environment.Environment;
import exceptions.EnvironmentException;
import gameplay.TimeObserver;
import weapon.Weapon;

/**
 * Keeps track of the information associated with a simple life form. Also
 * provides the functionality related to the life form.
 * 
 * @author Ethan Fulton
 */
public abstract class LifeForm implements TimeObserver
{
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST = 2;
	public static final int WEST = 3;
	private String myName;
	protected int currentLifePoints;
	protected int attackStrength;
	protected Weapon weapon; // The weapon held by the lifeform
	private int rowCell; // The row of the Cell holding the LifeForm.
	private int colCell; // The col of the cell holding the lifeform
	private boolean isInTheWorld; // checks if the FifeForm is in the Environemnt
	protected int maxSpeed;
	private int currentDirection;

	/**
	 * Create an instance
	 *
	 * @param name
	 *            the name of the life form
	 * @param points
	 *            the current starting life points of the life form
	 */
	public LifeForm(String name, int points)
	{
		myName = name;
		currentLifePoints = points;
		rowCell = -1;
		colCell = -1;
		this.setInTheWorld(false);
		maxSpeed = 0;
		currentDirection = NORTH;
	}

	/**
	 * @return the name of the life form.
	 */
	public String getName()
	{
		return myName;
	}

	/**
	 * @return the amount of current life points the life form has.
	 */
	public int getCurrentLifePoints()
	{
		return currentLifePoints;
	}

	/**
	 * decrease the LifeForm life points by damage amount
	 * 
	 * @param damage
	 *            the amount of damage the lifeform should take
	 */
	public void takeHit(int damage)
	{
		currentLifePoints = currentLifePoints - damage < 0 ? 0 : currentLifePoints - damage;
	}

	/**
	 * This allow a lifeform to pick a weapon that they can use to fight.
	 * 
	 * @param wep
	 *            The weapon to be picked up by the lifeform
	 */
	public void pickWeapon(Weapon wep)
	{
		if (this.weapon == null)
			this.weapon = wep;

	}

	/**
	 * @return returns the weapon held by the Lifeform.
	 */
	public Weapon getWeapon()
	{
		return this.weapon;
	}

	public void dropWeapon()
	{
		if (this.weapon != null)
			this.weapon = null;

	}

	/**
	 * reload the weapon held by this lifeform
	 */
	public void reload()
	{
		if (weapon != null)
		{
			weapon.reload();
		}
	}

	/**
	 * @return - returns the row of the Cell holding the Lifeform
	 */
	public int getRowCell()
	{
		return rowCell;
	}

	/**
	 * @return - returns the row of the Cell holding the Lifeform.
	 */
	public int getColCell()
	{
		return colCell;
	}

	/**
	 * @param rowCell
	 *            the rowCell to set
	 */
	public void setRowCell(int row)
	{
		this.rowCell = row;
	}

	/**
	 * @param colCell
	 *            the colCell to set
	 */
	public void setColCell(int col)
	{
		this.colCell = col;
	}

	/**
	 * @return the isInTheWorld
	 */
	public boolean isInTheWorld()
	{
		return isInTheWorld;
	}

	/**
	 * @param isInTheWorld
	 *            the isInTheWorld to set
	 */
	public void setInTheWorld(boolean val)
	{
		this.isInTheWorld = val;
	}

	/**
	 * Simulate to LifeForms attacking each other
	 * 
	 * @param other
	 *            the lifeform to attack
	 * @throws EnvironmentException
	 */
	public void attack(LifeForm other) throws EnvironmentException
	{
		Environment e = Environment.getWorld();
		int distance = -1;
		if (e != null)
			distance = e.getDistance(this, other);
		if (weapon == null)
		{
			if (distance <= 5)
			{
				other.takeHit(currentLifePoints == 0 ? 0 : attackStrength);
			}
		} else
		{
			if (weapon.getRemainingAmmo() == 0 && distance <= 5)
			{
				other.takeHit(currentLifePoints == 0 ? 0 : attackStrength);
			} else
			{
				other.takeHit(weapon.fireWeapon(distance));
			}
		}
	}

	/**
	 * 
	 * @return the number of cells a LifeForm can move
	 */
	public int getMaxSpeed()
	{
		return maxSpeed;
	}

	/**
	 * 
	 * @return the current direction of the LifeForm
	 */
	public int getCurrentDirection()
	{
		return currentDirection;
	}

	/**
	 * update the lifeform's direction if a valid direction is passed
	 * 
	 * @param newDirection
	 *            the new direction to face
	 */
	public void changeDirection(int newDirection)
	{
		if (newDirection == NORTH || newDirection == SOUTH || newDirection == EAST || newDirection == WEST)
		{
			currentDirection = newDirection;
		}
	}
}

