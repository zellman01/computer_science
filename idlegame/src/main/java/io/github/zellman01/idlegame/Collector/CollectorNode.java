package io.github.zellman01.idlegame.Collector;

import java.util.ArrayList;

import io.github.zellman01.idlegame.core.Node;

public class CollectorNode extends Node {

	private final int baseAmount = 2;
	
	public CollectorNode() {
		super("Collector", "Temp desc", 10000, 2, false);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateNode() {
		if (nodeHardCap() > amountInNode()) {
			int addedAmount = 0;
			ArrayList<Node> parents = this.getParentNodes();
			for (Node i : parents) {
				addedAmount += i.amountInNode();
				i.addAmount(-i.amountInNode());
			}
			addAmount(baseAmount + addedAmount);
		}
	}
}
