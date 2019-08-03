package pokemon;

import java.awt.BorderLayout;
//import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;

	public Window() {
		super("testing");
		ControlPanel controls = new ControlPanel();
		Container c = getContentPane();
		c.add(controls, BorderLayout.SOUTH);
	}

	public static void main(String [] args) {
		Window window = new Window();
		window.setBounds(100, 100, 100, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
