package ch06lab;

public class CrapsConsoleTest {
	public static void main(String[] args) {
		Die die = new Die();
		die.roll();
		System.out.println(die.getNumDots());
		die.roll();
		System.out.println(die.getNumDots());
		die.roll();
		System.out.println(die.getNumDots());
		die.roll();
		System.out.println(die.getNumDots());
		die.roll();
		System.out.println(die.getNumDots());
		die.roll();
		System.out.println(die.getNumDots());
		die.roll();
		System.out.println(die.getNumDots());
	}
}
