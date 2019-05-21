package ch06lab;

//Statistical simulation of craps

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CrapsStats extends JFrame
implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CrapsGame game;
	private JTextField numberIn, statsOut;

	// Constructor
	public CrapsStats() {
		super("Craps test");

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		c.add(new JLabel("Number of games to run(1-999999):"));

		numberIn = new JTextField(5);
		numberIn.addActionListener(this);
		c.add(numberIn);

		statsOut = new JTextField(18);
		statsOut.setEditable(false);
		c.add(statsOut);

		game = new CrapsGame();
	}

	// Called when a number is entered in the numberIn text field
	public void actionPerformed(ActionEvent e) {
		String s = numberIn.getText();
		int nGames = Integer.parseInt(s);
		int result, gameCount = 0, winCount = 0, lossCount = 0;
		Die die1 = new Die();
		Die die2 = new Die();

		while (gameCount < nGames)
		{
			die1.roll();
			die2.roll();
			int total = die1.getNumDots() + die2.getNumDots();
			result = game.processRoll(total);
			if (result != 0)
				gameCount++;
			if (result > 0)
				winCount++;
			if (result < 0)
				lossCount++;
		}
		numberIn.setText("");
		statsOut.setText(" Games: " + gameCount + " Wins: " + winCount + " Losses: " + lossCount);
	}

	public static void main(String args[]) {
		CrapsStats window = new CrapsStats();
		window.setBounds(100, 100, 500, 075);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}
}

