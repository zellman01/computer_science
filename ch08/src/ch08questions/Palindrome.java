package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 12/18/18
 * DateCompleted: 12/18/18
 */
 public class Palindrome {
	public boolean isPanlindrome(String word) {
		if (word.length() == 1) {
			return true;
		} else if (word.length() == 0) {
			return false;
		}
		String s = word.substring(1, word.length()-1);
		char s2 = s.charAt(0);
		char s3 = s.charAt(s.length()-1);
		return s2==s3;
	}
	public static void main(String[] args) {
		Palindrome pd = new Palindrome();
		String word = "Madam I'm Adam";
		word = word.toUpperCase();
		word = word.trim();
		word = word.replaceAll("\\W", ""); 
		System.out.println(pd.isPanlindrome(word));
	}
}
