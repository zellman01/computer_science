package character;

import equipment.Weapon;
import equipment.Headgear;
import equipment.BreastPlate;

/**
 * @deprecated Not seeable how this is useful.
*/
@Deprecated
public interface EquipmentManager {
	public void addWeapon(Weapon w);
	public void addHeadgear(Headgear hg);
	public void addBreastPlate(BreastPlate bp);
}
