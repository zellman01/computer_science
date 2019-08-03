package yugioh;

public class LPCounter {
	public static int LP1;
	public static int LP2;
	
	public LPCounter(int lp1, int lp2) {
		LP1 = lp1;
		LP2 = lp2;
	}
	
	public static int LP1Decrease(int decrease1) {
		LP1 = LP1 - decrease1;
		return LP1;
	}
	
	public static int LP2Decrease(int decrease2) {
		LP2 = LP2 - decrease2;
		return LP2;
	}
	
	public static int LP1Increase(int increase1) {
		LP1 = LP1 + increase1;
		return LP1;
	}
	
	public static int LP2Increase(int increase2) {
		LP2 = LP2 + increase2;
		return LP2;
	}
}
