package ch07problems;

/**
 * 
 * @author Zach Wellman
 * Date: 9/12/2018
 * Ch. 07 Number 21
 */
public class Checkardboard {
	public static void printCheckardboard(int n) {
		for (int row = 1; row <= n; row++) {
			int col = 1;
			while (col <= n && row % 2 != 0) {
				if (col % 2 != 0) {
					System.out.print("#");
				} else {
					if (col % 2 == 0) {
						System.out.print("o");
					}
				}
				col++;
			}
			while (col <= n && row % 2 == 0) {
				if (col % 2 != 0) {
					System.out.print("o");
				} else {
					if (col % 2 == 0) {
						System.out.print("#");
					}
				}
				col++;
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int n = 10;
		if (n < 1 || n > 10) {
			System.out.println(n > 10 ? "You think you can go above the limit set at 10?" : "You thing you can go below the limit set at 1?");
			System.exit(1);
		}
		printCheckardboard(n);
	}

}
