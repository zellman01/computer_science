package utils;

import java.awt.Color;
import utils.Card;

public class Deck {
	private Card[] cardList;
	private int topCard;
	
	//TODO: Needs to be better
	// Potentially make it create a card from the three things to create a card, then search like that
	//  Other potential: Add multiple ways to search
	public Card search(Card test) {
		return cardSearch(test);
	}
	
	public Card search(Color color, char suit, String face) {
		Card test = new Card(color, suit, face);
		return cardSearch(test);
	}
	
	private Card cardSearch(Card test) {
		Card ret = null;
		for (int i = topCard; i < cardList.length; i++) {
			if (test.equals(cardList[i])) {
				ret = cardList[i];
				break;
			}
		}
		return ret;
	}
	
	/**
	 * Creates a deck.
	 */
	public Deck() {
		topCard = 0;
		cardList = createDeck();
	}
	
	/**
	 * Shuffles the constructed deck
	 */
	public void shuffle() {
		java.util.Random rand = new java.util.Random();
		for (int i = 0; i < cardList.length; i++) {
			int randomIndexToSwap = rand.nextInt(this.cardList.length);
			Card temp = this.cardList[randomIndexToSwap];
			this.cardList[randomIndexToSwap] = this.cardList[i];
			this.cardList[i] = temp;
		}
	}
	
	/**
	 * "Draws" the top card of the deck and then removes it from the deck, and moves what the top card is.
	 * @return The card being drawn
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws NullPointerException
	 */
	@SuppressWarnings("unused")
	public Card draw() throws ArrayIndexOutOfBoundsException, NullPointerException {
		if (topCard == cardList.length)
			topCard = Integer.MIN_VALUE;
		Card ret = cardList[topCard];
		cardList[topCard] = null;
		topCard++;
		if (ret == null)
			throw new Error("NullPointerExcecption");
		else if (ret != null) {
			ret.flip();
			return ret;
		}
		// Should never happen
		throw new Error("SYSTEMERROR - DECK");
	}
	
	public String toString() {
		String str = "Cards remaining in deck: " + (cardList.length-topCard) + ".";
		return str;
	}
	
	private Card[] createDeck() {
		Card[] deck = new Card[52];
		Color[] colors = {Color.BLACK, Color.RED};
		char[] suits = {'C', 'H', 'S', 'D'};
		int cardNum = 0;
		for (int i = 0; i < 4; i++) {
			char suitUsed = suits[i];
			Color color = colors[i%2];
			for (int face = 1; face < 14; face++) {
				deck[cardNum] = new Card(color, suitUsed, returnValue(face));
				cardNum++;
			}
		}
		
		return deck;
	}
	
	private String returnValue(int a) {
		String returna = Integer.toString(a);
		switch(a) {
		case 1:
			returna = "A";
			break;
		case 11:
			returna = "J";
			break;
		case 12:
			returna = "Q";
			break;
		case 13:
			returna = "K";
			break;
		}
		return returna;
	}
}
