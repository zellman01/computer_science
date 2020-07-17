package game.nameHere.lowestlevel;

/**
 * This is the class that handles the low level Naming functions
 * @author zellman01
 * @since 0.1.0
 * @version 0.1.0
 */
public class Name {
	private String firstName, lastName;
	
	public Name(String firstName) {
		this.firstName = firstName;
		lastName = null;
	}
	
	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toString() {
		String str = "";
		if (lastName.equals(null))
			str += firstName;
		else
			str += firstName + " " + lastName;
		return str;
	}
}
