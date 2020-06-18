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
import weapon.Booster;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Scope;
import weapon.Stabilizer;
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
	 * Creates the legend on the board
	 */
	public void createGrid()
	{
		 setLayout(new BorderLayout());
		 
		 JPanel legend = new JPanel(new GridLayout(9, 2));
		 legend.setBackground(new Color(255,255,255));
		 JLabel[] labelArray = new JLabel[9];
		 for(int r=0; r < 9; r++)
		 {
				 labelArray[r] = new JLabel();
				 labelArray[r].setBorder(BorderFactory.createLineBorder(Color.black));
				 
				 BufferedImage img = createSpace();
				 switch (r)
				 {
				 case 1:
					 createLifeForm(img, new Human("",0,0));
					 labelArray[r].setText("Human");
					 break;
				 case 2: 
					 createLifeForm(img, new Alien("", 0));
					 labelArray[r].setText("Alien");
					 break;
				 case 3:
					 createWeapon(img,new Pistol(0,0,0,0), 1);
					 labelArray[r].setText("Pistol");
					 break;
				 case 4:
					 createWeapon(img,new PlasmaCannon(0,0,0,0), 1);
					 labelArray[r].setText("Plasma Cannon");
					 break;
				 case 5:
					 createWeapon(img,new ChainGun(0,0,0,0), 1);
					 labelArray[r].setText("ChainGun");
					 break;
				 case 6:
					 Pistol  p = new Pistol(0,0,0,0);
					 Scope s = new Scope(p);
					 createWeapon(img, s, 0);
					 labelArray[r].setText("Weapon w/ Attachment");
					 break;
				 case 7:
					 ChainGun  cg = new ChainGun(0,0,0,0);
					 Booster b = new Booster(cg);
					 Stabilizer s2 = new Stabilizer(b);
					 labelArray[r].setText("Weapon w/ 2 Attachment");
					 createWeapon(img, s2,0);
					 break;
				default:
					break;
				 }
			     labelArray[r].setIcon(new ImageIcon(img));
				 legend.add("East",labelArray[r]);
			 }
		 add("East",legend);
		 updateGrid();

	}
	/**
	 * Adds lifeforms and weapons to the board, 
	 * used to create or update the entire board
	 */
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