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
	private Random rand; // Randomly select an attack
	
	public Bat(Random random) {
		// HP between 5 and 9
		super(StatFactory.createStatArray(random.nextInt(3)+2, random.nextInt(3)+1, random.nextInt(2)+1, random.nextInt(6)+1), "Bat");
		rand = random;
	}
	
	public int damage() {
		return 0;
	}
	
	public Attack makeAttack() {
		int attackIndexSize = attackArray.size();
		return attackArray.get(rand.nextInt(attackIndexSize));
	}
}