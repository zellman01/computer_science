package ch08lab;

/**
 * 
 * @author Zach Wellman
 * Date: 10/12/2018
 * Date Completed: 10/16/2018
 */
public class LipogramAnalyzer {
	private String b;
	public LipogramAnalyzer(String a) {
		b = a;
	}

	public String mark(char c) {
		String s2 = this.b.replace(c, '#');
		return s2;
	}

	public String allWordsWith(char c) {
		int x = test(this.b);
		String string = "hi";
		String[] ha = this.b.split(" ");
		String check = Character.toString(c);
		for (int i = 0; i < x; i++) {
			if (ha[i].contains(check)) {
				if (string.contains(ha[i])) {
					continue;
				}
				if (string.equalsIgnoreCase("hi")) {
					string = ha[i] + "\n";
				} else {
					string += ha[i] + "\n";
				}
			}
		}
		if (string.equalsIgnoreCase("hi")) {
			string = "";
		}
		return string;
	}

	private int test(String a) {
		String str1 = a;

		int wordCount = 1;

		for (int i = 0; i < str1.length(); i++) 
		{
			if (str1.charAt(i) == ' ') 
			{
				wordCount++;
			} 
		}
		return wordCount;
	}
}
