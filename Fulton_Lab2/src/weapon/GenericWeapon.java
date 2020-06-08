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
	protected int baseDamage;
	protected int maxRange;
	protected int rateOfFire;
	private int maxAmmo;
	protected int remainingAmmo;
	private int numAttachments;
	protected int shotsFired;

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
	public int fireWeapon(int distance)
	{
		if (shotsFired < rateOfFire && distance <= maxRange)
		{
			remainingAmmo--;
			shotsFired++;
			return baseDamage;
		} else if (distance > maxRange)
		{
			remainingAmmo--;
			shotsFired++;
			return 0;
		} else
			return 0;
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
	public void setRemainingAmmo(int newAmmo)
	{
		remainingAmmo = newAmmo;

	}

	@Override
	public void updateTime(int time)
	{
		resetShotsFired();
	}

	@Override
	public void updateShotsFired()
	{
		shotsFired++;
	}
	
	@Override
	public int getNumAttachments()
	{
		return numAttachments;
	}
	@Override
	public void addAttachment() 
	{
		if (numAttachments < 2)
			numAttachments++;
	}
}