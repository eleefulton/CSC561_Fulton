package weapon;

/**
 * a class to handle the similar functions of a weapon like firing, reloading,
 * and getting info about the weapon
 * 
 * @author Ethan Fulton
 *
 */
public abstract class GenericWeapon implements Weapon
{
	private int baseDamage;
	private int maxRange;
	private int rateOfFire;
	private int maxAmmo;
	private int remainingAmmo;
	private int numAttachments;
	private int shotsFired;

	public GenericWeapon(int bd, int mr, int rof, int ma)
	{
		baseDamage = bd;
		maxRange = mr;
		rateOfFire = rof;
		maxAmmo = ma;
		shotsFired = 0;
		numAttachments = 0;
		remainingAmmo = maxAmmo;
	}

	/**
	 * fire the weapon if shots left this round, if not return 0 damage
	 */
	@Override
	public int FireWeapon()
	{
		if (shotsFired < rateOfFire)
		{
			remainingAmmo--;
			shotsFired++;
			return baseDamage;
		} else
		{
			return 0;
		}
	}

	@Override
	public void Reload()
	{
		remainingAmmo = maxAmmo;

	}

	@Override
	public int getRange()
	{
		return maxRange;
	}

	@Override
	public int getBaseDamage()
	{
		return baseDamage;
	}

	@Override
	public int getRemainingAmmo()
	{
		return remainingAmmo;
	}

	@Override
	public int getMaxAmmo()
	{
		return maxAmmo;
	}

	@Override
	public int getRateOfFire()
	{
		return rateOfFire;
	}

	@Override
	public int getShotsFired()
	{
		return shotsFired;
	}

	@Override
	public void resetShotsFired()
	{
		shotsFired = 0;

	}

	@Override
	public void setReaminingAmmo(int newAmmo)
	{
		remainingAmmo = newAmmo;

	}

	@Override
	public void updateTime(int time)
	{
		resetShotsFired();
	}

}
