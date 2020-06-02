/**
 * Interface to create a timer that can update it's observers when a new round occurs
 * @author Ethan Fulton
 */
package gameplay;

public interface Timer
{

	/**
	 * add a new observer to the timer
	 * 
	 * @param observer
	 *            the TimeObserver to be added
	 */
	public void addTimeObserver(TimeObserver observer);

	/**
	 * remove an observer from the list of observers
	 * 
	 * @param observer
	 *            the TimeObserver to be removed
	 */
	public void removeTimeObserver(TimeObserver observer);

	/**
	 * notify observers that the time changed
	 */
	public void timeChanged();

}
