#include "npda.h"
#include <iterator>
#include <iostream>
#include <cstring>

using namespace std;

npda::npda() {
	currentState = "";
	initialState = "";
}

void npda::reset() {
	currentState = initialState;
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
	startingStack = symbol;
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

bool npda::validSymbol(string symbol) {
	for (int i = 0; i < inputSymbols.size(); i++) {
		if (inputSymbols.at(i) == symbol) return true;
	}
	return false;
}

bool npda::transition(string symbol, vector<string> stack, bool first) {
	string newSymbol = "", sym = "", stackTop = "", prevState = currentState;
	if (first) {
		// insert into the outputString
		//string output = "(" + currentState + "," + symbol + "," + stack.at(0) + ")";
		//cout << output << endl;
		//outputString.push_back(output);
		debug(prevState, symbol, stack);
	}
	if (symbol.length() == 0) {
		if (isFinal()) {
			debug(prevState, symbol, stack);
			//string output1 = "|- (" + currentState + ",*," + stackContent(stack) + ")";
			//cout << output1 << endl;
			return true; // insert into the outputString
		}
	} else {
		newSymbol = symbol.substr(1);
		sym = symbol.substr(0,1);
	}
	
	if (stack.empty()) {
		return false;
	} else {
		stackTop = stack.at(0);
		stack.erase(stack.begin());
	}
	
	auto exist = transitions.find(make_tuple(currentState, sym, stackTop)); // To check if the transition exists
	auto exist2 = transitions.find(make_tuple(currentState, "*", stackTop)); // To check if a lambda transition exists with the give state and stack top
	if (exist == transitions.end() && exist2 == transitions.end()) {
		return false; // If the transition does not even exist
	}
	if (exist != transitions.end()) {
		// Not accounting for lambda transitions
		currentState = exist->second.first;
		if (exist->second.second != "*") {
			for (int i = exist->second.second.length()-1; i > -1; i--) {
				string a = exist->second.second.substr(i,i+1);
				stack.insert(stack.begin(), a);
			}
		}
		if (transition(newSymbol, stack, false)) {
			debug(prevState, newSymbol, stack);
			return true; // Insert into the outputString
		}
	}
	// If a lambda transition exists on the state if the above runs, and the lambda goes into a final state, the string is accepted with the below code
	if (exist2 != transitions.end()) {
		currentState = exist2->second.first;
		if (exist2->second.second != "*") {
			for (int i = exist2->second.second.length()-1; i > -1; i--) {
				string a = exist2->second.second.substr(i,i+1);
				stack.insert(stack.begin(), a);
			}
		}
		if (transition(symbol, stack, false)) {
			debug(prevState, symbol, stack);
			return true; // Insert into the outputString
		}
	}
	return false;
}

void npda::debug(string state, string symbols, vector<string> stack) {
	cout << "(" << state << "," << symbols << ",";
	for (int i = 0; i < stack.size(); i++) {
		cout << stack.at(i);
	}
	cout << ")" << endl;
}

bool npda::isFinal() {
	string state = getCurrentState();
	for (int i = 0; i < finalStates.size(); i++) {
		if (state == finalStates.at(i)) return true;
	}
	return false;
}
