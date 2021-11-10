package main;
import tests.PrivateTest;
import attack.Attack;
import attack.CommonAttack;

/**
 * Main class of the program. Executes the application.
 * @author zellman01
*/
public class Main {
	public static void main(String[] args) {
		CommonAttack.addAttack(new Attack("test1", 10, 10, 10));
		CommonAttack.addAttack(new Attack("test2", 15, 15, 8));
		PrivateTest.PublicTest();
	}
}
