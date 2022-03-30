#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "Graph.h"

using namespace std;

int main(int argc, char** argv) {
	vector<string> nodeNames;
	vector<node> nodeList;
	cout << "Please insert the name of the file to use for the graph: ";
	string inputFile;
	cin >> inputFile;
	fstream file(inputFile);
	if (!file) {
		cout << "File not found!";
		return -1;
	}
	string inputHandler = "";
	while (file >> inputHandler && !isdigit(inputHandler.at(0))) {
		nodeNames.insert(nodeNames.end(), inputHandler);
	}
	for (int i = 0; i < nodeNames.size(); i++) {
		node a;
		a.name = nodeNames.at(i);
		nodeList.insert(nodeList.end(), a);
	}
	Graph g(nodeList.size());
	for (int i = 0; i < nodeList.size(); i++) {
		g.addNode(nodeList.at(i));
	}
	int row = 0, col = 1, number = atoi(inputHandler.c_str());
	inputHandler = "";
	g.addEdge(g.searchNode(row).name, g.searchNode(col).name, number);
	col++;
	while (file >> number) {
		string rowNodeName = g.searchNode(row).name, colNodeName = g.searchNode(col).name;
		col++;
		if (file.peek() == '\n') {
			row++;
			col = row+1;
		}
		g.addEdge(rowNodeName, colNodeName, number);
	}
	g.print();
	g.mst();
	return 0;
}
