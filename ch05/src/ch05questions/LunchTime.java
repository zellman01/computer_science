package ch05questions;

import java.util.Scanner;

/**
 * This program calculates the time remaining to lunch, if lunch is at 1PM, or 13:00
 * @author Zach Wellman
 *
 */

public class LunchTime {
	public static int minutesUntilLunch(int hours, int minutes) {
		int n = Math.abs((13 - hours) * 60 - minutes);
		if (n == 720) {
			n = 0;
		}
		//return Math.abs((13 - hours) * 60 - minutes);
		return n;
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner kboard = new Scanner(System.in);
		System.out.print("What time is it (in hh:mm format) (8:00AM to 12:59PM): ");
		String s = kboard.next();
		int i = s.indexOf(":");
		int hours = Integer.parseInt(s.substring(0, i));
		int minutes = Integer.parseInt(s.substring(i+1));
		if (minutesUntilLunch(hours, minutes) == 0) {
			System.out.println("Lunch has already happened today!");
		} else if (minutesUntilLunch(hours, minutes) != 1) {
			System.out.println("The time until lunch is " + minutesUntilLunch(hours, minutes) + " minutes.");
		} else if (minutesUntilLunch(hours, minutes) == 1) {
			System.out.println("The time until lunch is " + minutesUntilLunch(hours, minutes) + " minute.");
		}
	}
}
