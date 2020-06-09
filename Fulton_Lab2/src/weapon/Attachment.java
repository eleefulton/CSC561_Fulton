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
		if (w.getNumAttachments() < 2)
		{
			myWeapon = w;
			myWeapon.addAttachment();
		}
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
	public void reload()
	{
		myWeapon.reload();

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

	@Override
	public void addToCell()
	{
		myWeapon.addToCell();
	}

	@Override
	public void removeFromCell()
	{
		myWeapon.removeFromCell();
	}

	@Override
	public boolean getInCell()
	{
		return myWeapon.getInCell();
	}

}