package Character;

import Stat.Stat;
import Equipment.EquipmentPage;
import Character.EquipmentManager;

public class Character implements EquipmentManager{
	private Stat[] statArray;
	private String name;
	private EquipmentPage ep;
	
	public Character(Stat[] statArray, String name) {
		this.statArray = statArray;
		this.name = name;
		ep = new EquipmentPage();
	}
	
	private String listStats() {
		String str = "\n\t";
		
		for (int i = 0; i < statArray.length; i++) {
			str += statArray[i];
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
	
	public void addWeapon(Weapon w) {
		
	}
	
	public void addHeadgear(Headgear hg) {
		
	}
	
	public void addBreastPlate(BreastPlate bp) {
		
	}
}