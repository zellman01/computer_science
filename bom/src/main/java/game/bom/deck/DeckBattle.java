package game.bom.deck;

import java.util.ArrayList;
import java.util.Collections;

import game.bom.card.Card;
import game.bom.utilities.Loader;

public class DeckBattle {
	private Deck deck;
	private ArrayList<Card> cardOrder;
	
	public DeckBattle(Deck d) {
		deck = d;
		cardOrder = new ArrayList<>();
		shuffle();
	}
	
	private void shuffle() {
		ArrayList<Card> tempList = new ArrayList<>();
		for (int a : deck.getCardList()) {
			tempList.add(Loader.card(Integer.toString(a)));
		}
		Collections.shuffle(tempList);
		for (Card a : tempList) {
			cardOrder.add(a);
		}
	}
	
	public Card draw() { return cardOrder.remove(0); }
	public void addCard(Card card, int pos) {
		cardOrder.add(pos, card);
	}
}
