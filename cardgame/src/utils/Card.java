package utils;

import java.awt.Color;

public class Card {
	private Color cardColor;
	private boolean faceUp;
	private char suit;
	private String face;
	
	public Card(Color cardColor, char suit, String face) {
		this.cardColor = cardColor;
		faceUp = false;
		this.suit = suit;
		this.face = face;
	}
	
	public Color cardColor() { return this.cardColor; }
	public boolean faceUp() { return this.faceUp; }
	public char suitChar() { return this.suit; }
	public String suit() { return suitValue(this.suit); }
	public String face() { return this.face; }
	
	public void flip() { faceUp = !faceUp; }
	
	public String toString() {
		String str = null;
		if (!faceUp) {
			str = "Card is face-down.";
		} else {
			str = "Color: " + cardColor() + "\n";
			str += "Suit: " + suit() + "\n"; //TODO: Display the name of the suit
			str += "Face value: " + face() + "\n";
		}
		return str;
	}
	
	private String suitValue(char suit) {
		String str = "Invalid";
		switch(suit) {
		case 'D':
			str = "Diamond";
			break;
		case 'S':
			str = "Spade";
			break;
		case 'C':
			str = "Clover";
			break;
		case 'H':
			str = "Heart";
			break;
		default:
			str += ": Suit " + suit + " not reconized.";
		}
		return str;
	}
}
