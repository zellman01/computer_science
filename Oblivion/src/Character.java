import java.io.Serializable;

// File Extension: .chr
public class Character implements Serializable {
	private static final long serialVersionUID = 1L;
	private String charName, cod, curse, id;
	private int charHp, charEnergy, burnLevel, timeFrozenTimer, frozenTimer, poisonDamage, boundTimer, possessTurns, defHp;
	private boolean burn, alive, overdrive, timeFrozen, frozen, poison, paralysis, slow, turn, blind, bound, charged;
	private Character possess;
	private Rune equippedRunes;

	public Character(String name, int hp, int energy, boolean burned, int burnedLevel, boolean isAlive, boolean overDrive, boolean tf, 
			int tft, boolean freeze, int freezeTimer, String causeOD, boolean poisoned, int poisonDamaged, boolean para, boolean slowed,
			boolean turnStatus, String cursed, boolean blinded, boolean binded, int boundedTime, Character possession, int possessedTurns,
			int defHP, String iden, boolean charg, Rune rune) {
		charName = name;
		charHp = hp;
		charEnergy = energy;
		burn = burned;
		burnLevel = burnedLevel;
		alive = isAlive;
		overdrive = overDrive;
		timeFrozen = tf;
		timeFrozenTimer = tft;
		frozen = freeze;
		frozenTimer = freezeTimer;
		cod = causeOD;
		poison = poisoned;
		poisonDamage = poisonDamaged;
		paralysis = para;
		slow = slowed;
		turn = turnStatus;
		curse = cursed;
		blind = blinded;
		bound = binded;
		boundTimer = boundedTime;
		possess = possession;
		possessTurns = possessedTurns;
		defHp = defHP;
		id = iden;
		charged = charg;
		equippedRunes = rune;
	}

	/**
	 * Constructing a character
	 * @param name Character's name
	 * @param hp Starting HP of the character
	 * @param energy Starting energy of the character
	 * @param iden Identification of the character
	 */
	public Character(String name, int hp, int energy, String iden) {
		charName = name;
		charHp = hp;
		charEnergy = energy;
		burn = false;
		burnLevel = 0;
		alive = true;
		overdrive = false;
		timeFrozen = false;
		timeFrozenTimer = 0;
		frozen = false;
		frozenTimer = 0;
		cod = "Damage";
		poison = false;
		poisonDamage = 0;
		paralysis = false;
		slow = false;
		turn = false;
		curse = "";
		blind = false;
		bound = false;
		boundTimer = 0;
		possess = null;
		possessTurns = 0;
		defHp = hp;
		id = iden;
		charged = false;
		equippedRunes = new Rune();
	}

	public int getHp() { return this.charHp; }
	public String getName() { return this.charName; }
	public boolean getBurn() { return this.burn; }
	public int getEnergy() { return this.charEnergy; }
	public int getBurnLevel() { return this.burnLevel; }
	public boolean getAlive() { return this.alive; }
	public boolean getOverdrive() { return this.overdrive; }
	public boolean getTimeFrozen() { return this.timeFrozen; }
	public int getTimeFrozenTimer() { return this.timeFrozenTimer; }
	public boolean getFrozen() { return this.frozen; }
	public int getFrozenTimer() { return this.frozenTimer; }
	public String getCauseOfDeath() { return this.cod; }
	public boolean getPoison() { return this.poison; }
	public int getPoisonDamage() { return this.poisonDamage; }
	public boolean getPayalysis() { return this.paralysis; }
	public boolean getSlow() { return this.slow; }
	public boolean getTurn() { return this.turn; }
	public String getCurse() { return this.curse; }
	public boolean getBlind() { return this.blind; }
	public boolean getBound() { return this.bound; }
	public int getBoundTime() { return this.boundTimer; }
	public Character getPossess() { return this.possess; }
	public int getPossessTurns() { return this.possessTurns; }
	public int getStartingHp() { return this.defHp; }
	public String getIdentification() { return this.id; }
	public boolean getCharged() { return this.charged; }
	public Rune getEquippedRunes() { return this.equippedRunes; }

	public String toString() {
		String str = "Name: " + this.getName() + "\nHP: " + this.getHp() + "\nEnergy: " + this.getEnergy() + "\nAlive?: " + this.getAlive();
		if (this.getAlive()) {
			if (this.getBurn()) {
				str += "\nBurned?: " + this.getBurn();
				str += "\nBurn level: " + this.getBurnLevel();
			}
			str += "\nOverdrive?: " + this.getOverdrive();
			if (this.getTimeFrozen()) {
				str += "\nTime frozen?: " + this.getTimeFrozen();
				str += "\nTime frozen time: " + this.getTimeFrozenTimer();
			}
			if (this.getFrozen()) {
				str += "\nFrozen?: " + this.getFrozen();
				str += "\nFrozen time: " + this.getFrozenTimer();
			}
			if (this.getPoison()) {
				str += "\nPoisoned?: " + this.getPoison();
				str += "\nPoison damage: " + this.getPoisonDamage();
			}
			if (this.getPayalysis())
				str += "\nParalyzed?: " + this.getPayalysis();
			if (this.getSlow())
				str += "\nSlowed?: " + this.getSlow();
			if (!this.getCurse().equals(""))
				str += "\nCurse: " + this.getCurse();
			if (this.getBlind())
				str += "\nBlinded?: " + this.getBlind();
			if (this.getBound()) {
				str += "\nBound?: " + this.getBound();
				str += "\nBounded time: " + this.getBoundTime();
			}
			if (this.getPossess() != null) {
				str += "\nPossessed by: " + this.getPossess().getName();
				str += "\nPossessed turns: " + this.getPossessTurns();
			}
			if (this.getCharged())
				str += "\nCharged?: " + this.getCharged();
			str += "\nRunes: " + this.getEquippedRunes();
		} else {
			str += "\nCause of death: " + this.getCauseOfDeath();
		}
		str += ".\n";
		return str;
	}

