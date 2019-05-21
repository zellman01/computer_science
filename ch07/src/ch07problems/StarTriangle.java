package ch07problems;

import java.util.Scanner;
// TODO: Make it a Christmas tree
public class StarTriangle {
	public static void printStarTriangle(int n) {

		for (int row = 1; row <= n; row++) {
			int col = 1;
			while (col <= n - row) {
				System.out.print(" ");
				col++;
			}
			while (col < n + row) {
				System.out.print("*");
				col++;
			}
			if (row < n)
				System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("How many rows? (1-10): ");
		int n = kb.nextInt();
		kb.close();
	
		/*if (n < 1 || n > 10) {
			System.out.println("You think you are so smart you can deny the limits set between 1 and 10?");
			System.exit(1);
		}*/
		printStarTriangle(n);
	}
}