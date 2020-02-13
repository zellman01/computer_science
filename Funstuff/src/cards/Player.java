package cards;

public class Player {
	private Hand[] hands;
	
	public Player(int numHands, int numCards) {
		hands = new Hand[numHands];
		for (int i = 0; i < numHands; i++) {
			hands[i] = new Hand(numCards);
		}
	}
	
	public Hand getHand(int handNum) { return hands[handNum-1]; }
	
	public String toString() {
		String str = "";
		for (int i = 0; i < hands.length; i++) {
			str += "Hand " + (i+1) + "'s length: " + hands[i].length() + "\n";
		}
		return str;
	}
}
