package ch07problems;

public class Sequence17 {
	private static final double GOLDEN = (1+Math.sqrt(5))/2;
	private static double test(int n) {
		if (n == 0)
			return 1;
		return 1.0 + 1.0 / (test(n-1));
	}

	public static void main(String [] args) {
		for (int n = 0; n <= 9; n++) {
			double diff = Math.abs(test(n) - GOLDEN);
			System.out.print(n+1 + ".)");
			System.out.println(n + 1 < 10 ? "\tseq=" + test(n) : "    seq=" + test(n));
			System.out.println("\t" + GOLDEN + " is the golden ratio.");
			System.out.printf("\tDifference: %.20f\t", diff);
			System.out.println("\n");
		}
	}
}
