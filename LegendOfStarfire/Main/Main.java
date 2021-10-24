package main;

import java.util.Optional;
import java.util.Random;

import character.enemies.Slime;
import character.PC;
import stat.StatFactory;
import equipment.Weapon;
import equipment.Equipment;

/**
 * Main class of the program. Executes the application.
 * @author zellman01
*/
public class Main {
	public static void main(String[] args) {
		PC test = new PC(StatFactory.createStatArray(3, 5, 8, 1), "Lyla");
		Weapon w = new Weapon("Weapon of winning", -2);
		test.addItem(w);
		test.addWeapon(Equipment.returnWeapon(test.removeItem(1).get()).get());
		System.out.println(test);
		System.out.println(test.removeItem(1).get());
	}
}
