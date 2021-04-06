#include "Graph.h"
#include <string>
#include <iostream>
#include <iomanip>
#include <vector>

using namespace std;

Graph::Graph(int totalNodes) {
	defaultNode.name = "";
	graphSize = totalNodes;
	totalEdges = graphSize*graphSize;
	currentNodes = 0;
	currentEdges = 0;
	nodeArray = new node[totalNodes];
	edgeArray = new int*[graphSize];
	for (int i = 0; i < graphSize; i++) {
		nodeArray[i] = defaultNode; // Initialize the values
		edgeArray[i] = new int[graphSize];
		for (int j = 0; j < graphSize; j++) {
			edgeArray[i][j] = 0; // Initalize all weights to have no edges
		}
	}
}

int Graph::searchNode(string nodeName) {
	for (int i = 0; i < currentNodes; i++) {
		if (nodeArray[i].name == nodeName) return i;
	}
	return -1;
}

void Graph::print() {
	for (int i = 0; i < currentNodes; i++) {
		cout << setw(5) << nodeArray[i].name;
	}
	cout << endl;
	for (int i = 0; i < currentNodes; i++) {
		cout << nodeArray[i].name << setw(4);
		for (int j = 0; j < currentNodes; j++) {
			cout << edgeArray[i][j] << setw(5);
		}
		cout << "\n";
	}
}

void Graph::addNode(node newNode) {
	nodeArray[currentNodes] = newNode;
	currentNodes++;
}

void Graph::addEdge(string pointa, string pointb, int weight) {
	int pointALocation = searchNode(pointa);
	int pointBLocation = searchNode(pointb);
	edgeArray[pointALocation][pointBLocation] = weight;
	edgeArray[pointBLocation][pointALocation] = weight;
}

void Graph::mst() { // Should be ran after displaying the full adjency matrix
	bool noEdges = true;
	for (int i = 0; i < graphSize; i++) {
		for (int j = 0; j < graphSize; j++) {
			if (edgeArray[i][j] != 0  && noEdges) noEdges = false;
		}
	}
	if (!noEdges) { // Will satisfy very first condition, given the above loop (no edges in the graph)
		// Search for smallest edge in the adjency graph (starting from top left cornor)
		// put it in a vector of edges, and put the nodes in a vector in the order of the edge to get it to display later (set it to 0 on both sides so it will not be picked again)
		// Check if it is completed
		// If not, do it again. If it is, display the edges and weights of the MST, and then calculate the total weight
		
		// (Cannot go back to a node to make a loop)
		
		// (Vector setup: Pair (Pair (nodes that are in the edge), Weight)
		vector<pair<pair<node, node>,int>> minSpanTree;
		
	}
}
