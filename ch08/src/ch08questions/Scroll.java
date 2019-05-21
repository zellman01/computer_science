package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 12/10/2018
 * DateCompleted: 12/10/2018
 *
 */
public class Scroll {
	private String scroll(String a) {
		char c = a.charAt(0);
		String b = a.substring(1) + c;
		return b;
	}
	
	public static void main(String[] args) {
		Scroll s = new Scroll();
		String thing = "Every good boy does fine";
		System.out.println(s.scroll(thing));
	}
}
