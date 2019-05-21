package lab02;

//Sieve of Eratosthenes

public class Sieve {
	// Returns an array isPrime of n elements;
	// isprime[p] is set to true if and only if p is a prime
	public static boolean[] markPrimes(int n) {  
		boolean[] isPrime = new boolean[n]; // all set to false by default

		isPrime[0] = isPrime[1] = false; // optional

		for (int i = 2; i < n; i++) { // {false, false, true, true,..., true}
			if (i == 2 || i % 2 == 1)
				isPrime[i] = true;
		}

		for (int p = 3; p < n; p += 2) {
			if (isPrime[p])  // if isPrime[p] is true {
				for (int i = 2*p; i < n; i += p) {
					isPrime[i] = false;
				}
		}
		return isPrime;
	}

	public static void main(String[] args) {
		int n = 1500000;
		boolean[] isPrime = markPrimes(n);
		int count = 0;
		for (int p = 0; p < n; p++)
			if (isPrime[p]) {
				System.out.print(p + " ");
				count++;
				if (count % 10 == 0) {
					System.out.println();
				}
			}
		System.out.println();
		System.out.println(count + " primes under " + n);
	}  
}

