package exceptions;

/**
 * This handles a situation where there is already an instance of 
 * the singleton. This is a custom exception.
 * 
 * @author Moumouni Noma
 */
public class ExistingWorldException extends Exception
{

	/**
	 * The serial number of the Exception
	 */
	private static final long serialVersionUID = 1L;

	public ExistingWorldException()
	{
		super("Friendly reminder: I am the Singleton, there can only be one of me.");
	}

}