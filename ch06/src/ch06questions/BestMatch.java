package ch06questions;

import java.util.Scanner;

public class BestMatch {
	static Scanner kb = new Scanner(System.in);
	/**
	 * 
	 * @param r Red
	 * @param g Green
	 * @param b Blue
	 * @return color Color that matches best
	 */
	public static String bestMatch(int r, int g, int b) {
		String msg = null;
		if (r > g && r > b) {
			msg = "Red";
		} else if (g > r && g > b) {
			msg = "Green";
		} else if (b > r && b > g) {
			msg = "Blue";
		}
		
		if (r > b && g > b) {
			msg = "Yellow";
		} else if (r > g && b > g) {
			msg = "Magenta";
		} else if (b > r && g > r) {
			msg = "Cyan";
		}
		return msg;
	}
	
	public static void main(String [] args) {
		System.out.print("What is the value of red (0-255)?: ");
		int r = kb.nextInt();
		System.out.print("What is the value of green (0-255)?: ");
		int g = kb.nextInt();
		System.out.print("What is the value of blue (0-255)?: ");
		int b = kb.nextInt();
		if (bestMatch(r, g, b) == "Red" || bestMatch(r, g, b) == "Green" || bestMatch(r, g, b) == "Blue" || bestMatch(r, g, b) == "Yellow" || bestMatch(r, g, b) == "Magenta" || bestMatch(r, g, b) == "Cyan")
			System.out.println("The best color from what has been inputed is " + bestMatch(r, g, b) + ".");
		else
			System.out.println("The best color from what has been inputed is Gray.");
	}
}
