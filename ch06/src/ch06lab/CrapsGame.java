package ch06lab;

// Implements the game of Craps logic

public class CrapsGame {
	private int point = 0;
	private enum State {NEW_ROLL, KEEP_ROLLING};
	
	State gameState = State.NEW_ROLL;

	/**
	 *  Calculates the result of the next dice roll in the Craps game.
	 *  The parameter total is the sum of dots on two dice.
	 *  point is set to the saved total, if the game continues,
	 *  or 0, if the game has ended.
	 *  Returns 1 if player won, -1 if player lost,
	 *  0 if player continues rolling.
	 *  @param total Total of the first roll
	 *  @return gameState if the player has won, lost, or keeps rolling
	 */
	public int processRoll(int total) {
		int result = 0;

		switch (gameState) {
		case NEW_ROLL:
			if (total == 7 || total == 11) {
				result = 1;
			} else if (total == 2 || total == 3 || total == 12) {
				result = -1;
			}
			if (result == 0) {
				gameState = State.KEEP_ROLLING;
			}
			point = total;
			break;
		case KEEP_ROLLING:
			if (total == point) {
				result = 1;
			} else if (total == 7) {
				result = -1;
			}
			if (result != 0) {
				gameState = State.NEW_ROLL;
			}
			break;
		}

		return result;
	}

	/**
	 *  Returns the saved point
	 *  @return point What was rolled from the dice
	 */
	public int getPoint() {
		return point;
	}
}

