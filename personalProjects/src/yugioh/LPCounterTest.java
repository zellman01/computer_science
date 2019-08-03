package yugioh;

import java.util.Scanner;

public class LPCounterTest extends LPCounter {
	public LPCounterTest(int lp1, int lp2) {
		super(lp1, lp2);
	}
	
	static String gameState = "ongoing";

	
	public static void main (String[] args) {
		@SuppressWarnings("resource")
		Scanner kboard = new Scanner(System.in);
		System.out.print("How much LP does Player 1 have when this was launched?: ");
		int lp1 = kboard.nextInt();
		System.out.print("How much LP does Player 2 have when this was launched?: ");
		int lp2 = kboard.nextInt();
		@SuppressWarnings("unused")
		LPCounter lpcounter = new LPCounter(lp1, lp2);
		while (gameState != "Finished") {
			if (LP1 <= 0) {
				System.out.println("GAME OVER");
				System.out.println("The game was won by Player 2 with a total of " + LP2 + " Life points remaining.");
				System.out.println("First player lost with a total of " + LP1 + " Life Points.");
				gameState = "Finished";
				System.exit(0);
			} else if (LP2 <= 0) {
				System.out.println("GAME OVER");
				System.out.println("The game was won by Player 1 with a total of " + LP1 + " Life points remaining.");
				System.out.println("Second player lost with a total of " + LP2 + " Life Points.");
				gameState = "Finished";
				System.exit(0);
			}
			System.out.println("Current LP total (in 1st player | 2nd player): " + LP1 + "|" + LP2 + ".\n Enter a command from the list: (decrease 1st, decrease 2nd, increase 1st, increase 2nd, 1st give, 2nd give)");
			String command = kboard.nextLine();
			switch (command) {
			case "decrease 1st":
				System.out.print("How much does Player 1 lose?: ");
				int decrease1 = kboard.nextInt();
				LP1Decrease(decrease1);
				break;
			case "decrease 2nd":
				System.out.print("How much does Player 2 lose?: ");
				int decrease2 = kboard.nextInt();
				LP2Decrease(decrease2);
				break;
			case "increase 1st":
				System.out.print("How much does Player 1 gain?: ");
				int increase1 = kboard.nextInt();
				LP1Increase(increase1);
				break;
			case "increase 2nd":
				System.out.print("How much does Player 2 gain?: ");
				int increase2 = kboard.nextInt();
				LP2Increase(increase2);
				break;
			case "1st give":
				System.out.println("GAME OVER");
				System.out.println("Player 2 won with " + LP2 + " Life Points remaining.");
				if (LP1 == LP2)
					System.out.println(LP2 + " Is the difference of the winning player's LP compared to the losing player.");
				else
					System.out.println(LP2 - LP1 + " Is the difference of the winning player's LP compared to the losing player.");
				gameState = "Finished";
				System.exit(0);
				break;
			case "2nd give":
				System.out.println("GAME OVER");
				System.out.println("Player 1 won with " + LP1 + " Life Points remaining.");
				if (LP2 == LP1)
					System.out.println(LP1 + " Is the difference of the winning player's LP compared to the losing player.");
				else
					System.out.println(LP1 - LP2 + " Is the difference of the winning player's LP compared to the losing player.");
				gameState = "Finished";
				System.exit(0);
				break;
			}
		}
	}
}
