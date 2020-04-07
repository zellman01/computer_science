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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainGUI extends JFrame implements ActionListener {
	private MainGUI w;
	private static final long serialVersionUID = 1L;
	private JFileChooser jc;
	private Character test;
	private final Color currentTurn = Color.GREEN, notTurn = Color.RED;
	private final Object[] STATUSES = {"Burned", "Frozen", "Paralysis", "Time Frozen", "Poisoned", "Slow", "Blinded", "Binded"};
	JLabel hp, hp1, name, name1, charge, charge1, statuses, statuses1, turnStarted, turnStatus, rune, runeList;

	// Closes the character information GUI and asks if you want to save it, then saves it as a character file
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (askForSave())
			System.exit(0);

	}

	public MainGUI(int a) {
		super("Battle Simulator 1.3.1");
		
		// Setup file searcher
		jc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Character files", "chr", ".chr");
		jc.setFileFilter(filter);
		
		// Setup the actual character to use
		test = createOrLoad();
		
		// Labels
		hp = new JLabel("HP:");
		hp1 = new JLabel(Integer.toString(test.getHp()));

		name = new JLabel("Name:");
		name1 = new JLabel(test.getName());

		charge = new JLabel("Charge:");
		charge1 = new JLabel(Integer.toString(test.getEnergy()));

		statuses = new JLabel("Statuses:");
		statuses1 = new JLabel(test.getStatuses());

		turnStarted = new JLabel("Turn?");
		turnStatus = new JLabel(test.turnAlignment());

		rune = new JLabel("Runes:");
		runeList = new JLabel(test.getEquippedRunes().toString());

		if (test.getTurn()) {
			turnStatus.setForeground(currentTurn);
		} else {
			turnStatus.setForeground(notTurn);
		}

		// Buttons
		JButton close = new JButton("Close");
		close.addActionListener(this);

		JButton damage = new JButton("Damage");
		damage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String damage = JOptionPane.showInputDialog(w, "How much is " + test.getName() + " damaged for? (negative number for healing)",
						"Damage", JOptionPane.QUESTION_MESSAGE);
				test.modifyHp(Integer.parseInt(damage)*-1);
				hp1.setText(Integer.toString(test.getHp()));
				if (test.getHp() > 0 && !test.getAlive()) {
					test.alive(true);
					statuses1.setText(test.getStatuses()); // For removing the "Dead" status
					JOptionPane.showMessageDialog(w, test.getName() + " has been revived.", "Alive",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (test.getHp() <= 0) {
					test.alive(false);
					statuses1.setText(test.getStatuses()); // For adding the "Dead" status
					JOptionPane.showMessageDialog(w, test.getName() + " has been killed.", "Dead",
							JOptionPane.INFORMATION_MESSAGE);
					test.turn(false);
					turnStatus.setForeground(notTurn);
					turnStatus.setText(test.turnAlignment());
				}
			}
		});

		JButton startTurn = new JButton("Start turn");
		startTurn.addActionListener(new ActionListener() {
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
						endTurn(true);
						statuses1.setText(test.getStatuses()); // Update status in case the player died
						hp1.setText(Integer.toString(test.getHp())); // Update HP in case burn/poison happened
						return;
					}
					if (test.getFrozen()) {
						JOptionPane.showMessageDialog(w, test.getName() + "'s turn has been stopped from being frozen.", "Action Unavailable", 
								JOptionPane.ERROR_MESSAGE);
						test.frozen(test.getFrozenTimer()-1);
						statuses1.setText(test.getStatuses());
						endTurn(true);
						statuses1.setText(test.getStatuses()); // Update status in case the player died
						hp1.setText(Integer.toString(test.getHp())); // Update HP in case burn/poison happened
						return;
					}
					if (test.getPayalysis()) {
						int random = ThreadLocalRandom.current().nextInt(1, 20);
						if (random < 11) {
							JOptionPane.showMessageDialog(w, test.getName() + "'s turn has been stopped from being paralyzed.", "Action Unavailable", 
									JOptionPane.ERROR_MESSAGE);
							endTurn(true);
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
		endTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!test.getTurn()) {
					JOptionPane.showMessageDialog(w, test.getName() + "'s turn has not started yet, or is killed", "Action unavailable", 
							JOptionPane.ERROR_MESSAGE);
				} else {
					endTurn(true);
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
		modifyCharge.addActionListener(new ActionListener() {
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

		JButton runeAdd = new JButton("Add rune");
		runeAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (test.getRuneCount() < 3) {
					String name = JOptionPane.showInputDialog(w, "What is the name of the rune being equipped?",
							"Add Rune", JOptionPane.QUESTION_MESSAGE);
					if ((name != null) && name.length() > 0) {
						test.runeEquip(name, -1);
						runeList.setText(test.getEquippedRunes().toString());
					} else {
						error();
					}
				} else {
					JOptionPane.showMessageDialog(w, test.getName() + " already has three equipped runes.", "Rune Error", 
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton runeRemove = new JButton("Remove rune");
		runeRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(w, "What is the name of the rune being removed?", "Remove Rune",
						JOptionPane.QUESTION_MESSAGE);
				if ((name != null) && (name.length() > 0)) {
					test.runeEquip("", test.obtainRunePos(name));
					runeList.setText(test.getEquippedRunes().toString());
				} else {
					error();
				}
			}
		});

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				askForSave();
			}
		});

		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(w, "Would you like to reset this character to before any modifications"
						+ " were done to the character in this window? (WARNING: It will fully reset it from file."
						+ " And and all edits done from before the last save would have to be re-done.)", "Reset Character from file",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // yes = 0, no = 1
				switch (n) {
				case 0:
					test = loadCharSystem(test.saveName());

					// Start to reset all labels
					if (test.getTurn())
						turnStatus.setForeground(currentTurn);
					else
						turnStatus.setForeground(notTurn);
					turnStatus.setText(test.turnAlignment());
					statuses1.setText(test.getStatuses()); // Update statuses
					hp1.setText(Integer.toString(test.getHp())); // Update HP
					test.updateEnergy(test.getEnergy()); // Update energy
					rune.setText(test.getEquippedRunes().toString()); // Upodate runes

					break;
				case 1:
					break;
				default:
					JOptionPane.showConfirmDialog(w, "Error: Window was either closed or something went wrong.");
				}
			}
		});


		Container c = getContentPane();
		c.setBackground(Color.WHITE);

		JPanel p = new JPanel(); // Displayed text
		p.setLayout(new GridLayout(6,1));

		p.add(hp); p.add(hp1);
		p.add(name); p.add(name1);
		p.add(charge); p.add(charge1);
		p.add(statuses); p.add(statuses1);
		p.add(turnStarted); p.add(turnStatus);
		p.add(rune); p.add(runeList);

		JPanel s = new JPanel(); // Actions
		s.setLayout(new GridLayout(5,2));

		s.add(close); s.add(damage); s.add(startTurn); s.add(endTurn); s.add(addStatus); s.add(modifyCharge); s.add(runeAdd); 
		s.add(runeRemove); s.add(save); s.add(reset);

		c.add(p, BorderLayout.CENTER);
		c.add(s, BorderLayout.SOUTH);
	}

	// Only exists to prevent having to type in a character name twice
	public MainGUI() {
	}

	// Asks to either load or create a new character
	private Character createOrLoad() {
		String name = "";
		int n = JOptionPane.showConfirmDialog(w, "Do you already have a character file created you would like to use? (Note, "
				+ "the program will have to be re-launched if you create a new character)", "Character Selection", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // yes = 0, no = 1
		if (n == 0) { // Selected yes
			int returnVal = jc.showDialog(w, "Load character");
			if (returnVal == JFileChooser.APPROVE_OPTION)
				name = jc.getSelectedFile().getAbsolutePath().replaceFirst(".chr", "");
			return loadCharSystem(name);
		} else if (n == 1) {
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

	private void endTurn(boolean endTurn) { // Have to do it this way so that both startTurn and endTurn actionlisteners can use it
		if (endTurn) {
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
		w.setBounds(300,300,300,275); // Only modify the last one - height
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(EXIT_ON_CLOSE);
		w.setVisible(true);
		w.setResizable(false);
	}

	/**
	 * Loads a character into the program
	 * @param name The name of the character
	 * @return The loaded character
	 */
	public Character loadCharSystem(String name) {
		File file = new File(name + ".chr");
		System.out.println(name);
		try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return (Character) in.readObject();

		} catch(IOException | ClassNotFoundException ex) {
			error();
			throw new Error();
		}
	}

	//Asks to save a character
	private boolean askForSave() {
		int n = JOptionPane.showConfirmDialog(w, "Would you like to save the character?", "Character Save",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // yes = 0, no = 1
		if (n == 0)
			internalSave();
		else if (n == 1) { // Essentially, do nothing. Can't exclude it as the other else if for a critical failure (otherwise, 
			//  closing the window without selecting anything)
			return false;
		} else {
			error();
			return false;
		}
		return true;
	}

	// Saves a character in the window
	private void internalSave() {
		CharacterCreator cc = new CharacterCreator();
		cc.char1 = test;
		int returnVal = jc.showDialog(w, "Save character");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				cc.saveSystem(jc.getSelectedFile().getAbsolutePath());
			} catch (Exception ex) {
				error();
				throw new Error();
			}
		}
	}

	public static void main(String[] args) { // Essentially useless, only used to start the program
		MainGUI s = new MainGUI();
		s.start();
	}

}
