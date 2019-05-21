package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 1/9/2019
 * DateCompleted: 1/9/2019
 *
 */
public class IndexOfThing {
	String obj;
	public IndexOfThing(String obj1) {
		obj = obj1;
	}
	public int indexOf(char ch, int fromPos) {
		String[] test = this.getString().split("");
		String ch1 = Character.toString(ch);
		for (int i = fromPos; i < test.length; i++) {
			if (test[i].equals(ch1)) {
				return i;
			}
		}
		return -1;
	}
	public String getString() {
		return this.obj;
	}
	public static void main(String[] args) {
		IndexOfThing iot = new IndexOfThing("Hi-ho, hi-ho");
		int psn = iot.indexOf('h', 4);
		System.out.println(psn);
	}
}
