package game.bom.deck;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.bom.card.CardViewer;
import game.bom.global.Globals;
import game.bom.utilities.Loader;

/**
 * GUI for creating decks
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public class DeckGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> possibleCards, selectedCards;
	
	public DeckGUI() {
		super("Deck Creator");
		possibleCards = new ArrayList<>();
		selectedCards = new ArrayList<>();
		for (int i = 1; i <= Globals.NUM_CARDS; i++) {
			possibleCards.add(i);
		}
		
		Container c = getContentPane();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2));
		for (int i = 1; i <= possibleCards.size(); i++) {
			JCheckBox b = new JCheckBox(Loader.card(Integer.toString(possibleCards.get(i-1))).getName());
			int d = i;
			b.setSelected(false);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(b.isSelected()) {
						selectedCards.add(d);
					} else {
						selectedCards.remove(d);
					}
				}
				
			});
			p.add(b);
		}
		
		JButton finalize = new JButton("Create Deck");
		finalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeckCreator dc = new DeckCreator();
				dc.setCardArray(selectedCards);
				try {
					dc.createDeck("Default");
					System.out.println("Deck created");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		c.add(finalize, BorderLayout.SOUTH);
		c.add(p, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		DeckGUI s = new DeckGUI();
		s.pack();
		s.setBounds(0, 0, 250, 250);
		s.setLocationRelativeTo(null);
		s.setDefaultCloseOperation(EXIT_ON_CLOSE);
		s.setVisible(true);
	}
}
