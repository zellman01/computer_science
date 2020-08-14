package game.bom.battle;

import game.bom.utilities.Loader;
import game.bom.deck.DeckBattle;
import game.bom.error.ErrorCodes;
import game.bom.card.Card;
import game.bom.player.Player;

import java.util.Random;

import game.bom.battle.TerminateNumber;

/**
 * Basic battle class
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public class Battle {
	private Player player, opponent, winner, currentTurn;
	private DeckBattle playerDeck, opponentDeck;
	private TerminateNumber battleTerminator;
	private final int manaRecovered = 3;
	
	public Battle(Player p1, Player p2) {
		player = p1;
		opponent = p2;
	}
	
	private DeckBattle getDeck(String name) {
		return new DeckBattle(Loader.deck(name));
	}
	
	public void setUsedDeck(int playerNum, String name) {
		if (playerNum == 1)
			playerDeck = getDeck(name);
		else if (playerNum == 2)
			opponentDeck = getDeck(name);
		else {
			battleTerminator = TerminateNumber.abnormal;
			System.err.println("Battle ended unexpectantly - Player # " + playerNum + " not found.");
			System.exit(-1);
		}
	}
	
	private boolean winCond1() {
		boolean playerDead = player.getHealth() <= 0, opponentDead = opponent.getHealth() <= 0;
		if (playerDead && opponentDead) {
			battleTerminator = TerminateNumber.normal;
			winner = new Player(-1, "Tie");
			return true;
		}
		if (playerDead || opponentDead) {
			battleTerminator = TerminateNumber.normal;
			if (playerDead)
				winner = opponent;
			else
				winner = player;
			return true;
		}
		return false;
	}
	
	private void switchPlayer() {
		if (currentTurn.getName().equals(player.getName()))
			currentTurn = opponent;
		else
			currentTurn = player;
	}
	
	private void executeTurn(Player turnTaker) {
		boolean endTurn = false;
		while (!endTurn && !someoneWon()) {
			turnTaker.modifyHealth(-3);
			endTurn = true;
		}
		turnTaker.turnEnd(manaRecovered);
		switchPlayer();
	}
	
	public void executeBattle() {
		firstTurn();
		while (!someoneWon()) {
			executeTurn(currentTurn);
		}
		System.out.println(battleTerminator + "\nWinner: " + winner.getName());
	}
	
	private boolean someoneWon() {
		return winCond1();
	}

	private void firstTurn() {
		Random rand = new Random();
		int a = rand.nextInt(2);
		switch (a) {
		case 0:
			currentTurn = player;
			break;
		case 1:
			currentTurn = opponent;
			break;
		default:
			System.exit(ErrorCodes.E900.errorNum());
		}
	}
	
	public static void main(String[] args) {
		Battle b = new Battle(new Player(10, "Test"), new Player(10, "Test2"));
		b.setUsedDeck(1, "Default");
		b.executeBattle();
	}
}
