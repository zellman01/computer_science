package ch06lab;

public class Die {
	
	int n = 0;
	int face = 0;
	
	public Die() {
		face = 6;
	}
	
	public Die(int a) {
		face = a;
	}
	
	public void roll() {
		n = (int)(Math.random() * face + 1);
		while (n < 1 || n > face) {
			n = (int)(Math.random() * face + 1);
		}
	}
	
	public int getNumDots() {
		return n;
		
	}
}
