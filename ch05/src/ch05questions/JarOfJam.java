package ch05questions;

import java.util.Scanner;

public class JarOfJam {
	public static double computeShippingCost(int nJars) {
		int nCartons = (nJars + 11) / 12;
		int totalOunces = (nJars * 21) + (nCartons * 25);
		int lbs = 1 + totalOunces/16;
		return (nCartons * 1.44 + lbs * 0.96 + 3);
	}
	
	public static void main(String [] args) {
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		System.out.print("How many jar(s) are there? (1-255): ");
		int nJars = kb.nextInt();
		if (nJars == 1) {
			System.out.println("The cost of shipping one jar will be $" + (computeShippingCost(nJars)) + ".");
		} else {
			System.out.println("The cost of shipping " + nJars + " jars will be $" + (computeShippingCost(nJars)) + ".");
		}
	}
}
