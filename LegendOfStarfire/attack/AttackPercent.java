package attack;

/**
 * Percent verifier for the Attack Class
 * @author zellman01
*/
public class AttackPercent {
	private int percent;
	public final static int MAX_PERCENT_PUBLIC = 100;
	private final int MAX_PERCENT = AttackPercent.MAX_PERCENT_PUBLIC;
	private final int MIN_PERCENT = 0;
	
	/**
	 * Creates the percent verifier object
	 * @param percent The percent of the hit/crit chance
	*/
	public AttackPercent(int percent) {
		if (percent < MIN_PERCENT) {
			percent = MIN_PERCENT;
		}
		
		if (percent > MAX_PERCENT) {
			percent = MAX_PERCENT;
		}
		
		this.percent = percent;
	}
	
	public int getPercent() {
		return percent;
	}
}