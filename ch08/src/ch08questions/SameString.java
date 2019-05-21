package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 12/10/2018
 * DateCompleted: 2/4/2019
 *
 */
public class SameString {
	private boolean check(String a) {
		if (a.equals("")) {
			return false;
		}
		char c = a.charAt(0);
		String b = a.substring(1) + c;
		return b.equals(a);
	}
	public static void main(String[] args) {
		SameString ss = new SameString();
		System.out.println(ss.check("aAa"));
	}
}
