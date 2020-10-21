package game.bom.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import game.bom.card.Card;
import game.bom.error.ErrorCodes;

public class CardPack implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int DIRT_RARITY = 95, GRASS_RARITY = 93, SILVER_RARITY = 70, GOLD_RARITY = 40, DIAMOND_RARITY = 15, PLATINUM_RARITY = 3;
	private ArrayList<Card> cards, dirt, grass, silver, gold, diamond, platinum;
	private String packName;
	
	/**
	 * Creates a card pack to be saved
	 * @param name The name of the pack
	 */
	public CardPack(String name) {
		packName = name;
	}
	
	/**
	 * Amount of cards in the pack
	 * @return The amount of cards in the pack
	 */
	public int cardAmount() {
		return cards.size();
	}
	
	/**
	 * Name of the pack
	 * @return The name of the pack
	 */
	public String packName() {
		return packName;
	}
	
	/**
	 * Check to see if the card is in the pack
	 * @param card Card to check for
	 * @return If card does exist.
	 */
	public boolean cardInPack(Card card) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).isSame(card))
				return true;
		}
		return false;
	}
	
	/**
	 * Adds a card to the pack, and to the right rarity of the pack
	 * @param card The card to add
	 */
	public void addCard(Card card) {
		if (!cardInPack(card)) {
			cards.add(card);
			switch(card.getRarity()) { // Put the card in the right rarity area for pulling purposes
			case "Dirt":
				dirt.add(card);
				break;
			case "Grass":
				grass.add(card);
				break;
			case "Silver":
				silver.add(card);
				break;
			case "Gold":
				gold.add(card);
				break;
			case "Diamond":
				diamond.add(card);
				break;
			case "Platinum":
				platinum.add(card);
				break;
			default:
				System.exit(ErrorCodes.E900.errorNum());
			}
		} else {
			System.err.println(ErrorCodes.E603.errorNum());
			System.exit(ErrorCodes.E603.errorNum());
		}
	}
	
	/**
	 * Opens a pack and adds it to a user's collection
	 * @return The card that was drawn
	 */
	public Card openPack() {
		Random random = new Random();
		int rand = random.nextInt(99)+1;
		// if/else block incoming for the ability to use < and >
		if (rand <= PLATINUM_RARITY) { // Pull only platinum rarity cards
			return platinumDraw();
		} else if (rand <= DIAMOND_RARITY) { // Pull only diamond rarity cards
			return diamondDraw();
		} else if (rand <= GOLD_RARITY) { // Pull only gold rarity cards
			return goldDraw();
		} else if (rand <= SILVER_RARITY) { // Pull only silver rarity cards
			return silverDraw();
		} else if (rand <= GRASS_RARITY) { // Pull only grass rarity cards
			return grassDraw();
		} else if (rand <= DIRT_RARITY) { // Pull only dirt rarity cards
			return dirtDraw();
		} else { // Able to pull any card from the pack
			return draw();
		}
	}
	
	private Card platinumDraw() {
		return null;
	}
	
	private Card diamondDraw() {
		return null;
	}
	
	private Card goldDraw() {
		return null;
	}
	
	private Card silverDraw() {
		return null;
	}
	
	private Card grassDraw() {
		return null;
	}
	
	private Card dirtDraw() {
		return null;
	}
	
	private Card draw() {
		return null;
	}
}
