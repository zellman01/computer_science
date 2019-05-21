package helloWorld;

import java.awt.*;
import javax.swing.*;

public class HelloGui extends JFrame {
	/**
	 * Fourth Hello Application
	 */
	private static final long serialVersionUID = 1L;

	public HelloGui() {
		super("Hello World with GUI");
		Container c = getContentPane();
		c.setBackground(Color.CYAN);
		c.setLayout(new FlowLayout());
		c.add(new JLabel("Hello, World!"));
	}
	
	public static void main(String [] args) {
		HelloGui window = new HelloGui();
		// Set upper-window's location and size:
		// upper-left corner at 300, 300; width 200, height 100
		window.setBounds(300, 300, 200, 100);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
