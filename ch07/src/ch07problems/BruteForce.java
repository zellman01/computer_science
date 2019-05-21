package ch07problems;

/**
 * 
 * @author Zach Wellman
 * Date: 10/8/2018
 */
public class BruteForce {
	private final int start = 0;
	int begin;
	static BruteForce hi = new BruteForce();

	public BruteForce() {
		begin = start;
	}

	public void updateNumber() {
		this.begin++;
	}

	public int obtainNumber() {
		return this.begin;
	}
	private void check(int a) {
		int LUCKY_NUM = 7, UNLUCKY_NUM = 9;
		String chosenNumber = String.valueOf(a);
		String luckyNumber = String.valueOf(LUCKY_NUM);
		String unluckyNum = String.valueOf(UNLUCKY_NUM);
		if (chosenNumber.contains(luckyNumber) && chosenNumber.contains(unluckyNum))
			hi.updateNumber();
	}
	public static void main(String [] args) {
		int x = 999999;
		for (int i = 1; i <= x; i++) {
			hi.check(i);
		}
		System.out.println(hi.obtainNumber());
	}
}
