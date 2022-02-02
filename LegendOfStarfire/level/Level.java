package level;

import level.ExperiencePoints;

/**
 * General Level class
*/
public class Level {
	private ExperiencePoints exp, nextRequired;
	private int levelNumber;
	private final int FIRSTLEVELUP = 25, MAXLEVEL = 100;
	
	public Level(int exp) {
		this.exp = new ExperiencePoints(exp);
		levelNumber = 1;
		nextRequired = new ExperiencePoints(FIRSTLEVELUP);
	}
	
	public Level() {
		exp = new ExperiencePoints(0);
		levelNumber = 1;
		nextRequired = new ExperiencePoints(FIRSTLEVELUP);
	}
	
	private void calculateNextLevel() {
		nextRequired.setAmount((int)(nextRequired.getAmount()*1.5));
	}
	
	public boolean levelUp() {
		if (exp.getAmount()-nextRequired.getAmount() > 0 && levelNumber < MAXLEVEL) {
			exp.raiseEXP(-nextRequired.getAmount());
			calculateNextLevel();
			return true;
		}
		return false;
	}
}