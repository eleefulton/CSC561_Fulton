package recovery;

/**
 * A class for no recovery, current life points remain the same
 * 
 * @author Ethan Fulton
 *
 */
public class RecoveryNone implements RecoveryBehavior
{

	/**
	 * @return current life, no recovery takes place
	 */
	@Override
	public int calculateRecovery(int currentLife, int maxLife)
	{
		return currentLife;
	}

}
