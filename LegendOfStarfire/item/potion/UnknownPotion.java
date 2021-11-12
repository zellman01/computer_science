package item.potion;

import java.util.Random;

import item.Potion;
import character.Character;
import item.potion.HealthPotion;
import item.potion.DamagePotion;

/**
 * A Potion item that has a random effect of any potion in the game
*/
public class UnknownPotion extends Potion {
	private final int totalPotionAmount;
	private Random rand;
	
	/**
	 * Creates an Unknown potion
	 * @param rand A random class object that was created earlier
	*/
	public UnknownPotion(Random rand) {
		super("Potion...?");
		totalPotionAmount = 1;
		this.rand = rand;
	}
	
	@Override
	public void doEffect(Character user) {
		int randChosen = rand.nextInt(totalPotionAmount);
		switch (randChosen) {
		case 1: // Health potion
			HealthPotion hp = new HealthPotion();
			hp.doEffect(user);
			break;
		case 2:
			DamagePotion dp = new DamagePotion();
			dp.doEffect(user);
			break;
		}
	}
}