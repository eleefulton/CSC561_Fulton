package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/* A Cell that can hold a LifeForm.
 *
 */
public class Cell
{

	private LifeForm myLifeForm;
	private Weapon weapon1; //The first weapon in the cell
	private Weapon weapon2; //The second weapon in the cell

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
			myLifeForm.setInTheWorld(true);
			return true;
		} else
			return false;
	}

	/**
	 * Removes the LifeForm from the cell
	 */
	public boolean removeLifeForm()
	{
		if (myLifeForm != null)
		{
			myLifeForm.setInTheWorld(false);
		    myLifeForm = null;
		    return true;
		}
		else
			return false;
	}
    
	/**
	 * @return return the first weapon in the cell.
	 */
	public Weapon getWeapon1() 
	{
		return weapon1;
	}

	/**
	 * @return return the second weapon in the cell.
	 */
	public Weapon getWeapon2() 
	{
		return weapon2;
	}

	/**
	 * Sets the first weapon in the cell
	 * @param weap1
	 * @return - returns true if the weapon is added, false otherwise
	 */
	public boolean addWeapon1(Weapon weap1) 
	{
		if (getWeapon1() == null)
		{
			weapon1 = weap1;
			return true;
		}
		else
			return false;
	}

	/**
	 * Sets the second weapon in the cell
	 * @param weap2
	 * @return - returns true if the weapon is added, false otherwise
	 */
	public boolean addWeapon2(Weapon weap2) 
	{
		if (getWeapon2() == null)
		{
			weapon2 = weap2;
			return true;
		}
		else
			return false;
	}

	/**
	 * Remove the weapon in slot 1.
	 */
	public boolean removeWeapon1() 
	{
		if(getWeapon1() != null)
		{
			weapon1 = null;
			return true;
		}
		return false;
	}
	
	/**
	 * Remove the weapon in slot 2.
	 */
	public boolean removeWeapon2() 
	{
		if(getWeapon2() != null)
		{
			weapon2 = null;
			return true;
		}
		return false;
	}
	
}
