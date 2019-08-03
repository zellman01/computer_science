package otherThings;

public class Book {
	public static int numPages;
	public static int currentPage;
	public static int amountOfPages;
	
	public Book(int p, int sp) {
		numPages = p;
		currentPage = sp;
	}
	
	public static int getCurrentPage() {
		return currentPage;
	}
	
	public static int getNumPage() {
		return numPages;
	}
	
	public static void nextPage() {
		if (currentPage < numPages) {
			currentPage++;
			amountOfPages++;
		}
	}
	
	public static void previousPage() {
		if (currentPage < numPages) {
			currentPage--;
			amountOfPages--;
		}
	}
		
	public static void pagesRead() {
		System.out.println("Pages read: " + amountOfPages + ".");
		System.out.println(currentPage - amountOfPages + " is the remaining amount of pages left in the book.");
	}
}