package ch07problems;

/**
 * 
 * @author Zach Wellman
 * Date: 9/15/2018
 * DateCompleted: 10/4/2018
 */
public class S18 {
	private int num = 0, denom = 0;
	int num1, denom1;
	static S18 confuse = new S18();
	private final static double RATIO = (1+Math.sqrt(5))/2;
	
	public S18() {
		num1 = num;
		denom1 = denom;
	}
	
	public void setNum(int a) {
		this.num1 = a;
	}

	public void setDenom(int b) {
		this.denom1 = b;
	}
	
	public int getNum() {
		return this.num1;
	}
	
	public int getDenom() {
		return this.denom1;
	}
	
	public static void display(int a, int b) {
		if (a == 1 && b == 1) {
			System.out.println("1");
		} else {
			System.out.println(a + "/" + b);
		}
	}
	
	public static void update(int a, int b) {
		confuse.setDenom(a);
		int temp = a + b;
		confuse.setNum(temp);
	}
	
	public static double ratioCheck(int a, int b) {
		double temp = (double)a/b;
		temp = RATIO - temp;
		if (temp < 0)
			temp *= -1;
		return temp;
	}
	
	public static void main(String [] args) {
		//System.out.println(RATIO);
		//System.out.println();
		confuse.setNum(1);
		confuse.setDenom(1);
		display(confuse.getNum(), confuse.getDenom());
		System.out.println(ratioCheck(confuse.getNum(), confuse.getDenom()));
		System.out.println();
		while (confuse.getDenom() < 34) {
			update(confuse.getNum(), confuse.getDenom());
			display(confuse.getNum(), confuse.getDenom());
			System.out.println(ratioCheck(confuse.getNum(), confuse.getDenom()));
			System.out.println();
		}
	}
}
