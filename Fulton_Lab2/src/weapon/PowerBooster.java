package weapon;

/**
 * The scope increases the damage done to a target the further away it is
 * 
 * damage = base damage * (1 + (distance) / max range)
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
		return (int) (myWeapon.fireWeapon(distance) * (1 + (double) (distance) / getRange()));
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