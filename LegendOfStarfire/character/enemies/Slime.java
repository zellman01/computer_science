package character.enemies;

import java.util.Random;

import character.NPC;
import stat.StatFactory;

public class Slime extends NPC {
	public Slime(Random rand) {
		// HP between 5 and 9
		super(StatFactory.createStatArray(rand.nextInt(5)+5, rand.nextInt(2)+1, rand.nextInt(3), rand.nextInt(2)+1), "Slime");
	}
	
	public int damage() {
		return 0;
	}
}