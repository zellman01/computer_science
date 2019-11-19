package maths;

public class Something {
	private boolean even, odd, xRef, yRef, oRef;
	// even: If it is an even function
	// odd: If it is an odd function
	// xRef: Symmetry across x-axis
	// yRef: Symmetry across y-axis
	// oRef: Symmetry across the origin
	
	public Something() {
		even = false;
		odd = false;
		xRef = false;
		yRef = false;
		oRef = false;
	}
	
	public boolean getEven() { return this.even; }
	public boolean getOdd() { return this.odd; }
	public boolean getXRef() { return this.xRef; }
	public boolean getYRef() { return this.yRef; }
	public boolean getORef() { return this.oRef; }
	
	
	
	public static void main(String[] args) {
		
	}
}
