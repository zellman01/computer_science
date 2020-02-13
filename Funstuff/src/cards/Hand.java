package cards;

public class Hand {
	private int handSize = 7; // Default hand size - Games can change this
	private int handNum = 0; // How many cards are in the hand?
	private Card[] hand;

	/**
	 * @deprecated This is for testing only. In actual use, the other one should be used.
	 */
	public Hand() {
		hand = new Card[handSize];
	}

	/**
	 * 
	 * @param hand Hand size
	 */
	public Hand(int size) {
		handSize = size;
		hand = new Card[handSize];
	}

	public int getHandSize() { return this.handSize; }
	public Card[] getHand() { return this.hand; }
	public int length() { return handNum; }

	public String toString() {
		String str = "";
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null)
				str += hand[i];
		}
		if (str.equals(""))
			str = "No cards found in the hand.";
		return str;
	}

	/**
	 * Adds a card to the hand if the hand is not already full.
	 * @param a The card object to be added.
	 * @throws CustomException If trying to add a card to a full hand.
	 */
	public void addCard(Card a) {
		if (handNum < handSize) {
			hand[handNum] = a;
			handNum++;
		} else {
			throw new Error("Hand is too big");
		}
	}

	/**
	 * Removes the specified card from the hand, and returns it if necessary.
	 * BUG: Jokers are not removed
	 * @param isJoker If the card is a joker
	 * @param suitT The suit of the card. Must be "D", "C", "S", "H".
	 * @param face The face of the card. Any value from 2-9, "A", "J", "Q" or "K" will work.
	 * @return The card removed.
	 * @throws ArrayIndexOutOfBoundsException If the card could not be found.
	 */
	public Card removeCard(boolean isJoker, String suitT, String face) throws ArrayIndexOutOfBoundsException {
		int cardNum = -1;
		Card thing = null;
		if (!isJoker)
			for (int i = 0; i < handNum; i++) {
				if (hand[i].suit().equalsIgnoreCase(suitT) && hand[i].faceValue().equalsIgnoreCase(face)) {
					cardNum = i;
					i = handNum;
				}
			}
		else if (isJoker)
			for (int i = 0; i < handNum; i++) {
				if (hand[i].joker()) {
					cardNum = i;
					i = handNum;
				}
			}
		thing = hand[cardNum];
		hand[cardNum] = null;
		for (int i = cardNum; i < handNum-1; i++) {
			hand[i] = hand[i+1];
			hand[i+1] = null;
		}
		return thing;
	}
	
	@SuppressWarnings("unused")
	public Card removeTopCard() {
		Card ret = hand[handNum-1];
		hand[handNum-1] = null;
		handNum--;
		if (ret == null) {
			System.out.println("There was no cards in the hand.");
			throw new Error("NullPointerExcecption");
		} else if (ret != null) {
			return ret;
		}
		// Should never happen
		throw new Error("SYSTEMERROR - HAND");
	}
}
