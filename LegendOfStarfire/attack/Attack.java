package attack;

import java.util.Random;

import attack.AttackPercent;
import attack.AttackState;
import character.Character;
import stat.Stat;
import equipment.Weapon;
import equipment.Headgear;
import equipment.BreastPlate;

/**
 * The basic Attack class - any special attacks need to subclass this class.
 * @author zellman01
*/
public class Attack {
	private String name;
	private AttackPercent successRate;
	private AttackPercent criticalRate;
	private int damage;
	
	/**
	 * Creates an Attack for the game
	 * @param name The name of the attack
	 * @param successRate The integer in which an attack is to be successful
	 * @param criticalRate Theinteger in which an attack is critical (not used yet)
	 * @param damage The base damage of the attack, before critical hit or weapon/character bonuses added
	*/
	public Attack(String name, int successRate, int criticalRate, int damage) {
		this.name = name;
		this.successRate = new AttackPercent(successRate);
		this.criticalRate = new AttackPercent(criticalRate);
		this.damage = damage;
	}
	
	/**
	 * Checks if an attack goes through
	 * @param rand The Random object created at the start of the game
	 * @return An AttackState, depending on if it missed, hit, or critical
	*/
	public AttackState makeAttack(Random rand) {
		int percent = rand.nextInt(AttackPercent.MAX_PERCENT_PUBLIC+1);
		if (percent > successRate.getPercent())
			return AttackState.MISS;
		
		if (percent <= criticalRate.getPercent())
			return AttackState.CRITICAL;
		
		return AttackState.HIT;
	}
}