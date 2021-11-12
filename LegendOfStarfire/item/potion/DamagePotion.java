package item.potion;

import item.Potion;
import character.Character;

/**
 * A potion that damages the user/person who got splahed with it
*/
public class DamagePotion extends Potion {
	/**
	 * Creates a DamagePotion
	*/
	public DamagePotion() {
		super("Damage Potion", "Causes some damage to whoever gets this on them, or in them.");
	}
	
	@Override
	public void doEffect(Character user) {
		if (user.isDead()) return;
		
		int maxHealth = user.getMaximumHealthStat().getStatAmount();
		int damage = (int)(maxHealth*0.20);
		user.getHealthStat().modifyStat(damage, false);
	}
}