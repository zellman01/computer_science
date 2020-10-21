package ch09problems;

/**
 * 
 * @author Zach Wellman
 * Date Started: 2/25/19
 * Date Completed: 5/1/19
 * Question 20
 */

public class Pascal {

private static int max = 0;
	
	public static int[][]pascalTriangle(int n) {
		int [][] pascal = new int[n+1][n+1];
		
		for (int i = 0; i < pascal.length; i++) {
			for (int j = 0; j < pascal[i].length; j++) {
				pascal[i][j] = 0;
			}
		}
		
		pascal[1] = new int[1+2];
		pascal[1][1]=1;
		for (int i = 2; i <= n; i++) {
			pascal[i] = new int[i+2];
			for (int j = 1; j < pascal[i].length-1; j++) {
				pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
				String str = Integer.toString(pascal[i][j]);
				int len = str.length();
				if (len > max) {
					max = len;
				}
			}
		}
		return pascal;
	}
	
	public static void main(String[] args) {
		int n = 7;
		int[][] pascal = pascalTriangle(n);
		for (int i = 1; i<= n; i++) {
			for (int k = n; k > i; k--) System.out.format("%-" + max + "s", " ");
			for (int j = 1; j < pascal[i].length - 1; j++) System.out.format("%-" + (max + max) + "s", pascal[i][j]);
			System.out.println();
		}
	}
}
