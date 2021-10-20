package Main;

import Character.NPC;
import Character.PC;
import Stat.StatFactory;

public class Main {
	public static void main(String[] args) {
		System.out.println(new NPC(StatFactory.createStatArray(5, 5, 5, 5), "Test"));
		System.out.println(new PC(StatFactory.createStatArray(3, 5, 8, 1), "Lyla"));
	}
}