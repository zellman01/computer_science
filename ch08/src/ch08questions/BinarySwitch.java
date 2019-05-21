package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 10/18/2018
 * DateCompleted: 12/10/2018
 *
 */
public class BinarySwitch {
	private void switchBinary(String a) {
		a = a.replaceAll("0", "2").replaceAll("1", "3").replaceAll("2", "1").replaceAll("3", "0");
		System.out.println(a);
	}
	
	public static void main(String[] args) {
		BinarySwitch sb = new BinarySwitch();
		String a = "101 111 0011";
		sb.switchBinary(a);
	}
}
