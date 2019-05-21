package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 12/10/2018
 * DateCompleted: 12/12/2018
 *
 */
public class CCNumber {
	public static void main(String[] args) {
		String ccNumber = "1234 5678 9012 3456";
		if (ccNumber.length() != 19) {
			System.exit(1);
		}
		int test = ccNumber.length() - 4;
		int test2 = ccNumber.length() - 6;
		String last4 = ccNumber.substring(test);
		String last5 = ccNumber.substring(test2);
		last5 = last5.replaceAll(" ", "");
		System.out.println(last4);
		System.out.println(last5);
	}
}
