package multigames;

import java.util.Scanner;

public class Take132P {
	private static Scanner kboard;

	private int stonesLeft;

	private static String p1, p2, p3, p4;
	private static int numPlayers;

	/**
	 * @param n Amount of stones
	 */
	public Take132P(int n) {
		stonesLeft = n;
	}

	/**
	 * Prompts the user to take a number of stones;
	 * If the move is valid, returns the number of stones
	 * taken; otherwise, displays an error message --
	 * "You are allowed to only take 1, 2, or 3 stones" or
	 * "Can't take that many: only <nStones> left in the pile"
	 * -- and returns -1:
	 * Precondition: nStones > 0
	 * @return n Stones to take
	 */
	public int humanMove1(int nStones) {
		System.out.print("How many stones does " + p1 + " take? ");
		int n = kboard.nextInt();

		if (n > 3 || n < 1) {
			System.out.println("You are allowed to take 1, 2, or 3 stones");
			return -1;
		}

		if (n > nStones) {
			System.out.println("Can't take that many: only " + nStones + " left in the pile.");
			return -1;
		}

		return n;
	}

	public int humanMove2(int nStones) {
		System.out.print("How many stones does " + p2 + " take? ");
		int n = kboard.nextInt();

		if ( n < 1 || n > 3) {
			System.out.println("You are allowed to take 1, 2, or 3 stones");
			return -1;
		}

		if (n > nStones) {
			System.out.println("Can't take that many: only " + nStones + " left in the pile.");
			return -1;
		}
		return n;
	}

	public int humanMove3(int nStones) {
		System.out.print("How many stones does " + p3 + " take? ");
		int n = kboard.nextInt();

		if ( n < 1 || n > 3) {
			System.out.println("You are allowed to take 1, 2, or 3 stones");
			return -1;
		}

		if (n > nStones) {
			System.out.println("Can't take that many: only " + nStones + " left in the pile.");
			return -1;
		}
		return n;
	}
	
	public int humanMove4(int nStones) {
		System.out.print("How many stones does " + p4 + " take? ");
		int n = kboard.nextInt();

		if ( n < 1 || n > 3) {
			System.out.println("You are allowed to take 1, 2, or 3 stones");
			return -1;
		}

		if (n > nStones) {
			System.out.println("Can't take that many: only " + nStones + " left in the pile.");
			return -1;
		}
		return n;
	}

	/**
	 * Plays the game
	 */
	public void play() {
		switch (numPlayers) {
		case 1:
			System.out.print("What is the name of the third player: ");
			p3 = kboard.nextLine();
			break;
		case 2:
			System.out.print("What is the name of the third player: ");
			p3 = kboard.nextLine();
			System.out.print("What is the name of the fourth player: ");
			p4 = kboard.nextLine();
			break;
		}
		System.out.println();
		int playerTurn = 1;
		while (stonesLeft > 0) {
			if (stonesLeft > 1) 
				System.out.println(stonesLeft + " stones left in the pile");
			else
				System.out.println(stonesLeft + " stone left in the pile");

			int n = -1;
			while (n < 0)
				switch(playerTurn) {
				case 1:
					do {
						n = humanMove1(stonesLeft);
					} while (n < 1 || n > 3);
					stonesLeft -= n;
					if (n > 1) {
						System.out.println(p1 + " took " + n + " stones.");
						System.out.println();
					} else {
						System.out.println(p1 + " took " + n + " stone.");
						System.out.println();
					}
					if (stonesLeft == 0) {
						System.out.println(p1 + " wins!");
						System.exit(0);
					}
					playerTurn = 2;
					break;
				case 2:
					do {
						n = humanMove2(stonesLeft);
					} while (n < 1 || n > 3);
					stonesLeft -= n;
					if (n > 1) {
						System.out.println(p2 + " took " + n + " stones.");
						System.out.println();
					} else {
						System.out.println(p2 + " took " + n + " stone.");
						System.out.println();
					}
					if (stonesLeft == 0) {
						System.out.println(p2 + " wins!");
						System.exit(0);
					}
					if (numPlayers > 2) {
						playerTurn = 3;
					} else {
						playerTurn = 1;
					}
					break;
				case 3:
					do {
						n = humanMove3(stonesLeft);
					} while (n < 1 || n > 3);
					stonesLeft -= n;
					if (n > 1) {
						System.out.println(p3 + " took " + n + " stones.");
						System.out.println();
					} else {
						System.out.println(p3 + " took " + n + " stone.");
						System.out.println();
					}
					if (stonesLeft == 0) {
						System.out.println(p3 + " wins!");
						System.exit(0);
					}
					if (numPlayers > 3) {
						playerTurn = 4;
					} else {
						playerTurn = 2;
					}
					break;
				case 4:
					do {
						n = humanMove4(stonesLeft);
					} while (n < 1 || n > 3);
					stonesLeft -= n;
					if (n > 1) {
						System.out.println(p4 + " took " + n + " stones.");
						System.out.println();
					} else {
						System.out.println(p4 + " took " + n + " stone.");
						System.out.println();
					}
					if (stonesLeft == 0) {
						System.out.println(p4 + " wins!");
						System.exit(0);
					}
					playerTurn = 1;
					break;
				default:
					throw new Error("Error in player turn counter.");	
				}
		}
	}

	/****************************************************************/

	public static void main(String[] args) {
		kboard = new Scanner(System.in);
		System.out.print("What is the name of the first player: ");
		p1 = kboard.nextLine();
		System.out.print("What is the name of the second player: ");
		p2 = kboard.nextLine();
		System.out.print("How many other players, up to 2, are playing? ");
		do {
			numPlayers = kboard.nextInt();
		} while (numPlayers < 1 || numPlayers > 2);

		int n = 23 + (int)(23 * Math.random());
		Take132P game = new Take132P(n);
		game.play();

		kboard.close();
	}
}
