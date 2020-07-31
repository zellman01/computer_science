package game.bom.deck;

import java.io.Serializable;
import java.util.ArrayList;

import game.bom.card.Card;
import game.bom.error.ErrorCodes;
import game.bom.utilities.Loader;
import game.bom.global.Globals;

// TODO: Set up a way to save only the card id numbers, then search them up that way
/**
 * Deck objects
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class Deck implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> cardList;
	private String deckName;
	
	/**
	 * Creates the Deck object
	 * @param name The name to give the deck
	 */
	public Deck(String name) {
		cardList = new ArrayList<>();
		deckName = name;
	}
	
	/**
	 * Adds a card to the deck
	 * @param card The card being added
	 */
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
	
	/**
	 * Obtains a card from the deck
	 * @param pos the position of the card to get
	 * @return The card, or null if no card is in the given position
	 */
	public Card getCard(int pos) {
		return Loader.card(Integer.toString(cardList.get(pos)));
	}
	/**
	 * Obtains the size of the deck
	 * @return Size of deck
	 */
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
