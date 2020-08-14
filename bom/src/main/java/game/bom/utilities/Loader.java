package game.bom.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import game.bom.card.Card;
import game.bom.deck.Deck;

/**
 * Custom Loader class
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class Loader {
	/**
	 * Load a card object
	 * @param id The id number of the card to load
	 * @return The card if it exists, or null otherwise
	 */
	public static Card card(String id) {
		File file = new File("cards/" + id + ".crd");
		Card crd = (Card) loadFile(file);
		return crd;
	}
	
	/**
	 * Load a deck object
	 * @param name The name of the deck to load
	 * @return The deck if it exists, or null otherwise
	 */
	public static Deck deck(String name) {
		File file = new File("deck/" + name + ".dek");
		Deck dek = (Deck) loadFile(file);
		return dek;
	}
	
	private static Object loadFile(File file) {
		Object obj = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			obj = in.readObject();
			in.close();
		} catch(IOException | ClassNotFoundException ex) {
			return null;
		}
		return obj;
	}
}
