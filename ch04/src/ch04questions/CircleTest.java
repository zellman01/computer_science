package ch04questions;

import java.util.Scanner;

public class CircleTest extends Circle {

	public CircleTest(int r) {
		super(r);
	}
	
	public static void main(String [] args) {
		@SuppressWarnings("resource")
		Scanner kboard = new Scanner(System.in);
		System.out.print("Enter the radius of the circle (1-50): ");
		int r = kboard.nextInt();
		if (r < 1 || r > 50) {
			System.out.println("The program ran into an unexpected integer");
			System.exit(1);
		}
		System.out.println("The radius of the circle is: " + getRadius(r) + ".");
	}

}
