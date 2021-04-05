package Haha;

public class ChristmasEve {
	public static String eve(int number) {
		String str = "Christmas ";
		for (; number > 0; number--) {
			str += "eve ";
		}
		str += ".";
		return str;
	}
	
	public static void main(String[] args) {
		int thing = 116;
		System.out.println(eve(thing));
		
	}
}
