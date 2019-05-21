package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 10/16/2018
 * Date completed: 10/16/2018
 */
public class SSN {
	private String removeDashes(String a) {
		String s = "";
		a = a.replaceAll("-", s);
		return a;
	}
	
	public static void main(String[] args) {
		SSN socialNumber = new SSN();
		String SSN = "987-65-4321";
		if (SSN.length() != 11) {
			System.out.println("Incorrect length for a Social Security Number.");
			System.exit(1);
		}
		System.out.println(socialNumber.removeDashes(SSN));
	}
}
