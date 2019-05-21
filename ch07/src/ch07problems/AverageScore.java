package ch07problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Zach Wellman
 *
 */

public class AverageScore {
	static Scanner input;
	public static void main(String[] args) {
		int hahaFinal = 0;
		double a = 0;

		try {
			input = new Scanner(new File("scores.dat"));
		} catch (FileNotFoundException e) {
			System.out.println("***  Can't open scores.dat ***");
			System.exit(1);
		} finally {	
			while (input.hasNextInt()) {
				hahaFinal++;
				a += input.nextInt();
			}
			System.out.println(a/hahaFinal);
		}
	}
}
