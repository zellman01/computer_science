package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 10/18/2018
 * DateCompleted: 11/27/2018
 */
public class HTML {
	private String removeTag(String a) {
		int c = a.indexOf("<");
		char a2 = a.charAt(c + 1);
		int d = a.indexOf("</");
		char a3 = a.charAt(d + 2);
		if (a2 == a3) {
			a = a.replaceAll("<" + a2 + ">", "").replaceAll("</" + a2 + ">", "");
		}
		return a;
	}
	
	public static void main(String[] args) {
		HTML hm = new HTML();
		String a = "<b>Strings are immutable</b>";
		System.out.println(hm.removeTag(a));
	}
}
