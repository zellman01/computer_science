package game.bom.deck;

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

import game.bom.card.Card;
import game.bom.error.ErrorCodes;
import game.bom.utilities.CardGUIViewer;
import game.bom.utilities.Loader;

/**
 * Views cards in a deck, like {@link game.bom.card.CardViewer}
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
//TODO: Create a clicker to view cards without having to go through another popup menu
public class DeckViewer extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton cardSel, deckSel;
	private int deckPos;
	private String deckName;
	private Card card;
	private Deck deck;
	private DeckViewer r;
	private Container c;
	private Component p;

	public DeckViewer() {
		super("Deck Viewer");

		deckPos = 0;
		deckName = "Default";

		cardSel = new JButton("Select Card Position");
		cardSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deckPos = Integer.parseInt(JOptionPane.showInputDialog(r, "Input the deck position you want to view."
						+ "The total cards in this deck is " + deck.getDeckSize() + ".", "Deck Position", 
						JOptionPane.QUESTION_MESSAGE))-1;
				c.remove(p);
				loadCard();
				revalidate();
				repaint();
			}
		});

		deckSel = new JButton("Select Deck");
		deckSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deckName = JOptionPane.showInputDialog(r, "Input the deck name you want to view", "Deck Selection", 
						JOptionPane.QUESTION_MESSAGE);
				c.remove(p);
				loadDeck();
				revalidate();
				repaint();
			}
		});

		c = getContentPane();

		loadDeck();

		JPanel s = new JPanel();
		s.add(cardSel); s.add(deckSel);

		c.add(s, BorderLayout.SOUTH);
	}

	private void loadCard() {
		this.card = deck.getCard(deckPos);
		try {
			p = CardGUIViewer.viewCard(card);
			c.add(p, BorderLayout.CENTER);
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(r, ErrorCodes.E500, "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void loadDeck() {
		this.deck = Loader.deck(deckName);
		if (!deck.equals(null)) {
			deckPos = 0;
			loadCard();
		} else
			JOptionPane.showMessageDialog(r, ErrorCodes.E600, "Error",
					JOptionPane.ERROR_MESSAGE);
	}

	public void start(DeckViewer r) {
		Path path = Paths.get("deck/");
		if(!Files.exists(path)) {
			JOptionPane.showMessageDialog(r, ErrorCodes.E600, "Fatal Error", 
					JOptionPane.ERROR_MESSAGE);
			throw new Error(ErrorCodes.E600.toString());
		}
		r.pack();
		r.setBounds(0, 0, 300, 250);
		r.setLocationRelativeTo(null);
		r.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		r.setVisible(true);
	}
}
