package game.bom.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import game.bom.card.Card;
import game.bom.deck.Deck;

public class Loader {
	public static Card card(String id) {
		File file = new File("cards/" + id + ".crd");
		Card crd = (Card) loadFile(file);
		return crd;
	}
	
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
