package ch02Questions;

import java.util.Scanner;

public class Number11 {
	public static void main (String [] args) {
		Scanner kboard = new Scanner(System.in);
		System.out.print("What is your favorite movie? ");
		String favoriteMovie = kboard.next();
		kboard.close();
		System.out.println("I think " + favoriteMovie + " is horrible!");
		System.out.println("Just kidding! I think " + favoriteMovie + " is pretty good as well.");
	}
}
