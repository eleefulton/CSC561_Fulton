package weapon;

import gameplay.TimeObserver;

/**
 * An interface for taking care of basic weapon functions
 * 
 * @author Ethan Fulton
 *
 */
public interface Weapon extends TimeObserver
{

	/**
	 * fire the weapon
	 * 
	 * @return damage done
	 */
	public int fireWeapon(int distance);

	/**
	 * reset the amount of shots
	 */
	public void Reload();

	/**
	 * 
	 * @return the max range of the weapon
	 */
	public int getRange();

	/**
	 * 
	 * @return the base damage the weapon does
	 */
	public int getBaseDamage();

	/**
	 * 
	 * @return the ammo left in the weapon
	 */
	public int getRemainingAmmo();

	/**
	 * 
	 * @return the max ammo a weapon can have
	 */
	public int getMaxAmmo();

	/**
	 * 
	 * @return the number of times a weapon can be fired per round
	 */
	public int getRateOfFire();

	/**
	 * 
	 * @return the number of times the weapon has fired this round
	 */
	public int getShotsFired();

	/**
	 * reset the number of times a weapon has fired for a round
	 */
	public void resetShotsFired();

	/**
	 * set the ammo amount left
	 */
	public void setRemainingAmmo(int newAmmo);

	/**
	 * increment shots fired
	 */
	public void updateShotsFired();
	
	/**
	 * Get Number of attachments
	 */
	public int getNumAttachments();
	/**
	 * add new attachment
	 */
	public void addAttachment();
}