/*
 * This program accepts a counting number
 * and finds the sum of squares to that integer
 * Zach Wellman
 * 8/29/2017
 */

package sumofsquares;

import java.util.Scanner;

public class SumOfSquares {
	public static int addSquares(int n) {
		if(n == 1)
			return 1;
		else
			return addSquares(n-1) + n * n;
	}

public static void main (String [] args) {
	Scanner kboard = new Scanner(System.in);
	System.out.print ("Enter a counting number to find the sum of squares to that number: ");
	int toThisNumber = kboard.nextInt();
	System.out.println("The sum of squares to " + toThisNumber + " is " + addSquares(toThisNumber) + ".");
	kboard.close();
}
}