package ch04lab;

import java.awt.Color;

public class ConsoleBalloon extends Balloon {
	public static void main(String [] args) {
		Balloon b = new Balloon(100, 100, 20, Color.RED);
		System.out.println(b);
	}

}
