package character.enemies;

import java.util.Random;

import character.NPC;
import stat.StatFactory;
import attack.Attack;
import attack.CommonAttack;

/**
 * The Slime enemy class
 * @author zellman01
*/
public class Slime extends NPC {
	public Slime(Random rand) {
		// HP between 5 and 9
		super(StatFactory.createStatArray(rand.nextInt(5)+5, rand.nextInt(2)+1, rand.nextInt(3)+1, rand.nextInt(2)+1), "Slime");
		
		// Generate the attacks Slime can use
		for (int i = 0; i < CommonAttack.arrayLength; i++) {
			super.addAttack(CommonAttack.commonAttacks.get(i));
		}
	}
	
	public int damage() {
		return 0;
	}
	
	@Override
	public Attack makeAttack() {
		return null;
	}
}