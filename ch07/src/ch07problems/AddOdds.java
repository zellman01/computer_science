package ch07problems;

public class AddOdds {
	/**
	 * @author Zach Wellman
	 * @param n
	 * @return
	 */
	public static void addOdds(int n) {
		int sum=0;
		for (int k = 1; k <= n; k+=2)
			sum += k;
		System.out.println(sum);
	}
	
	public static void main(String [] args) {
		System.out.println("Restrictions: 1 to 9999.");
		addOdds(100);
	}
}
