package phndrctry;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BookCoverJPanel extends JPanel implements ActionListener {
	final int PANEL_WIDTH=300;	
	final int PANEL_HEIGTH=300;	
	Image img;
	Timer timer;
	int xVelocity=1;
	int yVelocity=2;
	int x=0;
	int y=50;
	
	BookCoverJPanel(){
		//setted the features of the "cover image" panel
		this.setBounds(0,100,PANEL_WIDTH,PANEL_HEIGTH);
		this.setBackground(Color.WHITE);
		
		img=new ImageIcon("phoneDirIcon.jpg").getImage();
		
		//setted the timer
		timer=new Timer(20,this);
		timer.start();
	}

	public void paint(Graphics g) {
		//background color
		super.paint(g);
		
		//added the icon image to panel 
		Graphics2D g2d= (Graphics2D)g;
		g2d.drawImage(img,x,y,null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//animate the image
		if (x>=PANEL_WIDTH-img.getWidth(null)||x<0) {
			xVelocity=xVelocity*-1;
		}
		x=x+xVelocity;
		if (y>=PANEL_HEIGTH-img.getHeight(null)||y<0) {
			yVelocity=yVelocity*-1;
		}
		y=y+yVelocity;
		repaint();
	}
}
