package game.nameHere.lowestlevel;
import game.nameHere.lowestlevel.Name;

/**
 * Low level enum for types
 * @author zellman01
 * @since 0.1.0
 * @version 0.1.0
 */
public enum Type {;
	private Name typeName;
	
	private Type(String name) {
		typeName = new Name(name);
	}
	
	public String toString() {
		return typeName.toString();
	}
}
