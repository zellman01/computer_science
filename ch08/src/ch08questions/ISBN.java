package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 11/27/2018
 * DateCompleted: 11/27/2018
 */

public class ISBN {
	public boolean isValidISBN(String isbn) {
		int isbn2 = Integer.parseInt(isbn);
		return isbn2 % 10 == 0;
	}

	public static void main(String[] args) {
		ISBN isb = new ISBN();
		String temp = "978-0-9824775-6-5";
		temp = temp.replaceAll("-", "");
		if (temp.length() != 13) {
			System.out.println("You have inputted an incorrect amount of digits for a ISBN number.");
			System.exit(1);
		}
		String[] temp2 = temp.split("");
		int[] a = new int[temp2.length];
		for (int i = 0; i < temp2.length; i++) {
			a[i] = Integer.parseInt(temp2[i]);
		}
		int b = 0;
		boolean c = false;
		for (int i = 0; i < a.length; i++) {
			if (c == false) {
				b += a[i];
				c = true;
			} else {
				b += 3 * a[i];
				c = false;
			}
		}
		String isbn = Integer.toString(b);
		System.out.println(isb.isValidISBN(isbn));
	}
}
