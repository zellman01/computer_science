#include <iostream>
#include <fstream>
#include "Automata.h"

using namespace std;

/* run this program using the console pauser or add your own getch, system("pause") or input loop */

int main(int argc, char** argv) {
	if (argc != 2) {
		cout << "usage: <prog_name> <file_name>" << endl;
		return -1;
	}
	const string state = "<states>", alphabet = "<alphabet>", transition = "<transitions>", initState = "<initial state>", finalStates "<final states>";
	fstream file(argv[1]); // Should pull the file out correctly
	return 0;
}
