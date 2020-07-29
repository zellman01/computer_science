package game.bom.card;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.bom.error.ErrorCodes;
import game.bom.utilities.Loader;
import game.bom.utilities.Update;

public class CardViewer extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel id, name, hp, atk, displayId, displayName, displayHp, displayAtk;
	private JButton cardSel;
	private int idNum;
	private Card card;
	CardViewer r;

	public CardViewer() {
		super("Card Viewer");

		idNum = 1;

		id = new JLabel("ID: ");
		name = new JLabel("Name: ");
		hp = new JLabel("HP: ");
		atk = new JLabel("ATK: ");

		cardSel = new JButton("Select Card ID");
		cardSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idNum = Integer.parseInt(JOptionPane.showInputDialog(r, "Input the card ID number you want to view", "Card ID Select", 
						JOptionPane.QUESTION_MESSAGE));
				loadCard();
			}
		});

		displayId = new JLabel("");
		displayName = new JLabel("");
		displayHp = new JLabel("");
		displayAtk = new JLabel("");

		loadCard();

		Container c = getContentPane();

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,4));
		p.add(id); p.add(displayId);
		p.add(name); p.add(displayName);
		p.add(hp); p.add(displayHp);
		p.add(atk); p.add(displayAtk);

		JPanel s = new JPanel();
		s.add(cardSel);

		c.add(p, BorderLayout.CENTER);
		c.add(s, BorderLayout.SOUTH);
	}

	public CardViewer(boolean b) {
	}

	private void loadCard() {
		this.card = Loader.card(Integer.toString(idNum));
		try {
			if (!card.equals(null)) {
				displayId.setText(Integer.toString(card.getIdNumber()));
				displayName.setText(card.getName());
				displayHp.setText(Integer.toString(card.getHealth()));
				displayAtk.setText(Integer.toString(card.getAttack()));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(r, ErrorCodes.E500, "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void start() {
		Update u = new Update();
		if (!u.updateCards(true)) {
			JOptionPane.showMessageDialog(r, ErrorCodes.E300 + " Checking if the directory exists.", "Error", 
					JOptionPane.ERROR_MESSAGE);
			Path path = Paths.get("cards/");
			if(!Files.exists(path)) {
				JOptionPane.showMessageDialog(r, ErrorCodes.E501, "Fatal Error", 
						JOptionPane.ERROR_MESSAGE);
				throw new Error(ErrorCodes.E501.toString());
			}
		}
		r = new CardViewer();
		r.pack();
		r.setBounds(0, 0, 250, 250);
		r.setLocationRelativeTo(null);
		r.setDefaultCloseOperation(EXIT_ON_CLOSE);
		r.setVisible(true);
	}


	public static void main(String[] args) {
		CardViewer s = new CardViewer(true);
		s.start();
	}

}
