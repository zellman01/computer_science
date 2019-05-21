package ch07problems;

/**
 * @author Zach Wellman
 */


public class GCFAlgo {

	private static int GCF(int a, int b) {
		while (a != b) {
			if (b > a) {
				b -= a;
			} else {
				a -= b;
			}
		}
		return a;
	}
	
	public static void main(String[] args) {
		System.out.println("Restrictions: 1-200.");
		System.out.println(GCF(1, 1));
	}
}
