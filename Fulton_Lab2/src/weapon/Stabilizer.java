package weapon;

/**
 * This holds the functionalities of the stabilizer attachment.
 * The Stabilizer provides auto reload ability. Every time the weapon is fired, 
 * if it is reduced to 0 ammo the weapon is automatically reloaded.
 *  In addition it increases the damage done by the weapon by 25% (always rounded down). 
 * @author Moumouni Noma
 */
public class Stabilizer extends Attachment
{

	/**
	 * Class default constructor.
	 * @param w the weapon on which the attachment is applied to.
	 */
	public Stabilizer(Weapon w)
	{
	      super(w);
	}

	@Override
	public void updateTime(int time) 
	{

	}
	
	/**
	 *  This reset the ammo to zero
	 *  Reloads the weapon and increases the damage by 25%.
	 *  @return Return the modified damage of decorated weapon If the target is out
	 *         of range, still expend ammo but do no damage If the target is in
	 *         range but out of shots / ammo, do no damage
	 */
	@Override
	public int fireWeapon(int distance)
	{
		int damage = (int) ((double)this.myWeapon.fireWeapon(distance) * 1.25);
		if (this.getRemainingAmmo() == 0)
		{
			this.Reload();
		}
		return damage;
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
