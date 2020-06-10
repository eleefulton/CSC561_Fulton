package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/* A Cell that can hold a LifeForm.
 *
 */
public class Cell
{

	private LifeForm myLifeForm;
	private Weapon weaponOne;
	private Weapon weaponTwo;

	/**
	 * @return the LifeForm in this Cell.
	 */
	public LifeForm getLifeForm()
	{
		return myLifeForm;
	}

	/**
	 * Tries to add the LifeForm to the Cell. Will not add if a LifeForm is already
	 * present.
	 * 
	 * @return true if the LifeForm was added to the Cell, false otherwise.
	 */
	public boolean addLifeForm(LifeForm entity)
	{
		if (myLifeForm == null)
		{
			myLifeForm = entity;
			myLifeForm.putInCell();
			return true;
		} else
			return false;
	}

	/**
	 * Removes the LifeForm from the cell
	 */
	public void removeLifeForm()
	{
		myLifeForm.removeFromCell();
		myLifeForm = null;

	}

	public Weapon getWeaponOne()
	{
		return weaponOne;
	}

	public Weapon getWeaponTwo()
	{
		return weaponTwo;
	}

	public Weapon addWeapon(Weapon w)
	{
		if (weaponOne == null)
		{
			weaponOne = w;
			w.addToCell();
			return w;
		} else if (weaponTwo == null)
		{
			weaponTwo = w;
			w.addToCell();
			return w;
		} else
		{
			return null;
		}
	}

	public void removeWeapon(Weapon w)
	{
		if (weaponOne == w)
		{
			w.removeFromCell();
			weaponOne = null;
		} else if (weaponTwo == w)
		{
			w.removeFromCell();
			weaponTwo = null;
		}

	}
}
