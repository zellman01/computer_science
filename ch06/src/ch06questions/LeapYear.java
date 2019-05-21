package ch06questions;

import java.util.Scanner;

public class LeapYear {
	static Scanner kb = new Scanner(System.in);
	/**
	 * 
	 * @param year Year of what the user wants to find out
	 * @return true If the given year is a leap year
	 */
	public static boolean isLeapYear(int year) {
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String [] args) {
		System.out.print("What year do you want to know if it is a leap year? (1-5000): ");
		int year = kb.nextInt();
		kb.close();
		if (isLeapYear(year) == true) {
			System.out.println("The year " + year + " is a leap year.");
		} else {
			System.out.println("The year " + year + " is not a leap year.");
		}
	}
}
