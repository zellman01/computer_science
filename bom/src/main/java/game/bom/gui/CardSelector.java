package game.bom.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.bom.sqlConnection.SQL;

public class CardSelector extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel id, name, displayId, displayName;
	private SQL newSQL;

	public CardSelector() {
		super("test");

		id = new JLabel("ID: ");
		name = new JLabel("Name: ");

		displayId = new JLabel("");
		displayName = new JLabel("");
		Container c = getContentPane();

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,2));
		p.add(id); p.add(name);
		p.add(displayId); p.add(displayName);

		c.add(p, BorderLayout.CENTER);

		newSQL = new SQL();
		//connect = getConnection();
	}


	public static void main(String[] args) {
		CardSelector s = new CardSelector();
		s.pack();
		s.setBounds(0, 0, 500, 500);
		s.setDefaultCloseOperation(EXIT_ON_CLOSE);
		s.setVisible(true);
	}

}
