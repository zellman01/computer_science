package ch08questions;

/**
 * @author Zach Wellman
 * Date: 11/29/2018
 * DateCompleted: 11/29/2018
 */
public class CutString {
	private String cutOut(String a, String cut) {
		a = a.replaceFirst(cut, "");
		return a;
	}
	
	public static void main(String [] args) {
		CutString cs = new CutString();
		String str = "Hi-ho, hi-ho";
		String result = cs.cutOut(str, "-ho");
		System.out.println(result);
	}
}
