package exceptions;

/**
 * This handles a situation where at least one of the LifeForm is not present 
 * in the Environment. This is a custom exception.
 * 
 * @author Moumouni Noma
 */
public class EnvironmentException extends Exception
{

	/**
	 * The serial number of the Exception
	 */
	private static final long serialVersionUID = 1L;

	public EnvironmentException()
	{
		super("At least One of the of the Lifeforms is not the Environment.");
	}

}
