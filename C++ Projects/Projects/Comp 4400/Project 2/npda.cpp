#include "npda.h"
#include <iterator>
#include <iostream>

using namespace std;

npda::npda() {
	currentState = "";
	initialState = "";
}

void npda::reset() {
	currentState = initialState;
	stack.clear();
	stackPush(startingStack);
}

void npda::addState(string state) {
	if (state.at(0) == '<') return;
	states.insert(states.end(), state);
}

void npda::addSymbol(string symbol) {
	if (symbol.at(0) == '<') return;
	inputSymbols.insert(inputSymbols.end(), symbol);
}

void npda::addStackSymbol(string symbol) {
	if (symbol.at(0) == '<') return;
	stackSymbols.insert(stackSymbols.end(), symbol);
}

void npda::addTransition(string state, string symbol, string stackSymbol, string endState, string stackReplacement) {
	if (state.at(0) == '<') return;
	transitions.insert(make_pair(make_tuple(state, symbol, stackSymbol), make_pair(endState, stackReplacement)));
}

void npda::setInitialState(string initState) {
	currentState = initState;
	initialState = initState;
}

void npda::addFinalState(string finalState) {
	finalStates.insert(finalStates.end(), finalState);
}

void npda::stackStart(string symbol) {
	stack.insert(stack.begin(), symbol);
	startingStack = symbol;
}

void npda::stackPush(string symbol) {
	stack.insert(stack.begin(), symbol);
}

string npda::stackPop() {
	string stackTop = stack.at(0);
	stack.erase(stack.begin());
	return stackTop;
}

void npda::description() {
	cout << "---------N P D A---------" << endl;
	cout << "<states>" << endl;
	for (int i = 0; i < states.size(); i++) cout << states.at(i) << " ";
	cout << endl;
	cout << "<alphabet>" << endl;
	for (int i = 0; i < inputSymbols.size(); i++) cout << inputSymbols.at(i) << " ";
	cout << endl;
	cout << "<stack alphabet>" << endl;
	for (int i = 0; i < stackSymbols.size(); i++) cout << stackSymbols.at(i) << " ";
	cout << endl;
	cout << "<transitions>" << endl;
	for (auto i : transitions) cout << "(" << get<0>(i.first) << "," << get<1>(i.first) << "," << get<2>(i.first) << ")->(" << i.second.first << "," << i.second.second << ")" << endl;
	cout << "<initial state>" << endl;
	cout << initialState << endl;
	cout << "<stack start>" << endl;
	cout << startingStack << endl;
	cout << "<final states>" << endl;
	for (int i = 0; i < finalStates.size(); i++) cout << finalStates.at(i) << " ";
	cout << endl;
	cout << "-------------------------" << endl;
}

bool npda::validSymbol(string syymbol) {
	for (int i = 0; i < inputSymbols.size(); i++) {
		if (inputSymbols.at(i) == symbol) return true;
	}
	return false;
}

string npda::getCurrentState() { // probably needs to change
	return currentState;
}

bool npda::changeStates(string symbol) { // Recursive
	if (validSymbol(symbol)) {
		string autoState = currentState;
		map<pair<string, string>, string>::iterator itr;
		itr = transitions.find(make_pair(autoState, symbol));
		if (itr != transitions.end()) {
			currentState = itr->second;
		}
	} else return false;
}

bool npda::isFinal() {
	string state = getCurrentState();
	for (int i = 0; i < finalStates.size(); i++) {
		if (State == finalStates.at(i)) return true;
	}
	return false;
}
