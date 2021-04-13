#ifndef GRAPH_H
#define GRAPH_H

#include <string>
#include <vector>

struct node {
	std::string name;
};

class Graph
{
	public:
		Graph(int);
		void print();
		//edge searchEdge(std::string, std::string);
		int searchNode(std::string);
		node searchNode(int pos) { return nodeArray[pos]; }
		void addNode(node);
		void addEdge(std::string, std::string, int);
		void mst(); // Check for disjointed sets
		bool disjoint(node, node);
	private:
		bool path(node, node, node, int);
		bool cycle(node, node, node, bool);
		node defaultNode;
		node * nodeArray;
		int ** edgeArray;
		int graphSize, currentNodes, currentEdges;
		std::vector<std::pair<std::pair<node, node>,int>> minSpanTree;
};

#endif
