package ch07problems;

/**
 * Prints out all possible combos of an amount of cents
 * @author Zach Wellman
 * Date: 9/28/2018
 */
public class Money {
	public static void main(String [] args) {
		int cost = 1, count = 0;
		System.out.println("Restrictions: 1 to 650.");
		for (int quarter = 0; quarter <= cost; quarter++) {
			for (int dime = 0; dime <= cost; dime++) {
				for (int nickel = 0; nickel <= cost; nickel++) {
					if (quarter*25+dime*10+nickel*5<cost+1) {
						int penny = (cost-(quarter*25+dime*10+nickel*5));
						if (penny < 0) {
							penny = 0;
						}
						System.out.println(cost + " cents = " + quarter + " quarters + " + dime + " dimes + " + nickel + " nickels + " + penny + " pennies");
						count++;
					}
				}
			}
		}
		System.out.println(count + " way(s)");
	}
}
