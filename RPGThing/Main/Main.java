package Main;

import Character.NPC;
import Character.PC;
import Stat.StatFactory;
import Equipment.Weapon;

/**
 * Main class of the program. Executes the application.
 * @author zellman01
*/
public class Main {
	public static void main(String[] args) {
		System.out.println(new NPC(StatFactory.createStatArray(5, 5, 5, 5), "Test"));
		PC test = new PC(StatFactory.createStatArray(3, 5, 8, 1), "Lyla");
		Weapon w = new Weapon("Weapon of winning", 12);
		if (!test.addWeapon(w)) {
			System.out.println("Something went wrong.");
		}
		System.out.println(test);
	}
}