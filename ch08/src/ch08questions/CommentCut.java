       package ch08questions;

/**
 * @author Zach Wellman
 * Date: 12/12/2018
 * DateCompleted: 2/4/2019
 *
 */
public class CommentCut {
	private String removeComment(String a) {
		int b = a.indexOf("/*");
		int c = a.indexOf("*/");
		if (b < c && a.length() > 3) {
			String s = a.substring(b, c + 2);
			a = a.replace(s, "");
		}
		return a;
	}
	public static void main(String[] args) {
		CommentCut cc = new CommentCut();
		String remove = "/**/*";
		System.out.println(cc.removeComment(remove));
	}
}
