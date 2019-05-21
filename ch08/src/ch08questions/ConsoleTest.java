package ch08questions;

/**
 * @author Zach Wellman
 * Date: 1/9/2019
 * DateCompleted: 1/9/2019
 */

import java.util.Scanner;

public class ConsoleTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer (1-500): ");
		int n1 = in.nextInt();
		//int a = Integer.MAX_VALUE;
		System.out.print("Enter another positive integer (1-500): ");
		int n2 = in.nextInt();
		if (n1 < 1 || n1 > 500 || n2 < 1 || n2 > 500)
			System.exit(1);
		in.close();
		String s1 = "", s2 = "";
		s1 += n1; 
		s2 += n2;
		int n3 = s1.compareTo(s2);
		int n4 = n1 - n2;
		System.out.println(n3 + "|" + n4);
		
	}
}
