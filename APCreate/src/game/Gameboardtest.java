 package game;

public class Gameboardtest {

	public static void main(String[] args) {
		Gameboard gb = new Gameboard();
		int[][] gameBoardThing = gb.obtainBoard();
		for (int i = 0; i < gameBoardThing.length; i++) {
			for (int j = 0; j < gameBoardThing[i].length; j++) {
				System.out.print(gameBoardThing[i][j] + " ");
			}
			System.out.println();
		}
	}
}
