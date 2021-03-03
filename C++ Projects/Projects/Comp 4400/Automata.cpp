// Name: Zachary Wellman
// File Name: Automata.cpp
// Date: 24 February, 2021
// Description: Functions for the Automata class objects

#include "Automata.h"
#include <iterator>

using namespace std;

Automata::Automata() {
	currentState = "";
}

void Automata::addState(string state) {
	states.insert(states.end(), state);
}

void Automata::addSymbol(string symbol) {
	inputSymbols.insert(inputSymbols.end(), symbol);
}

void Automata::addTransition(string symbolState, string endState) {
	transitions.insert(pair<string, string>(symbolState, endState));
}

void Automata::setInitialState(string initState) {
	currentState = initState;
}

void Automata::addFinalState(string finalState) {
	finalStates.insert(finalStates.end(), finalState);
}

void Automata::description() {
	// Look at documentation for this
}

bool Automata::validSymbol(string symbol) {
	for (int i = 0; i < inputSymbols.size(); i++) {
		if (symbol == inputSymbols.at(i)) return true;
	}
	return false;
}

string Automata::getCurrentState() {
  return currentState;
}

bool Automata::changeStates(string symbol) { // symbol is one symbol being brought in
	if (validSymbol(symbol)) {
		//cout << "thing";
	} else return false; // Ends in an error
	// Transition stuff
}

bool Automata::isFinal() {
	string state = getCurrentState();
	for (int i = 0; i < finalStates.size(); i++) {
		if (state == finalStates.at(i)) return true;
	}
	return false;
}
