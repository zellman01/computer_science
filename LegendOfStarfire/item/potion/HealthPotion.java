package item.potion;

import item.Potion;
import character.Character;

/**
 * Classic potion of healing class
*/
public class HealthPotion extends Potion {
	/**
	 * Creates a 15% health potion
	*/
	public HealthPotion() {
		super("Health Potion", "A healing potion that heals 15% of your maximum health");
	}
	
	@Override
	public void doEffect(Character user) {
		if (user.isDead()) return; // Do nothing if the character is dead
		
		int maxHealth = user.getMaximumHealthStat().getStatAmount();
		int healing = (int)(maxHealth*0.15);
		
		if (user.getHealthStat().getStatAmount()+healing > maxHealth) {
			user.fullHeal();
			return;
		}
		
		user.getHealthStat().modifyStat(healing, true);
	}
}