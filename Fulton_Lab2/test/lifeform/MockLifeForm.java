package lifeform;

public class MockLifeForm extends LifeForm
{
	protected int time = 0;

	public MockLifeForm(String name, int points)
	{
		super(name, points);
	}

	public MockLifeForm(String name, int points, int attack)
	{
		this(name, points);
		attackStrength = attack;
	}

	@Override
	public void updateTime(int t)
	{
		time = t;

	}

	public int getTime()
	{
		return time;
	}

}