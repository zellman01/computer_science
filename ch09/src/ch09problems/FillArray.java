package ch09problems;

/**
 * @author Zach Wellman
 * Date: 2/21/19
 * DateCompeted: 2/21/2019
 * Problem 10
 */
public class FillArray {
	/**
	 * @return an array filled with values
	 * 1, 2, ..., n-1, n, n-1, ..., 2, 1
	 * Precondition: n>= 1
	 */
	public static int[] createWedge(int n) {
		int[] array = new int[(n*2)-1];
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
		if (n >= 1) {
			int i = 1;
			boolean reverse = false;
			for (int a = 0; a < array.length; a++) {
				array[a] = i;
				if (!reverse)
					i++;
				else
					i--;
				if (i > n) {
					i = n - 1;
					reverse = true;
				}
				
			}
		}
		return array;
	}
	
	public static void main(String[] args) {
		int[] test = createWedge(12);
		for(int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
}
