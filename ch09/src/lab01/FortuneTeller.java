package lab01;

//Fortune Teller

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FortuneTeller extends JFrame
implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final EasySound ding = new EasySound("ding.wav");

	// Declare an array of "fortunes" (strings):
	private String[] fortunes = {"You will have a long and happy life.",
			"Life will slowly get better for you.", 
			"You will never know what you may see.", 
			"Rely on miracles.", 
			"Being happy is not always being perfect.", 
			"You are slowly dying.", 
			"Do everything smarter, and not harder.",
			"A Tsunami won't hit you.",
			"Hitting a guy with glasses won't help them see.",
			"The chicken is great.",
			"You will survive everything.",
			"Your lucky number is 42. Life and death await you.",
			"Run.",
			"About time I got out of that cookie.",
			"Ignore previous cookie.",
			"I cannot help you, for I am a cookie.",
			"Help! I'm being held prisoner in a Chinese bakery!"};

	private JTextField display;

	public FortuneTeller() {
		super("Fortune Teller");

		display = new JTextField("  Press \"Next\" to see your fortune...", 25);
		display.setBackground(Color.WHITE);
		display.setEditable(false);

		JButton go = new JButton("Next");
		go.addActionListener(this);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(display);
		c.add(go);
	}

	public void actionPerformed(ActionEvent e) {
		// Pick and display a random fortune:
		Random rand = new Random();
		int randFort = rand.nextInt(fortunes.length);
		String fort = fortunes[randFort];

		display.setText("  " + fort );
		ding.play();
	}

	public static void main(String[] args) {
		JFrame window = new FortuneTeller();
		window.setBounds(300, 300, 300, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true); 
	}
}
