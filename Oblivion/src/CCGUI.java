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
import javax.swing.SwingConstants;

// Character Creator GUI
public class CCGUI extends JFrame implements ActionListener {
	private CCGUI w;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField fName, lName, hp;

	public CCGUI() {
		super("Character Creator");
		JLabel firstName = new JLabel("First name: ", SwingConstants.RIGHT);
		fName = new JTextField(1);
		JLabel lastName = new JLabel("Last name: ", SwingConstants.RIGHT);
		lName = new JTextField(1);
		JLabel hpThing = new JLabel("HP: ", SwingConstants.RIGHT);
		hp = new JTextField(1);

		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(this);

		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(firstName);
		p.add(fName);
		p.add(lastName);
		p.add(lName);
		p.add(hpThing);
		p.add(hp);
		c.add(p, BorderLayout.CENTER);
		c.add(confirm, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CharacterCreator cc = new CharacterCreator();
		String firName = fName.getText(), lasName = lName.getText(), check = hp.getText();
		while (check.indexOf(',') >= 0) {
			check = check.substring(0,check.indexOf(',')) + check.substring(check.indexOf(',')+1, check.length());
		}
		int hp1 = Integer.parseInt(check);
		cc.system(firName, lasName, hp1);
		if (lasName.equalsIgnoreCase("n/a") || lasName.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(w,
					"The character " + cc.getChar1().getName() + " with the HP of " + cc.getChar1().getHp() + " has been created."
							+ "\nThe character was saved as " +  firName + ".");
		} else {
			JOptionPane.showMessageDialog(w,
					"The character " + cc.getChar1().getName() + " with the HP of " + cc.getChar1().getHp() + " has been created."
							+ "\nThe character was saved as " + lasName + "_" + firName + ".");
		}
		/*int dialogButton = JOptionPane.showConfirmDialog(null, "Testing", "Warning", JOptionPane.YES_NO_OPTION);
		if (dialogButton == JOptionPane.NO_OPTION) {
			w.setVisible(false);
		}*/
	}

	public void start() {
		w = new CCGUI();
		w.pack();
		w.setBounds(300,300,300,150);
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(EXIT_ON_CLOSE);
		w.setVisible(true);
	}

	public static void main(String[] args) {
		CCGUI s = new CCGUI();
		s.start();
	}

}
