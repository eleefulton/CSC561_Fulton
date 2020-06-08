package weapon;

import exceptions.NegativeDistanceException;

/**
 * This class implements the functionalities of the Plasma Cannon Weapon. In the
 * case of the Plasma Cannon, every time it is fired it weakens the power it has
 * for the next shot. So a fully loaded Plasma Cannon does more damage than one
 * with only a shot left.
 * 
 * @author Moumouni Noma
 */
public class PlasmaCannon extends GenericWeapon implements Weapon
{
	/**
	 * Default class constructor
	 * 
	 * @param damage
	 *            - the base damage of the Weapon
	 * @param range
	 *            - the max range of the Weapon
	 * @param rof
	 *            - the rate of fire of the weapon
	 * @param maxAmmo
	 *            - the max ammo of the weapon.
	 */
	public PlasmaCannon(int damage, int range, int rof, int maxAmmo)
	{
		super(damage, range, rof, maxAmmo);
		// this.numAttachments = 0;
	}

	/**
	 * This recalculates the damage for a Plasma Cannon damage = baseDamage *
	 * (actualAmmo/maxAmmo)
	 * 
	 * @return Returns the modified damage. If the target is out of range, still
	 *         expend ammo but do no damage. If the target is in range but out of
	 *         shots / ammo, do no damage
	 */
	@Override
	public int fireWeapon(int distance) throws NegativeDistanceException
	{
		int damage = (int) (baseDamage * ((double) this.getRemainingAmmo() / this.getMaxAmmo()));
		if (distance < 0)
		{
			throw new NegativeDistanceException();
		} else if (this.getShotsFired() < this.getRateOfFire() && distance <= this.getRange())
		{
			remainingAmmo--;
			shotsFired++;
			return damage;
		} else if (distance > this.getRange())
		{
			remainingAmmo--;
			return 0;
		} else
			return 0;
	}

}
