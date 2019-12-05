import java.io.Serializable;

// File extension: .spa
public class Specials implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int damage;
	private String userName;
	

	/**
	 * 
	 * @param namea The name of the special
	 * @param amount The amount of damage/healing done
	 * @param charName The character that can use it's name
	 */
	public Specials(String namea, int amount, String charName) {
		this.name = namea;
		this.damage = amount;
		this.userName = charName;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUser() {
		return this.userName;
	}
	
	public boolean useableSpecial(String nameTest) {
		return this.userName.equals(nameTest);
	}
}
