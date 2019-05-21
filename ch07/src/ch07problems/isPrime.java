package ch07problems;

/**
 * 
 * @author Zach Wellman
 *
 */

public class isPrime {
	public static boolean IsPrime(int n) {
		if (n < 5)
			return n == 2 || n == 3;
		else if (n % 2 == 0 || n % 3 == 0)
			return false;
		int m = 5;
		while (m*m <= n) {
			if (n % m == 0 || n % (m + 2) == 0)
				return false;
			m+=6;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(IsPrime(11));
	}
}
