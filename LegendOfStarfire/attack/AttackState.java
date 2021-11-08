package attack;

/**
 * Enumeration of attack states.
*/
public enum AttackState {
	/**
	 * Miss the attack entirely
	*/
	MISS("Miss"),
	
	/**
	 * The attack was successfully landed
	*/
	HIT("Hit"),
	
	/**
	 * The attack landed successfully, and was critical
	*/
	CRITICAL("Critical");
	
	private final String name;
	
	private AttackState(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}