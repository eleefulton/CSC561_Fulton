package weapon;

public class Stabilizer extends Attachment
{

	public Stabilizer(Weapon w) 
	{
		super(w);
	}

	@Override
	public void updateTime(int time) 
	{
		
	}
	
	/**
	 * @return Return the modified damage of decorated weapon If the target is out
	 *         of range, still expend ammo but do no damage If the target is in
	 *         range but out of shots / ammo, do no damage
	 *  This reset the ammo to zero
	 *  Reloads the weapon
	 *  and increases the damage by 25%.
	 */
	@Override
	public int fireWeapon(int distance)
	{
		int damage = (int) (myWeapon.fireWeapon(distance) * 1.25);
		this.setRemainingAmmo(0);
		this.Reload();
		return damage;
	}

}
