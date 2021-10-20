package Equipment;

import Equipment.*;

public class EquipmentPage {
	private final int TOTALEQUIPPED = 1;
	private Headgear headGear;
	private BreastPlate breastPlate;
	private Weapon weapon;
	
	public EquipmentPage() {
		
	}
	
	public void equipHeadgear(Headgear hg) {
		if (hg != null) {
			headGear = hg;
		}
	}
	
	public void equipBreastplate(BreastPlate bp) {
		if (bp != null) {
			breastPlate = bp;
		}
	}
	
	public void equipWeapon(Weapon w) {
		if (w != null) {
			weapon = w;
		}
	}
	
	public void unEquipHeadgear() {
		headGear = null;
	}
	
	public void unEquipBreastplate() {
		breastPlate = null;
	}
	
	public void unEquipWeapon() {
		weapon = null;
	}
	
	public boolean isEmpty() {
		return (headGear == null) && (breastPlate == null) && (weapon == null);
	}
}