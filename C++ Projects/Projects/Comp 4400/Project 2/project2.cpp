// Name: Zachary Wellman
// File Name: program2.cpp
// Date: 4 April, 2021
// Description: Driver code to run the ndpa class

#include <iostream>
#include <fstream>
#include <cstring>
#include "npda.h"

using namespace std;

int main(int argc, char** argv) {
	if (argc != 2) {
		cout << "usage: <prog_name> <file_name>"; //Force a file name to be found
		return -1;
	}
	const char * state = "<states>", * alphabet = "<input alphabet>", * transition = "<transitions>", * initState = "<initial state>", * stackStart = "<stack start>",
	* finalStates = "<final states>", * stackAlphabet = "<stack alphabet>";
	npda autom;
	
	string stackTop = "";
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
		while (strcmp(line, stackAlphabet) != 0) {
			readFile.getline(line, 59);
			autom.addSymbol(line);
		}
	}
	
	if (strcmp(line, stackAlphabet)==0) {
		while (strcmp(line, transition) != 0) {
			readFile.getline(line, 59);
			autom.addStackSymbol(line);
		}
	}

	if (strcmp(line, transition)==0) {
		while (readFile.peek() != (int)'<') {
			char startState[4] = {}, symbol[3] = {}, stackTop[3] = {}, endState[4] = {}, stackReplacement[3] = {};
			readFile.getline(startState, 3, ' ');
			readFile.getline(symbol, 2, ' ');
			readFile.getline(stackTop, 2, ' ');
			readFile.getline(endState, 3, ' ');
			readFile.getline(stackReplacement, 3);
			autom.addTransition(startState, symbol, stackTop, endState, stackReplacement);
		}
		readFile.getline(line, 59);
	}

	if (strcmp(line, initState)==0) {
		readFile.getline(line, 59);
		autom.setInitialState(line);
	}

	readFile.getline(line, 59);
	
	if (strcmp(line, stackStart) == 0) {
		readFile.getline(line, 59);
		autom.stackStart(line);
		stackTop = line;
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
		vector<string> stack;
		stack.push_back(stackTop);
		autom.transition(line, stack, true);
		// Below needs to be changed
		/*cout << "[" << autom.getCurrentState() << "]";
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
		cout << " : ";*/
		cout << autom.getCurrentState() << endl;
		if (autom.isFinal() && !invalid) cout << "Accepted" << endl;
		else cout << "Rejected" << endl;
	}
	delete state, alphabet, transition, initState, finalStates, stackAlphabet;
	return 0;
}

