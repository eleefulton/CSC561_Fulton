package weapon;

import exceptions.NegativeDistanceException;

/**
 *
 * @author Moumouni Noma
 *
 */
public class PlasmaCannon extends GenericWeapon implements Weapon
{
	/**
	 * Default class constructor
	 * @param damage
	 * @param range
	 * @param rof
	 * @param maxAmmo
	 */
	public PlasmaCannon(int damage, int range, int rof, int maxAmmo)
	{
		super(damage, range, rof, maxAmmo);
	}
	
	/**
	 * This recalculates the damage for a Plasma Cannon
	 *  damage = baseDamage * (actualAmmo/maxAmmo)
	 */
	@Override
	public int fireWeapon(int distance) throws NegativeDistanceException
	{
		int damage = (int) (baseDamage * ((double) this.getRemainingAmmo() / this.getMaxAmmo()));
		if(distance < 0)
		{
			throw new NegativeDistanceException();
		}
		else if (this.getShotsFired() < this.getRateOfFire() && distance <= this.getRange())
		{
			remainingAmmo--;
			shotsFired++;
			return damage;
		} 
		else if (distance > this.getRange())
		{
			remainingAmmo--;
			return 0;
		}
		else
			return 0;
	}

}
