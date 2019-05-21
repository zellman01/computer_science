package ch05questions;

/**
 * This program will convert feet and inches to only inches
 * @author Zach Wellman
 */

import java.util.Scanner;

public class FeetToInches  {
  public static int toInches(int feet, int inches) {
    return 12*feet + inches;
  }

  public static void main(String[] args) {
	  
    Scanner kboard = new Scanner(System.in);

    System.out.print("Feet (0-50): ");
    int feet = kboard.nextInt();

    System.out.print("Inches (0-120): ");
    int inches = kboard.nextInt();

    kboard.close();
    
    if (feet < 0 || feet > 50) {
    	System.out.println("You may only set feet equal to a number between 0-50");
    	System.exit(1);
    }
    
    if (inches < 0 || inches > 120) {
    	System.out.println("You may only set inches equal to a number between 0-120");
    	System.exit(1);
    }

    int totalInches = toInches(feet, inches);

    if (inches != 1) {
    	System.out.println(feet + " feet and " + inches + " inches = " + totalInches + " inches");
    } else if (inches == 1) {
    	System.out.println(feet + " feet and " + inches + " inch = " + totalInches + " inches");
    }
  }
}