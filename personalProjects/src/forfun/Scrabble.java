package forfun;

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
	
	/**
	 * 
	 * @param word The word being used to check score
	 * @param doubleScore Position of the letter getting double score (-1 if none)
	 * @param tripleScore Position of the letter getting tripled score (-1 if none)
	 * @return The score of the word
	 */
	public int computeScore(String word, int doubleScore, int tripleScore) {
		int totalScore = 0;
		String[] seperate = word.split("");
		int i = 0;
		for (String value : seperate) {
			int scoreIndex = alphabet.indexOf(value);
			if (i == doubleScore) {
				totalScore += (values[scoreIndex] * 2);
			} else if (i == tripleScore) {
				totalScore += (values[scoreIndex]*3);
			} else {
				totalScore += values[scoreIndex];
			}
			i++;
		}
		return totalScore;
	}
	
	public static void main(String[] args) {
		Scrabble scr = new Scrabble();
		System.out.println(scr.computeScore("thing"));
	}
}
