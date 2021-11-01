package attack;

import attack.AttackPercent;
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
	 * Makes an attack against a character object
	 * @param defender The character object being attacked
	 * @param attacker The character object doing the attack
	*/
	public void makeAttack(Character defender, Character attacker) {
		Stat defense = defender.getDefenseStat();
		Stat attack = attacker.getAttackStat();
		
		int totalDamage = damage+attack.getStatAmount()+attacker.getWeapon().orElse(new Weapon("", 0)).getAmountModified();
		totalDamage -= defense.getStatAmount()+defender.getHeadgear().orElse(new Headgear("", 0)).getAmountModified()+defender.getBreastPlate().orElse(new BreastPlate("", 0)).getAmountModified();
		
		if (totalDamage < 0) totalDamage = 1; // Healing from being attacked is not allowed; will always take at least 1 point of damage.
		
		defender.tookDamage(totalDamage);
	}
}