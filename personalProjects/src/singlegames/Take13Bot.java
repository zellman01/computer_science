package singlegames;

import java.util.Scanner;
import java.util.Random;

public class Take13Bot {
  private static Scanner kboard;

  private int stonesLeft;

  /**
   * @param n Amount of stones
   */
  public Take13Bot(int n) {
    stonesLeft = n;
  }

  /**
   * Returns the correct number of stones to take
   * (according to the winning strategy) when nStones
   * remain in the pile; if such a move is not possible,
   * returns a random number of stones to take.
   * Precondition: nStones greater than 0
   * @param nStones Amount of stones left in the pile
   * @return Stones to take
   */
  public int computerMove(int nStones) {

    if (nStones > 0) {
    	switch (stonesLeft) { //All cases will return out of the function, so no case needs a break statement
    	case 10:
    		return 2;
    	case 9:
    		return 1;
    	case 7:
    		return 3;
    	case 6:
    		return 2;
    	case 5:
    		return 1;
    	case 3:
    		return 3;
    	case 2:
    		return 2;
    	case 1:
    		return 1;
    	}
    	
    	
    }
    Random rand = new Random();
	int n = rand.nextInt(3) + 1;
	while (n > nStones)
		n = rand.nextInt(3) + 1;
    return n;
  }

  /**
   * Prompts the user to take a number of stones;
   * If the move is valid, returns the number of stones
   * taken; otherwise, displays an error message --
   * "You are allowed to only take 1, 2, or 3 stones" or
   * "Can't take that many: only nStones left in the pile"
   * -- and returns -1:
   * Precondition: nStones greater than 0
   * @param nStones Amount of stones left in the pile
   * @return n Stones to take
   */
  public int humanMove(int nStones) {
	  if (nStones > 0) {
	    	switch (stonesLeft) { //All cases will return out of the function, so no case needs a break statement
	    	case 10:
	    		return 2;
	    	case 9:
	    		return 1;
	    	case 7:
	    		return 3;
	    	case 6:
	    		return 2;
	    	case 5:
	    		return 1;
	    	case 3:
	    		return 3;
	    	case 2:
	    		return 2;
	    	case 1:
	    		return 1;
	    	}
	    	
	    	
	    }
	    Random rand = new Random();
		int n = rand.nextInt(3) + 1;
		while (n > nStones)
			n = rand.nextInt(3) + 1;
	    return n;
  }

  /**
   * Plays the game
   */
  public void play() {
    while (stonesLeft > 0) {
    	if (stonesLeft > 1)
    		System.out.println(stonesLeft + " stones left in the pile");
    	else
    		System.out.println(stonesLeft + " stone left in the pile");
    	System.out.println();
      int n = -1;
      while (n < 0)
        n = humanMove(stonesLeft);
      stonesLeft -= n;
      if (n > 1)
    	  System.out.println("First bot takes " + n + " stones.");
      else
    	  System.out.println("First bot takes " + n + " stone.");
      if (stonesLeft == 0) {
        System.out.println("First bot wins.");
        System.exit(0);
      } else {
    	  if (stonesLeft > 1)
    	  		System.out.println(stonesLeft + " stones left in the pile");
    	  	else
    	  		System.out.println(stonesLeft + " stone left in the pile");
    	    }
      	System.out.println();
        n = computerMove(stonesLeft);
        stonesLeft -= n;
        if (n > 1)
        	System.out.println("Second bot takes " + n + " stones.");
        else
        	System.out.println("Second bot takes " + n + " stone.");
        if (stonesLeft == 0) {
          System.out.println("Second bot wins.");
          System.exit(0);
        }
      }
  }

  /****************************************************************/

  /**
   * @param args Something
   */
  public static void main(String[] args) {
    kboard = new Scanner(System.in);
    
    /*int n = 12 + (int)(90 * Math.random());
    while (n > 100) {
    	n = 12 + (int)(90 * Math.random());
    }*/
    //final int n = Integer.MAX_VALUE;
    final int n = 99999999;
    Take13Bot game = new Take13Bot(n);
    game.play();

    kboard.close();
  }
}
