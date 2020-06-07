package exceptions;

/**
 * This handles a negative distance rate exception.
 * This is a custom exception.
 * @author Moumouni Noma
 */
public class NegativeDistanceException  extends IllegalArgumentException
{

	/**
	 * The serial number of the Exception
	 */
	private static final long serialVersionUID = 1L;
	
	public NegativeDistanceException()
	{
		super("The distance cannot be negative.");
	}

}