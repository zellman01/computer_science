package maths;

public class MatrixUnitTests {
	public static void main(String[] args) {
		RandomizeArrays ra = new RandomizeArrays();
		int[][] arrayTest = new int[4][5];
		arrayTest = ra.randomizeDoubleIntArray(arrayTest, 50);
		Matrix test = new Matrix(arrayTest);
		System.out.println("Original: ");
		System.out.println(test);
		System.out.println();
		test.addRow(1, 2);
		System.out.println("Add rows 1 and 2, and set it to 2: ");
		System.out.println(test);
		System.out.println();
		System.out.println("Subtracts rows 2 and 3, and set it to 3: ");
		test.subtractRow(2, 3);
		System.out.println(test);
		System.out.println();
		System.out.println("Adds 10 from row 4, and set it to 4: ");
		test.rowOperator(4, "+", 10);
		System.out.println(test);
		System.out.println();
		System.out.println("Subtracts 10 from row 1, and set it to 1: ");
		test.rowOperator(1, "-", 10);
		System.out.println(test);
		System.out.println();
	}
}
