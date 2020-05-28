package recovery;

/**
 * A class to handle fractional recovery, at each recovery a predetermined
 * percentage is added to current health
 * 
 * @author Ethan Fulton
 *
 */
public class RecoveryFractional implements RecoveryBehavior
{

	private double percentRecovery;

	public RecoveryFractional(double percent)
	{
		percentRecovery = percent;
	}

	/**
	 * @return the fractional recovery amount based on percentRecovery of current
	 *         life if above 0
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife)
	{
		if (currentLife > 0)
		{
			int recoveryAmount = (int) (currentLife * percentRecovery);
			return currentLife + recoveryAmount > maxLife ? maxLife : currentLife + recoveryAmount;
		} else
			return 0;
	}

}
