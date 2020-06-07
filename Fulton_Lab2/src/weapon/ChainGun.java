package weapon;

import exceptions.NegativeDistanceException;

/**
 * 
 * @author CASquires
 * Chain Gun class
 */
public class ChainGun extends GenericWeapon implements Weapon{

	public ChainGun(int bd, int mr, int rof, int ma) {
		super(bd, mr, rof, ma);
	}

	/**
	 * Fire Weapon
	 * Chain Gun does base damage x (distance/max range) 
	 * If the distance is negative, it returns an exception
	 * If the distance is further than the range of the weapon, no damage
	 *    is done, but that ammo is spent
	 * 
	 * @param distance from weapon to enemy
	 * @return damage
	 */
	@Override
	public int fireWeapon(int distance) throws NegativeDistanceException 
	{
		System.out.println("distance: "+distance);
		System.out.println("Range: "+ super.getRange());
		if (distance < 0 )
		{
			throw new NegativeDistanceException();
		} else if( distance > super.getRange())
		{
			remainingAmmo--;
			return 0;
		}
			
		System.out.println("shots fired: "+ getShotsFired());
		System.out.println("rate of fire: "+ super.getRateOfFire());
		if (super.getShotsFired() < super.getRateOfFire())
		{
			System.out.println("In the if");
			System.out.println("super.getBaseDamage() "+ super.getBaseDamage());
			remainingAmmo--;
			shotsFired++;
			System.out.println((float)super.getBaseDamage() * ((float)distance / (float)super.getRange() ));
			int damage = (int)((float)super.getBaseDamage() * ((float)distance / (float)super.getRange() ));
			return damage;
		} else
		{
			return 0;
		}
	}

}
