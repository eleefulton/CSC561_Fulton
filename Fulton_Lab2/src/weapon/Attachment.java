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
	protected Weapon myWeapon;

	public Attachment(Weapon w)
	{
		myWeapon = w;
	}

	public Weapon getWeapon()
	{
		return myWeapon;
	}

	@Override
	public int fireWeapon(int distance)
	{
		return myWeapon.fireWeapon(distance);
	}

	@Override
	public void Reload()
	{
		myWeapon.Reload();

	}

	@Override
	public int getRange()
	{
		return myWeapon.getRange();
	}

	@Override
	public int getBaseDamage()
	{
		return myWeapon.getBaseDamage();
	}

	@Override
	public int getRemainingAmmo()
	{
		return myWeapon.getRemainingAmmo();
	}

	@Override
	public int getMaxAmmo()
	{
		return myWeapon.getMaxAmmo();
	}

	@Override
	public int getRateOfFire()
	{
		return myWeapon.getRateOfFire();
	}

	@Override
	public int getShotsFired()
	{
		return myWeapon.getShotsFired();
	}

	@Override
	public void resetShotsFired()
	{
		myWeapon.resetShotsFired();

	}

	@Override
	public void setRemainingAmmo(int newAmmo)
	{
		myWeapon.setRemainingAmmo(newAmmo);

	}

	@Override
	public void updateShotsFired()
	{
		myWeapon.updateShotsFired();
	}

}