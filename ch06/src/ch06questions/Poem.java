package ch06questions;
import java.util.Scanner;

public class Poem {
	public static void main(String [] args) {
		boolean stop = false;
		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		while (stop == false) {
			System.out.print("Enter a number 1-10 (or 0 to quit): ");
			int number = kb.nextInt();

			if (number < 0 || number > 10) {
				System.out.println("You think you can pick a number smaller than 0 or greater than 10! I think not!");
				System.exit(1);
			}
			switch(number) {
			case 1:
			case 2:
				System.out.println("Buckle your shoe");
				System.out.println();
				break;
			case 3:
			case 4:
				System.out.println("Shut the door");
				System.out.println();
				break;
			case 5:
			case 6:
				System.out.println("Pick up sticks");
				System.out.println();
				break;
			case 7:
			case 8:
				System.out.println("Don't be late");
				System.out.println();
				break;
			case 9:
			case 10:
				System.out.println("Do it over again");
				System.out.println();
				break;
			default:
				System.out.println("Bye");
				stop = true;
				break;
			}
		}
	}
}
