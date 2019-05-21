package ch02Questions;

import java.util.Scanner;

public class Number13a {
	public static void main (String [] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter an interger (1 - 50): ");
		int n = keyboard.nextInt();
		keyboard.close();
		
		System.out.println("2 * " + n + " = " + (n+n));
	}
}
