#include "Graph.h"
#include <string>
#include <iostream>
#include <iomanip>
#include <vector>

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
	cout << "Adjency Matrix" << endl;
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
	if (!noEdges) {
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
			if (iInGraph && jInGraph && path(nodeArray[lowesti], nodeArray[lowestj], defaultNode, 0)) {
				// Rejection
			} else {
				minSpanTree.push_back(make_pair(make_pair(nodeArray[lowesti], nodeArray[lowestj]), edgeArray[lowesti][lowestj]));
				if (cycle(nodeArray[lowesti], defaultNode, defaultNode, true) && cycle(nodeArray[lowestj], defaultNode, defaultNode, true)) {
					// rejection
				} else {
					if (!iInGraph) nodesInGraph.push_back(nodeArray[lowesti]);
				if (!jInGraph) nodesInGraph.push_back(nodeArray[lowestj]);
				}
				
			}
			edgeArray[lowesti][lowestj] = 0;
			edgeArray[lowestj][lowesti] = 0;
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
		
		cout << "The total weight of the mininal spaning tree is: " << totalWeight;
	}
}

// Needs to go through the path, not keep switch between two paths
bool Graph::path(node target, node current, node previous, int tries) {
	if (current.name == target.name) {
		return true;
	} else if (tries >= graphSize*2) {
		return false;
	}
	
	for (auto elem : minSpanTree) {
		if (elem.first.first.name == current.name && previous.name != elem.first.second.name) {
			tries++;
			if (path(target, elem.first.second, current, tries)) return true;
		}
		if (elem.first.second.name == current.name && previous.name != elem.first.first.name) {
			tries++;
			if (path(target, elem.first.first, current, tries)) return true;
		}
	}
	
	return false;
}

// Target is the node, current and previous starts as default
bool Graph::cycle(node target, node current, node previous, bool start) {
	if (target.name == current.name) return true;
	
	if (start) {
		int paths = 0;
		for (auto elem : minSpanTree) {
			if (elem.first.first.name == target.name) paths++;
			else if (elem.first.second.name == target.name) paths++;
		}
		if (paths < 2) return false;
		current = target;
	}
	
	for (auto elem : minSpanTree) {
		if (elem.first.first.name == current.name && previous.name != elem.first.second.name) {
			if (cycle(target, elem.first.second, current, false)) return true;
		}
		if (elem.first.second.name == current.name && previous.name != elem.first.first.name) {
			if (cycle(target, elem.first.first, current, false)) return true;
		}
	}
	return false;
}
