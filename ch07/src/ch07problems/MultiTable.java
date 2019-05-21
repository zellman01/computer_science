package ch07problems;

public class MultiTable {
	public static void main(String[] args) {
		int a, b;
		for (a=1; a<=12; a++) {
		    for (b=1; b<=12; b++) {
		    	System.out.printf("%4d",a*b); 
		    }
		    System.out.println();
		} 
	}
}
