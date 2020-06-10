package environment;

import java.util.ArrayList;

import lifeform.LifeForm;
import weapon.Weapon;

/* A Cell that can hold a LifeForm.
 *
 */
public class Cell
{

	private LifeForm myLifeForm;
	private Weapon[] weapons = new Weapon[2];

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
			return true;
		} else
			return false;
	}

	/**
	 * Removes the LifeForm from the cell
	 */
	public void removeLifeForm()
	{
		myLifeForm = null;

	}
	
	public Weapon[] getWeapons()
	{
		return weapons;
	}

	public void addWeapon(Weapon w) 
	{
		if (weapons[0] == null)
			weapons[0] = w;
		else if (weapons[1] == null)
			weapons[1] = w;
	}

	public void removeWeapon(Weapon w) {
		if(weapons[0] == w)
			weapons[0] =null;
		if(weapons[1] == w)
			weapons[1] =null;
	}
}
