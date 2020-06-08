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
			return weapon.fireWeapon(distance);
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
}
