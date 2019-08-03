package ch04questions;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.Timer;

import ch04lab.FancyBalloon;
import ch04lab.OvalBalloon;
import ch04lab.RoundBalloon;
import ch04lab.SquareBalloon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * #14 chapter 4: This program teaches balloons how to fly
 * 
 * @author Zach Wellman Date: 12/11/17
 */
public class BalloonBanner extends JApplet implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xPos, yPos;

	public void init() {
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		yPos = c.getWidth();
		xPos = c.getHeight() / 2;
		Timer clock = new Timer((int) 0.00003, this);
		clock.start();
	}

	public void paint(Graphics g) {
		super.paint(g); // Paint the background
		// g.setColor(Color.RED);
		RoundBalloon b1 = new RoundBalloon(xPos + 200, yPos + 50, 50, Color.ORANGE);
		b1.draw(g, true);
		OvalBalloon b2 = new OvalBalloon(xPos + 300, yPos + 50, 50, Color.YELLOW);
		b2.draw(g, true);
		SquareBalloon b3 = new SquareBalloon(xPos + 400, yPos + 50, 50, Color.GREEN);
		b3.draw(g, true);
		FancyBalloon b4 = new FancyBalloon(xPos + 525, yPos + 50, 50, Color.BLUE);
		b4.draw(g, true);
	}

	public void actionPerformed(ActionEvent e) {
		Container c = getContentPane();
		yPos--;
		if (yPos < -100) {
			yPos = c.getWidth();
		}
		/*Random rand = new Random();
		/*xPos -= rand.nextInt(50);
		if (xPos < -c.getHeight()) {
			xPos = c.getHeight();
		}*/
		xPos = c.getWidth() / 2;
		repaint();
	}
}
