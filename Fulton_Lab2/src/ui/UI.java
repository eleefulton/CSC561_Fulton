package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.geom.Arc2D;
import java.awt.geom.Arc2D.Double;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.Attachment;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

/**
 * UI class, creates board and pieces
 * @author Caitlin Squires
 *
 */
public class UI extends JFrame{

	private Environment theWorld;
	
	public UI()
	{
		theWorld = Environment.getWorld();
		createGrid();
	}
	public UI(Environment e)
	{
		theWorld = e;
		createGrid();
	}
	
	/**
	 * CreateGrid, uses the environment to create the board
	 * Adds lifeforms and weapons as it creates the board
	 */
	public void createGrid()
	{
		setLayout(new BorderLayout());
 
		 JButton textButton, imageButton;
		 JLabel textLabel, imageLabel; 
		 
		 textLabel = new JLabel("North");
		 add("North",textLabel);
	 
		 textButton = new JButton("A Button");
		 add("East",textButton);

//		 JPanel centerPanel = new JPanel(new GridLayout(theWorld.getNumberOfRows(),theWorld.getNumberOfColumns()));
//		 centerPanel.setBackground(new Color(255,255,255));
//		 JLabel[][] labelArray = new JLabel[theWorld.getNumberOfRows()][theWorld.getNumberOfColumns()];
//		 
//		 for (int r=0;r< theWorld.getNumberOfRows(); r++)
//		 {
//			 for (int c=0;c<theWorld.getNumberOfColumns();c++)
//			 {
//				 labelArray[r][c] = new JLabel();
//				 labelArray[r][c].setBorder(BorderFactory.createLineBorder(Color.black));
//				 
//				 BufferedImage img = createSpace();
//				 if (theWorld.getLifeForm(r, c) != null)
//					 createLifeForm(img,theWorld.getLifeForm(r, c));
//				 if (theWorld.getWeapon1(r, c) != null )
//					 createWeapon(img,theWorld.getWeapon1(r, c), 1);
//				 if(theWorld.getWeapon2(r, c) != null )
//					 createWeapon(img,theWorld.getWeapon2(r, c), 2);
//				 
//			     labelArray[r][c].setIcon(new ImageIcon(img));
//				 centerPanel.add(labelArray[r][c]);
//			 }
//		 }
//		 
//		 add("Center",centerPanel);
//		 pack();
//		 setVisible(true);
		 updateGrid();

	}
	
	public void updateGrid()
	{
		JPanel centerPanel = new JPanel(new GridLayout(theWorld.getNumberOfRows(),theWorld.getNumberOfColumns()));
		 centerPanel.setBackground(new Color(255,255,255));
		 JLabel[][] labelArray = new JLabel[theWorld.getNumberOfRows()][theWorld.getNumberOfColumns()];
		 
		 for (int r=0;r< theWorld.getNumberOfRows(); r++)
		 {
			 for (int c=0;c<theWorld.getNumberOfColumns();c++)
			 {
				 labelArray[r][c] = new JLabel();
				 labelArray[r][c].setBorder(BorderFactory.createLineBorder(Color.black));
				 
				 BufferedImage img = createSpace();
				 if (theWorld.getLifeForm(r, c) != null)
					 createLifeForm(img,theWorld.getLifeForm(r, c));
				 if (theWorld.getWeapon1(r, c) != null )
					 createWeapon(img,theWorld.getWeapon1(r, c), 1);
				 if(theWorld.getWeapon2(r, c) != null )
					 createWeapon(img,theWorld.getWeapon2(r, c), 2);
				 
			     labelArray[r][c].setIcon(new ImageIcon(img));
				 centerPanel.add(labelArray[r][c]);
			 }
		 }
		 
		 add("Center",centerPanel);
		 pack();
		 setVisible(true);
	}
	
	/**
	 * Create the life form piece, takes in the buffered image and the lifeform and direction
	 * @param lifeform 
	 * @param l human or alien
	 * @param dir direction the lifeform is facing
	 */
	public void createLifeForm(BufferedImage lifeform, LifeForm l)
	{
		int x = 5;
		int y = 5;
		int width = 40;
		int height = 40;
		Graphics drawer = lifeform.getGraphics();
							
		if (l instanceof  Alien)
			drawer.setColor(new Color(248,206,204));
		else if (l instanceof  Human)
			drawer.setColor(new Color(218,232,252));
		drawer.fillOval(x, y, width, height);
		drawer.setColor(new Color(200,200,200));
		//Color is direction they're facing
		//Facing West
		if (l.getCurrentDirection() == l.WEST)
			drawer.fillArc(x, y, width, height, 90, -180);
		//Facing East
		else if (l.getCurrentDirection() == l.EAST)
			drawer.fillArc(x, y, width, height, 90, 180);
		//Facing North
		else if (l.getCurrentDirection() == l.NORTH)
			drawer.fillArc(x, y, width, height, -180, 180);
		//Facing South
		else if (l.getCurrentDirection() == l.SOUTH)
			drawer.fillArc(x, y, width, height, 180, -180);
		//if the lifeform has a weapon, go ahead and add that icon
		if(l.getWeapon() != null)
			createWeapon(lifeform, (Weapon)l.getWeapon(), 0);
	}
	
	/**
	 * Updates the bufferedimage to add the weapon icon, can be done by adding onto 
	 * existing icon (for lifeform) or not (for placing in cell)
	 * @param weaponIcon
	 * @param w weapon 
	 * @param loc Indicates if the weapon is being held (0) weapon1 (1) or weapon2 (2)
	 */
	public void createWeapon(BufferedImage weaponIcon, Weapon w, int loc)
	{
		Graphics drawer = weaponIcon.getGraphics();
		int x =0; 
		int y =0 ;
		int width = 15;
		int height = 15;
		
		
		if (loc ==0 )
		{
			x = 5;
			y = 15;
		}
		else if (loc == 2)
		{
			x = 35;
			y = 35;
		}
		
		if (w instanceof PlasmaCannon)
			drawer.setColor(Color.green);
		else if (w instanceof ChainGun)
			drawer.setColor(Color.orange);
		else if (w instanceof Pistol)
			drawer.setColor(Color.yellow);
		else if ( w instanceof Attachment) 
		{		
			drawer.setColor(Color.gray);
			width = 5;
			height = 5;
			
			if (w.toString().contains("Stabilizer"))
				x = x + width;
			else if (w.toString().contains("Scope"))
				x = x + width* 2;
			createWeapon(weaponIcon, ((Attachment) w).getWeapon(), loc);
		}
			
		drawer.fillOval(x, y, width, height);
		
	}
	
	/**
	 * This just creates the blank space that is used
	 * as the building block for images
	 * @return the BufferedImage
	 */
	public BufferedImage createSpace()
	{
		BufferedImage space = new
		BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
		Graphics drawer = space.getGraphics();
									
		drawer.setColor(new Color(255,255,255));
		drawer.fillRect(0, 0, 50, 50);
		return space;		
	}
}