package ch09problems;

/**
 * 
 * @author Zach Wellman
 * Date: 2/27/19
 * DateCompleted: 3/28/19
 * Question 7/8
 */
public class Quadratic {
	public double[] QuadraticRoots(int a, int b, int c) {
		double[] returns = new double[1];
		double[] returns2 = new double[2];
		if (a == b && b == c && c == 0)
			throw new IllegalArgumentException("a = b = c = 0");
		if (a == 0) {
			if (b!=0) {
				returns[0] = (-c/b);
			} else {
				throw new IllegalArgumentException("a = 0");
			}
		} else {
			double d = Math.pow(b, 2) - 4 * a * c;
			if (d == 0) {
				returns[0] = (-b/(2*a));
			} else if (d > 1) {
				returns2[0] = (-b - Math.sqrt(d))/(2*a);
				returns2[1] = (-b + Math.sqrt(d))/(2*a);
			}
			if (returns2[1] == 0) {
				if (returns[0] == 0.0) {
					returns[0] = Integer.MIN_VALUE;
					return returns;
				}
				return returns;
			}
			else if (returns2[1] != 0)
				return returns2;
			return null;
		}
		return returns;
	}

	public static void main(String[] args) {
		Quadratic quad = new Quadratic();
		int a = 1;
		int b = 3;
		int c = 9;
		double[] ints;
		try {
			ints = quad.QuadraticRoots(a, b, c);
		} catch (IllegalArgumentException e) {
			if (a == 0 && b == 0 && c == 0) 
				System.out.println("a=b=c=0");
			else
				System.out.println("a=0");
			System.exit(1);
		} finally {
		}
		ints = quad.QuadraticRoots(a, b, c);
		if (ints[0] == Integer.MIN_VALUE) {
			System.out.println("null"); 
			System.exit(0);
		}
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] == 0.0) {
				
			} else {
				System.out.println(ints[i]);
			}
		}
	}
}
