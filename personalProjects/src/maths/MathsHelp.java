package maths;

public class MathsHelp {
	private static String algo1(int x) {
		if (x > 5000) {
			throw new Error("Ya can't go that big, boi.");
		}
		int[] usedNumbers = new int[99999999];
		for (int a : usedNumbers) {
			usedNumbers[a] = 0;
		}
		String returnString = "";
		int number = 0;
		for (int a = 1; a <= x; a++) {
			for (int b = 1; b <= x; b++) {
				boolean stop = false;
				if (a*b == x) {
					for (int e : usedNumbers) {
						if (a == usedNumbers[e] || b == usedNumbers[e]) {
							stop = true;
						}
					}
					if (!stop) {
						number++;
						returnString += number + ":" +  a + "*" + b + "\n";
						for (int d : usedNumbers) {
							if (a != 0) {
								if (usedNumbers[d] == 0) {
									usedNumbers[d] = a;
									a = 0;
								}
							}
							if (b != 0) {
								if (usedNumbers[d] == 0) {
									usedNumbers[d] = b;
									b = 0;
								}
							}
						}
					}
				}
			}
		}
		return returnString;
	}

	public static void main(String[] args) {
		System.out.println(algo1(32));
	}
}
