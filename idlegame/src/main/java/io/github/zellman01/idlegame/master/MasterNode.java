package io.github.zellman01.idlegame.master;

import io.github.zellman01.idlegame.core.Node;

/**
 * The beginning node. What should appear with every start
 * @author zellman01
 * @version 0.0.1
 * @since 0.0.1
 */
public class MasterNode extends Node {
	private final int baseAmount = 1;
	
	public MasterNode() {
		super("Master", "The starting node. Nothing else should be said about it. There is nothing special.", 500, 1, true);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updateNode() {
		if (nodeHardCap() > amountInNode()) {
			addAmount(baseAmount);
		}
	}
	
}
