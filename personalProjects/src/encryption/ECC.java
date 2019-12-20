package encryption;

import java.util.ArrayList;

public class ECC {
	private int x; // Private key
	public int a, b; // Public key
	private int max; // Max number

	public ECC(int start, int end, int maximum) {
		x = (int) (Math.random() * end);
		a = start; b = end;
		max = maximum;
	}

	private int obtainPrivate() {
		return this.x;
	}

	public int obtainPublic() {
		return 0;
	}

	public int[] getPoint(int y) {
		int[] point = new int[2];
		int t = (int) (Math.pow(this.obtainPrivate(), 3) + (a * this.obtainPrivate()) + b);
		while (t > max) {
			t -= max;
		}
		point[0] = y; point[1] = t;
		return point;
	}

	public static void main(String[] args) {
		ECC test = new ECC(1, 20, 500);
		for (int i = 0; i < 30; i++) {
			int[] test1 = test.getPoint(i);
			for (int j = 0; j < test1.length; j++) {
				System.out.print(test1[j] + " ");
			}
			System.out.println();
		}
	}
}
