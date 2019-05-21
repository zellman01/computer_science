package ch09problems;

/**
 * 
 * @author Zach Wellman
 * Date: 3/7/19
 * DateCompleted: 3/7/19
 * Problem 18b
 *
 */
public class SumElements {
	public int sumOfValues(int[] a) {
		int sum = 0;
		for (int x : a)
			sum += x;
		return sum;
	}

	public int[] sumsOfRows(int[][] t) {
		int[] sums = new int[t.length];
		int k = 0;
		for (int[] row : t) {
			sums[k++] = sumOfValues(row);
		}
		return sums;
	}
	
	public static void main(String[] args) {
		RandomizeArrays ra = new RandomizeArrays();
		SumElements se = new SumElements();
		int row_col = 5;
		int[][] t = new int[row_col][row_col];
		t = ra.randomizeDoubleIntArray(t, 100);
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				System.out.println(i + ", " + j + ": " + t[i][j]);
			}
		}
		System.out.println();
		/*t[0][0] = 0; t[0][1] = 0; t[0][2] = 0; t[0][3] = 0; t[0][4] = 0;
		t[1][0] = 0; t[1][1] = 0; t[1][2] = 0; t[1][3] = 0; t[1][4] = 0;
		t[2][0] = 0; t[2][1] = 0; t[2][2] = 0; t[2][3] = 0; t[2][4] = 0;
		t[3][0] = 0; t[3][1] = 0; t[3][2] = 0; t[3][3] = 0; t[3][4] = 0;
		t[4][0] = 0; t[4][1] = 0; t[4][2] = 0; t[4][3] = 0; t[4][4] = 0;*/
		int[] a = se.sumsOfRows(t);
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
}
