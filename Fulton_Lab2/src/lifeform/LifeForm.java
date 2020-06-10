package lifeform;

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
	private String myName;
	protected int currentLifePoints;
	protected int attackStrength;
	protected Weapon weapon; // The weapon held by the lifeform
	private int rowCell;      // The row of the Cell holding the LifeForm.
	private int colCell;     //The col of the cell holding the lifeform
	private boolean isInTheWorld;  //checks if the FifeForm is in the Environemnt

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

	public int attack(int distance)
	{
		if (weapon == null)
		{
			if (distance > 5)
			{
				return 0;
			} else
			{
				return currentLifePoints == 0 ? 0 : attackStrength;
			}
		} else
		{
			if (weapon.getRemainingAmmo() == 0 && distance <= 5)
			{
				return currentLifePoints == 0 ? 0 : attackStrength;
			} else
			{
				return weapon.fireWeapon(distance);
			}
		}
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
	public Object getWeapon()
	{
		return this.weapon;
	}

	public void dropWeapon()
	{
		if (this.weapon != null)
			this.weapon = null;

	}

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
	 * @param rowCell the rowCell to set
	 */
	public void setRowCell(int row) 
	{
		this.rowCell = row;
	}

	/**
	 * @param colCell the colCell to set
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
	 * @param isInTheWorld the isInTheWorld to set
	 */
	public void setInTheWorld(boolean val) 
	{
		this.isInTheWorld = val;
	}
	
	/**
	 * Simulate to LifeForms attacking each other
	 * @param other
	 * @return
	 */
	public int attack(LifeForm other)
	{
		int distance = getDistance(other);
		if (weapon == null)
		{
			if (distance > 5)
			{
				return 0;
			} else
			{
				return currentLifePoints == 0 ? 0 : attackStrength;
			}
		} else
		{
			if (weapon.getRemainingAmmo() == 0 && distance <= 5)
			{
				return currentLifePoints == 0 ? 0 : attackStrength;
			} else
			{
				return weapon.fireWeapon(distance);
			}
		}
	}
	
	/**
	 * @param life
	 * @return - returns the distance to another lifeForm.
	 */
	public int getDistance(LifeForm life)
	{
		if(life.getRowCell() == this.getRowCell())
		{
			return 5 * Math.abs(life.getColCell() - this.getColCell());
		}
		else if (life.getColCell() == this.getColCell())
		{
			return 5 * Math.abs(life.getRowCell() - this.getRowCell());
		}
		else
		{
			int x = life.getColCell() - this.getColCell();
			int y = life.getRowCell() - this.getRowCell();
			return (int) (5 * Math.sqrt((x * x + y * y)));
		}

	}
	
}
