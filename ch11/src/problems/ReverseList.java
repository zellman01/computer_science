package problems;

import java.util.ArrayList;

/**
 * 
 * @author Zach Wellman
 * Date Started: 5/1/19
 * Date Completed: 5/1/19
 * Question 5
 *
 */
public class ReverseList {

	public ArrayList<String> reverse(ArrayList<String> list) {
		ArrayList<String> reversed = new ArrayList<String>(list.size());
		for (int i = list.size() - 1; i >= 0; i--)
			reversed.add(list.get(i));
		return reversed;
	}
	
	public static void main(String[] args) {
		ReverseList thing = new ReverseList();
		ArrayList<String> words = new ArrayList<String>();
		String[] wordList = {"Automobiles", "Planes", "22Trains", "Movie"};
		// Input things into the list
		for (int i = 0; i < wordList.length; i++) {
			words.add(wordList[i]);
		}
		
		System.out.println(thing.reverse(words));
	}
}
