package ch07problems;

public class IPS {
	public static boolean isPerfectSquare(int n) {
		if (n < 1 || n > 5000) {
			System.exit(1);
		}
		int k = 1, sum = 0;
		while (sum < n) {
			sum += k;
			k += 2;
		}
		return sum == n;
	}
	
	public static void main(String[] args) {
		System.out.println("Restrictions: 1 to 5000.");
		System.out.println(isPerfectSquare(5000));
		
	}
}
