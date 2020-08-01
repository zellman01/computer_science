package game.bom.battle;

import game.bom.utilities.Loader;
import game.bom.deck.DeckBattle;
import game.bom.card.Card;
import game.bom.player.Player;
import game.bom.battle.TerminateNumber;

/**
 * Basic battle class
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public class Battle {
	private Player player, opponent, winner;
	private DeckBattle playerDeck, opponentDeck;
	private TerminateNumber battleTerminator;
	
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
	
	public void executeBattle() {
		while (!winCond1()) {
			player.modifyHealth(-1);
			try {
			System.out.println(playerDeck.draw());
			} catch(Exception e) {
				setUsedDeck(1, "Default");
			}
			opponent.modifyHealth(-3);
			
		}
		System.out.println(battleTerminator + "\nWinner: " + winner.getName());
	}
	
	public static void main(String[] args) {
		Battle b = new Battle(new Player(10, "Test"), new Player(10, "Test2"));
		b.setUsedDeck(1, "Default");
		b.executeBattle();
	}
}
