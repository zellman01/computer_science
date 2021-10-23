package io.github.zellman01.idlegame.core;

import java.util.ArrayList;

import io.github.zellman01.idlegame.Collector.CollectorNode;
import io.github.zellman01.idlegame.master.MasterNode;

/**
 * Setting up of the game
 * @author zellman01
 * @version 0.0.1
 * @since 0.0.1
 */
public class Startup {
	private int totalAmountNeeded = 35000;
	
	private MasterNode mn;
	private CollectorNode cn;
	
	private ArrayList<Node> allNodes;
	
	private void setup() {
		allNodes = new ArrayList<>();
		
		mn = new MasterNode();
		allNodes.add(mn);
		
		cn = new CollectorNode();
		allNodes.add(cn);
		
		cn.addParentNode(mn);
		//cn.enableNode();
	}
	
	private void testRun() {
		while (calculateTotal() < totalAmountNeeded) {
			mn.updateNode();
			if (mn.amountInNode() == mn.nodeHardCap()) {
				cn.updateNode();
			}
		}
		System.out.println(mn);
		System.out.println(cn);
		System.out.println(calculateTotal());
	}
	
	private void run() {
		while (calculateTotal() < totalAmountNeeded) {
			for (Node i : allNodes) {
				if (i.nodeEnabled() && i.checkAutoCollect()) i.updateNode();
			}
			// Check if the node has been clicked on
		}
	}
	
	private int calculateTotal() {
		int total = 0;
		for (Node i : allNodes) {
			if (i.nodeEnabled()) total += i.amountInNode();
		}
		return total;
	}
	
	public static void main(String[] args) {
		/*MasterNode mn = new MasterNode();
		CollectorNode test = new CollectorNode();
		mn.updateNode();
		mn.updateNode();
		mn.updateNode();
		test.addParentNode(mn);
		System.out.println(test.getParentNodes().get(0));
		System.out.println(test + "\n");
		
		test.updateNode();
		System.out.println(test.getParentNodes().get(0));
		System.out.println(test + "\n");*/
		Startup s = new Startup();
		s.setup();
		s.testRun();
	}
}
