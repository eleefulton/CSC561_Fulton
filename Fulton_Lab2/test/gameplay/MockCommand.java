package gameplay;
/**
 * 
 * @author Moumouni Noma
 *
 */

public class MockCommand implements Command 
{
	private String name;
	
	public MockCommand(String name)
	{
		this.name = name;
	}

	@Override
	public void execute(int r, int c) 
	{
		

	}
	
	/*
	 * Return the name of the commmand.
	 */
	public String getName()
	{
		return name;
	}


}
