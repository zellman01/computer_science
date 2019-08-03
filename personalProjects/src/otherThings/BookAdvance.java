/**
 * TODO: Make a custom GUI for this program with clickable buttons so it is no longer a console application
 */

package otherThings;

import java.util.Scanner;

public class BookAdvance extends Book {
	
	public BookAdvance(int p, int sp) {
		super(p, sp);
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String [] args) {
		Scanner kboard = new Scanner(System.in);
		System.out.print("How many pages are there in the book? ");
		int p = kboard.nextInt();
		int setNumPages = p;
		System.out.print("What page are you starting on?: ");
		int sp = kboard.nextInt();
		int setCurrentPage = sp;
		Book b = new Book(setNumPages, setCurrentPage);
		if (getCurrentPage() > getNumPage() || getCurrentPage() <= 0) {
			System.out.println("There is no way you can start on a non-existant page.");
			System.exit(1);
		}
		if (getCurrentPage() == setNumPages) {
			System.out.println("You are already on the final page and this program is not needed.");
			System.exit(1);
		}
		System.out.println("Starting page: " + getCurrentPage());
		while (getCurrentPage() < getNumPage()) {
			System.out.print("Are you done with the current page? ");
			String command = kboard.next();
			switch (command) {
			case "Yes":
			case "yes":
			case "y":
			case "Y":
				nextPage();
				System.out.println("Current Page : " + getCurrentPage());
				if (getCurrentPage() == setNumPages) {
					System.out.println("Final Page: " + getCurrentPage());
				}
				break;
			case "Back":
			case "Previous":
			case "B":
			case "back":
			case "previous":
				previousPage();
				System.out.println("Current page :" + getCurrentPage());
				break;
			case "Stop":
				pagesRead();
				System.exit(0);
				break;
			default:
				System.out.println("Current Page : " + getCurrentPage());
			}
		}
	}
}
