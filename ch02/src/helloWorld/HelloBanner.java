package helloWorld;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloBanner extends JApplet implements ActionListener {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int xPos, yPos;
		
		public void init() {
			Container c = getContentPane();
			c.setBackground(Color.WHITE);
			xPos = c.getWidth();
			yPos = c.getHeight() / 2;
			
			Timer clock = new Timer(40, this);
			clock.start();
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			g.drawString("Hello, World!", xPos, yPos);
		}
			
		public void actionPerformed(ActionEvent e) {
			Container c = getContentPane();
			
			xPos--;
			if (xPos < -100) {
				xPos = c.getWidth();
			}
			
			yPos = c.getHeight() / 2;
			repaint();
		}
	}
