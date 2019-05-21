package ch07problems;

import java.util.Scanner;

public class SumOfAllNumbers {

public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	System.out.print("Enter a positive integer under 10: ");
	int n = input.nextInt();
	input.close();
	if (n > 10) {
		System.exit(1);
	}
	int sum = 0;
	for (int k = 1; k <= n; k++) {
		if (k > 1)
			System.out.print(" + ");
			System.out.print(k);
			sum += k;
		}
	System.out.println(" = " + sum);
	input.close();
	}
}