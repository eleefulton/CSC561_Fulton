
package gameplay;

/**
 * An interface to allow the observation of a Timer class
 *
 * @author Ethan Fulton
 *
 */
public interface TimeObserver
{
	/**
	 * receive a notification that the time has changed
	 * 
	 * @param time
	 *            the new time
	 */
	public void updateTime(int time);

}
