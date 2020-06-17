package gameplay;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;

import javax.swing.*;

import environment.Environment;

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
	JLabel textLabel, imageLabel; 
	
	private Environment theWorld;
	private Command reloadCommand;
	private Command attackCommand;
    private Command acquireCommand;
	private Command dropCommand;
	private Command moveCommand;

	/**
	 * Class constructor, it creates and instance of the Invoker
	 * that we can use.
	 */
	
	public Invoker()
	{
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
		JButton jbtMovePlayer = new JButton("Move Player");

				
		// Create a panel to hold the buttons
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
		JButton jbtNorth = new JButton("N");
		//jbtNorth.setIcon(new ImageIcon(Invoker.class.getResource("/image/north.png")));
		JButton jbtWest = new JButton("W");
		//jbtWest.setIcon(new ImageIcon(Invoker.class.getResource("/image/west.png")));
		JButton jbtEast= new JButton("E");
		//jbtEast.setIcon(new ImageIcon(Invoker.class.getResource("/image/east.png")));
		JButton jbtSouth = new JButton("S");
		//jbtSouth.setIcon(new ImageIcon(Invoker.class.getResource("/image/south.png")));
		JLabel centerControl = new JLabel("<html>Turn<br />Player</html>");

		// Create a panel to hold the buttons
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
	 * Performs the action initiated by the user.
	 */
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == jbtReload)
		{
			jbtReload.setBackground(Color.RED);
		}
	}

	/**
	 * @return - returns the name of the command pressed.
	 */
	public String inputMethod() 
	{
		jbtReload.doClick(5000);
		return "worked";
	}


	/**
	 * This sets the command to be executed.
	 * The command type is checked before setting the correct instance.
	 * @param cmd - The command to be set.
	 */
	public void setCommand(Command cmd) 
	{
		if(cmd instanceof ReloadCommand)
		{
			this.reloadCommand = cmd;
		}
		else if(cmd instanceof MoveCommand)
		{
			this.attackCommand = cmd;
		}
		else if(cmd instanceof AcquireCommand)
		{
			this.acquireCommand = cmd;
		}
		else if(cmd instanceof DropCommand)
		{
			this.dropCommand = cmd;
		}
		else
		{
			this.moveCommand = cmd;
		}
		
		/*switch(name)
		{
		case "reload":
			this.moveCommand = cmd;
			break;
		case "attack":
			this.attackCommand = cmd;
			break;
		case "acquire":
			this.acquireCommand = cmd;
			break;
		case "drop":
			this.dropCommand = cmd;
			break;
		case "move":
			this.dropCommand = cmd;
		default:
		}*/			
		
	}


	/**
	 * @return - Returns the Commands.
	 */
	public Object getCommand() 
	{
		return moveCommand;
	}

}
