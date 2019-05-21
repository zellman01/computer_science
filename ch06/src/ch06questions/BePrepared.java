package ch06questions;

import java.util.Scanner;

public class BePrepared {
	public static double bePreparedCost = 20.95, nextBestCost = 21.95;
	static Scanner kb = new Scanner(System.in);
	/**
	 * 
	 * @param bp Be Prepared book number
	 * @param nb Next Best book number
	 * @return total Price that is required to purchase both books
	 */
	public static double getOrderTotal(int bp, int nb) {
		double total;
		total = (bePreparedCost * bp) + (nextBestCost * nb);
		if ((bp + nb) == 2) {
			total = 39.95;
		}else if ((bp + nb) == 3) {
			total = 16.95 * 3;
		} else if ((bp + nb) >= 12) {
			total = (16.00 * bp) + (16.00 * nb);
		}
		return total;
	}
	
	public static void main(String[] args) {
		System.out.print("How much of the book \"Be Prepared\" would you like to order?: ");
		int bp = kb.nextInt();
		System.out.print("How much of the book \"Next Best\" would you like to order?: ");
		int nb = kb.nextInt();
		kb.close();
		System.out.printf("Your order comes out to be $%.2f.", getOrderTotal(bp, nb));
	}
}
