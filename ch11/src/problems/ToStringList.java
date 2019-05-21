package problems;

import java.util.ArrayList;

/**
 * 
 * @author Zach Wellman
 * Date Started: 5/1/19
 * Date Completed: 5/1/19
 * Problem 9
 *
 */
public class ToStringList extends ArrayList<Object> {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		String thing = "[";
		for (int i = 0; i < this.size(); i++) {
			if (i != this.size()-1) {
				thing += this.get(i) + ", ";
			} else {
				thing += this.get(i);
			}
			thing += "]";
		}
		return thing;
	}
	
	public static void main(String[] args) {
		ArrayList<String> test = new ArrayList<String>();
		String[] thingsToAdd = {"1", "2", "3", "4", "8", "7", "19"};
		for (int i = 0; i < thingsToAdd.length; i++) {
			test.add(thingsToAdd[i]);
		}
		System.out.println(test);
	}

}
