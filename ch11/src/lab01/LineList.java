package lab01;

import java.util.ArrayList;

/**
 * 
 * @author Zach Wellman
 * Date Started: ?/?/19
 * Date Completed: 5/1/19
 */

public class LineList {
	private ArrayList<String> words;
	
	public LineList() {
		words = new ArrayList<String>();
	}
	
	public int size() {
		return this.words.size();
	}
	
	public String get(int k) {
		return this.words.get(k);
	}
	
	public void add(String line) {
		this.words.add(line);
	}
	
	public String remove(int k) {
		return this.words.remove(k);
	}
	
	public void move(int index, int newIndex) {
		String word1 = this.remove(index);
		this.words.add(newIndex, word1);
	}
	
	public void shuffle() {
		int n = this.size()-2;
		while (n >= 2) {
			int randLi = (int) (Math.random()*((n-0)+0))+0;
			String temp = this.remove(randLi);
			String temp2 = this.remove(n);
			this.words.add(n, temp);
			this.words.add(randLi, temp2);
			n--;
		}
	}
}
