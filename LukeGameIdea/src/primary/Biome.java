package primary;

import java.util.concurrent.ThreadLocalRandom;

public class Biome {
	public String name;
	private int startingLevel;
	private int endLevel;
	private String[] enemyName;
	private Enemy[] enemy;
	
	public String toString() {
		String str = "Name: " + name + "\nLevel range: " + startingLevel + "-" + endLevel + "\nEnemies: ";
		for (int i = 0; i < enemyName.length; i++) {
			str += enemyName[i];
			if (i != enemyName.length -1) {
				str += ", ";
			}
		}
		str += "\n";
		for (int i = 0; i < enemy.length; i++) {
			str += enemy[i] + "\n\n";
		}
		return str;
	}
	
	public Biome(String bName, int sLevel, int eLevel, String[] eNames) {
		name = bName;
		startingLevel = sLevel;
		endLevel = eLevel;
		enemyName = new String[eNames.length];
		enemyName = eNames;
		enemy = new Enemy[enemyName.length];
		boolean levelRange = (sLevel-eLevel)!=0;
		for (int i = 0; i < enemy.length; i++) {
			int level;
			if (!levelRange)
				level = sLevel;
			else
				level = ThreadLocalRandom.current().nextInt(startingLevel, endLevel);
			String name = enemyName[(int)(Math.floor(Math.random()*enemyName.length-1)+1)];
			String type = "Psy";
			enemy[i] = new Enemy(name, level, type);
		}
	}
	
	public static void main(String[] args) {
		String[] enemies = {"Slime", "Treeant", "Crow", "Spitting Bulb", "Missingno"};
		System.out.println(new Biome("Forest", 1000, 3000, enemies));
	}
}
