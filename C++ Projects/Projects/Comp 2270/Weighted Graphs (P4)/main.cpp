#include <iostream>
#include <fstream>
#include <string>
#include "Graph.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char** argv) {
	/*int graphSize = 0;
	cout << "Please insert the name of the file to use for the graph: ";
	string input;
	cin >> input;
	fstream file(input);
	while (getline(file, input)) {
		if (file.peek() )
	}*/
	node a, b, c, d, e, f;
	a.name = "A";
	b.name = "B";
	c.name = "C";
	d.name = "D";
	e.name = "E";
	f.name = "F";
	Graph g(6);
	g.addNode(a);
	g.addNode(b);
	g.addNode(c);
	g.addNode(d);
	g.addNode(e);
	g.addNode(f);
	g.addEdge("B","A", 5);
	g.addEdge("A","C", 10);
	g.addEdge("A", "D", 9);
	g.addEdge("B", "D", 4);
	g.addEdge("C", "D", 13);
	g.addEdge("C", "E", 14);
	g.addEdge("D", "E", 7);
	g.addEdge("D", "F", 8);
	g.addEdge("E", "F", 2);
	g.print();
	g.mst();
	cout << endl;
	g.print();
	return 0;
}
