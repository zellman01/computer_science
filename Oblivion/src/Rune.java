import java.io.Serializable;

public class Rune implements Serializable {
	private static final long serialVersionUID = 1L;
	private Rune[] runes;
	private String name;
	private int count;
	public Rune() {
		runes = new Rune[3];
		for (int i = 0; i < runes.length; i++) {
			runes[i] = new Rune("");
		}
		count = 0;
	}

	public Rune(String n) {
		this.name = n;
	}

	public String toString() {
		boolean test = false;
		String str = "";
		for (int i = 0; i < runes.length; i++) {
			if (!runes[i].getName().equals("")) {
				str += runes[i].getName().substring(0, 1).toUpperCase();
				str += runes[i].getName().substring(1).toLowerCase();
				test = true;
				if (i < count - 1)
					str += ", ";
			}
		}
		if (!test)
			str += "No runes are equipped to this character";
		return str;
	}

	private String getName() {
		return this.name;
	}

	/**
	 * Adds a rune to the character's array of runes
	 * @param name The name of the rune
	 * @param pos The position (if replacing or removing; otherwise -1)
	 */
	public void addRune(String name, int pos) {
		if (pos == -1) {
			boolean stop = false;
			for (int i = 0; i < runes.length; i++) {
				if (!stop)
					if (runes[i].getName().equals("")) {
						pos = i;
						stop = true;
					}
			}
			if (stop) this.count++;
		}
		System.out.println(pos);
		if (pos != -1) {
			runes[pos] = new Rune(name);
			if (runes[1].getName().equals("") && !runes[2].getName().equals("")) {
				runes[1] = runes[2];
				runes[2] = new Rune();
			}
		}
	}
	
	/**
	 * Checks a character's rune list for the specified rune.
	 * @param runeName The name of the rune to check for.
	 * @return If the rune being checked for is present.
	 */
	public boolean runeCheck(String runeName) {
		for (int i = 0; i < runes.length; i++) {
			if (!runes[i].getName().equals("")) {
				if (runes[i].getName().equalsIgnoreCase(runeName))
					return true;
			}
				//str += runes[i].getName();
		}
		return false;
	}
}
