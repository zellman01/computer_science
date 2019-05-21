package ch09problems;

/**
 * @author Zach Wellman
 * Date: 3/13/19
 * DateCompleted: 3/19/19
 * Problem 24b
 */
public class RotateBoi {
	void rotateLeft(int[] a) {
		int[] b = new int[a.length];
		b[a.length-1] = a[0];
		for (int i = 1; i < a.length; i++) {
			b[i-1] = a[i];
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = b[i];
		}
	}

	void rotateRight(int[] a) {
		int[] b = new int[a.length];
		b[0] = a[a.length-1];
		for (int i = 1; i < a.length; i++) {
			b[i] = a[i-1];
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = b[i];
		}
	}

	public static void rotate(int[] a, int d) {
		RotateBoi rb = new RotateBoi();
		if (d < 0) {
			while (d < 0) {
				rb.rotateLeft(a);
				d++;
			}
		} else if (d > 0) {
			while (d > 0) {
				rb.rotateRight(a);
				d--;
			}
		}
	}

	public static void main(String[] args) {
		RandomizeArrays ra = new RandomizeArrays();
		int[] a = new int[10];
		a = ra.randomizeSingleIntArray(a, 15);
		System.out.println("Original:");
		for (int i = 0; i < a.length; i++) {
			if (i < a.length-1)
				System.out.print(a[i] + ", ");
			else
				System.out.print(a[i] + ".");
		}
		rotate(a, 2);
		System.out.println();
		System.out.println("Rotation:");
		for (int i = 0; i < a.length; i++) {
			if (i < a.length-1)
				System.out.print(a[i] + ", ");
			else
				System.out.print(a[i] + ".");
		}
	}
}
