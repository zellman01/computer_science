package ch09problems;

/**
 * 
 * @author Zach Wellman
 * Date: 2/12/2019
 * DateCompleted: 2/12/2019
 * Problem 14
 */
public class ReturnArray {
	private static double positiveMax(double[][] m) {
		double largest = 0;
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[r].length; c++) {
				if (m[r][c] > largest) {
					largest = m[r][c];
				}
			}
		}
		return largest;
	}
	
	public static void main(String[] args) {
		RandomizeArrays ra = new RandomizeArrays();
		double[][] t = new double[4][4];
		t = ra.randomizeDoubleDoubleArray(t, -5);
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				System.out.println(i + ", " + j + ": " + t[i][j]);
			}
		}
		//System.out.println();
		/*t[0][0] = 0; t[0][1] = 0; t[0][2] = 0; t[0][3] = 0;
		t[1][0] = 0; t[1][1] = 0; t[1][2] = 0; t[1][3] = 0;
		t[2][0] = 0; t[2][1] = 0; t[2][2] = 0; t[2][3] = 0;
		t[3][0] = 0; t[3][1] = 0; t[3][2] = 0; t[3][3] = 0;*/
		System.out.println(positiveMax(t));
	}
}
