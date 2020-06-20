package gameplay;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import lifeform.LifeForm;

/**
 * The Invoker acts as our remote.
 * It stores the commands and displays the commands that we can use. 
 * It therefore also a GUI.
 * @author Moumouni Noma
 */
public class Invoker extends JFrame implements ActionListener
{	
	Command[] theCommands;  // the commands used on the remote control
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
	JLabel textLabel; 
	
	private LifeForm entity;  // The entity being manager by the Invoker
	private String clickedButton;
	

	
	/**
	 * Class constructor, it creates and instance of the Invoker
	 * that we can use.
	 * It sets up the InvokerUI that will be used as a remote
	 * control to invoke the command buttons.
	 * Sets all the slots with a NoCommand instance.
	 */
	public Invoker()
	{
		theCommands = new Command[9];
		Command noCommand = new NoCommand(); 
		for (int i = 0; i < 9; i++) 
		{
			theCommands[i] = noCommand;
		}

        	createUIDisplay();	
		
	}
	
	/**
	 * Creates the UI display of the Invoker;
	 */
	public void createUIDisplay()
	{
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
		textLabel = new JLabel("Click on any button to enjoy our UI!");

				
		// Create a panel to hold the Command buttons
		JPanel commandPanel = new JPanel();
		commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
		commandPanel.add(jbtReload);
		commandPanel.add(jbtAttack);
		commandPanel.add(jbtAcquire);
		commandPanel.add(jbtDrop);
		commandPanel.add(jbtMovePlayer);
		commandPanel.add(textLabel);
		add(commandPanel);  // Add panel to the frame
		
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
		
		add(controlPanel);  // Add panel to the frame
		pack();
		setLocation(800, 0);  //Place it on the Right so that it does not overlap
		setVisible(true);	 // with the main UI.
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
	public LifeForm getLifeForm() 
	{
		return entity;
	}
	
	/**
	 * Performs the action initiated by the user.
	 * Also sets the name of the button clicked.
	 * and returns the name of the button clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (this.entity != null)
		{
			if (event.getSource() == jbtReload)
			{
				buttonWasPressed(Command.RELOAD);
			}
			else if(event.getSource() == jbtAttack)
			{
				buttonWasPressed(Command.ATTACK);
			}
			else if(event.getSource() == jbtAcquire)
			{
				buttonWasPressed(Command.ACQUIRE);
			}
			else if(event.getSource() == jbtDrop)
			{
				buttonWasPressed(Command.DROP);
			}
			else if(event.getSource() == jbtMovePlayer)
			{
				buttonWasPressed(Command.MOVE);
			}
			else if(event.getSource() == jbtNorth)
			{
				buttonWasPressed(Command.NORTH);
			}
			else if(event.getSource() == jbtSouth)
			{
				buttonWasPressed(Command.SOUTH);
			}
			else if(event.getSource() == jbtEast)
			{
				buttonWasPressed(Command.EAST);
			}
			else if(event.getSource() == jbtWest)
			{
				buttonWasPressed(Command.WEST);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "A LifeForm is needed to be acted on.\n"
					+ " Please set one to continue.");
		}
	}
	
	/**
	 * This sets the command to be executed.
	 * @param cmd - The command to be set.
	 * @param slot - the slot where to set  the command.
	 */
	public void setCommand(Command cmd, int slot) 
	{ 
		theCommands[slot] = cmd;
	}

	/**
	 * @param slot - the slot of the command to return.
	 * @return - returns the Command at the slot provided.
	 * Each command is assign to a specific slot.
	 */
	public Command getCommand(int slot) 
	{
		return theCommands[slot];
	}

	/**
	 * @return Returns the name of the button that was clicked.
	 */
	public String getClickName() 
	{
		return clickedButton;
	}
	
	/**
	 * Performs the action initiated by the user.
	 * Also sets the name of the button clicked.
	 * Each command is assign to a specific slot.
	 * Set the name of the button clicked and displays
	 * the name of the button in the label.
	 */
	public void buttonWasPressed(int slot) 
	{
		Command cmd;
		switch(slot)
		{
		case 0:
			clickedButton = "Reload";
			cmd = new ReloadCommand();
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 1:
			clickedButton = "Attack";
			cmd =  new AttackCommand();
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 2:
			clickedButton = "Acquire";
			cmd = new AcquireCommand();
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 3:
			clickedButton = "Drop";
			cmd = new DropCommand();
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 4:
			clickedButton = "Move";
			cmd = new MoveCommand();
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 5:
			clickedButton = "North";
			cmd = new TurnCommand(LifeForm.NORTH);
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 6:
			clickedButton = "South";
			cmd = new TurnCommand(LifeForm.SOUTH);
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 7:
			clickedButton = "East";
			cmd = new TurnCommand(LifeForm.EAST);
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		case 8:
			clickedButton = "West";
			cmd = new TurnCommand(LifeForm.WEST);
			cmd.execute(entity.getRowCell(), entity.getColCell());
			textLabel.setText(clickedButton);
			break;
		default:
			break;
		}
					
	}	
	/*
	*/
	/**
	 * Performed the action of the slot's command.
	 * @param slot
	 *//*
	public void buttonWasPushed(int slot) 
	{ 
		theCommands[slot].execute(entity.getRowCell(), entity.getColCell());
	}*/
	
	/**
	 * This override the toString method to display the different commands
	 * their slots, and the names.
	 */
	@Override
	public String toString() 
	{
		StringBuffer stringBuff = new StringBuffer(); 
		stringBuff.append("\n------ Remote Control -------\n"); 
		for (int i = 0; i < theCommands.length; i++) 
		{
			stringBuff.append("[slot " + i + "]  " + 
		           theCommands[i].getClass().getName() + "\n");
		}
		return stringBuff.toString(); 
	}

}
