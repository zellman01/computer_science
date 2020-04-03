import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Possible solutions:
 *  
 */

/*
 * What to do:
 *  Add things to allow updates (maybe also a frame of endturn function to run those and auto-update files?)
 *  Possibly add a turn thing to signify if it is the character's turn 
 *   (has to be updated manually per character - no way to check if another character is also "in turn")
 *  Save sub-character files (with asking the user)
 */
public class MainGUI extends JFrame implements ActionListener {
	private MainGUI w;
	private static final long serialVersionUID = 1L;
	private String name;
	private Character test;
	private final Color currentTurn = Color.GREEN, notTurn = Color.RED;
	private final Object[] STATUSES = {"Burned", "Frozen", "Paralysis", "Time Frozen", "Poisoned", "Slow", "Blinded", "Binded"};

	// Closes the character information GUI and asks if you want to save it, then saves it as a character file
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int n = JOptionPane.showConfirmDialog(w, "Would you like to save the character? (will overwrite current character file"
				+ ", recommeneded to make a backup of original character file)", "Character Save",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // yes = 0, no = 1
		if (n == 0) {
			CharacterCreator cc = new CharacterCreator();
			cc.char1 = test;
			cc.saveSystem(name);
		} else if (n == 1) { // Essentially, do nothing. Can't exclude it as the other else if for a critical failure (otherwise, 
			//  closing the window without selecting anything)
		} else {
			error();
		}
		System.exit(0);

	}

