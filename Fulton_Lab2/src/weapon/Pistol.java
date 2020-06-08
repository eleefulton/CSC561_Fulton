package weapon;

import exceptions.NegativeDistanceException;

/**
 * A simple pistol where damage = base damage * (max range - distance + 5)/max
 * range
 * 
 * @author Ethan Fulton
 *
 */
public class Pistol extends GenericWeapon implements Weapon
{

	public Pistol(int bd, int mr, int rof, int ma)
	{
		super(bd, mr, rof, ma);
	}

	/**
	 * @return Return the adjusted damage based on range
	 */
	@Override
	public int fireWeapon(int distance)
	{
		if (distance < 0)
		{
			throw new NegativeDistanceException();
		}
		int newDamage = (int) (baseDamage * ((double) (maxRange - distance + 5) / maxRange));
		if (this.getShotsFired() < this.getRateOfFire() && distance <= this.getRange() && this.getRemainingAmmo() > 0)
		{
			remainingAmmo--;
			shotsFired++;
			return newDamage;
		} else if (distance > this.getRange())
		{
			remainingAmmo--;
			return 0;
		} else
			return 0;
	}

}
