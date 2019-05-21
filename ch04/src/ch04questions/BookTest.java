package ch04questions;

public class BookTest extends Book {
	
	public BookTest(int p) {
		super(p);
	}
	
	public static void main(String [] args) {
		int setNumPages = 3;
		@SuppressWarnings("unused")
		Book b = new Book(setNumPages);
		while (getCurrentPage() < getNumPage()) {
			nextPage();
			System.out.println(getCurrentPage());
		}
		nextPage();
		System.out.println(getCurrentPage());
	}
}
