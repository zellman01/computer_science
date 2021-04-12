// Name: Zachary Wellman
// File Name: program2.cpp
// Date: 4 April, 2021
// Description: Class code for the NODA

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

bool npda::transition(string symbol, vector<string> stack, bool first) { // What I think is happening: It is going into the lambda transition into a final state
	// with just a in a^nb^n because it is checking strictly for if a lambda exists for the transition and taking it, when it should be ignoring it sense
	// it should not be accepted
	if (first) {
		// insert into the outputString
		//string output = "(" + currentState + "," + symbol + "," + stack.at(0) + ")";
		//cout << output << endl;
		//outputString.push_back(output);
	}
	string newSymbol = "", sym = "", stackTop = "", prevState = currentState;
	if (symbol.length() == 0) {
		if (isFinal()) {
			//string output1 = "|- (" + currentState + ",*," + stackContent(stack) + ")";
			//cout << output1 << endl;
			return true; // insert into the outputString
		}
		
		newSymbol = "*";
		sym = "*";
	} else {
		newSymbol = symbol.substr(1);
		sym = symbol.substr(0,1);
	}
	
	if (stack.empty()) {
		stackTop = "";
	} else {
		stackTop = stack.at(0);
		stack.erase(stack.begin());
	}
	
	auto itr1 = transitions.find(make_tuple(currentState, sym, stackTop));
	auto itr2 = transitions.find(make_tuple(currentState, "*", stackTop));
	if (itr1 == transitions.end() && itr2 == transitions.end()) {
		return false; // No transition exists
	}
	
	if (itr1 != transitions.end() && sym != "*") {
		currentState = itr1->second.first;
		for (int i = itr1->second.second.length()-1; i > -1; i--) {
			string a = itr1->second.second.substr(i,i+1);
			stack.insert(stack.begin(), a);
		}
		if (transition(newSymbol, stack, false)) return true;
	} if (itr2 != transitions.end()) {
		currentState = itr2->second.first;
		for (int i = itr2->second.second.length()-1; i > -1; i--) {
			string a = itr2->second.second.substr(i,i+1);
			stack.insert(stack.begin(), a);
		}
		if (transition(symbol, stack, false)) return true;
	}
	/*auto itr1 = transitions.lower_bound(make_tuple(currentState, sym, stackTop));
	auto itr2 = transitions.upper_bound(make_tuple(currentState, sym, stackTop));
	auto itr3 = transitions.lower_bound(make_tuple(currentState, "*", stackTop));
	auto itr4 = transitions.upper_bound(make_tuple(currentState, "*", stackTop));
	while (itr1 != itr2) {
		// Not accounting for lambda transitions
		currentState = itr1->second.first;
		if (itr1->second.second != "*") {
			for (int i = itr1->second.second.length()-1; i > -1; i--) {
				string a = itr1->second.second.substr(i,i+1);
				stack.insert(stack.begin(), a);
			}
		}
		if (transition(newSymbol, stack, false)) {
			return true; // Insert into the outputString
		}
		itr1++;
	}
	// If a lambda transition exists on the state if the above runs, and the lambda goes into a final state, the string is accepted with the below code
	while (itr3 != itr4) {
		currentState = itr3->second.first;
		if (itr3->second.second != "*") {
			for (int i = itr3->second.second.length()-1; i > -1; i--) {
				string a = itr3->second.second.substr(i,i+1);
				stack.insert(stack.begin(), a);
			}
		}
		if (transition(symbol, stack, false)) {
			return true; // Insert into the outputString
		}
		itr3++;
	}*/
	return false;
}

bool npda::isFinal() {
	string state = getCurrentState();
	for (int i = 0; i < finalStates.size(); i++) {
		if (state == finalStates.at(i)) return true;
	}
	return false;
}
