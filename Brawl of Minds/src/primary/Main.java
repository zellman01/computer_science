package primary;

/**
 * Debugging class
 * @author Zachary Wellman
 * @version 0.0.1
 * @since 0.0.1
 */
public class Main {
	public static void main(String[] args) {
		Player test = new Player(10, "Test");
		System.out.println(test);
		Card test2 = new Card("", 10, "test2");
		System.out.println("\n" + test2);
		System.out.println("\n\nTest uses test2\n\n");
		if (test2.canBeUsed(test)) {
			test.usedMana(test2.getManaCost());
		}
		System.out.println(test);
		/*System.out.println("\n\nTest gets 3 mana crystals locked\n\n");
		test.lockMana(3);
		System.out.println(test);*/
		System.out.println("\n\nTest gains 15 mana crystals\n\n");
		test.turnEnd(10);
		test.addTempMana(5);
		System.out.println(test);
	}
}
