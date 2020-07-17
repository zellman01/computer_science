package game.bom.primary;

import game.bom.primary.Card;

public class Deck {
	private final int MAX_CARDS = 45;
	
	private int totalCards;
	private Card[] cardList;
	private String deckName;
	
	public Deck(String name) {
		cardList = new Card[MAX_CARDS];
		deckName = name;
		totalCards = 0;
	}
	
	public void addCard(Card card) {
		if (totalCards < MAX_CARDS) {
			cardList[totalCards] = card;
			totalCards++;
		} else {
			throw new Error("addCard - Over the limit of allowed cards.");
		}
	}
	
	private String displayCards() {
		String str = "";
		for (int i = 0; i < totalCards; i++) {
			str += " " + cardList[i] + "\n";
		}
		return str;
	}
	
	public String toString() {
		String str = "";
		str += "Deck name: " + deckName;
		str +="\nCards in deck: " + totalCards;
		str += "\nCard list: \n" + displayCards();
		return str;
	}
}
