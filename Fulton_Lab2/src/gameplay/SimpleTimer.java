package gameplay;

import java.util.ArrayList;

public class SimpleTimer extends Thread implements Timer
{
	/**
	 * @author Ethan Fulton A class to keep track of the current round and notify
	 *         observers of it
	 */

	private int round;
	private ArrayList<TimeObserver> theObservers;
	private int waitTime;

	public SimpleTimer()
	{
		this(0);
	}

	public SimpleTimer(int i)
	{
		round = 0;
		theObservers = new ArrayList<TimeObserver>();
		waitTime = i;
	}

	/**
	 * @return Return the current round.
	 */
	public int getRound()
	{
		return round;
	}

	/**
	 * 
	 * @return Return the list of observers.
	 */
	public ArrayList<TimeObserver> getObservers()
	{
		return theObservers;
	}

	/**
	 * add a new observer to the list
	 */
	@Override
	public void addTimeObserver(TimeObserver observer)
	{
		theObservers.add(observer);
	}

	/**
	 * remove an observer from the list
	 */
	@Override
	public void removeTimeObserver(TimeObserver observer)
	{
		theObservers.remove(observer);
	}

	/**
	 * change the round and notify observers
	 */
	@Override
	public void timeChanged()
	{
		round++;
		for (int i = 0; i < theObservers.size(); i++)
		{
			theObservers.get(i).updateTime(round);
		}
	}

	public void run()
	{
		while (round < 5)
		{
			try
			{
				Thread.sleep(waitTime);
				this.timeChanged();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
