package ch07problems;

/**
 * 
 * @author Zach Wellman
 * Date: 10/2/2018
 */
public class Lines {

	public static int numLines() {
		int lines = 0; {
			for (int x = 1; x <= 4; x++) {
				for (int y = 1; y <= 2; y++) {
					if (1 <= y && y <= x && x <=100 && gcf(x, y) ==1) {
						lines++;
					}
				}
			}
		}
		return lines;
	}

	private static int gcf(int number1, int number2) {
		if (number2 == 0) {
			return number1;
		}
		return gcf(number2, number1 % number2);
	}



	public static void main(String[] args) {
		System.out.print(numLines());
	}
}