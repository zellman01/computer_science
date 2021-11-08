package main;

import java.util.Optional;
import java.util.Random;

import character.PC;
import stat.StatFactory;
import item.HealthPotion;

/**
 * Main class of the program. Executes the application.
 * @author zellman01
*/
public class Main {
	public static void main(String[] args) {
		PC pc = new PC(StatFactory.createStatArray(500, 15, 15, 15), "Test");
		pc.tookDamage(384);
		System.out.println(pc);
		System.out.println("Uses a potion!");
		HealthPotion hp = new HealthPotion("Test");
		pc.addItem(hp);
		pc.useItem(1);
		//hp.doEffect(pc);
		System.out.println(pc);
	}
}
