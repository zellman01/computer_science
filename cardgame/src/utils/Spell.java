package utils;

import java.awt.Color;

@SuppressWarnings("unused")
public enum Spell {;
	/*RESFUFFLE(3, Array(new Card(Color.BLACK, ' ', "Jack"), new Card(Color.RED, ' ', "Queen"), new Card(Color.BLACK, ' ', "King")), 
			"Reshuffle", "Reshuffles your deck");*/
	
	//RESFUFFLE(3, {}, "Resuffle", "Resuffles your deck");
	
	private Card[] spellCost;
	private String name, description;
	
	private Spell(int totalCost, Card[] spellCost, String name, String description) {
		if (totalCost != spellCost.length) {
			System.err.print("Total cost of a spell must be the same as the spell cost length.");
			System.exit(1);
		}
		
		this.spellCost = new Card[totalCost];
		this.spellCost = spellCost;
		this.name = name;
		this.description = description;
	}

}
