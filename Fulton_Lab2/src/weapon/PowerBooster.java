package weapon;

/**
 * The power booster increases the damage done to a target the more ammo is left
 * in the weapon
 * 
 * damage = base damage * (1 + (remaining ammo / max ammo))
 *
 */
public class PowerBooster extends Attachment
{

	public PowerBooster(Weapon w)
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
		int newDamage = (int) (myWeapon.fireWeapon(distance) * (1 + (double) (getRemainingAmmo() + 1) / getMaxAmmo()));
		return newDamage;
	}

	@Override
	public int getNumAttachments()
	{
		return myWeapon.getNumAttachments();
	}

	@Override
	public void addAttachment()
	{
		myWeapon.addAttachment();
	}

}