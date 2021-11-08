package character.bosses;

import java.util.Random;

import character.NPC;
import stat.StatFactory;
import attack.Attack;

public class Raguel extends NPC {
	private Random rand;
	
	public Raguel(Random random) {
		super(StatFactory.createStatArray(50, 3, 3, 8), "Raguel");
		rand = random;
	}
	
	public Attack makeAttack() {
		int attackIndexSize = attackArray.size();
		return attackArray.get(rand.nextInt(attackIndexSize));
	}
}