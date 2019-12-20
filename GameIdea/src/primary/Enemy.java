package primary;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy {
	public int level, atk, def;
	public double hp;
	public String name, type;
	
	public boolean isDead() {
		return hp == 0.0;
	}
	
	public Enemy(String eName, int lv, String type1) {
		level = lv;
		name = eName;
		if (Math.random() >= 0.5)
			hp = Math.floor(lv + (ThreadLocalRandom.current().nextDouble(1, 3.5)));
		else
			hp = Math.floor(lv - (ThreadLocalRandom.current().nextDouble(1, 3.5)));
		
		if (Math.random() >= 0.5)
			atk = (int)((lv/2) + (ThreadLocalRandom.current().nextDouble(1, 1.5)));
		else
			atk = (int)((lv/2) - (ThreadLocalRandom.current().nextDouble(1, 1.5)));
		
		if (Math.random() >= 0.5)
			def = (int)((level * 0.05) + (ThreadLocalRandom.current().nextDouble(1, 2.75)));
		else
			def = (int)((level * 0.05) - (ThreadLocalRandom.current().nextDouble(1, 2.75)));
		
		if (atk < 0)
			atk = 0;
		if (def < 0)
			def = 0;
		if (hp <= 0.0)
			hp = (hp * -1) + 2;
		type = type1;
	}
	
	public String toString() {
		return "Name: " + name + "\nLevel: " + level + "\nHP: " + hp + "\nATK: " + atk + "\nDEF: " + def + "\nType: " + type;
	}
	
	public static void main(String[] args) {
		System.out.println(new Enemy("Slime", 100, "P"));
	}
}

// ATK (Smaller fraction of HP)
// DEF (0 most likely)
// Type (Thing)