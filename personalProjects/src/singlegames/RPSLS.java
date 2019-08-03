package singlegames;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RPSLS extends JFrame
implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final char moves[] = {'R', 'P', 'S', 'L', 'O'};
	private JRadioButton rock, paper, scissors, lizard, spock;
	private JTextField display;

	public RPSLS() {
		super("Rock, Paper, Scissors, Lizard, Spock");

		rock = new JRadioButton("   Rock   ");
		paper = new JRadioButton("   Paper  ");
		scissors = new JRadioButton(" Scissors ");
		lizard = new JRadioButton(" Lizard ");
		spock = new JRadioButton(" Spock ");

		ButtonGroup rpsButtons = new ButtonGroup();
		rpsButtons.add(rock);
		rpsButtons.add(paper);
		rpsButtons.add(scissors);
		rpsButtons.add(lizard);
		rpsButtons.add(spock);

		JButton go = new JButton("         Go         ");
		go.addActionListener(this);

		display = new JTextField(25);
		display.setEditable(false);
		display.setBackground(Color.YELLOW);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(rock);
		c.add(paper);
		c.add(scissors);
		c.add(lizard);
		c.add(spock);
		c.add(go);
		c.add(display);
	}


	/** 
	 * @param computerMove Computer move state
	 * @param playerMove Player move state
	 * @return
	 * returns -1 if the player wins,
	 *  0 if it's a tie, and 1 if the computer wins
	 */
	private int nextPlay(char computerMove, char playerMove) {

		switch (playerMove) { // All possible cases break out of the switch
		case 'R':
			switch (computerMove) {
			case 'R':
				return 0;
			case 'P':
				return 1;
			case 'S':
				return -1;
			case 'L':
				return -1;
			case 'O':
				return 1;
			}
		case 'P':
			switch (computerMove) {
			case 'R':
				return -1;
			case 'P':
				return 0;
			case 'S':
				return 1;
			case 'L':
				return -1;
			case 'O':
				return 1;
			}
		case 'S':
			switch (computerMove) {
			case 'R':
				return 1;
			case 'P':
				return -1;
			case 'S':
				return 0;
			case 'L':
				return -1;
			case 'O':
				return 1;
			}
		case 'L':
			switch (computerMove) {
			case 'R':
				return 1;
			case 'P':
				return -1;
			case 'S':
				return 1;
			case 'L':
				return 0;
			case 'O':
				return -1;
			}
		case 'O':
			switch (computerMove) {
			case 'R':
				return 1;
			case 'P':
				return -1;
			case 'S':
				return 1;
			case 'L':
				return -1;
			case 'O':
				return 0;
			}
		}
		return 0;
	}

	public void actionPerformed(ActionEvent e) {
		char playerMove, computerMove;
		if (rock.isSelected())
			playerMove = 'R';
		else if (paper.isSelected())
			playerMove = 'P';
		else if (scissors.isSelected())
			playerMove = 'S';
		else if (lizard.isSelected()) 
			playerMove = 'L';
		else if (spock.isSelected())
			playerMove = 'O';
		else {
			display.setText("You cannot select an empty response in this game.");
			playerMove = '1';
		}

		int k = (int)(Math.random() * 5);
		computerMove = moves[k];
		int result = nextPlay(computerMove, playerMove);

		String msg = "  You said " + makeWord(playerMove) + ", I said " +
				makeWord(computerMove);
		if (result < 0)
			msg += " -- you win.";
		else if (result == 0)
			msg += " -- tie.";
		else // if (result > 0)
			msg += " -- I win.";
		display.setText(msg);
	}

	private String makeWord(char move) {
		String word = "";

		switch (move) {
		case 'R': word = "ROCK"; break;
		case 'P': word = "PAPER"; break;
		case 'S': word = "SCISSORS"; break;
		case 'L': word = "LIZARD"; break;
		case 'O': word = "SPOCK"; break;
		}
		return word;
	}

	public static void main(String[] args) {
		RPSLS window = new RPSLS();
		window.setBounds(300, 300, 425, 120);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}
}

