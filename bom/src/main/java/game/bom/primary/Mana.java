package game.bom.primary;

/**
 * General Mana class for Brawl of Minds card game.
 * @author Zachary Wellman
 * @version 0.0.1
 * @since 0.0.1
 */
public class Mana {
	private final int MAX = 10;
	private int available, tempMana, locked; // Last one is character-only
	
	/**
	 * Creates a Mana pool for a card
	 * @param amount The amount of mana cost for the card
	 */
	public Mana(int amount) {
		if (amount > MAX)
			amount = MAX;
		this.available = amount;
		this.tempMana = 0; // Defined on cards as well from potentially changing the amount of cost on cards
	}
	
	/**
	 * Creates a mana pool for the player
	 * @param amount Amount of mana to start the game with
	 * @param locked Amount of mana to start locked
	 * @param temp Amount of mana to start (temporary)
	 */
	public Mana(int amount, int locked, int temp) {
		if (amount > MAX)
			amount = MAX;
		this.available = amount;
		this.locked = locked;
		this.tempMana = temp;
	}
	
	// General methods
	
	public int totalTemp() { return this.tempMana; }
	
	// Card-specific methods
	
	public int retrieveCardManaCost() { return available + tempMana; }
	
	/**
	 * Checks if a card is useable based on the player's mana available
	 * @param player The player trying to use the card
	 * @return If the card is useable based on mana requirements
	*/ 
	public boolean useable(Player player) {
		return player.remainingMana() >= this.retrieveCardManaCost();
	}
	
	// Player-specific methods //
	
	/**
	 * Retrieves a character's available mana
	 * @return character/card mana cost
	 */
	public int retrieveCharacterManaAmount() { return (available - locked) + tempMana; } // Locked mana not subtracted from available mana
	// locked should be between 0-10 with players.
	
	/**
	 * Locks a given amount of mana from a player
	 * @param lockAmount The amount of mana to lock
	 */
	public void lockMana(int lockAmount) {
		if (lockAmount > MAX)
			lockAmount = MAX;
		locked = lockAmount;
	}
	
	/**
	 * Recovers a set amount of mana (primarily used at the beginning of a turn
	 * @param recoverAmount The amount to recover from a player
	 */
	public void recoverMana(int recoverAmount) {
		available += recoverAmount;
		if (available > MAX)
			available = MAX;
	}
	
	/**
	 * Sets used mana to not be able to be used.
	 * @param manaCost Amount of mana used
	 */
	public void useMana(int manaCost) {
		available -= manaCost;
		if (available < 0) {
			throw new Error("Unexpected result - Mana reserve fell below 0 - Should never happen");
		}
	}
	
	/**
	 * Adds temporary mana.
	 * @param amount The amount to add/remove (add if pos, remove if neg)
	 */
	public void addTemp(int amount) {
		tempMana += amount;
	}
}
