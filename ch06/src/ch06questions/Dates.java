package ch06questions;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Dates {
	static Scanner kb = new Scanner(System.in);
	/**
	 * 
	 * @param month1 First Month (in number)
	 * @param day1 First Day (in number)
	 * @param year1 First Year (in number)
	 * @param cent1 First Century (in number format)
	 * @param month2 Second Month (in number)
	 * @param day2 Second Day (in number)
	 * @param year2 Second Year (in number)
	 * @param cent2 Second Century
	 * @return true if first date is after second date
	 */
  public static boolean isLater(int month1, int day1, int year1, int cent1,
                                int month2, int day2, int year2, int cent2) {
	  return (year1 > year2 && year1 != 00 && year2 != 00) || (cent1 > cent2) || (year1 == year2 && month1 > month2)
				|| (year1 == year2 && month1 == month2 && day1 > day2);
  }

  public static void main(String[] args) {
    DecimalFormat dateFormat = new DecimalFormat("00");
    //DecimalFormat yearFormat = new DecimalFormat("##0");
    System.out.print("Enter the first date  (month day year (first two digits) year (last two digits): ");
    int month1 = kb.nextInt();
    int day1 = kb.nextInt();
    int cent1 = kb.nextInt();
    int year1 = kb.nextInt();

    System.out.print("Enter the second date (month day year (first two digits) year (last two digits)): ");
    int month2 = kb.nextInt();
    int day2 = kb.nextInt();
    int cent2 = kb.nextInt();
    int year2 = kb.nextInt();

    kb.close();

    System.out.println();  // blank line
    
    String msg = dateFormat.format(month1) + "/" + dateFormat.format(day1) + "/" + dateFormat.format(year1);
    if (isLater(month1, day1, year1, cent1, month2, day2, year2, cent2))
    	msg += " IS ";
    else
    	msg += " is NOT ";

    msg += "later than " + dateFormat.format(month2) + "/" + dateFormat.format(day2) + "/" + dateFormat.format(year2);
    System.out.println(msg);
  }
}

