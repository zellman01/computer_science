package Stat;

import Stat.Stat;
import Stat.StatName;

public class StatFactory {
	public static Stat[] createStatArray(int hp, int atk, int def, int spd) {
		Stat array[] = new Stat[4];
		array[0] = new Stat(StatName.HP, hp);
		array[1] = new Stat(StatName.ATK, atk);
		array[2] = new Stat(StatName.DEF, def);
		array[3] = new Stat(StatName.SPD, spd);
		
		return array;
	}
}