#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include "Graph.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

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
		file.ignore();
	}
	// Create the nodes for the graph
	for (int i = 0; i < nodeNames.size(); i++) {
		node a;
		a.name = nodeNames.at(i);
		nodeList.insert(nodeList.end(), a);
	}
	Graph g(nodeList.size());
	// Insert the nodes into the graph
	for (int i = 0; i < nodeList.size(); i++) {
		g.addNode(nodeList.at(i));
	}
	g.print();
	inputHandler = "";
	int row = 0;
	while (getline(file, inputHandler, '\n')) {
		
	}
	return 3;
}
