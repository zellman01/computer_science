package cards;

import java.awt.Color;

public class Deck {
	// NUMCARDS should never be changed; NUMJOKERS can be changed
	private final int NUMCARDS = 52, NUMJOKERS = 3;
	private int totalCards = -1, topCard = 0;
	private Card[] cards;

	/**
	 * Creates a deck
	 * @param allowJokers if Jokers should be allowed in the deck or not.
	 */
	public Deck(boolean allowJokers) {
		if (allowJokers)
			totalCards = NUMCARDS+NUMJOKERS;
		else
			totalCards = NUMCARDS;
		cards = new Card[totalCards];
		setDeck(allowJokers);
	}

	/**
	 * "Draws" the top card of the deck and then removes it from the deck, and moves what the top card is.
	 * @return The card being drawn
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws NullPointerException
	 */
	@SuppressWarnings("unused")
	public Card draw() throws ArrayIndexOutOfBoundsException, NullPointerException {
		if (topCard == totalCards)
			topCard = Integer.MIN_VALUE;
		Card ret = cards[topCard];
		cards[topCard] = null;
		topCard++;
		if (ret == null) {
			throw new Error("NullPointerExcecption");
		} else if (ret != null) {
			return ret;
		}
		// Should never happen
		throw new Error("SYSTEMERROR - DECK");
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

	private void setDeck(boolean Jokers) {
		Color[] colors = {Color.BLACK, Color.RED};
		String[] suits = {"C", "H", "S", "D"};
		int cardNum = 0;
		for (int i = 0; i < 4; i++) {
			String suitUsed = suits[i];
			Color color = colors[i%2];
			for (int face = 1; face < 14; face++) {
				cards[cardNum] = new Card(returnValue(face), suitUsed, color, false);
				cardNum++;
			}
		}
		if (Jokers) {
			for (int j = 0; j < NUMJOKERS; j++) {
				cards[cardNum] = new Card(true);
				cardNum++;
			}
		}
	}

	/**
	 * Shuffles the constructed deck
	 */
	public void shuffle() {
		java.util.Random rand = new java.util.Random();
		for (int i = 0; i < totalCards; i++) {
			int randomIndexToSwap = rand.nextInt(this.cards.length);
			Card temp = this.cards[randomIndexToSwap];
			this.cards[randomIndexToSwap] = this.cards[i];
			this.cards[i] = temp;
		}
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < totalCards; i++) {
			if (cards[i] != null)
				str += cards[i];
		}
		return str;
	}
}
