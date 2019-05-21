package ch07problems;

import java.util.Scanner;

public class TextNTimes {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		System.out.print("Enter a positive integer (1-10): ");
		int n = kb.nextInt();
		kb.nextLine();
		
		if (n < 1 || n > 10) {
			System.out.println("You think you are so special that you can go above/below the limit? I don't think so!");
			System.exit(1);
		}
		
		System.out.print("Enter a line of text: ");
		String text = kb.nextLine();
		kb.close();
		
		int i = 0;
		
		do {
			System.out.println(text);
			i++;
		} while (i < n);
			
	}
}
