package cards;

public class Testing {
	private static final int amountShuffle = 10;
	public static void main(String[] args) {
		Deck test1 = new Deck(true);
		for (int i = 0; i < amountShuffle; i++)
			test1.shuffle();

		Player test3 = new Player(2, 10);
		for (int i = 0; i < 10; i++)
			try {
				test3.getHand(1).addCard(test1.draw());
			} catch(Exception ex) {
				System.out.println("Deck is out of cards.");
			}
		for (int i = 0; i < 10; i++)
			try {
				test3.getHand(2).addCard(test1.draw());
			} catch(Exception ex) {
				System.out.println("Deck is out of cards.");
			}
		java.util.Random rand = new java.util.Random();
		
		for (int i = 0; i < rand.nextInt(test3.getHand(1).getHandSize()); i++)
			System.out.println(test3.getHand(1).removeTopCard());
		
		for (int i = 0; i < rand.nextInt(test3.getHand(2).getHandSize()); i++)
			System.out.println(test3.getHand(2).removeTopCard());
		System.out.println(test3);
		
	}
}
