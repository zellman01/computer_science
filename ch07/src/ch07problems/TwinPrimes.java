package ch07problems;

public class TwinPrimes {
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
		int startingNumber = 1, startingNumber2 = 3, finalNumber = 315;
		while (startingNumber <= finalNumber) {
			while (startingNumber2 <= finalNumber) {
				if (IsPrime(startingNumber)) {
					if (IsPrime(startingNumber2)) {
						if (startingNumber2 - startingNumber == 2)
						System.out.println(startingNumber + " | " + startingNumber2);
					}
				}
				startingNumber++;
				startingNumber2++;
			}
		}
	}
}
