package cards;

import java.awt.Color;

public class Card {
	private String faceValue, suit;
	private Color cardColor;
	private boolean joker;
	
	/**
	 * Basic card constructor
	 * @param face The face value of the card. Can be any number from 2-9, "A" (Ace), "J" (Jack), "Q" (Queen), or "K" (King)
	 * @param cardSuit The suit of the card ("D" (Diamonds), "S" (Spades), "H" (Hearts), or "C" (Covers))
	 * @param color The color of the card (Red or Black)
	 * @param isJoker if the card is a Joker
	 */
	public Card(String face, String cardSuit, Color color, boolean isJoker) {
		joker = isJoker;
		faceValue = face;
		suit = cardSuit;
		if (!isSuit()) {
			throw new Error("Not a possible suit.");
		}
		cardColor = color;
		if (!isColor()) {
			throw new Error("Not a possible color.");
		}
	}
	
	/**
	 * Card Initializer constructor. Only for testing purposes.
	 * @deprecated Should not really be used.
	 */
	public Card() {
		faceValue = null;
		suit = null;
		cardColor = null;
		joker = false;
	}
	
	/**
	 * Joker card constructor
	 * @param isJoker if the card is a Joker
	 */
	public Card(boolean isJoker) {
		if (!isJoker)
			throw new Error("Use the other constructor - This is only used for constructing Joker cards");
		joker = true;
		faceValue = "Joker";
		suit = "J";
		cardColor = Color.WHITE;
	}
	
	/**
	 * Calculates a card's value and return it.
	 * @return The value of the card. (Ace=1, Jack=11, Queen=12, King=13) 
	 */
	public int value() {
		int value = -1;
		try {
			value = Integer.parseInt(faceValue());
		} catch (NumberFormatException e) {
			switch (faceValue().toUpperCase()) {
			case "A":
				value = 1;
				break;
			case "J":
				value = 11;
				break;
			case "Q":
				value = 12;
				break;
			case "K":
				value = 13;
				break;
			case "JOKER":
				value = 0;
				break;
			default:
				throw new Error("Unexpected case: " + faceValue().toUpperCase());
			}
		}
		return value;
	}
	
	public String toString() {
		String str = "";
		str += "Face value: " + faceValue() + "\n";
		str += "Suit: " + returnSuit() + "\n";
		str += "Color: " + returnColor() + "\n";
		return str;
	}
	
	public String returnColor() {
		String str = "";
		if (cardColor() == Color.RED)
			str = "Red";
		else if (cardColor() == Color.BLACK)
			str = "Black";
		else
			str += "White";
		return str;
	}
	
	public String returnSuit() {
		String str = "";
		switch (suit()) {
		case "C":
			str = "Clubs";
			break;
		case "H":
			str = "Hearts";
			break;
		case "S":
			str = "Spades";
			break;
		case "D":
			str = "Diamonds";
			break;
			default:
				str = suit();
				break;
		}
		return str;
	}
	
	public String faceValue() { return this.faceValue; }
	public String suit() { return this.suit; }
	public Color cardColor() { return this.cardColor; }
	public boolean joker() { return this.joker; }
	
	private boolean isSuit() {
		return suit() == "C" || suit() == "H" || suit() == "S" || suit() == "D" || joker();
	}
	
	private boolean isColor() {
		return cardColor() == Color.RED || cardColor() == Color.BLACK || joker();
	}
}