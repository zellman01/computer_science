package ch08questions;

/**
 * @author Zach Wellman
 * Date: 11/29/2018
 * DateCompleted: 12/3/2018
 * No longer needed
 */
public class OnlyNumbers {
	private String[] alpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	
	public static void main(String[] args) {
		OnlyNumbers on = new OnlyNumbers();
		String numbers = "123";
		boolean truth = true;
		for(String test : on.alpha) {
			if (numbers.contains(test)) {
				truth = false;
			}
		}
		System.out.println(truth + "|" + numbers);
	}
}
