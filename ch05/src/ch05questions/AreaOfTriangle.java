package ch05questions;

/**
 * This program uses Heron's Theorum to calculate the Area of a triangle with three side inputs.
 * @author Zach Wellman
 */

import java.util.Scanner;

public class AreaOfTriangle {
	private static double areaOfTriangle(double a, double b, double c) {
		double s = (a+b+c)/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("What is the value of Side A? (0-150): ");
		double a = kb.nextInt();
		System.out.print("What is the value of Side B? (0-150): ");
		double b = kb.nextInt();
		System.out.print("What is the value of Side C? (0-150): ");
		double c = kb.nextInt();
		kb.close();
		if (a < 0 || a > 150 || b < 0 || b > 150 || c < 0 || c > 150) {
			System.out.println("You think you can go above the limits of being above 0 and lower than 150?");
			System.exit(1);
		}
		double total = areaOfTriangle(a, b, c);
		if (Double.isNaN(total)) {
			System.out.println("That triangle is not possible.");
			System.exit(0);
		}
		if (a + b < c || b + c < a || a + c < b) {
			System.out.println("That triangle is not possible due to the Triangle Inequality Theorem");
		}
		System.out.println(total);
	}
}
