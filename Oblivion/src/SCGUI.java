import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SCGUI extends JFrame implements ActionListener {
	SCGUI w;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField nameS, damage, nameU;
	
	public SCGUI() {
		super("Special creator");
		JLabel speName = new JLabel("Special Name: ");
		nameS = new JTextField(1);
		JLabel dam = new JLabel("Damage: ");
		damage = new JTextField(1);
		JLabel useName = new JLabel("User's name: ");
		nameU = new JTextField(1);
		
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,1));
		p.add(speName);
		p.add(nameS);
		p.add(dam);
		p.add(damage);
		p.add(useName);
		p.add(nameU);
		c.add(p, BorderLayout.CENTER);
		c.add(confirm, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SpecialCreator sc = new SpecialCreator();
		String speName = nameS.getText(), userName = nameU.getText(), check = damage.getText();
		while (check.indexOf(',') >= 0) {
			check = check.substring(0,check.indexOf(',')) + check.substring(check.indexOf(',')+1, check.length());
		}
		int hp1 = Integer.parseInt(check);
		sc.system(speName, userName, hp1);
		JOptionPane.showMessageDialog(w,
				"Special " + speName + " has been created successfully."
				+ "\nThe character able to use this is " + userName + ".");
	}
	
	private void start() {
		w = new SCGUI();
		w.pack();
		w.setBounds(300,300,300,150);
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(EXIT_ON_CLOSE);
		w.setVisible(true);
		JOptionPane.showMessageDialog(w, 
				"Special Name: The special's name. Will also be the file name."
				+ "\nDamage: The amount of damage done (if any) (must have 0 if no healing/damage is done). Put a negative number in if it heals instead of damages."
				+ "\nUser's Name: The name of the character that can use it. MUST be only the character's first name.");
	}
	
	public static void main(String[] args) {
		SCGUI s = new SCGUI();
		s.start();
	}
	
}
