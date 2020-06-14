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
	public void createGrid()
	{
		setLayout(new BorderLayout());
 
		 JButton textButton, imageButton;
		 JLabel textLabel, imageLabel; 
		 
		 textLabel = new JLabel("North");
		 add("North",textLabel);

//		 imageButton = new JButton(createImage());
//		 add("West",imageButton);
//
//		 imageLabel = new JLabel(createImage());
//		 add("South",imageLabel);
	 
		 textButton = new JButton("A Button");
		 add("East",textButton);

		 JPanel centerPanel = new JPanel(new GridLayout(theWorld.getNumberOfRows(),theWorld.getNumberOfColumns()));
		 centerPanel.setBackground(new Color(255,255,255));
		 //centerPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		 JLabel[][] labelArray = new JLabel[theWorld.getNumberOfRows()][theWorld.getNumberOfColumns()];
		 
		 for (int r=0;r< theWorld.getNumberOfRows(); r++)
		 {
			 for (int c=0;c<theWorld.getNumberOfColumns();c++)
			 {
				 labelArray[r][c] = new JLabel();
				 labelArray[r][c].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
				  
				  
				 if (theWorld.getLifeForm(r, c) != null)
					 labelArray[r][c].setIcon(createLifeForm(theWorld.getLifeForm(r, c), r,c,"north"));
				 else
					 labelArray[r][c].setIcon(createSpace(r,c));
				 centerPanel.add(labelArray[r][c]);
			 }
		 }
		 
		 add("Center",centerPanel);
		 pack();
		 setVisible(true);

	}
	
	public ImageIcon createLifeForm(LifeForm l, int x, int y, String dir)
	{
		BufferedImage lifeform = new
		BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
		Graphics drawer = lifeform.getGraphics();
							
		drawer.setColor(new Color(255,255,255));
		drawer.fillRect(x, y, 50, 50);
		if (l instanceof  Alien)
			drawer.setColor(new Color(248,206,204));
		else if (l instanceof  Human)
			drawer.setColor(new Color(218,232,252));
		drawer.fillOval(0, 0, 50, 50);
		drawer.setColor(new Color(200,200,200));
		//Color is direction they're facing
		//Facing West
		if (dir == "west")
			drawer.fillArc(0, 0, 50, 50, 90, -180);
		//Facing East
		else if (dir == "east")
			drawer.fillArc(0, 0, 50, 50, 90, 180);
		//Facing North
		else if (dir == "north")
			drawer.fillArc(0, 0, 50, 50, -180, 180);
		//Facing South
		else if (dir == "south")
			drawer.fillArc(0, 0, 50, 50, 180, -180);
		return new ImageIcon(lifeform);			
	}
	
	public ImageIcon createSpace(int x, int y)
	{
		BufferedImage space = new
		BufferedImage(50,50,BufferedImage.TYPE_3BYTE_BGR);
		Graphics drawer = space.getGraphics();
									
		drawer.setColor(new Color(255,255,255));
		drawer.fillRect(x, y, 50, 50);
		return new ImageIcon(space);		
	}
}

