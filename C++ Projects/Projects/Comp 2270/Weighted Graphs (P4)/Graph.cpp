#include "Graph.h"
#include <string>
#include <iostream>
#include <iomanip>
#include <vector>
//#include <iterator>

using namespace std;

Graph::Graph(int totalNodes) {
	defaultNode.name = "";
	graphSize = totalNodes;
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
	currentEdges++;
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

		// Probably needs to be a loop
		vector<pair<pair<node, node>,int>> minSpanTree;
		vector<node> nodesInGraph;
		while (minSpanTree.size() != currentEdges-1 && !noEdges) {
			noEdges = true;
			int lowesti = 0, lowestj = 0; // Row and column of the lowest non-0 element
			int lowestWeight = -1; // Weight of the above
			for (int i = 0; i < graphSize; i++) {
				for (int j = 0; j < graphSize; j++) {
					if (edgeArray[i][j] != 0) {
					   if (lowestWeight == -1 || edgeArray[i][j] < lowestWeight) {
						  lowestWeight = edgeArray[i][j];
						  lowesti = i;
						  lowestj = j;
						}
						noEdges = false;
					}
				}
			}
			bool iInGraph = false, jInGraph = false;
			for (node elem : nodesInGraph) {
				if (elem.name == nodeArray[lowesti].name && !iInGraph) {
					iInGraph = true;
				}

				if (elem.name == nodeArray[lowestj].name && !jInGraph) {
					jInGraph = true;
				}
			}
			if (iInGraph && jInGraph) {
				// Rejection
			} else {
				minSpanTree.push_back(make_pair(make_pair(nodeArray[lowesti], nodeArray[lowestj]), edgeArray[lowesti][lowestj]));
				if (!iInGraph) nodesInGraph.push_back(nodeArray[lowesti]);
				if (!jInGraph) nodesInGraph.push_back(nodeArray[lowestj]);
			}
			edgeArray[lowesti][lowestj] = 0;
			edgeArray[lowestj][lowesti] = 0;
			// Run through the adjency graph
			// Find the lowest number (ignore any 0's)
			// Once it is found, check if both are in the nodesInGraph vector. If they both are, then reject the edge.
			// If the above is not true, add the edge to the minSpanTree vector, add the two nodes to the nodesInGraph, and then set it to 0 on both sides
			// After the above is completely finished, then calculate the total weight given the int of the minSpanTree edge vector
		}
		
		// Display the results
		int totalWeight = 0;
		cout << endl;
		for (int i = 0; i < minSpanTree.size(); i++) {
			auto itr = minSpanTree.at(i);
			cout << "{" << itr.first.first.name << "  " << itr.first.second.name << "}     weight =    " << itr.second;
			totalWeight += itr.second;
			cout << endl;
		}
		
		cout << "Total weight: " << totalWeight;
	}
}
