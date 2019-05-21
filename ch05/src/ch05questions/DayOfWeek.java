package ch05questions;

import java.util.Scanner;

/**
 * This program calculates the day you want to find in January.
 * @author Zach Wellman
 *
 */
public class DayOfWeek {
	private static int dayOfWeek1, day, dayOfWeek;
	
	public static String day() {
		dayOfWeek = (dayOfWeek1 - 1 + day) % 7;
		if (day>31||day<1) {
			return "This day is not in January";
		}
		if (dayOfWeek == 0) {
			return "Sunday";
		} else if (dayOfWeek == 1) {
			return "Monday";
		} else if (dayOfWeek == 2) {
			return "Tuesday";
		} else if (dayOfWeek == 3) {
			return "Wendsday";
		} else if (dayOfWeek == 4) {
			return "Thursday";
		} else if (dayOfWeek == 5) {
			return "Friday";
		} else if (dayOfWeek == 6) {
			return "Saturday";
		} else
		return null;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner kboard = new Scanner(System.in);
		System.out.print("When is the first day of January? (0 = Sunday, 1 = Monday, 2 = Tuesday, 3 = Wendsday, 4 = Thursday, 5 = Friday, 6 = Saturday): ");
		dayOfWeek1 = kboard.nextInt();
		System.out.print("What day do you want to figure out? (1-31): ");
		day = kboard.nextInt();
		System.out.println("The day is " + day() + ".");
	}
}
