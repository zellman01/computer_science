package item;

import item.Potion;
import character.Character;

/**
 * A Potion item that has a random effect of any potion in the game
*/
public class UnknownPotion extends Potion {
	private final int totalPotionAmount;
	
	public UnknownPotion() {
		super("Potion...?");
		totalPotionAmount = 1;
	}
	
	@Override
	public void doEffect(Character user) {
	}
}