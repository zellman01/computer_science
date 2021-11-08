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
	private Random rand;
	
	public Slime(Random random) {
		// HP between 5 and 9
		super(StatFactory.createStatArray(random.nextInt(5)+5, random.nextInt(2)+1, random.nextInt(3)+1, random.nextInt(2)+1), "Slime");
		rand = random;
		
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
		int attackIndexSize = attackArray.size();
		return attackArray.get(rand.nextInt(attackIndexSize));
	}
}