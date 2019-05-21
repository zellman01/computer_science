package ch06questions;

import java.util.Scanner;

public class PerfectSquare {
	static Scanner kb = new Scanner(System.in);
	/**
	 * @param n A number
	 * @return true If the number given is a perfect square
	 */
	public static boolean isPerfectSquare(int n) {
		double a = Math.sqrt(n);
		double b = Math.round(a);
		double c = b*b;
		if (c == n) {
			return true;
		}
		return false;
	}
	
	public static void main(String [] args) {
		System.out.print("What number are you checking if it's a perfect square? (1-500): ");
		int n = kb.nextInt();
		if (n < 1 || n > 500) {
			System.out.println("You inputed a number lower or larger than given!");
			System.exit(1);
		}
		boolean test = isPerfectSquare(n);
		if (test == true) {
			System.out.println(n + " is a perfect square.");
		} else {
			System.out.println(n + " is not a perfect square.");
		}
	}
}
