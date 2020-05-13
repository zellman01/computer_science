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

/**
 * Changelog:
 * 1.5:
 * - Fixed Curse
 * - Renamed test to character
 * 
 * 1.6:
 * - Make all JButtons private global
 * - Add message for successful reset
 * - Refeactor damage/endTurn code
 * - Disabled/enabled the Start/End turn buttons depending on alive status
 * - Add error description for all thrown errors (including the custom error() method)
 * - Remove empty constructor
 * - Added more error cases
 * - Change STATUSES to a String array instead of an Object array
 * - Fix glitch of someone dying keep appearing if the HP stayed below zero even after modification of health
 * - Force program to close from the "Close" button
 * - Make saving clearer as to what character it is asking you to save
 * - Rearrange buttons
 *  
 * @author Zachary Wellman
 * @version 1.6
 * @since 1.0
 */
public class MainGUI extends JFrame implements ActionListener {
	protected MainGUI w;
	private static final long serialVersionUID = 1L;
	private JFileChooser jc;
	private Character character;
	private final Color currentTurn = Color.GREEN, notTurn = Color.RED;
	private final String[] STATUSES = {"Burned", "Frozen", "Paralysis", "Time Frozen", "Poisoned", "Slow", "Blinded", "Binded", "Cursed"};
	private JLabel hp, hp1, name, name1, charge, charge1, statuses, statuses1, turnStarted, turnStatus, rune, runeList;
	private JButton close, damage, startTurn, endTurn, addStatus, modifyCharge, runeAdd, runeRemove, save, reset;

