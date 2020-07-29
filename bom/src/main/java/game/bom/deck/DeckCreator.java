package game.bom.deck;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.bom.card.Card;
import game.bom.global.Globals;
import game.bom.utilities.Loader;
import game.bom.utilities.Saver;


public class DeckCreator extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> cardId;
	private Deck deck;

	public DeckCreator() {
		cardId = new ArrayList<Integer>();
	}

	public boolean addCard(Card card) {
		int idNum = card.getIdNumber();
		if (cardId.size() < Globals.MAX_CARDS)
			if (!cardId.contains(idNum)) {
				cardId.add(idNum);
				return true;
			}
		return false;
	}
	
	/**
	 * Adds a card to the deck based off of ID
	 * @param card The ID number of the card
	 * @return If the card was added successfully
	 * @deprecated Security concern. Would allow anyone to just put in a ID number and add that card
	 */
	@Deprecated
	public boolean addCard(int card) {
		if (cardId.size() < Globals.MAX_CARDS)
			if (!cardId.contains(card)) {
				cardId.add(card);
				return true;
			}
		return false;
	}
	
	protected void setCardArray(ArrayList<Integer> cardList) {
		cardId = cardList;
	}
	
	public void createDeck(String deckName) throws IOException {
		deck = new Deck(deckName);
		for (int i : cardId) {
			Card insert = null;
			insert = Loader.card(Integer.toString(i));
			if (!insert.equals(null))
			deck.addCard(insert);
			else
				System.err.println("Card with ID " + i + " does not exist.");
		}
		try {
			Saver.saveFile("deck", deckName, "dek", deck);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*DeckCreator dc = new DeckCreator();
		dc.addCard(2);
		System.exit(1);
		dc.addCard(1);
		try {
			dc.createDeck("Default");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(Globals.NUM_CARDS);
	}
}
