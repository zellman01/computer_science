package helloWorld;

import java.awt.*;
import javax.swing.*;

public class HelloGraphics extends JPanel {
	/**
	 * Fifth Hello Application
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);     // Call JPanel's PaintComponent method to paint the
								    //  background
		g.setColor(Color.RED);
		g.drawRect(20, 40, 150, 45);
		
		g.setColor(Color.BLUE);
		g.drawString("Hello, World!", 55, 65);
	}
	
	public static void main (String [] args) {
		JFrame window = new JFrame("Hello world with Graphics!");
		window.setBounds(300, 300, 200, 150);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HelloGraphics panel =  new HelloGraphics();
		panel.setBackground(Color.WHITE);
		
		Container c = window.getContentPane();
		c.add(panel);
		
		window.setVisible(true);
	}

}
