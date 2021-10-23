package Stat;

import Stat.Stat;
import Stat.StatName;

/**
 * This class is used to normalize all stat arrays used in the program.
 * @author zellman01
*/
public class StatFactory {
	/**
	 * Creates the formal stat array
	 * @param hp The value for the HP stat
	 * @param atk The value for the ATK stat
	 * @param def The value for the DEF stat
	 * @param spd The value for the SPD stat
	 * @return The normalized Stat array
	*/
	public static Stat[] createStatArray(int hp, int atk, int def, int spd) {
		Stat array[] = new Stat[4];
		array[0] = new Stat(StatName.HP, hp);
		array[1] = new Stat(StatName.ATK, atk);
		array[2] = new Stat(StatName.DEF, def);
		array[3] = new Stat(StatName.SPD, spd);
		
		return array;
	}
}
