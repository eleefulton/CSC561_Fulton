package weapon;

/**
 * The scope increases the damage done to a target the further away it is
 * 
 * damage = base damage * (1 + (max range - distance) / max range)
 * 
 * @author Ethan Fulton
 *
 */
public class Scope extends Attachment
{

	public Scope(Weapon w)
	{
		super(w);
	}

	@Override
	public void updateTime(int time)
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @return Return the modified damage of decorated weapon If the target is out
	 *         of range, still expend ammo but do no damage If the target is in
	 *         range but out of shots / ammo, do no damage
	 */
	@Override
	public int fireWeapon(int distance)
	{
		int newDamage = (int) (getBaseDamage() * (1 + (double) (getRange() - distance) / getRange()));
		if (getShotsFired() < getRateOfFire() && distance <= getRange())
		{
			setRemainingAmmo(-1);
			updateShotsFired();
			return newDamage;
		} else if (distance > getRange())
		{
			setRemainingAmmo(-1);
			return 0;
		} else
			return 0;
	}

}