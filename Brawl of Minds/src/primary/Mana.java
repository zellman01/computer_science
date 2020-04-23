package primary;

/**
 * General Mana class for Brawl of Minds card game.
 * @author Zachary Wellman
 * @version 0.0.1
 * @since 0.0.1
 */
public class Mana {
	private final int MAX = 10;
	private int available, locked, tempMana; // Last two are character-only
	
	/**
	 * Creates a Mana pool for a card or player
	 * @param amount The amount of mana to start the game (if player) or cost (if card)
	 */
	public Mana(int amount) {
		if (amount > MAX)
			amount = MAX;
		this.available = amount;
		this.locked = 0;
		this.tempMana = 0;
	}
	
	// General methods
	/**
	 * Retrieves a character/card mana cost
	 * @return character/card mana cost
	 */
	public int retrieveManaAmount() { return (available - locked) + tempMana; } // Locked mana not subtracted from available mana
	// locked should always be 0 with cards. Should be between 0-10 with players.
	
	public int totalTemp() { return this.tempMana; }
	
	// Card-specific methods
	
	/**
	 * Checks if a card is useable based on the player's mana available
	 * @param player The player trying to use the card
	 * @return If the card is useable based on mana requirements
	*/ 
	public boolean useable(Player player) {
		return player.remainingMana() >= this.retrieveManaAmount();
	}
	
	// Player-specific methods
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
	public void resetMana(int recoverAmount) {
		available += recoverAmount;
		if (available > MAX)
			available = MAX;
	}
	
	/**
	 * Sets used mana to not be able to be used.
	 * @param manaCost Amount of mana used
	 */
	public void usedMana(int manaCost) {
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
		tempMana = amount;
	}
}
