package game.bom.primary;

import java.io.Serializable;

/**
 * Class for Card objects
 * @author Zachary Wellman
 * @version 0.0.1
 * @since 0.0.1
 */
@SuppressWarnings("unused")
public class Card {
	private Position pos; // If a card is frontline, backline, or both
	private Mana manaCost;
	private String cardName;
	
	public Card(String position, int mCost, String name) {
		switch(position) {
		case "Both":
		case "Any":
			pos = Position.any;
			break;
		case "Back":
		case "Backline":
			pos = Position.backLine;
			break;
		case "Front":
		case "Frontline":
			pos = Position.frontLine;
			break;
		default:
			pos = Position.spell;
			break;
		}
		
		manaCost = new Mana(mCost);
		cardName = name;
	}
	
	public String getName() { return this.cardName; }
	public int getManaCost() { return this.manaCost.retrieveCardManaCost(); }
	public String getPosition() { return this.pos.getName(); }
	
	/**
	 * Checks to see if a player has the correct amount to use a card.
	 *  Does NOT reduce mana pool of the player
	 *  This method may be put in Player once decks are possible
	 * @param player The player using the card
	 * @return If the given player has the correct amount of mana
	 */
	public boolean canBeUsed(Player player) {
		return manaCost.useable(player);
	}
	
	public String toString() {
		String str = "";
		str += "Card name: " + getName();
		str += ", Cost for card: " + getManaCost();
		str += ", Card Position: " + getPosition();
		return str;
	}
}
