package game.nameHere.lowestlevel;

import game.nameHere.lowestlevel.Name;
import game.nameHere.lowestlevel.Type;

public class Weapon {
	private Name weaponName;
	private Type weaponType;
	private int atkBonous;
	private boolean ignoreDefense, psychical, oneHand;
	
	public String toString() {
		String str = "";
		str += weaponName.toString() + ", " + weaponType.toString() + ", " + atkBonous + ", " + ignoreDefense + ", " + psychical + ", " + oneHand + ".";
		return str;
	}
}