	public MainGUI(int a) {
		super("Battle Simulator 1.0");
		test = createOrLoad();
		JLabel hp = new JLabel("HP: ");
		//String hpValue = "";
		//hpValue = Integer.toString(test.getHp());
		JLabel hp1 = new JLabel(Integer.toString(test.getHp()));

		JLabel name = new JLabel("Name: ");
		JLabel name1 = new JLabel(test.getName());

		this.name = test.getName(); // for saving purposes

		JLabel charge = new JLabel("Charge: ");
		JLabel charge1 = new JLabel(Integer.toString(test.getEnergy()));

		JLabel statuses = new JLabel("Statuses: ");
		JLabel statuses1 = new JLabel(test.getStatuses());

		JLabel turnStarted = new JLabel("Turn?");
		JLabel turnStatus = new JLabel(test.turnAlignment());

		if (test.getTurn()) {
			turnStatus.setForeground(currentTurn);
		} else {
			turnStatus.setForeground(notTurn);
		}

		JButton close = new JButton("Close");
		close.addActionListener(this);

		JButton damage = new JButton("Damage");
		damage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String damage = JOptionPane.showInputDialog(w, "How much is " + test.getName() + " damaged for? (negative number for healing)",
						"Damage", JOptionPane.QUESTION_MESSAGE);
				test.modifyHp(Integer.parseInt(damage)*-1);
				if (test.getHp() > 0) {
					test.alive(true);
					statuses1.setText(test.getStatuses());
					JOptionPane.showMessageDialog(w, test.getName() + " has been revived.", "Alive",
							JOptionPane.INFORMATION_MESSAGE);
				}
				hp1.setText(Integer.toString(test.getHp()));
			}
		});

		JButton startTurn = new JButton("Start turn");
		startTurn.addActionListener(new ActionListener() { // TODO: Add sections to check if turn does not happen, and end turn there
			public void actionPerformed(ActionEvent e) {
				if (test.getTurn() || !test.getAlive()) {
					JOptionPane.showMessageDialog(w, test.getName() + "'s turn has already started, or is killed", "Action Unavailable", 
							JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (test.getTimeFrozen()) {
						JOptionPane.showMessageDialog(w, test.getName() + "'s turn has been stopped from being time frozen.", "Action Unavailable", 
								JOptionPane.ERROR_MESSAGE);
						test.timeFrozen(test.getTimeFrozenTimer()-1);
						statuses1.setText(test.getStatuses());
						endTurn();
						statuses1.setText(test.getStatuses()); // Update status in case the player died
						hp1.setText(Integer.toString(test.getHp())); // Update HP in case burn/poison happened
						return;
					}
					if (test.getFrozen()) {
						JOptionPane.showMessageDialog(w, test.getName() + "'s turn has been stopped from being frozen.", "Action Unavailable", 
								JOptionPane.ERROR_MESSAGE);
						test.frozen(test.getFrozenTimer()-1);
						statuses1.setText(test.getStatuses());
						endTurn();
						statuses1.setText(test.getStatuses()); // Update status in case the player died
						hp1.setText(Integer.toString(test.getHp())); // Update HP in case burn/poison happened
						return;
					}
					if (test.getPayalysis()) {
						int random = ThreadLocalRandom.current().nextInt(1, 20);
						if (random < 11) {
							JOptionPane.showMessageDialog(w, test.getName() + "'s turn has been stopped from being paralyzed.", "Action Unavailable", 
									JOptionPane.ERROR_MESSAGE);
							endTurn();
							statuses1.setText(test.getStatuses()); // Update status in case the player died
							hp1.setText(Integer.toString(test.getHp())); // Update HP in case burn/poison happened
							return;
						}
					}
					if (test.getBound()) {
						JOptionPane.showMessageDialog(w, test.getName() + "'s turn has started, but their actions are limited from being bound "
								+ "to something.", "Action Information", 
								JOptionPane.WARNING_MESSAGE);
						test.bind(test.getBoundTime()-1);
						statuses1.setText(test.getStatuses());
					}
					test.turn(true);
					turnStatus.setForeground(currentTurn);
					turnStatus.setText(test.turnAlignment());
				}
			}
		});

		JButton endTurn = new JButton("End Turn");
		endTurn.addActionListener(new ActionListener() { // TODO: Add sections to check if turn does not happen, and end turn there
			public void actionPerformed(ActionEvent e) {
				if (!test.getTurn()) {
					JOptionPane.showMessageDialog(w, test.getName() + "'s turn has not started yet, or is killed", "Action unavailable", 
							JOptionPane.ERROR_MESSAGE);
				} else {
					endTurn();
					test.turn(false);
					turnStatus.setForeground(notTurn);
					turnStatus.setText(test.turnAlignment());
					statuses1.setText(test.getStatuses()); // Update status in case the player died
					hp1.setText(Integer.toString(test.getHp())); // Update HP in case burn/poison happened
				}
			}
		});

		JButton addStatus = new JButton("Statuses");
		addStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog(w, "What status is being added/removed?", "Status Menu", JOptionPane.PLAIN_MESSAGE, null, STATUSES, STATUSES[0]);
				if ((s != null) && (s.length() > 0)) {
					if (s == "Paralysis" || s == "Slow" || s == "Blinded") {
						changeStatus(s, 0);
					} else {
						String add = (String)JOptionPane.showInputDialog(w, "Please input the additional information based on the below chart "
								+ "and the status being added (Do not use negative numbers):"
								+ "\nBurned - The given level (0-4) (0 to disable the status). Inputting anything bigger will not add "
								+ "the status."
								+ "\nPoisoned - Amount of damage done every turn (set to 0 to disable)"
								+ "\nFrozen - Amount of turns frozen (set to 0 to disable)"
								+ "\nTime Frozen - Amount of turns time frozen (set to 0 to disable)"
								+ "\nBinded - Amount of turns bound (set to 0 to disable)",
								"Additional information", JOptionPane.QUESTION_MESSAGE);
						try {
							changeStatus(s, Integer.parseInt(add));
						} catch (NumberFormatException b) {
							numError();
						}
					}
					statuses1.setText(test.getStatuses());
				}
			}
		});
		
		JButton modifyCharge = new JButton("Charge");
		modifyCharge.addActionListener(new ActionListener() { // TODO: Fix error
			public void actionPerformed(ActionEvent e) {
				String charge = JOptionPane.showInputDialog(w, "How much does " + test.getName() + " charge for? "
						+ "(negative number for removing charge)",
						"Damage", JOptionPane.QUESTION_MESSAGE);
				try {
					test.updateEnergy(Integer.parseInt(charge));
					if (test.getEnergy() < 0) {
						JOptionPane.showMessageDialog(w, test.getName() + " does not have enough stored to do what they planed on doing", 
								"General Error", JOptionPane.ERROR_MESSAGE);
						test.updateEnergy(Integer.parseInt(charge)*-1);
					}
					charge1.setText(Integer.toString(test.getEnergy()));
				} catch (NumberFormatException b) {
					numError();
				}
			}
		});

		Container c = getContentPane();
		c.setBackground(Color.WHITE);

		JPanel p = new JPanel(); // Displayed text
		p.setLayout(new GridLayout(5,1));

		p.add(hp); p.add(hp1);
		p.add(name); p.add(name1);
		p.add(charge); p.add(charge1);
		p.add(statuses); p.add(statuses1);
		p.add(turnStarted); p.add(turnStatus);

		JPanel s = new JPanel(); // Actions
		s.setLayout(new GridLayout(3,1));

		s.add(close); s.add(damage); s.add(startTurn); s.add(endTurn); s.add(addStatus); s.add(modifyCharge);

		c.add(p, BorderLayout.CENTER);
		c.add(s, BorderLayout.SOUTH);
	}

	// Only exists to prevent having to type in a character name twice
	public MainGUI() {
		// TODO Auto-generated constructor stub
	}

	// Asks to either load or create a new character
	private Character createOrLoad() {
		String name = "";
		int n = JOptionPane.showConfirmDialog(w, "Do you already have a character file created you would like to use? (Note, "
				+ "the program will have to be re-launched if you create a new character)", "Character Selection", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // yes = 0, no = 1
		if (n == 0) { // Selected yes
			name = JOptionPane.showInputDialog(w, "Input the name of the character to be added "
					+ "(must be in the same location as the .jar file)\nYou must input it as either <lastName_firstName> or <firstName>,"
					+ " replacing lastName and firstName with the chatacter's last and first name and without the \"<>\"",
					"Character selection", JOptionPane.QUESTION_MESSAGE);
			return loadCharSystem(name);
		} else if (n == 1) { // Selected no (TODO: Fix error)
			CCGUI e = new CCGUI();
			e.start();
		} else {
			error();
		}

		// Should never happen
		return null;
	}

	private void changeStatus(String name, int additional) {
		name = name.toLowerCase();
		switch (name) {
		case "paralysis":
			test.paralysis(!test.getPayalysis());
			break;
		case "burned":
			test.burned(additional);
			break;
		case "poisoned":
			test.poison(additional);
			break;
		case "frozen":
			test.frozen(additional);
			break;
		case "time frozen":
			test.timeFrozen(additional);
			break;
		case "slow":
			test.slow(!test.getSlow());
			break;
		case "binded":
			test.bind(additional);
			break;
		case "blinded":
			test.blind(!test.getBlind());
			break;
		default:
			error();
			break;
		}
	}

	private void error() {
		JOptionPane.showMessageDialog(w, "Something went wrong with that action.", "Critical Error", 
				JOptionPane.ERROR_MESSAGE);
		throw new Error("Something horribly went wrong");
	}
	
	private void numError() {
		JOptionPane.showMessageDialog(w, "What you inputted was not a number.", "Number Error", JOptionPane.ERROR_MESSAGE);
		throw new Error("Input of number went wrong");
	}
	
	private void endTurn() { // Have to do it this way so that both startTurn and endTurn actionlisteners can use it
		if (test.getBurn()) {
			int[] burnDamage = {25, 50, 75, 100};
			test.modifyHp(-burnDamage[test.getBurnLevel()-1]);
			JOptionPane.showMessageDialog(w, test.getName() + " took " + burnDamage[test.getBurnLevel()-1] + " in burn damage.", "Burn Damage",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (test.getPoison()) {
			test.modifyHp(-test.getPoisonDamage());
			JOptionPane.showMessageDialog(w, test.getName() + " took " + test.getPoisonDamage() + " in poison damage.", "Poison Damage",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (test.getHp() <= 0) {
			test.alive(false);
			JOptionPane.showMessageDialog(w, test.getName() + " has been killed.", "Dead",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void start() {
		w = new MainGUI(1);
		w.pack();
		w.setBounds(300,300,300,200); // Only modify the last one - height
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(EXIT_ON_CLOSE);
		w.setVisible(true);
		w.setResizable(false); // During testing - disable after testing is done
		/*JOptionPane.showMessageDialog(w, 
				"This is a function test GUI application. This will not be fully functional for a "
						+ "while while methods and everything are tested.", "Special Warning", JOptionPane.WARNING_MESSAGE);*/
	}

	// Loads a created character file
	private Character loadCharSystem(String name) {
		File file = new File(name + ".chr");
		try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return (Character) in.readObject();

		} catch(IOException | ClassNotFoundException ex) {
			error();
			throw new Error();
		}
	}

	public static void main(String[] args) { // Essentially useless, only used to start the program
		MainGUI s = new MainGUI();
		s.start();
	}

}
