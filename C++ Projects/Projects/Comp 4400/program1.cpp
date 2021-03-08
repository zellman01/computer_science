// Name: Zachary Wellman
// File Name: program1.cpp
// Date: 24 February, 2021
// Description: Driver code to run the Automata class

#include <iostream>
#include <fstream>
#include <cstring>
#include "Automata.h"

using namespace std;

int main(int argc, char** argv) {
	if (argc != 2) {
		cout << "usage: <prog_name> <file_name>"; //Force a file name to be found
		return -1;
	}
	const char * state = "<states>", * alphabet = "<alphabet>", * transition = "<transitions>", * initState = "<initial state>", *
finalStates = "<final states>";
	Automata autom;

	ifstream readFile(argv[1]);
	if (!readFile) {
		cout << "Automata file could not be opened!"; // If the file could not be read
		return -1;
	}

	char line[60] = {};
	readFile.getline(line, 59);
	if (strcmp(line, state) == 0) {
		while (strcmp(line, alphabet) != 0) {
			readFile.getline(line, 59);
			autom.addState(line);
		}
	}

	if (strcmp(line, alphabet)==0) {
		while (strcmp(line, transition) != 0) {
			readFile.getline(line, 59);
			autom.addSymbol(line);
		}
	}

	if (strcmp(line, transition)==0) {
		while (readFile.peek() != (int)'<') {
			char startState[4] = {}, symbol[3] = {}, endState[4] = {};
			readFile.getline(startState, 3, ' ');
			readFile.getline(symbol, 2, ' ');
			readFile.getline(endState, 3);
			autom.addTransition(startState, symbol, endState);
		}
		readFile.getline(line, 59);
	}

	if (strcmp(line, initState)==0) {
		readFile.getline(line, 59);
		autom.setInitialState(line);
	}

	readFile.getline(line, 59);

	if (strcmp(line, finalStates)==0) {
		while (readFile.peek() != EOF) {
			readFile.getline(line, 59);
			autom.addFinalState(line);
		}
	}

	readFile.close();
	autom.description();

	while (true) { // loop until control c is pressed
		bool invalid = false;
		autom.reset();
		cout << endl << "Enter a string to process (Ctrl^C to end): ";
		cin.getline(line, 59);
		cout << endl;
		cout << "[" << autom.getCurrentState() << "]";
		for (int i = 0; i < strlen(line); i++) {
			string str(1, line[i]);
			if (autom.changeStates(str)) {
				cout << "-" << str << "->[" << autom.getCurrentState() << "]";
			} else {
				cout << " (Invalid symbol " << str << ")";
				invalid = true;
				break;
			}
		}
		cout << " : ";
		if (autom.isFinal() && !invalid) cout << "Accepted" << endl;
		else cout << "Rejected" << endl;
	}
	return 0;
}
