package weapon;
/**
 * 
 * @author CASquires
 *
 */
public class ChainGun extends GenericWeapon implements Weapon{

	public ChainGun(int bd, int mr, int rof, int ma) {
		super(bd, mr, rof, ma);
	}

	@Override
	public int fireWeapon(int distance) 
	{
		if (getShotsFired() < getRateOfFire())
		{
			remainingAmmo--;
			shotsFired++;
			int damage = super.getBaseDamage() * (distance / super.getRange() );
			return damage;
			
			//damage = base damage x (distance/max range);
		} else
		{
			return 0;
		}
	}

}
