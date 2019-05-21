package helloWorld;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("deprecation")
public class HelloApplet extends JApplet{
	/**
	 * Sixth Hello Application
	 */
	private static final long serialVersionUID = 1L;

	public void init() {
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
	}
	
	public void paint(Graphics g) {
		super.paint(g);					//Call JApplet's paint method
		g.setColor(Color.RED);
		g.drawRect(20, 40, 150, 45);
		g.setColor(Color.BLUE);
		g.drawString("Hello, World!", 60, 65);
	}
}
