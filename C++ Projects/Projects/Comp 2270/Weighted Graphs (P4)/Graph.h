#ifndef GRAPH_H
#define GRAPH_H

#include <string>
#include <vector>

/*struct edge {
	std::string pointa, pointb; // pointa is one node in the connection. pointb is the second node in the connection
	int weight;
};*/ // Using a 2-d array of ints for edges

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
