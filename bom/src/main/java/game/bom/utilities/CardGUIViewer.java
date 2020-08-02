package game.bom.utilities;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.bom.card.Card;

public class CardGUIViewer {
	
	public static JPanel viewCard(Card card) {
		JLabel id = new JLabel("ID: " + card.getIdNumber());
		JLabel name = new JLabel("Name: " + card.getName());
		JLabel hp = new JLabel("HP: " + card.getHealth());
		JLabel atk = new JLabel("ATK: " + card.getAttack());
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,4));
		p.add(id);
		p.add(name);
		p.add(hp);
		p.add(atk);
		
		return p;
	}

}
