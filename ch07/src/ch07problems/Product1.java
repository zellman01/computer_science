package ch07problems;

import java.util.Scanner;

public class Product1 {
	// Returns the product of a and b
	// Precondition: a >= 0, b >= 0
	public static int product(int a, int b) {
		int sum = 0;
		int i = 0;
		do {
			sum += a+b;
			i++;
		} while(i==0);
		if (a == 1) {
			sum = b;
		}
		if (b == 1) {
			sum = a;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("What is the first value?(1-50): ");
		int a = kb.nextInt();
		System.out.print("What is the second value?(1-50): ");
		int b = kb.nextInt();
		kb.close();
		System.out.println(product(a, b));
	}
}
