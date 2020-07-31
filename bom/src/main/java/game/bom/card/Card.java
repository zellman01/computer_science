package game.bom.card;

import java.io.Serializable;

import game.bom.player.Player;

/**
 * Card object class
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	private Position pos; // If a card is frontline, backline, or both
	private Mana manaCost;
	private String cardName;
	private int id, atk, hp;
	
	public Card(String position, int mCost, String name, int idNum, int atk, int hp) {
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
		id = idNum;
		this.atk = atk;
		this.hp = hp;
	}
	
	public Card(Position posi, Mana mana, String name, int idNum, int atk, int hp) {
		pos = posi;
		manaCost = mana;
		cardName = name;
		id = idNum;
		this.atk = atk;
		this.hp = hp;
	}
	
	public String getName() { return this.cardName; }
	public int getManaCost() { return this.manaCost.retrieveCardManaCost(); }
	public String getPosition() { return this.pos.getName(); }
	public int getIdNumber() { return this.id; }
	public int getHealth() { return this.hp; }
	public int getAttack() { return this.atk; }
	
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
	
	public boolean isSame(Card test) {
		return
				this.getName().equals(test.getName()) &&
				this.getManaCost() == test.getManaCost() &&
				this.getPosition().equals(test.getPosition()) &&
				this.getIdNumber() == test.getIdNumber() &&
				this.getHealth() == test.getHealth() &&
				this.getAttack() == test.getAttack();
	}
	
	public String toString() {
		String str = "";
		str += "Card ID: " + getIdNumber();
		str += ", Card name: " + getName();
		str += ", Cost for card: " + getManaCost();
		str += ", Card Position: " + getPosition() + "\n";
		return str;
	}
}
