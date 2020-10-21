package game.bom.deck;

import java.util.ArrayList;

import game.bom.card.Card;
import game.bom.error.ErrorCodes;
import game.bom.global.Globals;

/**
 * Class for creating the hand for battles
 * @author zellma01
 * @version 0.1.0
 * @since 0.1.0
 */
public class Hand extends Deck {
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> cardList;

	/**
	 * Creates the hand object
	 */
	public Hand() {
		super("Player Hand");
	}
	
	@Override
	public void addCard(Card card) {
		if (getDeckSize() < Globals.MAX_HAND) {
			cardList.add(card);
		} else {
			throw new Error(ErrorCodes.E602.toString());
		}
	}
	
	/**
	 * Selects a card from the given hand
	 * @param selCard The card to try to select
	 * @return The selected card, or null if not found.
	 */
	public Card selectCard(Card selCard) {
		for(Card a : cardList) {
			if (a.equals(selCard))
				return cardList.remove(a.getIdNumber());
		}
		return null;
		
	}

}
