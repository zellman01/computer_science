package ch06questions;

import java.util.Scanner;

public class Max {
	static Scanner kb = new Scanner(System.in);
	/**
	 * 
	 * @param x First Number
	 * @param y Second Number
	 * @return biggest number
	 */
	public static int max(int x, int y) {
		if (x > y) {
			return x;
		} else if (y > x) {
			return y;
		}
		return x;
	}
	
	public static void main(String[] args) {
		System.out.print("What is the first number that you want to see is larger? (1-500): ");
		int x = kb.nextInt();
		System.out.print("What is the second number that you want to see is larger? (1-500): ");
		int y = kb.nextInt();
		kb.close();
		System.out.println(max(x, y));
	}
}
