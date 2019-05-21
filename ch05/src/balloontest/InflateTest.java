package balloontest;

import java.awt.Color;
import java.util.Scanner;

public class InflateTest extends InflatableBalloon {
	private static int r;
	private static int rma = 50;
	private static int rmi = 1;
	private static int pma = 2250;
	private static int pmi = 1;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner kboard = new Scanner(System.in);
		System.out.print("Enter the radius of the balloon(" + rmi + "-" + rma + "): ");
		r = kboard.nextInt();
		if (r < rmi || r > rma) {
			System.out.println("The radius cannot be larger than " + rma + " or smaller than " + rmi + ".");
			System.exit(1);
		}
		System.out.print("Enter a percentage to change the volume of the balloon (" + pmi + "-" + pma + "): ");
		int n = kboard.nextInt();
		if (n < pmi || n > pma) {
			System.out.println("The percentage to change the volume cannot be larger than " + pma + " or smaller than " + pmi + ".");
			System.exit(1);
		}
		InflatableBalloon inflate = new InflatableBalloon(100, 100, r, Color.RED);
		inflate.inflate(n);
		if (inflate.getRadius() <= 0) {
			System.out.println("The radius cannot be negative.");
			System.out.println("The radius is " + inflate.getRadius());
			System.exit(1);
		}
		System.out.print("The balloon has a new radius of " + inflate.getRadius() + ".");
	}

}
