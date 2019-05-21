package ch09problems;

/**
 * @author Zach Wellman
 * Question 19
 * This is not needed, not recording dates
 */
public class ProblemNot1 {
	private String[] allSubstrings(String word) {
		int length = word.length();
		int totalSubstrings = (length*(length+1))/2;
		String[] wordSubstrings = new String[totalSubstrings];
		int something = 0;
		/*for (int i = 0; i < totalSubstrings; i++) {
			wordSubstrings[i] = word.substring(0, length-1);
		}*/
		wordSubstrings[totalSubstrings-1] = word;
		something++;
		String[] wordSeperate = word.split("");
		for (int i = 0; i < wordSeperate.length; i++) {
			wordSubstrings[i] = wordSeperate[i];
			something++;
		}
		for (int i = 0; i < (something-totalSubstrings); i++) {
			
		}
		wordSubstrings[totalSubstrings-1] = word;
		return wordSubstrings;
	}
	
	public static void main(String[] args) {
		ProblemNot1 pn1 = new ProblemNot1();
		String word = "cat";
		String[] words = pn1.allSubstrings(word);
		for (int i = 0; i < words.length; i++) {
			System.out.print(words[i] + ", ");
		}
	}
}
