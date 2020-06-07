package weapon;

import exceptions.NegativeDistanceException;

/**
 * 
 * @author CASquires
 * Booster attachment for weapons
 */
public class Booster extends Attachment{

	public Booster(Weapon w) {
		super(w);
	}

	@Override
	public void updateTime(int time) {
		
	}

	/**
	 * FireWeapon
	 * Uses the base damage of the weapon * 1( weapon remaining ammo/ weapon max ammo)
	 * Returns negative distance exception if distance < 0 
	 * @param distance from weapon to enemy
	 * @return damage
	 */
	@Override
	public int fireWeapon(int distance) throws NegativeDistanceException
	{
		if (distance >= 0)
		{
			int damage = (int)(this.myWeapon.fireWeapon(distance) * (1 + ((double)this.myWeapon.getRemainingAmmo()/(double)this.myWeapon.getMaxAmmo())));
			return damage;
		} else
		{
			throw new NegativeDistanceException();
		}
	}
}
