package ch07problems;

public class SumDigits {
	
	private static void sumDigits(int num) {
		int sum = 0;
		while (num > 0) {
            sum +=  num % 10;
            num /= 10;
        }
        System.out.println(sum);
	}
	
	public static void main(String[] args) {
		System.out.println("Restrictions: 0 to 1500.");
		int n = 1234;
		if (n < 0) {
			System.out.println("You have entered a negitive number.");
			System.exit(1);
		} else if (n > 1500) {
			System.out.println("You have went out of the specified parameters.");
			System.exit(1);
		}
		sumDigits(n);
	}
}
