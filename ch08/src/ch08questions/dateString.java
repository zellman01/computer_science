package ch08questions;

/**
 * 
 * @author Zach Wellman
 * Date: 10/18/2018
 * DateCompleted: 11/29/2018
 */
public class dateString {
	private String datestr(int m, int d, int y) {  
		String dateStr = "mm/dd/yyyy";
		dateStr = dateStr.replace('/', '-');
		while (m > 12) {
			m -= 12;
		}
		String mon = Integer.toString(m);
		String day = Integer.toString(d);
		String yer = Integer.toString(y);
		if (mon.length() < 2) {
			String temp = mon;
			mon = "0";
			mon += temp;
		}
		if (day.length() < 2) {
			String temp = day;
			day = "0";
			day += temp;
		}
		dateStr = dateStr.replaceFirst("m", day);
		dateStr = dateStr.replaceFirst("d", mon);
		dateStr = dateStr.replaceFirst("y", yer);
		dateStr = dateStr.replaceAll("m", "");
		dateStr = dateStr.replaceAll("d", "");
		dateStr = dateStr.replaceAll("y", "");
		return dateStr;
	}
	public static void main(String[] args) {
		dateString ds = new dateString();
		System.out.println(ds.datestr(1, 7, 2019)); 
	}
}
