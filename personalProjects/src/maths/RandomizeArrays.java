package maths;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This program just randomizes all array setups with different values
 * The minimum is always -100, the maximum is set within the call to the function.
 * This program only works with arrays with numbers.
 * @author Zach Wellmam
 */
public class RandomizeArrays {
	private final int min = -100;

	/**
	 * 
	 * @param a 1-D Integer array
	 * @param b Max value to randomize up to
	 * @return The randomized array
	 */
	public int[] randomizeSingleIntArray(int[] a, int b) {
		for (int i = 0; i < a.length; i++) {
			a[i] = ThreadLocalRandom.current().nextInt(min, b + 1);
		}
		return a;
	}

	/**
	 * 
	 * @param a 2-D Integer array
	 * @param b Max value to randomize up to
	 * @return The randomized array
	 */
	public int[][] randomizeDoubleIntArray(int[][] a, int b) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = ThreadLocalRandom.current().nextInt(min, b + 1);
			}
		}
		return a;
	}

	/**
	 * 
	 * @param a 1-D Double array
	 * @param b Max value to randomize up to
	 * @return The randomized array
	 */
	public double[] randomizeSingleDoubleArray(double[] a, double b) {
		for (int i = 0; i < a.length; i++) {
			a[i] = ThreadLocalRandom.current().nextDouble(min, b + 1);
		}
		return a;
	}

	/**
	 * 
	 * @param a 2-D Double array
	 * @param b Max value to randomize up to
	 * @return The randomized array
	 */
	public double[][] randomizeDoubleDoubleArray(double[][] a, double b) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = ThreadLocalRandom.current().nextDouble(min, b + 1);
			}
		}
		return a;
	}
}
