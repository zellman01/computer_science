package ch05questions;

/**
 * This program calculates the amount of gas you use in a trip
 * @author Zach Wellman
 */

import java.util.Scanner;

public class GasMileage {

	public static double gasMileage(int miles, double mpg) {
		return miles/mpg;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Miles traveled (1-500): ");
		int miles = input.nextInt();
		System.out.print("Gas Mileage (1-500): ");
		double mpg = input.nextInt();

		input.close();

		double mileage = gasMileage(miles, mpg);
		System.out.print("You have spent a total of " + mileage + " gallons of gas.");
	}
}
