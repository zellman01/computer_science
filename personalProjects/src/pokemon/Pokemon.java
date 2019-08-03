package pokemon;

/**
 * 
 * @author Zach Wellman
 */
public class Pokemon {
	private String name1, type11, type21, nature;
	private Name Name = new Name();
	private Types Type = new Types();
	private Nature Nature = new Nature();
	/**
	 * Creates the object. This alone will not build the object fully.
	 * @param name The name of the pokemon (Can be anything)
	 * @param type1 The first type
	 * @param type2 The second type
	 */
	public Pokemon(String name, String type1, String type2, String natureN) {
		name1 = name;
		type11 = type1;
		type21 = type2;
		nature = natureN;
	}
	
	/**
	 * Creates the object. This alone will not build the object fully.
	 * @param name The name of the pokemon (Can be anything)
	 * @param type1 The first type
	 */
	public Pokemon(String name, String type1, String natureN) {
		name1 = name;
		type11 = type1;
		type21 = "NS";
		nature = natureN;
	}
	
	public String toString() {
		if (this.obtainType2().equalsIgnoreCase("ns")) {
			return "Pokemon: " + this.obtainName() + ". Type: " + this.obtainType1() + ".";
		}
		return "Pokemon: " + this.obtainName() + ". Type: " + this.obtainType1() + " " + this.obtainType2() + ".";
	}
	
	/**
	 * Fully builds the object. MUST be used after constructing the object.
	 */
	public void buildPokemon() {
		this.Name.setName(name1);
		this.Type.setType1(type11);
		this.Type.setType2(type21);
		this.Nature.setNature(nature);
	}
	
	/**
	 * @return The name of the pokemon
	 */
	public String obtainName() {
		return this.Name.obtainName();
	}
	
	/**
	 * @return The first type
	 */
	public String obtainType1() {
		return this.Type.obtainType1();
	}
	
	/**
	 * @return The second type
	 */
	public String obtainType2() {
		return this.Type.obtainType2();
	}
	
	public String obtainNature() {
		return this.Nature.obtainNature();
	}
	
	/**
	 * @param moveType The type of the move being used
	 * @return How effective it is
	 */
	public double effective(String moveType) {
		Resistance Resistance = new Resistance(this.Type.obtainType1(), this.Type.obtainType2());
		return Resistance.effective(moveType);
	}
	
	/**
	 * Sets the name of the pokemon to something else
	 * @param name The new name of the pokemon
	 */
	public void setName(String name) {
		this.Name.setName(name);
	}
	
	/**
	 * Sets the first type of the pokemon to something else
	 * @param type The new type
	 */
	public void setType1(String type) {
		this.Type.setType1(type);
	}
	
	/**
	 * Sets the second type of the pokoemon to something else
	 * @param type The new type
	 */
	public void setType2(String type) {
		this.Type.setType2(type);
	}
}
