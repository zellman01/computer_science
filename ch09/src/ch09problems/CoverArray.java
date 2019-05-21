package ch09problems;

/**
 * @author Zach Wellman
 * Date: 2/21/19
 * DateCompleted: 2/25/19
 * Problem 16
 */
public class CoverArray {
	private static boolean covers(double[][] m1, double[][] m2) {
		int size = m1.length * m1[0].length;
		int covered = 0;
		if (m1.length != m2.length || m1[0].length != m2[0].length) {
			return false;
		}
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[0].length; j++) {
				if (m1[i][j] > m2[i][j]) {
					covered++;
				}
			}
		}
		return 2 * covered >= size;
	}
	public static void main(String[] args) {
		RandomizeArrays ra = new RandomizeArrays();
		double[][] m1 = new double[3][3];
		m1 = ra.randomizeDoubleDoubleArray(m1, 50);
		double[][] m2 = new double[3][3];
		m2 = ra.randomizeDoubleDoubleArray(m2, 50);
		System.out.println("m1:");
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m1[i].length; j++) {
				System.out.println(i + ", " + j + ": " + m1[i][j]);
			}
		}
		System.out.println();
		System.out.println("m2:");
		for (int i = 0; i < m2.length; i++) {
			for (int j = 0; j < m2[i].length; j++) {
				System.out.println(i + ", " + j + ": " + m2[i][j]);
			}
		}
		System.out.println();
		/*m1[0][0] = 2; m1[0][1] = 2; m1[0][2] = 2;
		m1[1][0] = 2; m1[1][1] = 2; m1[1][2] = 2;
		m1[2][0] = 2; m1[2][1] = 2; m1[2][2] = 2;
		
		m2[0][0] = 1; m2[0][1] = 1; m2[0][2] = 1;
		m2[1][0] = 1; m2[1][1] = 1; m2[1][2] = 1;
		m2[2][0] = 1; m2[2][1] = 1; m2[2][2] = 1;*/
		
		System.out.println(covers(m1, m2));
	}
}
