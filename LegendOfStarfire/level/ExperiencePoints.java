package level;

public class ExperiencePoints {
	private int amount;
	
	/**
	 * Starts with a certian amount of EXP
	 * @param amount The amount to start with
	*/
	public ExperiencePoints(int amount) {
		this.amount = amount;
	}
	
	/**
	 * Starts with 0 EXP
	*/
	public ExperiencePoints() {
		amount = 0;
	}
	
	/**
	 * Raises the current amount of EXP by the given amount
	 * @param amount The amount to raise the EXP by
	*/
	public void raiseEXP(int amount) {
		this.amount += amount;
	}
	
	/**
	 * Retrieves the current amount of EXP
	 * @return Current EXP total (or EXP given by a certian NPC)
	*/
	public int getAmount() { return amount; }
}