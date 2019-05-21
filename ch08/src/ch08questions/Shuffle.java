package ch08questions;

import java.util.Random;

/**
 * @author Zach Wellman
 * Date: 10/26/18
 * DateCompleted: 2/4/2019
 */

public class Shuffle {
	private String RShuffle(String a) {
		StringBuffer sb = new StringBuffer(a);
		int n = sb.length();
		Random rand = new Random();
		while (n > 1) {
			int randChar = rand.nextInt(n);
			char temp = sb.charAt(n-1);
			char temp2 = sb.charAt(randChar);
			sb.setCharAt(n-1, temp2);
			sb.setCharAt(randChar, temp);
			n--;
		}
		String returnString = sb.toString();
		return returnString;
	}
	
	public static void main(String [] args) {
		Shuffle s = new Shuffle();
		System.out.println(s.RShuffle("abc"));
	}
}
