import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

// Making JDialog box, not JFrame
public class MoveHelper extends JDialog implements ActionListener {
	private JLabel moveName;
	private JLabel accuracy;
	private JLabel damagePool;
	private JLabel effect;
	private JLabel actionLabel;
	private JLabel statusLabel;
	private JLabel statusChanceLabel;
	private JLabel priorityLabel;
	private JLabel weatherLabel;
	private JLabel typeLabel;
	private JLabel accuracyModLabel;
	private JCheckBox highCritical;
	private JCheckBox lethal;
	private JCheckBox ranged;
	private JCheckBox blocked;
	private JCheckBox charge;
	private JCheckBox heal;
	private JCheckBox recharge;
	private JCheckBox neverFail;
	private JCheckBox rampage;
	private JCheckBox recoil;
	private JCheckBox shield;
	private JCheckBox soundBased;
	private JCheckBox switchSelf;
	private JCheckBox switchOpponent;
	private JComboBox<String> successiveActions;
	private JComboBox<String> status;
	private JComboBox<String> statusChance;
	private JComboBox<String> priority;
	private JComboBox<String> weather;
	private JComboBox<String> type;
	private JComboBox<String> accuracyMod;
	private JCheckBox switchMove;
	private JTextField accuracyInput;
	private JTextField moveNameInput;
	private JTextField damagePoolInput;
	private JTextField effectInput;
	private JButton add;
	private JButton addMore;
	private Manager moveList;
	
	
	public MoveHelper(Manager moveList) {
		this.moveList = moveList;
		
		String[] successiveActionList = {"-", "2", "5"};
		String[] statusList = {"-", "Paralysis", "Burn 1", "Burn 2", "Burn 3", "Sleep", "Poison", "Poison+", "Frozen", "Flinch", "Love", "Confuse"};
		String[] statusChanceList = {"-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
		String[] priorityList = {"-7", "-6", "-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3", "4", "5", "6", "7"};
		String[] weatherList = {"-", "Sunny", "Rainy", "Hail", "Fog"};
		String[] typesList = {"Bug", "Dark", "Dragon", "Electric", "Fairy", "Fighting", "Fire", "Flying", "Ghost", "Grass", "Ground", "Ice", "Normal", "Poison", "Psychic", "Rock", "Steel", "Water", "Typeless", "Max", "Z-Moves"};
		String[] accuracyModList = {"-7", "-6", "-5", "-4", "-3", "-2", "-1", "0", "1", "2", "3", "4", "5", "6", "7"};
		
		moveName = new JLabel("Input move name");
		accuracy = new JLabel("Enter Accuracy");
		damagePool = new JLabel("Enter Damage pool");
		effect = new JLabel("Add extra information not added by checkboxes or combo boxes");
		actionLabel = new JLabel("Successive actions");
		statusLabel = new JLabel("Status afflicted");
		statusChanceLabel = new JLabel("Status Chance Die");
		priorityLabel = new JLabel("Priority");
		weatherLabel = new JLabel("Weather");
		typeLabel = new JLabel("Type");
		accuracyModLabel = new JLabel("Accuracy Mod");
		
		accuracyInput = new JTextField(15);
		moveNameInput = new JTextField(15);
		damagePoolInput = new JTextField(15);
		effectInput = new JTextField(30);
		
		highCritical = new JCheckBox("High Critical"); 
		lethal = new JCheckBox("Lethal");
		blocked = new JCheckBox("Block");
		charge = new JCheckBox("Charge");
		heal = new JCheckBox("Heal");
		recharge = new JCheckBox("Recharge");
		neverFail = new JCheckBox("Never Fail");
		rampage = new JCheckBox("Rampage");
		recoil = new JCheckBox("Recoil");
		shield = new JCheckBox("Shield");
		soundBased = new JCheckBox("Sound Based");
		ranged = new JCheckBox("Ranged");
		switchSelf = new JCheckBox("Switch (Self)");
		switchOpponent = new JCheckBox("Switch (Opponent)");
		
		successiveActions = new JComboBox<>(successiveActionList);
		status = new JComboBox<>(statusList);
		statusChance = new JComboBox<>(statusChanceList);
		priority = new JComboBox<>(priorityList);
		priority.setSelectedIndex(7);
		weather = new JComboBox<>(weatherList);
		type = new JComboBox<>(typesList);
		accuracyMod = new JComboBox<>(accuracyModList);
		accuracyMod.setSelectedIndex(7);
		
		add = new JButton("Add");
		add.addActionListener(this);
		
		addMore = new JButton("Add another");
		addMore.setActionCommand("MORE");
		addMore.addActionListener(this);
		
		JPanel checkBoxPanel = new JPanel();
		JPanel textInput = new JPanel();
		JPanel comboFields = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel finalPanel = new JPanel();
		
		checkBoxPanel.add(highCritical);
		checkBoxPanel.add(lethal);
		checkBoxPanel.add(blocked);
		checkBoxPanel.add(charge);
		checkBoxPanel.add(heal);
		checkBoxPanel.add(recharge);
		checkBoxPanel.add(neverFail);
		checkBoxPanel.add(rampage);
		checkBoxPanel.add(recoil);
		checkBoxPanel.add(shield);
		checkBoxPanel.add(ranged);
		checkBoxPanel.add(soundBased);
		checkBoxPanel.add(switchSelf);
		checkBoxPanel.add(switchOpponent);
		
		comboFields.add(typeLabel);
		comboFields.add(type);
		comboFields.add(actionLabel);
		comboFields.add(successiveActions);
		comboFields.add(statusLabel);
		comboFields.add(status);
		comboFields.add(statusChanceLabel);
		comboFields.add(statusChance);
		comboFields.add(priorityLabel);
		comboFields.add(priority);
		comboFields.add(weatherLabel);
		comboFields.add(weather);
		comboFields.add(accuracyModLabel);
		comboFields.add(accuracyMod);
		
		textInput.add(moveName);
		textInput.add(moveNameInput);
		textInput.add(accuracy);
		textInput.add(accuracyInput);
		textInput.add(damagePool);
		textInput.add(damagePoolInput);
		textInput.add(effect);
		textInput.add(effectInput);
		
		buttonPanel.add(add);
		buttonPanel.add(addMore);
		
		finalPanel.add(textInput);
		finalPanel.add(checkBoxPanel);
		finalPanel.add(comboFields);
		finalPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		add(finalPanel);
		
		start();
	}
	
	private void start() {
		Toolkit tk;
		Dimension d;
		
		setTitle("Move Creator");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(1700, d.height/2);
		setLocation(150, d.height/4);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setVisible(true);
	}
	
	private void setToDefault() {
		successiveActions.setSelectedIndex(0);
		status.setSelectedIndex(0);
		statusChance.setSelectedIndex(0);
		priority.setSelectedIndex(7);
		weather.setSelectedIndex(0);
		accuracyMod.setSelectedIndex(7);
		//type.setSelectedIndex(0);
		accuracyInput.setText("");
		moveNameInput.setText("");
		damagePoolInput.setText("");
		effectInput.setText("");
		highCritical.setSelected(false);
		lethal.setSelected(false);
		blocked.setSelected(false);
		charge.setSelected(false);
		heal.setSelected(false);
		recharge.setSelected(false);
		neverFail.setSelected(false);
		recoil.setSelected(false);
		shield.setSelected(false);
		soundBased.setSelected(false);
		ranged.setSelected(false);
		switchSelf.setSelected(false);
		switchOpponent.setSelected(false);
		rampage.setSelected(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int accuracy = Integer.parseInt(accuracyInput.getText().trim());
		int critical = accuracy - 5;
		if (accuracy < 0) critical = 95;
		if (highCritical.isSelected()) {
			critical -= 10; // Subtracts 15
		}
		moveList.addMoves(Moves.makeMove(moveNameInput.getText().trim(), accuracy, critical, getEffect(), damagePoolInput.getText().trim(), type.getSelectedItem().toString(), accuracyMod.getSelectedItem().toString()));
		
		if (e.getActionCommand().equalsIgnoreCase("more")) {
			setToDefault();
		} else {
			dispose();
		}
	}
	
	private String getEffect() {
		String temp = "";
		
		if (!priority.getSelectedItem().toString().equals("0")) {
			temp += "Priority " + priority.getSelectedItem().toString() + ". ";
		}
		
		if (lethal.isSelected()) temp += "Lethal. ";
		if (highCritical.isSelected()) temp += "High critical. ";
		if (soundBased.isSelected()) temp += "Sound based. ";
		if (ranged.isSelected()) temp += "Ranged. ";
		if (blocked.isSelected()) temp += "All Pokemon may not switch out. ";
		if (charge.isSelected()) temp += "User waits for a turn, then unleashes the move. Check accuracy on the second turn. ";
		if (heal.isSelected()) temp += "Heals self and/or allies. Costs 1 will point to use. ";
		if (recharge.isSelected()) temp += "Requires the user to recharge for the next turn. ";
		if (neverFail.isSelected()) temp += "This attack will never fail (can be clashed) ";
		if (rampage.isSelected()) temp += "User uses the same move for 3 turns, then gets confused. ";
		if (recoil.isSelected()) temp += "Re-roll each damage success dice. If it is another success, damage the user. Minimum of 1 damage. ";
		if (shield.isSelected()) temp += "User is fully protected this turn. Successive actions of any other shield-based move will lower the accuracy by 35%. ";
		if (switchSelf.isSelected()) temp += "User switches out after dealing damage, and the chosen Pokemon is ready to fight. Roll initative for the new Pokemon. Will fail if blocked by an external force. ";
		if (switchOpponent.isSelected()) temp += "Forces the opponent to switch. If a wild battle, ends it by force. If last Pokemon on a trainer, fails. ";
		
		if (!successiveActions.getSelectedItem().toString().equals("-")) {
			temp += "Hits " + successiveActions.getSelectedItem().toString() + " times. Accuracy lowers 5-10% for every hit in succession, depending on how much damage the Pokemon has sustained up to this point. Critical lets all " + successiveActions.getSelectedItem().toString() + " hit instead of adding damage dice. ";
		}
		
		if (!status.getSelectedItem().toString().equals("-")) {
			String statusCondition = status.getSelectedItem().toString();
			if (!statusChance.getSelectedItem().toString().equals("-")) {
				if (statusChance.getSelectedItem().toString().equals("0")) {
					temp += "The opposing Pokemon is " + statusCondition + ". ";
				} else {
					temp += "Roll " + statusChance.getSelectedItem().toString() + " chance die. Opposing Pokemon is now " + statusCondition + " on a success. ";
				}
			}
		}
		
		if (!weather.getSelectedItem().toString().equals("-")) {
			temp += "Changes weather to " + weather.getSelectedItem().toString() + ". ";
		}
		
		temp += effectInput.getText().trim();
		return temp;
	}
}