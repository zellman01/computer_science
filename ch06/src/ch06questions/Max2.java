package ch06questions;

import java.util.Scanner;

public class Max2 {
	static Scanner kb = new Scanner(System.in);
	/**
	 * 
	 * @param x First number to check
	 * @param y Second number to check
	 * @param z Third number to take
	 * @param a Method to use
	 * @return Biggest number
	 * 
	 */
	public static int max(int x, int y, int z, int a) {
		if (a == 2) {
			if (x > y && x > z) {
				return x;
			} else if (y > x && y > z) {
				return y;
			} else if (z > x && z > y) {
				return z;
			}
			return x;
		} else if (a == 1) {
			return Math.max(z, Math.max(x, y));
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.print("What is the first number that you want to see is larger? (1-500): ");
		int x = kb.nextInt();
		System.out.print("What is the second number that you want to see is larger? (1-500): ");
		int y = kb.nextInt();
		System.out.print("What is the third number that you want to see is larger? (1-500): ");
		int z = kb.nextInt();
		System.out.print("Which method do you want to use? (The 1st uses a Math class, the 2nd doesn't.): ");
		int a = kb.nextInt();
		kb.close();
		System.out.println(max(x, y, z, a));	
	}
}
