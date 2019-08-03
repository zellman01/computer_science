package pokemon;

public class Types {
	private final String[] typeName = {"Normal", "Fire", "Water", "Grass", "Electric", "Ground", "Rock", "Flying", "Ice", "Poison", "Steel", "Dragon", "Fairy", "Psychic", "Dark", "Ghost", "Fighting", "Bug"};
	private String type1, type2;
	
	public Types() {
		type1 = "NS";
		type2 = "NS";
	}
	
	public Types(String type1a, String type2a) {
		type1 = "NS";
		type2 = "NS";
		for (String test : typeName) {
			if (test.equals(type1a)) {
				type1 = type1a;
			}
		}
		for (String test : typeName) {
			if (test.equals(type2a)) {
				type2 = type2a;
			}
		}
	}
	
	public Types(String type1a) {
		type1 = "NS";
		type2 = "NS";
		for (String test : typeName) {
			if (test.equals(type1a)) {
				type1 = type1a;
			}
		}
		System.out.println(type2);
	}
		
	public void setType1(String type) {
		this.type1 = "NS";
		for (String test : typeName) {
			if (test.equals(type)) {
				this.type1 = type;
			}
		}
	}
	
	public void setType2(String type) {
		this.type2 = "NS";
		for (String test : typeName) {
			if (test.equals(type)) {
				this.type2 = type;
			}
		}
	}
	
	public String obtainType1() {
		return this.type1;
	}
	
	public String obtainType2() {
		return this.type2;
	}
	
	public boolean validateType(String type1) {
		return !type1.equals("NS");
	}
}
