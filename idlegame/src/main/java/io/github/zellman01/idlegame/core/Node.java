package io.github.zellman01.idlegame.core;

import java.util.ArrayList;

/**
 * Parent class of all other nodes (Methods here should only be called by child classes)
 * @author zellman01
 * @version 0.0.1
 * @since 0.0.1
 */
public class Node {
	private String name, description;
	private ArrayList<Node> parentNodes; // Each reset will cause each of these to reset as well
	private int totalInNode, hardCap, level;
	private boolean enabled, autoCollect;
	
	/**
	 * Creates a node
	 * @param tempName The name of the node
	 * @param tempHardCap The set hard cap that the node can have
	 * @param tempLevel The level of the node
	 * @param tempEnabled If the node is enabled
	 */
	public Node(String tempName, String tempDesc, int tempHardCap, int tempLevel, boolean tempEnabled) {
		name = tempName;
		description = tempDesc;
		parentNodes = new ArrayList<Node>();
		totalInNode = 0;
		hardCap = tempHardCap;
		level = tempLevel;
		enabled = tempEnabled;
		autoCollect = false;
	}
	
	/**
	 * Gets the name of the node
	 * @return The name of the node
	 */
	public String getName() { return name; }
	
	/**
	 * Gets the node's description
	 * @return The description of the node
	 */
	public String getDescription() { return description; }
	
	/**
	 * Gets all parent nodes of this node
	 * @return The parent nodes
	 */
	public ArrayList<Node> getParentNodes() { return parentNodes; }
	
	/**
	 * Gets the total amount inside of the node
	 * @return The amount in the node
	 */
	public int amountInNode() { return totalInNode; }
	
	/**
	 * Returns the set hard cap of a node
	 * @return The node's hard cap
	 */
	public int nodeHardCap() { return hardCap; }
	
	/**
	 * Gets the node's level
	 * @return The level of the node
	 */
	public int nodeLevel() { return level; }
	
	/**
	 * Gets the status if the node is able to be seen and used
	 * @return The enabled status of the node
	 */
	public boolean nodeEnabled() { return enabled; }
	
	/**
	 * Checks to see if the game should auto-collect from the node
	 * @return Auto-collect status of the node
	 */
	public boolean checkAutoCollect() { return autoCollect; }
	
	/**
	 * Enables the node to be seen and used
	 */
	public void enableNode() { enabled = true; }
	
	/**
	 * Enables the node's auto collect feature
	 */
	public void enableAutoCollect() { autoCollect = true; }
	
	/**
	 * Should be overridden in every other node object class. Updates the node per click/per update (if the auto-collect is enabled for the node)
	 */
	public void updateNode() {}

	/**
	 * Adds an amount to a node's total
	 * @param amountToAdd The node's total amount
	 */
	public void addAmount(int amountToAdd) {
		totalInNode += amountToAdd;
	}
	
	/**
	 * Adds a node as a parent to this one
	 * @param node The node to add as a parent
	 */
	public void addParentNode(Node node) {
		parentNodes.add(node);
	}
	
	@Override
	public String toString() {
		return name + ": Amount: " + totalInNode;
	}
}
