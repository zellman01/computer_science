package ch05questions;

// Chapter 5 Question 27

// ________________________________________________

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rainbow extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declare skyColor:
	final Color skyColor = Color.CYAN;

	public Rainbow() {
		setBackground(skyColor);
	}

	// Draws the rainbow.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();    
		int height = getHeight();

		int largeRadius = (int)(width * 1.0 / 8);

		// Declare and initialize local int variables xCenter, yCenter
		// that represent the center of the rainbow rings:
		//int xCenter = (int)(width * 1.0 / 2);
		//int yCenter = (int)(height * (3.0 / 4));
		int xCenter = width / 2;
		int yCenter = height/2+largeRadius/2;

		//int yCenter = (int)(height * (3.0 / 4) + 12.5);

		// Declare and initialize the radius of the large semicircle:
		int smallRadius = (int)(width * 1.0 / 12);
		int mediumRadius =  width / 10;
		int smallestRadius = width / 14;

		g.setColor(Color.RED);

		// Draw the large semicircle:
		g.fillArc(xCenter - largeRadius, yCenter - largeRadius, 2 * largeRadius, 2 * largeRadius, 0, 180 );

		// Declare and initialize the radii of the small and medium
		// semicircles and draw them:

		g.setColor(Color.GREEN);
		g.fillArc(xCenter - mediumRadius, yCenter - mediumRadius, 2 * mediumRadius, 2 * mediumRadius, 0, 180);

		// Calculate the radius of the innermost (sky-color) semicircle
		// so that the width of the middle (green) ring is the
		// arithmetic mean of the widths of the red and magenta rings:

		g.setColor(Color.MAGENTA);
		g.fillArc(xCenter - smallRadius, yCenter - smallRadius, 2 * smallRadius, 2 * smallRadius, 0, 180);

		// Draw the sky-color semicircle:
		g.setColor(skyColor);
		g.fillArc(xCenter - smallestRadius, yCenter - smallestRadius, 2 * smallestRadius, 2 * smallestRadius, 0, 180);
	}

	public static void main(String[] args) {
		JFrame w = new JFrame("Rainbow");
		w.setBounds(300, 300, 300, 200);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		c.add(new Rainbow());
		w.setVisible(true);

	}
}
