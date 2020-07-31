package game.bom.card;

/**
 * Positions for cards
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public enum Position {
	frontLine("Frontline"),
	backLine("Backline"),
	any("Any"), spell("Spell");
	
	
	private String name;
	private Position(String name) {
		this.name = name;
	}
	
	public String getName() { return this.name; }
	
	/**
	 * Checks if the position given matches what is being tried
	 * @param posName The name of the position. Must be "Frontline", "Backline", "Any" or "Spell"
	 * @return If the card is played in a valid position
	 */
	public boolean validPosition(String posName) {
		return posName.equalsIgnoreCase(this.getName());
	}
}
