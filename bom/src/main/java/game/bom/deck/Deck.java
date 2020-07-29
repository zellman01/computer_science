package game.bom.deck;

import java.io.Serializable;
import java.util.ArrayList;

import game.bom.card.Card;
import game.bom.error.ErrorCodes;
import game.bom.utilities.Loader;
import game.bom.global.Globals;

// TODO: Set up a way to save only the card id numbers, then search them up that way
/**
 * Class for all deck objects
 * @author Zachary Wellman
 * @version 0.1.0
 * @since 0.1.0
 */
public class Deck implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> cardList;
	private String deckName;
	
	public Deck(String name) {
		cardList = new ArrayList<>();
		deckName = name;
	}
	
	public void addCard(Card card) {
		if (getDeckSize() < Globals.MAX_CARDS) {
			cardList.add(card.getIdNumber());
		} else {
			throw new Error(ErrorCodes.E601.toString());
		}
	}
	
	private String displayCards() {
		String str = "";
		for (int i = 0; i < getDeckSize(); i++) {
			str += " " + getCard(cardList.get(i)) + "\n";
		}
		return str;
	}
	
	public Card getCard(int pos) {
		return Loader.card(Integer.toString(cardList.get(pos)));
	}
	
	public int getDeckSize() { return cardList.size(); }
	protected ArrayList<Integer> getCardList() { return cardList; }
	
	public String toString() {
		String str = "";
		str += "Deck name: " + deckName;
		str +="\nCards in deck: " + getDeckSize();
		str += "\nCard list: \n" + displayCards();
		return str;
	}
}
