package game;

public class Gameboard {
	private int[][] board;
	private String[][] boardColours;
	
	public Gameboard() {
		board = new int[8][8];
		boardColours = new String[board.length][board[0].length];
		boardColours = setColourBoard(boardColours);
		board = setBoard(boardColours);
	}

	public String[][] obtainColourBoard() {
		return this.boardColours;
	}
	
	public int[][] obtainBoard() {
		return this.board;
	}

	private String[][] setColourBoard(String[][] board) {
		String colour = "r";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = colour;
				colour = changeColour(colour);
			}
			colour = changeColour(colour);
		}
		return board;
	}
	
	private int[][] setBoard(String[][] board) {
		int[][] intBoard = new int[board.length][board[0].length];
		int red = 1;
		int black = 0;
		for (int i = 0; i < intBoard.length; i++) {
			for (int j = 0; j < intBoard[i].length; j++) {
				if (check(board[i][j])) {
					intBoard[i][j] = red;
				} else {
					intBoard[i][j] = black;
				}
			}
		}
		return intBoard;
	}
	
	private String changeColour(String c) {
		if (check(c)) {
			return "b";
		} else {
			return "r";
		}
	}
	
	private boolean check(String c) {
		return c.equals("r");
	}
}
