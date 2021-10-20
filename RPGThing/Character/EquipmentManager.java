package Character;

import Equipment.Weapon;
import Equipment.Headgear;
import Equipment.BreastPlate;

/**
 * @deprecated Not seeable how this is useful.
*/
@Deprecated
public interface EquipmentManager {
	public void addWeapon(Weapon w);
	public void addHeadgear(Headgear hg);
	public void addBreastPlate(BreastPlate bp);
}