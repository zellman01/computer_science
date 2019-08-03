package maths;

public class Matrix {
	int[][] array;
	public Matrix(int[][] a) {
		array = a;
	}
	
	public Matrix() {
		array = new int[2][2];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = 0;
			}
		}
	}
	
	public String toString() {
		String returnStatement = "";
		for (int i = 0; i < this.array.length; i++) {
			for (int j = 0; j < this.array[i].length; j++) {
				returnStatement += this.array[i][j] + " ";
			}
			returnStatement += "\n";
		}
		return returnStatement;
	}
	
	/**
	 * Adds two rows together, and sets the result to thing
	 * @param row
	 * @param thing
	 */
	public void addRow(int row, int thing) {
		if (row == 0 || thing == 0) {
			throw new Error("Row or thing cannot be 0.");
		}
		row--; thing--;
		int[] rowThing = new int[this.array[row].length];
		int rowCheck = 0;
		for (int i : this.array[row]) {
			rowThing[rowCheck] = i;
			rowCheck++;
		}
		for (int i = 0; i < this.array[thing].length; i++) {
			this.array[thing][i] += rowThing[i];
		}
	}
	
	/**
	 * Subtracts two rows together, and sets the result to thing
	 * @param row
	 * @param thing
	 */
	public void subtractRow(int row, int thing) {
		if (row == 0 || thing == 0) {
			throw new Error("Row or thing cannot be 0.");
		}
		row--; thing--;
		int[] rowThing = new int[this.array[row].length];
		int rowCheck = 0;
		for (int i : this.array[row]) {
			rowThing[rowCheck] = i;
			rowCheck++;
		}
		for (int i = 0; i < this.array[thing].length; i++) {
			this.array[thing][i] -= rowThing[i];
		}
	}
	
	/**
	 * Multiplies two rows together, and sets the result to thing
	 * @param row
	 * @param thing
	 */
	public void multiplyRow(int row, int thing) {
		if (row == 0 || thing == 0) {
			throw new Error("Row or thing cannot be 0.");
		}
		row--; thing--;
		int[] rowThing = new int[this.array[row].length];
		int rowCheck = 0;
		for (int i : this.array[row]) {
			rowThing[rowCheck] = i;
			rowCheck++;
		}
		for (int i = 0; i < this.array[thing].length; i++) {
			this.array[thing][i] *= rowThing[i];
		}
	}
	
	/**
	 * Divides two rows together, and sets the result to thing
	 * @param row
	 * @param thing
	 */
	public void divideRow(int row, int thing) {
		if (row == 0 || thing == 0) {
			throw new Error("Row or thing cannot be 0.");
		}
		row--; thing--;
		int[] rowThing = new int[this.array[row].length];
		int rowCheck = 0;
		for (int i : this.array[row]) {
			rowThing[rowCheck] = i;
			rowCheck++;
		}
		for (int i = 0; i < this.array[thing].length; i++) {
			this.array[thing][i] /= rowThing[i];
		}
	}
	
	/**
	 * 
	 * @param row Row to be changed
	 * @param operator What operator to execute
	 * @param number What number to do
	 */
	public void rowOperator(int row, String operator, int number) {
		if (row == 0) {
			throw new Error("Row cannot be zero.");
		}
		row--;
		switch (operator) {
		case "+":
			for (int i = 0; i < this.array[row].length; i++) {
				this.array[row][i] += number;
			}
			break;
		case "-":
			for (int i = 0; i < this.array[row].length; i++) {
				this.array[row][i] -= number;
			}
			break;
		case "*":
			for (int i = 0; i < this.array[row].length; i++) {
				this.array[row][i] *= number;
			}
			break;
		case "/":
			for (int i = 0; i < this.array[row].length; i++) {
				this.array[row][i] /= number;
			}
			break;
		}
	}
}
