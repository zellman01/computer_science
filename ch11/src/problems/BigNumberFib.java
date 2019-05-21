package problems;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * 
 * @author Zach Wellman
 * Date Started: 5/1/19
 * Date Completed: 5/8/19
 * Question 11
 *
 */
public class BigNumberFib {
	ArrayList<BigInteger> bigInt;

	public BigNumberFib() {
		bigInt = new ArrayList<BigInteger>();
	}

	private static ArrayList<BigInteger> fib(int k) {
		ArrayList<BigInteger> fib1 = new ArrayList<BigInteger>();
		BigInteger start0 = new BigInteger("0");
		BigInteger start1 = new BigInteger("1");
		fib1.add(start0); 
		if (k > 0) fib1.add(start1);
		for (int i = 2; i <= k; i++) {
			BigInteger add = fib1.get(i-2);
			add = add.add(fib1.get(i-1));
			fib1.add(add);
		}
		return fib1;
	}

	public static void main(String[] args) {
		ArrayList<BigInteger> test = new ArrayList<BigInteger>();
		int n = 0;
		test = fib(n);
		//System.out.println(test);
		String num1 = test.get(n).toString();
		System.out.println(num1.length());
	}
	// How many digits
}
