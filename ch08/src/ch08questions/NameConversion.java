package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 10/16/2018
 * Date completed: 10/18/2018
 */
public class NameConversion {
	/**
	 * @param a Last name of someone
	 * @param b First name of someone
	 * @return First name Last Name
	 */
	public String nameConversion(String a) {
		String[] b = a.split(", ");
		if (b.length > 2) {
			System.out.println("A name may only be two things.");
			System.exit(1);
		}
		try {
			return b[1] + " " + b[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new Error("You must include a comma with the name.");
		}
	}
	
	public static void main(String [] args) {
		NameConversion test = new NameConversion();
		String firstLast = test.nameConversion("von Neumann, John");
		System.out.println(firstLast);
	}
}
