package game2;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private int playerTotal, turn;
	private int[][] gameBoard;
	private boolean aiPlayer;
	private int[] turnOrder;

	/**
	 * No-Args constructor
	 * @deprecated
	 */
	public Game() {
		playerTotal = 2;
		setup();
	}

	/**
	 * Constructor of the game
	 * @param players
	 * Precondition: Players is between 2 and 9.
	 */
	public Game(int players, boolean AI) {
		players = Math.abs(players);
		if (players < 2)
			players = 2;
		if (players > 9)
			players = 9;
		if (AI) {
			players++;
			aiPlayer = true;
		} else {
			aiPlayer = false;
		}
		playerTotal = players;
		setup();
	}

	/**
	 * Does secondary setup functions
	 */
	private void setup() {
		this.turn = 0;
		this.gameBoard = new int[this.getPlayerTotal() + 1][this.getPlayerTotal() + 1];
		this.gameBoard = randomizeDoubleIntArray(this.gameBoard, this.getPlayerTotal());
		this.turnOrder = new int[this.getPlayerTotal()];
		for (int i = 0; i < this.turnOrder.length; i++) {
			this.turnOrder[i] = -1;
		}
		setup2();
	}
	
	/**
	 * Setups the random turn array
	 */
	private void setup2() {
		for (int i = 0; i < this.turnOrder.length; i++) {
			int n = -1;
			boolean checked = false;
			do {
			n = ThreadLocalRandom.current().nextInt(1, this.getPlayerTotal() + 1);
			int checkedItems = 0;
			for (int items : this.turnOrder) {
				if (n != items)
					checkedItems++;
			}
			if (checkedItems == this.turnOrder.length)
				checked = true;
			} while (!checked);
			this.turnOrder[i] = n;
		}
	}

	/**
	 * Obtains total player count (includes AI player if it is playing)
	 * @return Player count
	 */
	public int getPlayerTotal() {
		return this.playerTotal;
	}

	/**
	 * Obtains the current turn number, or otherwise the current player playing
	 * @return The current turn
	 */
	public int getTurn() {
		return this.getTurnArray()[this.turn];
	}
	
	/**
	 * Obtains the turn list
	 * @return order of which players play
	 */
	public int[] getTurnArray() {
		return this.turnOrder;
	}

	/**
	 * Obtains the current board state
	 * @return The gameboard
	 */
	public int[][] getBoard() {
		return this.gameBoard;
	}
	
	/**
	 * Obtains if there is an AI player
	 * @return AI's existence
	 */
	public boolean getAiPlayer() {
		return this.aiPlayer;
	}

	public String toString() {
		int[][] board = this.getBoard();
		int player = this.getTurn();
		String thing = "";
		for (int i = 0; i < board.length; i++) {
			thing += (i+1) +".) " + board[i][player];
			thing += "\n";
		}
		return thing;
	}

	/**
	 * Randomizes everything in an array
	 * @param a 2-D Integer array
	 * @param b Max value to randomize up to
	 * @return The randomized array
	 */
	private int[][] randomizeDoubleIntArray(int[][] a, int b) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = ThreadLocalRandom.current().nextInt(1, b + 1);
			}
		}
		return a;
	}

	/**
	 * Updates the current turn number to the next player
	 */
	private void updateTurn() {
		if (this.getTurn() == this.getPlayerTotal()-1) {
			this.turn = 0;
			this.endTurnDistribute();
		} else {
			this.turn++;
		}
	}

	/**
	 * Asks the player how much they want to move.
	 * @param row The row that was selected
	 * @return Number to move
	 * <b>POSTCONDITION:</b> return is less or greater than the total in selected row
	 */
	private int playerMove(int row) {
		@SuppressWarnings("resource")
		Scanner kboard = new Scanner(System.in);
		int number = 0;
		System.out.println("You are taking from row " + (row+1));
		System.out.print("How much from that row would you like to take? ");
		number = kboard.nextInt();
		int[][] board = this.getBoard();
		if (board[row][this.getTurn()] < number) {
			number = board[row][this.getTurn()];
		}
		return number;
	}
	
	/**
	 * Allows an AI player to play
	 * @version 0.0.0
	 * @param row The row that the AI takes from
	 * @return The number that the AI takes
	 */
	private int computerMove(int row) {
		//int number = 0;
		System.out.println("Computer is taking from row " + (row+1) +  ".");
		return this.getBoard()[row][this.getTurn()];
	}

	/**
	 * Method to actually play the game.
	 */
	public void play() {
		while (!this.win()) {
			int row = ThreadLocalRandom.current().nextInt(0, this.getPlayerTotal() + 1);
			int player = this.getTurn();
			int[][] board = this.getBoard();
			while (board[row][player] == 0) {
				row = ThreadLocalRandom.current().nextInt(0, this.getPlayerTotal() + 1);
			}
			int n = -1;
			if (player == this.getPlayerTotal()-1 && getAiPlayer()) {
				System.out.println("Compuer's turn");
				System.out.println(this);
				n = computerMove(row);
			} else {
				System.out.println("Player " + (player+1)  + "'s turn");
				System.out.println(this);
				n = playerMove(row);
			}
			if (n > board[row][player]) {
				n = board[row][player];
			}
			System.out.println(n + " was taken.");
			if (n > 0) {
				this.gameBoard[row][player] -= n;
				this.distribute(n, row);
			} else {
				System.out.println("Turn skipped.");
			}
			System.out.println();
			this.updateTurn();
		}
	}
	
	/**
	 * Checks if a player has won. In order to win, you must be the first one to
	 * get your row to all be zeroes.
	 * @return if a player has won.
	 */
	private boolean win() {
		int [][] board = this.getBoard();
		int players = this.getPlayerTotal(), totalZeroes = board[0].length;
		for (int i = 0; i <= players-1; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[j][i] == 0)
					totalZeroes--;
			}
			if (totalZeroes == 0) {
				System.out.println("Player " +  (i + 1) + " has won!");
				this.revealBoard();
				return true;
			} else {
				totalZeroes = board[0].length;
			}
		}
		return false;
	}

	/**
	 * Distributes the amount of what the player specified to the entire row
	 * @param num The amount taken
	 * @param row The row that was randomly selected
	 */
	private void distribute(int num, int row) {
		int player = this.getTurn();
		while (num != 0) {
			player++;
			player = this.neutralize(player);
			this.gameBoard[row][player]++;
			num--;
		}
	}

	/**
	 * Distributes the final column to the other players
	 */
	private void endTurnDistribute() {
		int player = this.gameBoard.length-1;
		for (int row = 0; row < this.gameBoard[0].length; row++) {
			player = this.neutralize(player);
			int num = this.gameBoard[row][player];
			this.gameBoard[row][player] = 0;
			while (num != 0) {
				player++;
				player = this.neutralize(player);
				this.gameBoard[row][player]++;
				num--;
			}
		}
	}

	/**
	 * Neutralizes the player number in multiple methods
	 * @param player The player number being checked
	 * @return The number being fed into the methods
	 */
	private int neutralize(int player) {
		if (player > this.getPlayerTotal()) {
			player = 0;
		}
		return player;
	}

	/**
	 * Reveals the entire board after someone has won
	 */
	private void revealBoard() {
		int[][] board = this.getBoard();
		String thing = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++)
				thing +=  board[i][j] + " ";
			thing += "\n";
		}
		System.out.println(thing);
	}

	public static void main(String[] args) {
		Scanner playerCount = new Scanner(System.in);
		System.out.println("Rules:");
		System.out.println("Each turn, the player can decide an amount to take from the randomly "
				+ "selected row from the number available there.");
		System.out.println("The amount selected will be distributed between all players and a "
				+ "non-player row.");
		System.out.println("Then it goes to the next player, and so on.");
		System.out.println("Players may skip their turn by selecting 0 or less as their amount.");
		System.out.println("The player that first gets everything in their row to zero wins the "
				+ "game.\n");
		System.out.print("How many players are there? (2-9): ");
		int players = playerCount.nextInt();
		System.out.println();
		Game ge = new Game(players, false);
		for (int i = 0; i < ge.turnOrder.length; i++) {
			System.out.print(ge.turnOrder[i] + " ");
		}
		System.out.println();
		ge.play();
		playerCount.close();
	}
}
