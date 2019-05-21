package ch07problems;

import java.util.Scanner;

public class NextOrder {
	private static final double price = 0.26;
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		char answer;
		
		do {
			System.out.print("Enter quantity (1-5000): ");
			int a = kb.nextInt();
			/*if (a > 5000) {
				System.out.println("We do not have that much in stock");
				System.exit(1);
			}*/
			if (a < 1) {
				System.out.println("Sorry, but it is not possible to order less than 1.");
				System.exit(1);
			}
			int quantity = a;
			kb.nextLine();
			if ((quantity % 25) != 0) {
				System.out.println("Ripples can be ordered only in packs of 25.");
				System.out.println();
			} else {
				System.out.printf("You have ordered %d Ripples -- $%.2f\n\n",
						quantity, price * quantity);
			}
			do {
				System.out.print("Next customer (y/n): ");
				
				String str = kb.nextLine().trim();
				if (str.length() == 1) {
					answer = str.charAt(0);
				} else {
					answer = ' ';
				}
			} while(answer != 'n' && answer != 'y');
			if (answer == 'y') {
				System.out.println();
			}
		} while(answer != 'n');
		kb.close();
		System.out.println();
		System.out.println("Thank you for using Ripple Systems.");
	}
}
