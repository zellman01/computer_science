package character.enemies;

import java.util.Random;

import character.NPC;
import stat.StatFactory;
import attack.Attack;

/**
 * The bat enemy class
 * @author zellman01
*/
public class Bat extends NPC {
	public Bat(Random rand) {
		// HP between 5 and 9
		super(StatFactory.createStatArray(rand.nextInt(3)+2, rand.nextInt(3)+1, rand.nextInt(2)+1, rand.nextInt(6)+1), "Bat");
	}
	
	public int damage() {
		return 0;
	}
	
	public Attack makeAttack() {
		return null;
	}
}