// Name: Zachary Wellman
// File Name: Automata.cpp
// Date: 24 February, 2021
// Description: Functions for the Automata class objects

#include "Automata.h"
#include <iterator>
#include <iostream>

using namespace std;

Automata::Automata() {
	currentState = "";
	initialState = "";
}

void Automata::reset() {
	currentState = initialState;
}

void Automata::addState(string state) {
	if (state.at(0) == '<') return;
	states.insert(states.end(), state);
}

void Automata::addSymbol(string symbol) {
	if (symbol.at(0) == '<') return;
	inputSymbols.insert(inputSymbols.end(), symbol);
}

void Automata::addTransition(string state, string symbol, string endState) {
	if (state.at(0) == '<') return;
	transitions.insert(make_pair(make_pair(state, symbol), endState));
}

void Automata::setInitialState(string initState) {
	currentState = initState;
	initialState = initState;
}

void Automata::addFinalState(string finalState) {
	finalStates.insert(finalStates.end(), finalState);
}

void Automata::description() {
	cout << "----------D F A----------" << endl;
	cout << "<states>" << endl;
	for (int i = 0; i < states.size(); i++) cout << states.at(i) << " ";
	cout << endl;
	cout << "<alphabet>" << endl;
	for (int i = 0; i < inputSymbols.size(); i++) cout << inputSymbols.at(i) << " ";
	cout << endl;
	cout << "<transitions>" << endl;
	for (auto i : transitions) cout << "(" << i.first.first << "," << i.first.second << ")->" << i.second << endl;
	cout << "<initial state>" << endl;
	cout << initialState << endl;
	cout << "<final states>" << endl;
	for (int i = 0; i < finalStates.size(); i++) cout << finalStates.at(i) << " ";
	cout << endl;
	cout << "-------------------------" << endl;
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
		string autoState = currentState;
		map<pair<string, string>, string>::iterator itr;
		itr = transitions.find(make_pair(autoState, symbol));
		if (itr != transitions.end()) {
			currentState = itr->second;
		}
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
