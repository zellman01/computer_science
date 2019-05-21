package ch07problems;

/**
 * @author Zach Wellman
 * Date: 11/12/2018
 * DateCompleted: 11/14/2018
 */
public class ClosestFraction {
	private static final int numTarget = 2, denomTarget = 3;
	private static final double target = (double) numTarget / denomTarget;

	public static void main(String[] args) {
		int numLeft = 0, denomLeft = 100, numRight = 100, denomRight = 100;
		double leftDiff = 1, rightDiff = 1;
		for (int denom = 2; denom <= 100; denom++) {
			for (int num = 1; num < denom; num++) {
				double d = (double) num / denom - target;
				if (d > 0 && d < rightDiff) {
					rightDiff = d;
					numRight = num;
					denomRight = denom;
				}
				d = -d;
				if (d > 0 && d < leftDiff) {
					leftDiff = d;
					numLeft = num;
					denomLeft = denom;
				}
			}
		}
		System.out.println(numLeft + "/" + denomLeft);
		System.out.println(numRight + "/" + denomRight);
	}
}