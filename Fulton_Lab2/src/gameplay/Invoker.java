package gameplay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import lifeform.LifeForm;

/**
 * The Invoker acts as our remote control.
 * It stores the commands and displays the commands that we can use. 
 * It therefore also a GUI.
 * @author Moumouni Noma
 */
public class Invoker extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	protected JButton jbtReload;
	protected JButton jbtAttack;
	protected JButton jbtAcquire;
	protected JButton jbtDrop;
	protected JButton jbtMovePlayer;
	protected JButton jbtNorth;
	protected JButton jbtSouth;
	protected JButton jbtEast;
	protected JButton jbtWest;
	JLabel textLabel, imageLabel; 
	
	//private Environment theWorld;
	private Command reloadCommand;
	private Command attackCommand;
    private Command acquireCommand;
	private Command dropCommand;
	private Command moveCommand;
	private Command turnNorthCommand;
    private Command turnSouthCommand;
	private Command turnWestCommand;
	private Command turnEastCommand;
	
	private LifeForm entity;
	private String clickedButton;
	

	
	/**
	 * Class constructor, it creates and instance of the Invoker
	 * that we can use.
	 * It sets up the InvokerUI that will be used as a remote
	 * control to invoke the command buttons.
	 */
	public Invoker()
	{
		 //theWorld  = Environment.getWorld(); //Set the world to play in
		//setLayout(new BorderLayout());
		setLayout(new GridLayout(1,2)); //two panels are side-by-side.
		
		// Create the command buttons
		jbtReload = new JButton("Reload");
		jbtReload.addActionListener(this);
		jbtAttack = new JButton("Attack");
		jbtAttack.addActionListener(this);
		jbtAcquire = new JButton("Acquire");
		jbtAcquire.addActionListener(this);
		jbtDrop = new JButton("Drop");
		jbtDrop.addActionListener(this);
		jbtMovePlayer = new JButton("Move Player");
		jbtMovePlayer.addActionListener(this);

				
		// Create a panel to hold the Command buttons
		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
		commandPanel.add(jbtReload);
		commandPanel.add(jbtAttack);
		commandPanel.add(jbtAcquire);
		commandPanel.add(jbtDrop);
		commandPanel.add(jbtMovePlayer);
		
		add("West",commandPanel);  // Add panel to the frame
		
		pack();
		setVisible(true);
		
		// Create the control buttons
		jbtNorth = new JButton("N");
		jbtNorth.addActionListener(this);
		//jbtNorth.setIcon(new ImageIcon(Invoker.class.getResource("/image/north.png")));
		jbtWest = new JButton("W");
		jbtWest.addActionListener(this);
		//jbtWest.setIcon(new ImageIcon(Invoker.class.getResource("/image/west.png")));
		jbtEast= new JButton("E");
		jbtEast.addActionListener(this);
		//jbtEast.setIcon(new ImageIcon(Invoker.class.getResource("/image/east.png")));
		jbtSouth = new JButton("S");
		jbtSouth.addActionListener(this);
		//jbtSouth.setIcon(new ImageIcon(Invoker.class.getResource("/image/south.png")));
		JLabel centerControl = new JLabel("<html>Turn<br />Player</html>");

		// Create a panel to hold the turn buttons
		JPanel controlPanel = new JPanel(new BorderLayout());
		controlPanel.add("North", jbtNorth);
		controlPanel.add("South", jbtSouth);
		controlPanel.add("East", jbtEast);
		controlPanel.add("West", jbtWest);
		controlPanel.add("Center", centerControl);
		
		add("East", controlPanel);  // Add panel to the frame
		pack();
		setVisible(true);		
	}
	
	/**
	 * Set the LifeForm to be acted upon by our command controls.
	 * @param lf - The LifeForm acted upon.
	 */
	public void setLifeForm(LifeForm lf) 
	{
		this.entity = lf;
	}
	
	/**
	 * @return - Return the LifeForm instance being acted on.
	 */
	public Object getLifeForm() 
	{
		return entity;
	}
	
	/**
	 * Performs the action initiated by the user.
	 * Also sets the name of the button clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbtReload)
		{
			clickedButton = "Reload";
			//reloadCommand.execute(row, col);
		}
		else if(event.getSource() == jbtAttack)
		{
			clickedButton = "Attack";
			//attackCommand.execute(row, col);
		}
		else if(event.getSource() == jbtAcquire)
		{
			clickedButton = "Acquire";
			//acquireCommand.execute(row, col);
		}
		else if(event.getSource() == jbtDrop)
		{
			clickedButton = "Drop";
			//dropCommand.execute(row, col);
		}
		else if(event.getSource() == jbtMovePlayer)
		{
			clickedButton = "Move";
			//moveCommand.execute(row, col);
		}
		else if(event.getSource() == jbtNorth)
		{
			clickedButton = "North";
			//moveCommand.execute(row, col);
		}
		else if(event.getSource() == jbtSouth)
		{
			clickedButton = "South";
			//moveCommand.execute(row, col);
		}
		else if(event.getSource() == jbtEast)
		{
			clickedButton = "East";
			//moveCommand.execute(row, col);
		}
		else if(event.getSource() == jbtWest)
		{
			clickedButton = "West";
			//moveCommand.execute(row, col);
		}
	}
	
	/**
	 * This sets the command to be executed.
	 * The command type is checked before setting the correct instance.
	 * Tests to make sure command attaches to right slot.
	 * Each command is assign to a specific slot.
	 * @param cmd - The command to be set.
	 * @param slot - the slot where to set  the command.
	 */
	public void setCommand(Command cmd, int slot) 
	{	
		switch(slot)
		{
		case 1:
			this.reloadCommand = cmd;
			break;
		case 2:
			this.attackCommand = cmd;
			break;
		case 3:
			this.acquireCommand = cmd;
			break;
		case 4:
			this.dropCommand = cmd;
			break;
		case 5:
			this.moveCommand = cmd;
		case 6:
			this.turnNorthCommand = cmd;
		case 7:
			this.turnSouthCommand = cmd;
		case 8:
			this.turnEastCommand = cmd;
		case 9:
			this.turnWestCommand = cmd;
		default:
			this.turnNorthCommand = cmd;
		}			
	}


	/**
	 * @param slot - the slot of the command to return.
	 * @return - returns the Command at the slot provided.
	 */
	public Command getCommand(int slot) 
	{
		switch(slot)
		{
		case 1:
			return reloadCommand;
		case 2:
			return attackCommand;
		case 3:
			return acquireCommand;
		case 4:
			return dropCommand;
		case 5:
			return moveCommand;
		case 6:
			return turnNorthCommand;
		case 7:
			return turnSouthCommand;
		case 8:
			return turnEastCommand;
		case 9:
			return turnWestCommand;
		default:
			return turnNorthCommand;
		}			
	}

	/**
	 * @return Returns the name of the button that was clicked.
	 */
	public String getClickName() 
	{
		return clickedButton;
	}
	
}
