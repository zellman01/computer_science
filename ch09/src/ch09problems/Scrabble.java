package ch09problems;

/**
 * 
 * @author Zach Wellman
 * Date: 2/12/2019
 * DateCompleted: 2/12/2019
 * Problem 12
 */
public class Scrabble {
	private final int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	private final String alphabet = "abcdefghijklmnopqrstuvwxyz";
	
	/**
	 * 
	 * @param word The word being used to check score
	 * @return The score of the word
	 */
	public int computeScore(String word) {
		int totalScore = 0;
		String[] seperate = word.split("");
		for (String value : seperate) {
			int scoreIndex = alphabet.indexOf(value);
			totalScore += values[scoreIndex];
		}
		return totalScore;
	}
	
	public static void main(String[] args) {
		Scrabble scr = new Scrabble();
		//Maximum of seven letters per word
		System.out.println(scr.computeScore(""));
	}
}
