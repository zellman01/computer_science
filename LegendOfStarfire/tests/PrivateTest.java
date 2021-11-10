package tests;

import java.util.Optional;
import java.util.Random;

import character.PC;
import stat.StatFactory;
import item.HealthPotion;

/** 
 * Class to run various non-specific tests. Used in the main class as tests, and unit tests
*/
public class PrivateTest {
	/**
	 * Test that is run in main right now (quickly test something)
	*/
	public static void PublicTest() {
		PC pc = new PC(StatFactory.createStatArray(500, 15, 15, 15), "Test");
		pc.tookDamage(384);
		System.out.println(pc);
		System.out.println("Uses a potion!");
		HealthPotion hp = new HealthPotion();
		pc.addItem(hp);
		pc.useItem(1);
		//hp.doEffect(pc);
		System.out.println(pc);
	}
}