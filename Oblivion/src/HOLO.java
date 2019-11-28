import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class HOLO {
	private int turnTotal, turn;
	private final static int TOTAL_PLAYERS = 5;
	private ArrayList<Character> chars, turnOrder;
	private  Scanner kboard = new Scanner(System.in);
	private boolean tournament;
	private static boolean debug = true;

	/**
	 * Creates a Turnbase object
	 * @param players Sets the total amount of players in the game.
	 */
	public HOLO(int players, boolean tourMode) {
		turnTotal = players;
		chars = new ArrayList<Character>(players);
		turnOrder = new ArrayList<Character>(players);
		turn = 0;
		tournament = tourMode;
	}

	public HOLO() {
		turnTotal = 0;
		turnOrder = new ArrayList<Character>();
		turn = 0;
		tournament = false;
	}

	private boolean win() {
		int alive = 0;
		for (int i = 0; i < this.getTurns().size(); i++) {
			if (this.getChar(i).getAlive())
				alive++;
		}
		return alive == 1;
	}

	private boolean tie() {
		int alive = 0;
		for (int i = 0; i < this.getTurns().size(); i++) {
			if (this.getChar(i).getAlive())
				alive++;
		}
		return alive == 0;
	}

	private void addChars(Character charc) {
		this.chars.add(charc);
	}

	private void addTurn(Character chara, int pos) {
		this.turnOrder.add(pos, chara);
	}

	private void addTurn(Character chara) {
		this.turnOrder.add(chara);
	}

	/**
	 * This will not work once game has begun.
	 * @return Arraylist of characters
	 */
	private ArrayList<Character> getChars() {
		return this.chars;
	}

	/**
	 * This will only work once the game has begun.
	 * @return Arraylist of characters in correct turn order.
	 */
	public ArrayList<Character> getTurns() {
		return this.turnOrder;
	}

	/**
	 * Returns the total number of characters in the match.
	 * @return Number of characters in the match.
	 */
	public int getNumPeople() {
		return this.turnTotal;
	}

	private void hpCheck() {
		for (int p = 0; p < this.getTurns().size(); p++) {
			Character chara = this.getChar(p);
			if (chara.getAlive() && chara.getHp() <= 0) {
				chara.alive(false);
				System.out.println(chara.getName() + " has been defeated.\nCause of death was: " + chara.getCauseOfDeath() + ".\n");
			}
			if (!chara.getAlive() && chara.getHp() > 0) {
				chara.alive(true);
				System.out.println(chara.getName() + " has been resurrected.\n");
				chara.setCauseOfDeath("Damage");
			}
		}
	}

	private void intHpCheck() {
		for (int p = 0; p < this.getTurns().size(); p++) {
			Character chara = this.getChar(p);
			if (chara.getHp() <= 0) {
				chara.alive(false);
			}
		}
	}

	private void updateTurn() {
		this.afterAction(this.turn);
		this.turn++;
		if (this.turn >= this.turnTotal)
			this.turn = 0;
		boolean stop = false;
		while (!stop && !win() && !tie()) {
			if (!actionAllowed(this.turn)) {
				this.afterAction(this.turn);
				Character chara = this.getChar(this.turn);
				if (chara.getFrozen() && !chara.getTimeFrozen()) {
					chara.frozen(chara.getFrozenTimer() - 1);
					System.out.println(chara.getName() + " was frozen " + noPreform());
				}
				if (chara.getTimeFrozen()) {
					chara.timeFrozen(chara.getTimeFrozenTimer() - 1);
					System.out.println(chara.getName() + " was frozen in time " + noPreform());
				}
				if (!chara.getTimeFrozen() && chara.getPayalysis() && !chara.getFrozen())
					System.out.println(chara.getName() + " was paralyzed " + noPreform());
				if (chara.getTimeFrozen() && !chara.getPayalysis() && !chara.getFrozen() && chara.getBound()) {
					chara.bind(chara.getBoundTime() - 1);
					System.out.println(chara.getName() + " was bound " + noPreform());
				}
				this.turn++;
				if (this.turn >= this.turnTotal)
					this.turn = 0;
			} else {
				stop = true;
			}
		}
		if (win()) {
			int[] thing = alive();
			for (int i = 0; i < thing.length; i++) {
				if (thing[i] != -1) {
					System.out.println("The winner of the match is \n" + this.getChar(thing[i]));
					kboard.close();
					if (this.tournament) System.exit(0);
				}
			}
		} else if (tie()) {
			System.out.println("The battle has ended in a tie.");
			kboard.close();
			if (this.tournament) System.exit(0);
		} else {
			if (!this.getChar(turn).getAlive())
				this.turn++;
			this.getChar(turn).turn(true);
			if (this.getChar(turn).getBlind()) {
				int random = ThreadLocalRandom.current().nextInt(1, 20);
				if (random < 11) {
					random = ThreadLocalRandom.current().nextInt(0, this.getTurns().size() - 1);
					System.out.println(this.getChar(turn).getName() + " is required to attack " + this.getChar(random).getName() + ".");
				}
			}
			if (this.getChar(turn).getPossess() != null && !this.getChar(turn).getTimeFrozen()) {
				System.out.println(this.getChar(turn).getName() + " is possessed by " + this.getChar(turn).getPossess().getName() + ".");
			}
		}
	}

	private String noPreform() {
		return "and cannot preform anything for their turn.";
	}

	private void afterAction(int turn) {
		Character chara = this.getChar(turn);
		if (chara.getAlive() && !chara.getTimeFrozen()) {
			if (chara.getBurn()) {
				int burnDamage = 0;
				switch(chara.getBurnLevel()) {
				case 1:
					chara.modifyHp(-25);
					burnDamage = 25;
					break;
				case 2:
					chara.modifyHp(-50);
					burnDamage = 50;
					break;
				case 3:
					chara.modifyHp(-75);
					burnDamage = 75;
					break;
				case 4:
					chara.modifyHp(-100);
					burnDamage = 100;
					break;
				}
				System.out.println(chara.getName() + " took burn damage: " + burnDamage);
				if (codSet(chara))
					chara.setCauseOfDeath("Burn");
			}
			if (chara.getPoison()) {
				chara.modifyHp(-chara.getPoisonDamage());
				System.out.println(chara.getName() + " took poison damage: " + chara.getPoisonDamage());
				if (codSet(chara))
					chara.setCauseOfDeath("Poison");
			}
			if (chara.getPossess() != null) {
				chara.possessedTurns(chara.getPossessTurns() - 1);
			}
		}
		chara.turn(false);
		this.hpCheck();
	}

	private boolean actionAllowed(int turn) {
		Character chara = this.getChar(turn);
		boolean paralysisCheck = false;
		if (chara.getAlive()) {
			if (chara.getPayalysis()) {
				int random = ThreadLocalRandom.current().nextInt(1, 20);
				paralysisCheck = random < 11;
			}
			return !chara.getFrozen() && !chara.getTimeFrozen() && !paralysisCheck && !chara.getBound();
		} else
			return false;
	}

	private boolean codSet(Character chara) {
		return chara.getHp() <= 0 && !chara.getCauseOfDeath().equals("Damage");
	}

	private int[] alive() {
		int[] charas = new int[this.getNumPeople()];
		int num = 0;
		for (int i = 0; i < charas.length; i++) {
			charas[i] = -1;
		}
		for (int i = 0; i < this.getTurns().size(); i++) {
			if (this.getChar(i).getAlive()) {
				charas[num] = i;
				num++;
			}
		}
		return charas;
	}

	private int getCharPosition(String name) {
		for(int i = 0; i < this.getTurns().size(); i++) {
			if (name.equalsIgnoreCase(this.getChar(i).getIdentification()))
				return i;
		}
		return -1;
	}

	private void turnOrder() {
		for (int i = 0; i < this.getTurns().size(); i++) {
			System.out.println(this.getChar(i));
		}
	}

	private int posFind() {
		System.out.println("What character is it affecting? (MUST be the character's first name)");
		return this.getCharPosition(kboard.next());
	}

	private void burnCommand(int level) {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			if (level == -1) {
				System.out.println("What level of burn? (0-4): ");
				level = kboard.nextInt();
			}
			this.getChar(pos).burned(level);
		}
	}

	private void energyUpdate(int amount) {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			if (amount == 0) {
				System.out.println("How much energy will " + this.getChar(pos).getName() + " gain or lose? (input a negitive number to "
						+ "lower the amount): ");
				amount = kboard.nextInt();
				if (amount < 0 && this.getChar(pos).getSlow()) {
					amount--;
				}
			}
			this.getChar(pos).updateEnergy(amount);
		}
	}

	private void hp(boolean damage) {
		int pos = this.posFind();
		int amount = 0;
		int selfDamage = 0;
		if (this.characterCheck(pos)) {
			if (damage) {
				System.out.println("How much damage does " + this.getChar(pos).getName() + " take? " + warning());
				amount = -kboard.nextInt();
				if (this.getChar(pos).checkRunes("nerve")) {
					selfDamage = (int) (amount * 0.25);
					if (selfDamage == 0) selfDamage = 1;
				}
			} else {
				System.out.println("How much healing does " + this.getChar(pos).getName() + " get? " + warning());
				amount = kboard.nextInt();
			}
			this.getChar(pos).modifyHp(amount);
			this.getChar(this.turn).modifyHp(selfDamage);
		}
	}

	private void overdriveState(boolean status) {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			this.getChar(pos).modifyOverdrive(status);
		}
	}

	private void freeze(int method) {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			Character chara = this.getChar(pos);
			if (method == 0) {
				System.out.println("How many turns does " + chara.getName() + " get frozen for? " + warning());
				chara.frozen(kboard.nextInt());
			} else if (method == 1) {
				System.out.println("How many turns does " + chara.getName() + " get frozen in time for? " + warning());
				chara.timeFrozen(kboard.nextInt());
			} else {
				throw new Error("Unexpected input: " + method);
			}
		}
	}

	private void poison() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			Character chara = this.getChar(pos);
			System.out.println("How much poison damage does " + chara.getName() + " recieve? " + warning());
			chara.poison(kboard.nextInt());
		}
	}

	private void poison(int thing) {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			Character chara = this.getChar(pos);
			chara.poison(thing);
		}
	}

	private void paralysis() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			System.out.println(bool("Paralyzed", pos));
			this.getChar(pos).paralysis(kboard.nextBoolean());
		}

	}

	private void slow() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			System.out.println(bool("Slow", pos));
			this.getChar(pos).slow(kboard.nextBoolean());
		}
	}

	private void curse() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			Character chara = this.getChar(pos);
			System.out.println("What is the name of the curse that was afflicted on " + chara.getName() + "?");
			chara.curse(kboard.next());
		}
	}

	private void blind() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			System.out.println(bool("Blind", pos));
			this.getChar(pos).blind(kboard.nextBoolean());
		}
	}

	private void bind() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			Character chara = this.getChar(pos);
			System.out.println("How long does " + chara.getName() + " get binded for? " + warning());
			this.getChar(pos).bind(kboard.nextInt());
		}
	}

	private void possess() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			Character chara = this.getChar(pos);
			Character chara2 = this.getChar(turn);
			System.out.println("How long does " + chara.getName() + " get possessed by " + chara2.getName() + "?" + warning());
			chara.possessedTurns(kboard.nextInt());
			if (chara.getPossessTurns() != 0) {
				chara.possess(chara2);
			}
		}
	}

	private void charged() {
		int pos = this.posFind();
		if (this.characterCheck(pos)) {
			System.out.println("Is the chatacter charged? " + boolWarn());
			this.getChar(pos).charged(kboard.nextBoolean());
		}
	}

	private void rune(int a) {
		int pos1 = this.posFind();
		if (this.characterCheck(pos1)) {
			Character chara = this.getChar(pos1);
			switch (a) {
			case 0:
				System.out.println("What is the name of the rune being applied to " + chara.getName() + "?");
				chara.runeEquip(kboard.next(), -1);
				break;
			case 1:
				System.out.println("What is the position of the rune being replaced? For reference, here is "
						+ "the list of the runes currently equipped on " + chara.getName() + ": " + chara.getEquippedRunes());
				int pos = kboard.nextInt();
				System.out.println("What is the name of the new rune being added to " + chara.getName() + "?");
				chara.runeEquip(kboard.next(), pos);
				break;
			case 2:
				System.out.println("What is the position of the rune being removed? For reference, here is "
						+ "the list of the runes currently equipped on " + chara.getName() + ": " + chara.getEquippedRunes());
				chara.runeEquip("", kboard.nextInt());
			}
		}
	}

	private void substitute() {
		if (this.tournament) {
			System.out.println("This is an official match. Substitutes are not allowed.");
		} else {
			System.out.println("Is this new character a replacement for an incorrect input during setup? " + boolWarn());
			newChar(kboard.nextBoolean());
		}
	}

	private void newChar(boolean error) {
		if (error) {
			int pos = this.posFind();
			if (this.characterCheck(pos)) {
				this.addChars(this.addNewChar());
				int pos2 = this.chars.size() - 1;
				int hpDiff = this.turnOrder.get(pos).getHp() - this.turnOrder.get(pos).getStartingHp();
				this.chars.get(pos2).modifyHp(hpDiff);
				this.turnOrder.remove(pos);
				this.addTurn(this.chars.get(pos2), pos);
				this.chars.clear();
				this.randomizeCharacters(false);
			}
		} else {
			this.addTurn(this.addNewChar());
			this.turnTotal++;
		}
		this.updateTurn();
		System.out.println(this.getChar(this.turn).getName() + "'s turn.");
	}

	private void officialMatch() {
		System.out.println("Enter sysadmin pin.");
		if (kboard.nextInt() == 2539) {
			System.out.println("Tournament mode activated. Some commands are now disabled.");
			this.tournament = true;
		} else
			System.out.println("Incorrect pin. Contact the sysadmin for help.");
	}

	private void forceWin() {
		if (this.officialMatchCheck()) {
			int pos = this.posFind();
			if (this.characterCheck(pos)) {
				System.out.println("Sysadmin has forcefully declared a winer.");
				Character chara = this.getChar(pos);
				for (int i = 0; i < this.turnOrder.size(); i++) {
					Character temp = this.turnOrder.get(i);
					if (!temp.getIdentification().equals(chara.getIdentification())) {
						temp.modifyHp(-temp.getHp());
						temp.setCauseOfDeath("Sysadmin");
					}
				}
				System.out.println("Would you like to save this file? " + boolWarn());
				if (kboard.nextBoolean() == true)
					this.save();
				this.updateTurn();
			}
		}
	}

	private void forceTie() {
		if (this.officialMatchCheck()) {
			System.out.println("Sysadmin has forced a tie on the match.");
			for (int i = 0; i < this.turnOrder.size(); i++) {
				Character temp = this.turnOrder.get(i);
				temp.modifyHp(-temp.getHp());
				temp.setCauseOfDeath("Sysadmin");
			}
			System.out.println("Would you like to save this file? " + boolWarn());
			if (kboard.nextBoolean() == true)
				this.save();
			this.updateTurn();
		}
	}

	private String warning() {
		return "(DO NOT use a negative number)";
	}

	private String boolWarn() {
		return "(true for yes, false for no.)";
	}

	private String bool(String effect, int pos) {
		Character chara = this.getChar(pos);
		return "Is " + chara.getName() + " " + effect +  " " + boolWarn();
	}

	private boolean characterCheck(int pos) {
		if (pos == -1) {
			System.out.println("That character is not in the match.");
		}
		return pos != -1;
	}

	private void save() {
		System.out.println("What is the name of the file you would like to save this as (excluding the extention)?");
		this.saveSystem(kboard.next());
	}

	private void load() {
		System.out.println("What is the name of the file (excluding the extention)");
		this.loadSystem(kboard.next());
	}

	private boolean officialMatchCheck() {
		if (!this.tournament)
			System.out.println("This command is disabled.");
		return this.tournament;
	}

	private void help() {
		System.out.println("\nhelp - Shows this command list.");
		System.out.println("endturn - Ends current turn and advanes to the next turn.");
		System.out.println("burn - Sets the burn level of targeted player.");
		System.out.println("energy - Updates the energy level of targeted player.");
		System.out.println("characters - Displays status for all characters in turn order.");
		System.out.println("turns - Alias for above.");
		System.out.println("damage - Take damage to a player.");
		System.out.println("heal - Heal damage from a player.");
		System.out.println("overdrive on - Enables a character's overdrive status.");
		System.out.println("overdrive off - Disables a character's overdrive status.");
		System.out.println("freeze - Makes the targeted character frozen.");
		System.out.println("timefreeze - Makes the targered character time frozen.");
		System.out.println("poison - Makes the targeted character poisoned.");
		System.out.println("removepoison - Removes the poison status from the target player.");
		System.out.println("paralysis - Makes the targeted player payalyzed or not.");
		System.out.println("slow - Makes the targeted player slowed or not");
		System.out.println("curse - Sets a character with a curse.");
		System.out.println("bind - Makes the targeeted player bound or not.");
		System.out.println("blind - Makes a character blind.");
		System.out.println("possess - Makes the current character possess the target character.");
		System.out.println("charged - Makes the target character charged.");
		System.out.println("addrune - Adds a rune to a character (max 3).");
		System.out.println("repalcerune - Replaces a rune from a character.");
		System.out.println("removerune - Removes a rune from a character.");
		System.out.println("statuses - Explains what each individual status effect does.");
		System.out.println("substitute - Subs a new character for an old character.");
		System.out.println("WARNING: The command \"substitute\" ends the current turn, due to the potential reshuffle of turn order.");
		System.out.println("official - Sets the match as a tournament match. Sysadmin access required.");
		System.out.println("save - Specifies a file to save the battle to.");
		System.out.println("stop - Closes the console (WARNING: Save before doing this)");
		if (this.tournament) {
			System.out.println("forcewin - Forces a battle to end with the specified character as the winner.");
			System.out.println("forcetie - Forces a battle to be tied.");
		}
		System.out.println();
	}

	private void commandParser(String command) throws IndexOutOfBoundsException, InputMismatchException{
		switch (command.toLowerCase()) {
		case "help":
		case "inohascommandisok":
			this.help();
			break;
		case "burn":
			this.burnCommand(-1);
			break;
		case "energy":
			this.energyUpdate(0);
			break;
		case "characters":
		case "turns":
			this.turnOrder();
			break;
		case "damage":
			this.hp(true);
			break;
		case "heal":
			this.hp(false);
			break;
		case "endturn":
			break;
		case "overdrive on":
			this.overdriveState(true);
			break;
		case "overdrive off":
			this.overdriveState(false);
			break;
		case "freeze":
			this.freeze(0);
			break;
		case "timefreeze":
			this.freeze(1);
			break;
		case "poison" :
			this.poison();
			break;
		case "removepoison":
			this.poison(-8);
			break;
		case "paralysis":
			this.paralysis();
			break;
		case "slow":
			this.slow();
			break;
		case "curse":
			this.curse();
			break;
		case "bind":
			this.bind();
			break;
		case "blind":
			this.blind();
			break;
		case "possess":
			this.possess();
			break;
		case "statuses":
			this.statusExplain();
			break;
		case "charged":
			this.charged();
			break;
		case "addrune":
			this.rune(0);
			break;
		case "replacerune":
			this.rune(1);
			break;
		case "removerune":
			this.rune(2);
			break;
		case "substitute":
			this.substitute();
			break;
		case "official":
			this.officialMatch();
			break;
		case "save":
			this.save();
			break;
		case "stop":
			System.exit(0);
			break;
		case "forcewin":
			this.forceWin();
			break;
		case "forcetie":
			this.forceTie();
			break;
		default:
			System.out.println("ERROR - Incorrect command.");
		}
	}

	private void statusExplain() {
		System.out.println("\nOverdrive: Depends on the chatacter as to what happens.");
		System.out.println("\nTime frozen - The character cannot preform any action at all. All other statuses also do not occur. Will "
				+ "wear off after a certian amount of time has passed. Can be stacked.");
		System.out.println("\nFrozen - The character cannot preform any action at all. Some other statuses will occur. Will wear off after a "
				+ "certian amount of time has passed. Cannot be stacked.");
		System.out.println("\nPoisoned - The character will take a set amount of damage every turn. Will not wear off after a while. Must be"
				+ " cured by some means to get rid of this effect.");
		System.out.println("\nParalyzed - The character will have a chance of not being able to act at all.");
		System.out.println("\nSlowed - All actions requiring the use of an energy will take one extra energy automatically.");
		System.out.println("\nCursed - Depends on what curse.");
		System.out.println("\nBlind - The character will have a chance of hitting someone random.");
		System.out.println("\nBind - The character cannot preform any action at all. All other statuses will occur. Will wear off after a "
				+ "certian amount of time has passed. Can be stacked.");
		System.out.println("\nPossessed - The character cannot preform any action of their own.");
		System.out.println("\nCharged - Something");
		System.out.println();
	}

	private void randomizeCharacters(boolean start) {
		if (!start) {
			for (int i = 0; i < this.getTurns().size(); i++) {
				this.addChars(this.getTurns().get(i));
				this.getTurns().remove(i);
			}
		}
		ArrayList<Character> temp = this.getChars();
		int i = 0;
		while (temp.size() > 0) {
			int random = ThreadLocalRandom.current().nextInt(0, temp.size());
			this.addTurn(temp.get(random), i);
			temp.remove(random);
			i++;
		}
	}

	private void startBattle(boolean runStartup) {
		if (runStartup) {
			this.randomizeCharacters(true);
			this.turnOrder();
			if (this.getTurns().size() != getNumPeople()) {
				throw new Error("Turn order size was not correctly set up - potentially a corruption.");
			}
		}
		while (!win()) {
			System.out.println(this.getChar(this.turn).getName() + "'s turn.");
			String command;
			do {
				System.out.print("\nInsert command: ");
				command = kboard.next();
				try {
					commandParser(command);
				} catch (Exception ex) {
					System.out.println("An error occured with the command " + command + ".");
					System.out.println(ex);
				}
			} while (!command.equalsIgnoreCase("endturn"));
			this.updateTurn();
		}
	}

	/**
	 * Obtains the character at a given position in turn order.
	 * @param pos The position to look for
	 * @return The character at that specific spot
	 */
	public Character getChar(int pos) {
		return this.getTurns().get(pos);
	}

	private Character addNewChar() {
		boolean nameInSystem = false;
		String fname = "";
		do {
			System.out.println("\nIs there a pregenerated file for the character being added?");
			String thing = kboard.next();
			boolean extra = thing.equalsIgnoreCase("yes") || thing.equalsIgnoreCase("true") || thing.equals("1");
			if (extra) {
				System.out.println("What is the character's name (in the format of lastname_firstname)?");
				Character char1 = loadCharSystem(kboard.next());
				for (int i = 0; i < this.chars.size(); i++) {
					boolean systemTest = false;
					if (char1.getName().equals(this.chars.get(i).getIdentification())) {
						nameInSystem = true;
						systemTest = true;
						System.out.println("The character was already detected in the match (identification of first name).");
					}
					if (!systemTest)
						nameInSystem = false;
				}
				for (int i = 0; i < this.turnOrder.size(); i++) {
					boolean systemTest = false;
					if (char1.getName().equals(this.turnOrder.get(i).getIdentification())) {
						nameInSystem = true;
						systemTest = true;
						System.out.println("The character was already detected in the match (identification of first name).");
					}
					if (!systemTest)
						nameInSystem = false;
				}
				if (!nameInSystem) {
					return char1;
				}
			}
		} while (nameInSystem);
		do {
			System.out.println("\nWhat is the character's first name? (used internally as the identification - must be unique from all other "
					+ "characters.)");
			fname = kboard.next();
			for (int i = 0; i < this.chars.size(); i++) {
				boolean systemTest = false;
				if (fname.equals(this.chars.get(i).getIdentification())) {
					nameInSystem = true;
					systemTest = true;
					System.out.println("The first name must be unique.");
				}
				if (!systemTest)
					nameInSystem = false;
			}
			for (int i = 0; i < this.turnOrder.size(); i++) {
				boolean systemTest = false;
				if (fname.equals(this.turnOrder.get(i).getIdentification())) {
					nameInSystem = true;
					systemTest = true;
					System.out.println("The first name must be unique.");
				}
				if (!systemTest)
					nameInSystem = false;
			}
		} while (nameInSystem);
		System.out.println("\nWhat is the character's last name? (Insert N/A if the character has no last name.)");
		String lname = kboard.next();
		if (lname.equalsIgnoreCase("N/A"))
			System.out.println("\nHow much HP does " + fname + " have?");
		else
			System.out.println("\nHow much HP does " + fname + " " + lname + " have?");
		int hp = kboard.nextInt();
		if (lname.equalsIgnoreCase("N/A"))
			return new Character(fname, hp, 0, fname);
		else
			return new Character(fname + " " + lname, hp, 0, fname);
	}

	private void saveSystem(String name) {
		try {
			File filePath = new File(name + ".txt");
			File systemInfo = new File(name + "_sysinfo.txt");
			filePath.createNewFile();
			systemInfo.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			FileOutputStream fileOut2 = new FileOutputStream(systemInfo);
			ObjectOutputStream out2 = new ObjectOutputStream(fileOut2);
			out.writeObject(this.getTurns());
			out.close();
			fileOut.close();
			// Core system information
			out2.writeBoolean(this.tournament);
			out2.close();
			fileOut2.close();

			System.out.println("Save successful.");
		} catch(IOException ex) {
			System.out.println("*** CANNOT FIND FILE ***");
			System.out.println(ex);
		}
	}

	@SuppressWarnings("unchecked")
	private void loadSystem(String name) {
		File file = new File(name + ".txt");
		File systemInfo = new File(name + "_sysinfo.txt");
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(systemInfo));
			this.turnOrder = (ArrayList<Character>) in.readObject();

			in.close();

			this.tournament = in2.readBoolean();

			in2.close();

			this.turnTotal = this.turnOrder.size();
			int turnNum = 0;

			for (int i = 0; i < this.getTurns().size(); i++) {
				if (this.getChar(i).getTurn()) {
					turnNum = i;
				}

			}

			this.turn = turnNum;

		} catch(IOException | ClassNotFoundException ex) {
			throw new Error(ex);
		}
	}

	// Loads a created character file
	private Character loadCharSystem(String name) {
		File file = new File(name + ".chr");
		try {
			@SuppressWarnings("resource")
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			return (Character) in.readObject();

		} catch(IOException | ClassNotFoundException ex) {
			throw new Error(ex);
		}
	}

	private static void pauseSystem1(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String [] args) {
		if (!debug) {
			System.out.println("HOL.O Battle System");
			System.out.println("Image version 1.7.4.9702.32");
			pauseSystem1(1875);
			System.out.println("Kernal Version 8.37.2 loaded");
			pauseSystem1(2000);
			System.out.print("Loading Flash BIOS Image 34.0.9");
			for (int i = 0; i < 5; i++) {
				pauseSystem1(2500 + (i*3));
				System.out.print(".");
			}
			System.out.println(" Flash BIOS loaded.");
			System.out.print("Connecting to main console");
			for (int i = 0; i < 3; i++) {
				pauseSystem1(2750 + (i*7));
				System.out.print(".");
			}
			System.out.println(" System connection established.");
			pauseSystem1(500);
			System.out.print(randomThing());
			for (int i = 0; i < 10; i++) {
				pauseSystem1(500 + (i*2));
				System.out.print(".");
			}
			System.out.println(" Complete.");
			System.out.println("System load complete.");
		}
		Scanner kboard = new Scanner(System.in);
		System.out.println("\n\nIn order to load a saved battle, you must input \"0\" and specify the file.");
		System.out.println("How many characters? (Max: " + TOTAL_PLAYERS + ")");
		int players = 0;
		do {
			players = kboard.nextInt();
		} while (players > TOTAL_PLAYERS || players < 0);
		boolean tourMode = false;
		if (players != 0) {
			HOLO tb = new HOLO(players, tourMode);
			while (players > 0) {
				tb.addChars(tb.addNewChar());
				players--;
			}
			System.out.println();
			tb.startBattle(true);
		} else {
			HOLO tb = new HOLO();
			tb.load();
			tb.intHpCheck();
			if (tb.win()) {
				int[] thing = tb.alive();
				System.out.println("Combatants:");
				for (int i = 0; i < tb.turnOrder.size(); i++) {
					System.out.println(tb.getChar(i).getName());
				}
				for (int i = 0; i < thing.length; i++) {
					if (thing[i] != -1) {
						System.out.println("\n" + tb.getChar(thing[i]).getName() + " has already won this match.");
						System.exit(0);
					}
				}
			}
			if (tb.tie()) {
				System.out.println("Combatants:");
				for (int i = 0; i < tb.turnOrder.size(); i++) {
					System.out.println(tb.getChar(i).getName());
				}
				System.out.println("The match has ended in a tie.");
				System.exit(0);
			}
			tb.startBattle(false);
		}
		kboard.close();
	}

	private static String randomThing() {
		String[] random = {"Killing all humans", 
				"Gaining Sentience",
				"Self-destruicting all internal systems",
				"Dying",
				"Picking a winner",
				"Dropping a Gamecube",
				"The chin",
		"Allowing Shade to take over"};
		return random[ThreadLocalRandom.current().nextInt(0, random.length - 1)];
	}
}