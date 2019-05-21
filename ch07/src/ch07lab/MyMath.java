package ch07lab;

import java.util.Scanner;

public class MyMath
{
	/**
	 * Returns the sum of all integers from 1 to n, if n >= 1,
	 * and 0 otherwise.
	 */
	public static int sumUpTo(int n)
	{
		int sum = 0;

		for (int k = 1; k <= n; k++)
			sum += k;

		return sum;
	}

	/**
	 * Returns 1 * 2 * ... * n, if n >= 1; otherwise returns 1
	 */
	public static long factorial(int n)
	{
		long f = 1;

		for (int k = 2; k <= n; k++)
			f *= k;

		return f;
	}

	/**
	 * Returns true if n is a prime; otherwise returns false
	 */
	public static boolean isPrime(int n)
	{
		if (n <= 1)
			return false;

		int m = 2;

		while (m * m <= n)
		{
			if (n % m == 0)
				return false;
			m++;
		}

		return true;
	}

	/**
	 * Tests Goldbach conjecture for even numbers
	 * up to bigNum
	 */
	public static boolean testGoldbach(int bigNum)
	{
		for (int n = 6; n <= bigNum; n += 2)  // obviously true for n = 4
		{
			boolean found2primes = false;

			for (int p = 3; p <= n/2; p += 2)
			{
				if (isPrime(p) && isPrime(n - p))
					found2primes = true;
			}

			if (!found2primes)
			{
				System.out.println(n + " is not a sum of two primes!");
				return false;
			}
		}

		return true;
	}

	/**
	 * This is new.
	 * @param a
	 * @return
	 */
	public static void perfectNumber() {
		long a = 1;
		long f = 0;
		while (a <= 16) {
			while (a == 3 || a == 5 || a > 6 && a < 12 || a >= 13 && a <= 15) {
				a++;
			}
			long sum = 2;
			long b = a;
			long c = b;
			b -= 1;
			long sum2 = 2;
			while (b != 0) {
				sum2 *= 2;
				b--;
			}
			while (c != 0) {
				sum *= 2;
				c--;
			}
			sum -= 1;
			sum2 *= sum;
			f = sum2;
			System.out.println(f);
			a++;
		}
	}
	
	public static void mPerfectNumbers() {
		int a = 2;
		while (a < 18) {
			while (a == 4 || a == 6 || a == 8 || a > 8 && a < 13 || a > 13 && a < 17) {
				a++;
			}
			double sum = Math.pow(2,a)-1;
			System.out.println(sum);
			a++;
		}
	}

	/*********************************************************************/

	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		int n;

		do
		{
			System.out.print("Enter an integer from 4 to 20: ");
			n = kb.nextInt();
		} while (n < 4 || n > 20);

		kb.close();

		System.out.println();
		System.out.println("1 + ... + " + n + " = " + sumUpTo(n));
		System.out.println(n + "! = " + factorial(n));
		System.out.println("Primes: ");
		for (int k = 1; k <= n; k++)
			if (isPrime(k))
				System.out.print(k + " ");
		System.out.println();
		System.out.println("Goldbach conjecture up to " + n + ": " + testGoldbach(n));
		System.out.println();
		System.out.println("The first six Mersenne primes:");
		mPerfectNumbers();
		System.out.println();
		System.out.println("The first six perfect numbers:");
		perfectNumber();
		System.out.println();
	}
}

