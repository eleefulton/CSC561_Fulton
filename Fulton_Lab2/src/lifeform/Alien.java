package lifeform;

import exceptions.AlienConstructorException;
import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

/**
 * Keep track of information pertaining to Aliens including their max hit points
 * and recovery behavior
 * 
 * @author Ethan Fulton
 *
 */
public class Alien extends LifeForm
{

	private int maxLifePoints;
	private RecoveryBehavior myRecoveryBehavior;
	private int recoveryRate;

	public Alien(String name, int points)
	{
		this(name, points, new RecoveryNone());
	}

	public Alien(String name, int points, RecoveryBehavior rb)
	{
		super(name, points);
		maxLifePoints = points;
		myRecoveryBehavior = rb;
		attackStrength = 10;
	}

	public Alien(String name, int points, RecoveryBehavior rb, int rr) throws AlienConstructorException
	{
		this(name, points, rb);
		if (rr <= 0)
		{
			throw new AlienConstructorException();
		}
		recoveryRate = rr;
	}

	/**
	 * calculate the aliens recovery based on it's RecoveryBehavior
	 */
	protected void recover()
	{
		if (myRecoveryBehavior != null)
		{
			currentLifePoints = myRecoveryBehavior.calculateRecovery(currentLifePoints, maxLifePoints);
		}
	}

	/**
	 * 
	 * @return Return the recovery rate that determines on what round to update
	 */
	public int getRecoveryRate()
	{
		return recoveryRate;
	}

	@Override
	public void updateTime(int time)
	{
		if (time % recoveryRate == 0)
		{
			recover();
		}

	}

	/**
	 * change the aliens recovery rate
	 * 
	 * @param i
	 *            the value to make the aliens recovery rate
	 */
	public void setRecoveryRate(int i)
	{
		recoveryRate = i;

	}

}
