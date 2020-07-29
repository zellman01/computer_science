package game.bom.deck;

import java.util.ArrayList;

import game.bom.card.Card;
import game.bom.error.ErrorCodes;
import game.bom.global.Globals;

public class Hand extends Deck {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> cardList;

	public Hand() {
		super("Player Hand");
	}
	
	@Override
	public void addCard(Card card) {
		if (getDeckSize() < Globals.MAX_HAND) {
			cardList.add(card);
		} else {
			throw new Error(ErrorCodes.E601.toString());
		}
	}
	
	public Card selectCard(Card selCard) {
		for(Card a : cardList) {
			if (a.equals(selCard))
				return cardList.remove(a.getIdNumber());
		}
		return null;
		
	}

}
