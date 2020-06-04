package weapon;

/**
 * Attachment decorator for Weapons that has the ability to modify aspects of
 * the weapon
 * 
 * @author Ethan Fulton
 *
 */

public abstract class Attachment implements Weapon
{
	private Weapon myWeapon;

	public Attachment(Weapon w)
	{
		myWeapon = w;
	}

	@Override
	public int fireWeapon(int distance)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void Reload()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getRange()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBaseDamage()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRemainingAmmo()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxAmmo()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRateOfFire()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getShotsFired()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetShotsFired()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setReaminingAmmo(int newAmmo)
	{
		// TODO Auto-generated method stub

	}

}
