package game.bom.primary;

public enum CardList {
	test("Front", 8, "Testing card 1"),
	test2("Back", 15, "Testing card 2");
	
	
	Card thing;
	
	private CardList(String position, int mCost, String name) {
		thing = new Card(position, mCost, name);
	}

	public Card obtainCard() { return thing; }
	
}
