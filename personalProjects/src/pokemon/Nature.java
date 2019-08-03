package pokemon;

public class Nature {
	private final String[] STATS = {"hp", "atk", "def", "s,atk", "s.def", "spd"};
	private String nature, raise, lower;
	public Nature() {
		nature = "NS";
		raise = "NS";
		lower = "NS";
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public void setRaise(String raise) {
		this.raise = "NS";
		for (String test : STATS) {
			if (test.equals(raise)) {
				this.raise = raise;
			}
		}
	}
	
	public void setLower(String lower) {
		this.lower = "NS";
		for (String test : STATS) {
			if (test.equals(lower)) {
				this.lower = lower;
			}
		}
	}
	
	public String obtainNature() {
		return this.nature;
	}
	
	public String obtainRaise() {
		return this.raise;
	}
	
	public String obtainLower() {
		return this.lower;
	}
	
	public boolean validateNature() {
		return !this.raise.equals("NS") && !this.lower.equals("NS");
	}
}
