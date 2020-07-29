package game.bom.deck;

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

import game.bom.card.Card;
import game.bom.error.ErrorCodes;
import game.bom.utilities.Loader;

//TODO: Create a clicker to view cards without having to go through another popup menu
public class DeckViewer extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel id, name, hp, displayId, displayName, displayHp;
	private JButton cardSel, deckSel;
	private int deckPos;
	private String deckName;
	private Card card;
	private Deck deck;
	DeckViewer r;

	public DeckViewer() {
		super("Deck Viewer");
		
		deckPos = 0;
		deckName = "Default";

		id = new JLabel("ID: ");
		name = new JLabel("Name: ");
		hp = new JLabel("HP: ");
		
		cardSel = new JButton("Select Card Position");
		cardSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deckPos = Integer.parseInt(JOptionPane.showInputDialog(r, "Input the deck position you want to view."
						+ "The total cards in this deck is " + deck.getDeckSize() + ".", "Deck Position", 
						JOptionPane.QUESTION_MESSAGE))-1;
				loadCard();
			}
		});
		
		deckSel = new JButton("Select Deck");
		deckSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deckName = JOptionPane.showInputDialog(r, "Input the deck name you want to view", "Deck Selection", 
						JOptionPane.QUESTION_MESSAGE);
				loadDeck();
			}
		});
		
		displayId = new JLabel("");
		displayName = new JLabel("");
		displayHp = new JLabel("");
		
		loadDeck();
		
		Container c = getContentPane();

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3,2));
		p.add(id); p.add(displayId);
		p.add(name); p.add(displayName);
		p.add(hp); p.add(displayHp);
		
		JPanel s = new JPanel();
		s.add(cardSel); s.add(deckSel);

		c.add(p, BorderLayout.CENTER);
		c.add(s, BorderLayout.SOUTH);
	}
	
	public DeckViewer(boolean b) {
	}

	private void loadCard() {
		this.card = deck.getCard(deckPos);
		if (!card.equals(null)) {
		displayId.setText(Integer.toString(card.getIdNumber()));
		displayName.setText(card.getName());
		displayHp.setText(Integer.toString(card.getHealth()));
		} else {
			JOptionPane.showMessageDialog(r, ErrorCodes.E500, "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loadDeck() {
		this.deck = Loader.deck(deckName);
		if (!deck.equals(null)) {
			loadCard();
		} else
			JOptionPane.showMessageDialog(r, ErrorCodes.E600, "Error",
					JOptionPane.ERROR_MESSAGE);
	}

	private void start() {
			Path path = Paths.get("deck/");
			if(!Files.exists(path)) {
				JOptionPane.showMessageDialog(r, ErrorCodes.E600, "Fatal Error", 
						JOptionPane.ERROR_MESSAGE);
				throw new Error(ErrorCodes.E600.toString());
			}
		r = new DeckViewer();
		r.pack();
		r.setBounds(0, 0, 300, 250);
		r.setLocationRelativeTo(null);
		r.setDefaultCloseOperation(EXIT_ON_CLOSE);
		r.setVisible(true);
	}


	public static void main(String[] args) {
		DeckViewer s = new DeckViewer(true);
		s.start();
	}
}
