package ch07problems;

public class Algo1 {
	public static int algo11() {
		int n = 37, b = 2, p = 1;
		while (p <= n) {
			n -= p;
			p *= b;
		}
		return n;
	}
	
	public static void main(String[] args) {
		System.out.println(algo11());
	}
}
