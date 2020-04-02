package test;

import utils.Deck;

public class Testing {
	public static void main(String[] args) {
		Deck test = new Deck();
		//test.shuffle();
		System.out.println(test.draw());
		test.draw();
		test.draw();
		test.draw();
		System.out.println(test);
	}
}
