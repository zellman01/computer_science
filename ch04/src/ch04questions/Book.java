package ch04questions;

public class Book {
	public static int numPages;
	public static int currentPage;
	
	public Book(int p) {
		numPages = p;
		currentPage = 1;
	}
	
	public static int getCurrentPage() {
		return currentPage;
	}
	
	public static int getNumPage() {
		return numPages;
	}
	
	public static void nextPage() {
		if (currentPage < numPages) {
			//System.out.print("Current page: ");
			currentPage++;
		}
	}
}
