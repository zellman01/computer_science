package ch04questions;

import java.util.Scanner;

public class CylinderTest extends Cylinder {

	public CylinderTest(int r, int h) {
		super(r, h);
	}
	
	public static void main(String [] args) {
		@SuppressWarnings("resource")
		Scanner kboard = new Scanner(System.in);
		System.out.print("Enter the radius of the cylinder (1-147): ");
		int r = kboard.nextInt();
		System.out.print("Enter the hight of the cylinder (1-147): ");
		int h = kboard.nextInt();
		if (r < 1 || r > 147 || h < 1 || h > 147) {
			System.out.println("The program ran into an unexpected integer.");
			System.exit(1);
		}
		System.out.println("The Volume of the cylinder is: " + getVolume(r, h) + ".");
	}

}