	// Closes the character information GUI and asks if you want to save it, then saves it as a character file
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (askForSave()) // Needed to see if user wanted to exit, or accidently clicked on close
			System.exit(0);
	}

	public MainGUI(boolean a) {
		super("Battle Simulator 1.6");
		if (a) {

			// Setup file searcher
			jc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Character files", "chr", ".chr");
			jc.setFileFilter(filter);

			// Setup the actual character to use
			character = createOrLoad();

			// Labels
			hp = new JLabel("HP:");
			hp1 = new JLabel(Integer.toString(character.getHp()));

			name = new JLabel("Name:");
			name1 = new JLabel(character.getName());

			charge = new JLabel("Charge:");
			charge1 = new JLabel(Integer.toString(character.getEnergy()));

			statuses = new JLabel("Statuses:");
			statuses1 = new JLabel(character.getStatuses());

			turnStarted = new JLabel("Turn:");
			turnStatus = new JLabel(character.turnAlignment());

			rune = new JLabel("Runes:");
			runeList = new JLabel(character.getEquippedRunes().toString());

			if (character.getTurn()) {
				turnStatus.setForeground(currentTurn);
			} else {
				turnStatus.setForeground(notTurn);
			}

			// Buttons
			close = new JButton("Close");
			close.addActionListener(this);

			damage = new JButton("Damage");
			damage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String damage = JOptionPane.showInputDialog(w, "How much is " + character.getName() + " damaged for? (negative number for healing)",
							"Damage", JOptionPane.QUESTION_MESSAGE);
					character.modifyHp(Integer.parseInt(damage)*-1);
					checkHealth(true);
				}
			});

			startTurn = new JButton("Start turn");
			startTurn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (character.getTurn() || !character.getAlive()) {
						JOptionPane.showMessageDialog(w, character.getName() + "'s turn has already started, or is killed", "Action Unavailable", 
								JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						if (character.getTimeFrozen()) {
							JOptionPane.showMessageDialog(w, character.getName() + "'s turn has been stopped from being time frozen.", "Action Unavailable", 
									JOptionPane.ERROR_MESSAGE);
							character.timeFrozen(character.getTimeFrozenTimer()-1);
							statuses1.setText(character.getStatuses());
							endTurn(true);
							return;
						}
						if (character.getFrozen()) {
							JOptionPane.showMessageDialog(w, character.getName() + "'s turn has been stopped from being frozen.", "Action Unavailable", 
									JOptionPane.ERROR_MESSAGE);
							character.frozen(character.getFrozenTimer()-1);
							statuses1.setText(character.getStatuses());
							endTurn(true);
							return;
						}
						if (character.getPayalysis()) {
							int random = ThreadLocalRandom.current().nextInt(1, 20);
							if (random < 11) {
								JOptionPane.showMessageDialog(w, character.getName() + "'s turn has been stopped from being paralyzed.", "Action Unavailable", 
										JOptionPane.ERROR_MESSAGE);
								endTurn(true);
								return;
							}
						}
						if (character.getBound()) {
							JOptionPane.showMessageDialog(w, character.getName() + "'s turn has started, but their actions are limited from being bound "
									+ "to something.", "Action Information", 
									JOptionPane.WARNING_MESSAGE);
							character.bind(character.getBoundTime()-1);
							statuses1.setText(character.getStatuses());
						}
						character.turn(true);
						turnStatus.setForeground(currentTurn);
						turnStatus.setText(character.turnAlignment());
					}
				}
			});

			endTurn = new JButton("End Turn");
			endTurn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!character.getTurn()) {
						JOptionPane.showMessageDialog(w, character.getName() + "'s turn has not started yet, or is killed", "Action unavailable", 
								JOptionPane.ERROR_MESSAGE);
					} else {
						endTurn(true);
					}
				}
			});

			addStatus = new JButton("Statuses");
			addStatus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String s = (String)JOptionPane.showInputDialog(w, "What status is being added/removed?", "Status Menu", JOptionPane.PLAIN_MESSAGE, null, STATUSES, STATUSES[0]);
					if ((s != null) && (s.length() > 0)) {
						if (s == "Paralysis" || s == "Slow" || s == "Blinded" || s == "Cursed") {
							changeStatus(s, 0);
						} else { // Help for statuses
							String add = (String)JOptionPane.showInputDialog(w, "Please input the additional information based on the below chart "
									+ "and the status being added (Do not use negative numbers):"
									+ "\nBurned - The given level (0-4) (0 to disable the status). Inputting anything bigger will not add "
									+ "the status."
									+ "\nPoisoned - Amount of damage done every turn (set to 0 to disable)"
									+ "\nFrozen - Amount of turns frozen (set to 0 to disable)"
									+ "\nTime Frozen - Amount of turns time frozen (set to 0 to disable)"
									+ "\nBinded - Amount of turns bound (set to 0 to disable)"
									+ "\nCursed - The name of the curse",
									"Additional information", JOptionPane.QUESTION_MESSAGE);
							changeStatus(s, Integer.parseInt(add));
						}
						statuses1.setText(character.getStatuses());
					}
				}
			});

			modifyCharge = new JButton("Charge");
			modifyCharge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String charge = JOptionPane.showInputDialog(w, "How much does " + character.getName() + " charge for? "
							+ "(negative number for removing charge)",
							"Charge", JOptionPane.QUESTION_MESSAGE);
					try {
						character.updateEnergy(Integer.parseInt(charge));
						if (character.getEnergy() < 0) {
							JOptionPane.showMessageDialog(w, character.getName() + " does not have enough stored to do what they planed on doing", 
									"General Error", JOptionPane.ERROR_MESSAGE);
							character.updateEnergy(Integer.parseInt(charge)*-1);
						}
						charge1.setText(Integer.toString(character.getEnergy()));
					} catch (NumberFormatException b) {
						numError();
					}
				}
			});

			runeAdd = new JButton("Add rune");
			runeAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (character.getRuneCount() < 3) {
						String name = JOptionPane.showInputDialog(w, "What is the name of the rune being equipped?",
								"Add Rune", JOptionPane.QUESTION_MESSAGE);
						if ((name != null) && name.length() > 0) {
							character.runeEquip(name, -1);
							runeList.setText(character.getEquippedRunes().toString());
						} else {
							error("Rune names cannot be empty");
						}
					} else {
						JOptionPane.showMessageDialog(w, character.getName() + " already has three equipped runes.", "Rune Error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			runeRemove = new JButton("Remove rune");
			runeRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = JOptionPane.showInputDialog(w, "What is the name of the rune being removed?", "Remove Rune",
							JOptionPane.QUESTION_MESSAGE);
					if ((name != null) && (name.length() > 0)) {
						character.runeEquip("", character.obtainRunePos(name));
						runeList.setText(character.getEquippedRunes().toString());
					} else {
						error("No rune found with the given name");
					}
				}
			});

			save = new JButton("Save");
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					askForSave();
				}
			});

			reset = new JButton("Reset");
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showConfirmDialog(w, "Would you like to reset this character to before any modifications"
							+ " were done to the character in this window? (WARNING: It will fully reset it from file."
							+ " And and all edits done from before the last save would have to be re-done.)", "Reset Character from file",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // yes = 0, no = 1
					switch (n) {
					case 0:
						character = loadCharSystem(character.saveName());

						// Start to reset all labels
						if (character.getTurn())
							turnStatus.setForeground(currentTurn);
						else
							turnStatus.setForeground(notTurn);

						turnStatus.setText(character.turnAlignment());
						statuses1.setText(character.getStatuses()); // Update statuses
						hp1.setText(Integer.toString(character.getHp())); // Update HP
						character.updateEnergy(character.getEnergy()); // Update energy
						runeList.setText(character.getEquippedRunes().toString()); // Upodate runes

						JOptionPane.showMessageDialog(w, "Successful reset.", "Reset", JOptionPane.INFORMATION_MESSAGE);

						break;
					case 1: // Needed or else an error message would appear when they selected "no"
						break;
					default:
						JOptionPane.showConfirmDialog(w, "Error: Window was either closed or something went wrong.");
					}
				}
			});


			Container c = getContentPane();
			c.setBackground(Color.GREEN);

			JPanel p = new JPanel(); // Displayed text
			p.setLayout(new GridLayout(6,1));

			p.add(hp); p.add(hp1);
			p.add(name); p.add(name1);
			p.add(charge); p.add(charge1);
			p.add(statuses); p.add(statuses1);
			p.add(turnStarted); p.add(turnStatus);
			p.add(rune); p.add(runeList);

			JPanel s = new JPanel(); // Actions
			s.setLayout(new GridLayout(2,5));

			s.add(close);        s.add(runeAdd);    s.add(startTurn);  s.add(damage);     s.add(save); 
			s.add(modifyCharge); s.add(runeRemove); s.add(endTurn);    s.add(addStatus);  s.add(reset);

			c.add(p, BorderLayout.CENTER);
			c.add(s, BorderLayout.SOUTH);
		} else {

		}
	}

	private void checkHealth(boolean damageUse) {
		if (character.getHp() > 0 && !character.getAlive()) {
			character.alive(true);
			JOptionPane.showMessageDialog(w, character.getName() + " has been revived.", "Alive",
					JOptionPane.INFORMATION_MESSAGE);
			startTurn.setEnabled(true);
			endTurn.setEnabled(true);

		} else if (character.getHp() <= 0) {
			if (character.getAlive())
				JOptionPane.showMessageDialog(w, character.getName() + " has been killed.", "Dead",
						JOptionPane.INFORMATION_MESSAGE);
			character.alive(false);
			startTurn.setEnabled(false);
			endTurn.setEnabled(false);
			if (damageUse)
				endTurn(false);
		}
		statuses1.setText(character.getStatuses());
		hp1.setText(Integer.toString(character.getHp()));
	}

	// Test
	protected boolean isDead() {
		System.out.println(character);
		return !character.getAlive();
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
			error("Error creating or loading a character");
		}

		// Should never happen
		error("createOrLoad() - Internal Error");
		return null;
	}

	private void changeStatus(String name, int additional) {
		name = name.toLowerCase();
		switch (name) {
		case "paralysis":
			character.paralysis(!character.getPayalysis());
			break;
		case "burned":
			character.burned(additional);
			break;
		case "poisoned":
			character.poison(additional);
			break;
		case "frozen":
			character.frozen(additional);
			break;
		case "time frozen":
			character.timeFrozen(additional);
			break;
		case "slow":
			character.slow(!character.getSlow());
			break;
		case "binded":
			character.bind(additional);
			break;
		case "blinded":
			character.blind(!character.getBlind());
			break;
		case "cursed":
			if (character.getCurse() == null)
				character.curse(true);
			else if (character.getCurse() != null)
				character.curse(false);
			else
				error("cursed status condition - Internal Error");
			break;
		default:
			error("Status change error");
			break;
		}
	}

	private void error(String desc) {
		JOptionPane.showMessageDialog(w, "Something went wrong with that action.\nError description: " + desc, "Error", 
				JOptionPane.ERROR_MESSAGE);
		throw new Error("Something horribly went wrong: " + desc);
	}

	private void numError() {
		JOptionPane.showMessageDialog(w, "What you inputted was not a number.", "Number Error", JOptionPane.ERROR_MESSAGE);
		throw new Error("Input of number went wrong");
	}

	private void endTurn(boolean endTurn) { // Have to do it this way so that both startTurn and endTurn actionlisteners can use it
		if (endTurn) {
			if (character.getBurn()) {
				int[] burnDamage = {25, 50, 75, 100};
				character.modifyHp(-burnDamage[character.getBurnLevel()-1]);
				JOptionPane.showMessageDialog(w, character.getName() + " took " + burnDamage[character.getBurnLevel()-1] + " in burn damage.", "Burn Damage",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (character.getPoison()) {
				character.modifyHp(-character.getPoisonDamage());
				JOptionPane.showMessageDialog(w, character.getName() + " took " + character.getPoisonDamage() + " in poison damage.", "Poison Damage",
						JOptionPane.INFORMATION_MESSAGE);
			}
			checkHealth(false);
		}
		character.turn(false);
		turnStatus.setForeground(notTurn);
		turnStatus.setText(character.turnAlignment());
	}

	public void start() {
		w = new MainGUI(true);
		w.pack();
		w.setBounds(300,300,570,250); // Only modify the last one - height
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		w.setVisible(true);
		w.setResizable(false);
	}
	
	/**
	 * Loads a character into the program
	 * @param name The name of the character
	 * @return The loaded character
	 */
	private Character loadCharSystem(String name) {
		File file = new File(name + ".chr");
		System.out.println(name);
		try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return (Character) in.readObject();

		} catch(IOException | ClassNotFoundException ex) {
			error("loadCharSystem() - Internal Error");
			throw new Error("Error loading a character");
		}
	}

	//Asks to save a character
	private boolean askForSave() {
		int n = JOptionPane.showConfirmDialog(w, "Would you like to save the character " + character.getName() + "?", "Character Save",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); // yes = 0, no = 1
		if (n == 0)
			internalSave();
		else if (n == 1) { // Essentially, do nothing. Can't exclude it as the other else if for a critical failure (otherwise, 
			//  closing the window without selecting anything)
		} else {
			return false;
		}
		return true;
	}

	// Saves a character in the window
	private void internalSave() {
		CharacterCreator cc = new CharacterCreator();
		cc.char1 = character;
		int returnVal = jc.showDialog(w, "Save character");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				cc.saveSystem(jc.getSelectedFile().getAbsolutePath());
			} catch (Exception ex) {
				error("Save system error - Internal error");
				throw new Error("Save system error");
			}
		}
	}

	public static void main(String[] args) { // Essentially useless, only used to start the program
		MainGUI s = new MainGUI(false);
		s.start();
	}

}
