package Character;

import Stat.Stat;
import Stat.StatName;
import Equipment.EquipmentPage;
import Equipment.Weapon;
import Equipment.Headgear;
import Equipment.BreastPlate;

/**
 * The parent class of PC and NPC
 * @author zellman01
*/
public class Character {
	private Stat[] statArray;
	private String name;
	private EquipmentPage ep;
	
	/**
	 * Create a Character object
	 * @param statArray a normalized array of the stats in the game
	 * @param name The character's name
	*/
	public Character(Stat[] statArray, String name) {
		this.statArray = statArray;
		this.name = name;
		ep = new EquipmentPage();
	}
	
	private String listStats() {
		String str = "\n\t";
		
		for (int i = 0; i < statArray.length; i++) {
			str += statArray[i];
			if (!ep.isEmpty()) {
				if (ep.getWeapon() != null && statArray[i].getStatName() == ep.getWeapon().getStatName()) {
					str += "+" + ep.getWeapon().getAmountModified();
				} else if (ep.getHeadgear() != null && statArray[i].getStatName() == ep.getHeadgear().getStatName()) {
					str += "+" + ep.getHeadgear().getAmountModified();
				} else if (ep.getBreastPlate() != null && statArray[i].getStatName() == ep.getBreastPlate().getStatName()) {
					str += "+" + ep.getBreastPlate().getAmountModified();
				}
			}
			if (i != statArray.length-1) str += "\n";
			str += "\t";
		}
		
		return str;
	}
	
	private String listEquipment() {
		String str;
		str = "\n\t";
		if (ep.isEmpty()) str += "None equiped";
		return str;
	}
	
	@Override
	public String toString() {
		return "Character's name: " + name + "\n"
		+ "Stats: " + listStats() + "\n"
		+ "Equipment: " + listEquipment();
	}
	
	public boolean addWeapon(Weapon w) {
		return ep.equipWeapon(w);
	}
	
	public boolean addHeadgear(Headgear hg) {
		return ep.equipHeadgear(hg);
	}
	
	public boolean addBreastPlate(BreastPlate bp) {
		return ep.equipBreastPlate(bp);
	}
}