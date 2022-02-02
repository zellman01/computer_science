package stat;

import stat.Stat;
import stat.StatName;

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
		Stat array[] = new Stat[5];
		array[StatName.MAXHP.getArrayPos()] = new Stat(StatName.MAXHP, hp);
		array[StatName.HP.getArrayPos()] = new Stat(StatName.HP, hp);
		array[StatName.ATK.getArrayPos()] = new Stat(StatName.ATK, atk);
		array[StatName.DEF.getArrayPos()] = new Stat(StatName.DEF, def);
		array[StatName.SPD.getArrayPos()] = new Stat(StatName.SPD, spd);
		
		return array;
	}
}