	/**
	 * Sets the character's cause of death for when they die.
	 * @param causeOfDeath The character's cause of death.
	 */
	public void setCauseOfDeath(String causeOfDeath) {
		this.cod = causeOfDeath;
	}


	/**
	 * Specifies if the character's burn status is updated at all
	 * @param level What level burn it is.
	 * Precondition: level is between 0 and 4
	 */
	public void burned(int level) {
		if (level == 0) {
			this.burn = false;
			this.burnLevel = 0;
		} else if (level > 0) {
			this.burn = true;
			this.burnLevel = level;
		} else {
			System.out.println("The level of burn must be between 0 and 4.");
		}
	}

	/**
	 * Changes the character's HP
	 * @param hp the amount to adjust in the character's HP.
	 * Positive: Raises HP by that much
	 * Negative: Lowers HP by that much
	 */
	public void modifyHp(int hp) {
		this.charHp += hp;
	}

	/**
	 * Changes character's overdrive status
	 * @param status The status to change it to.
	 */
	public void modifyOverdrive(boolean status) {
		this.overdrive = status;
	}

	/**
	 * Changes the target character's energy
	 * @param energy The amount of energy to modify. Can be positive or negative.
	 */
	public void updateEnergy(int energy) {
		int before = this.charEnergy;
		this.charEnergy += energy;
		if (charEnergy < 0) {
			System.out.println(this.getName() +  " does not have enough Energy.");
			this.charEnergy = before;
		}
		if (charEnergy > 5 && !this.getOverdrive()) {
			System.out.println(this.getName() +  " will have too much Energy.");
			this.charEnergy = before;
		}
		if (charEnergy > 10 && this.getOverdrive()) {
			System.out.println(this.getName() + " will have too much energy (overdrive)");
			this.charEnergy = before;
		}
	}

	/**
	 * Marks a character's alive status
	 * @param status If the character is alive or not.
	 */
	public void alive(boolean status) {
		this.alive = status;
	}

	/**
	 * Marks the character time frozen.
	 * @param turns The amount of time the character is to be frozen in time.
	 */
	public void timeFrozen(int turns) {
		if (turns < 0) turns = 0;
		if (turns == 0) {
			this.timeFrozen = false;
		} else if (turns > 0) {
			this.timeFrozen = true;
		}

		this.timeFrozenTimer = turns;
	}

	/**
	 * Marks the character frozen
	 * @param turns The amount of time the character is to be frozen.
	 */
	public void frozen(int turns) {
		if (turns < 0) turns = 0;
		if (turns == 0) {
			this.frozen = false;
		} else if (turns > 0) {
			this.frozen = true;
		}

		this.frozenTimer = turns;
	}

	/**
	 * Sets the character to be poisoned.
	 * @param damage The amount of damage the character takes every turn.
	 */
	public void poison(int damage) {
		if (damage > 0) {
			this.poison = true;
			this.poisonDamage = damage;
		} else {
			this.poison = false;
			this.poisonDamage = 0;
		}
	}

	/**
	 * Sets the character's paralysis state
	 * @param para If the character is to be paralyzed or not.
	 */
	public void paralysis(boolean para) {
		this.paralysis = para;
	}

	/**
	 * Sets the character's slow state
	 * @param slowed If the character is to be slowed or not.
	 */
	public void slow(boolean slowed) {
		this.slow = slowed;
	}

	/**
	 * Marks if it is the character's turn
	 * @param turnStatus If it is the character's turn or not.
	 */
	public void turn(boolean turnStatus) {
		this.turn = turnStatus;
	}

	/**
	 * Puts a curse on the target player
	 * @param cursed The name of the curse
	 */
	public void curse(String cursed) {
		this.curse = cursed;
	}

	/**
	 * Marks if a character is blinded or not
	 * @param blinded If the character is to be blinded
	 */
	public void blind(boolean blinded) {
		this.blind = blinded;
	}

	public void bind(int turns) {
		if (turns < 0) turns = 0;
		if (turns == 0) {
			this.bound = false;
		} else {
			this.bound = true;
		}

		this.boundTimer = turns;
	}

	public void possess(Character chara) {
		this.possess = chara;
	}

	public void possessedTurns(int turns) {
		if (turns <= 0) {
			this.possess = null;
			turns = 0;
		}

		this.possessTurns = turns;
	}

	public void charged (boolean thing) {
		this.charged = thing;
	}

	public void runeEquip(String name, int pos) {
		this.equippedRunes.addRune(name, pos);
	}

	public boolean checkRunes(String runeName) {
		return this.equippedRunes.runeCheck(runeName);
	}
}