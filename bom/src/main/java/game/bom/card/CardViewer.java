package game.bom.card;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.bom.error.ErrorCodes;
import game.bom.utilities.CardGUIViewer;
import game.bom.utilities.Loader;

/**
 * GUI for viewing individual cards
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class CardViewer extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton cardSel;
	private int idNum;
	private Card card;
	private CardViewer r;
	private Container c;
	private Component p;

	public CardViewer() {
		super("Card Viewer");

		idNum = 1;
		
		p = CardGUIViewer.viewCard(Loader.card(Integer.toString(idNum)));

		cardSel = new JButton("Select Card ID");
		cardSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idNum = Integer.parseInt(JOptionPane.showInputDialog(r, "Input the card ID number you want to view", "Card ID Select", 
						JOptionPane.QUESTION_MESSAGE));
				c.remove(p);
				loadCard();
				revalidate();
				repaint();
			}
		});

		c = getContentPane();
		
		loadCard();


		JPanel s = new JPanel();
		s.add(cardSel);

		c.add(s, BorderLayout.SOUTH);
	}

	private void loadCard() {
		this.card = Loader.card(Integer.toString(idNum));
		try {
				p = CardGUIViewer.viewCard(card);
				c.add(p, BorderLayout.CENTER);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(r, ErrorCodes.E500, "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void start(boolean successUpdate) {
		if (!successUpdate) {
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
		r.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		r.setVisible(true);
	}
}
